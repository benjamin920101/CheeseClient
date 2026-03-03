/*
 * Decompiled with CFR 0.152.
 */
package okhttp3.internal.cache;

import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Flushable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.NoSuchElementException;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import okhttp3.internal.Util;
import okhttp3.internal.cache.FaultHidingSink;
import okhttp3.internal.io.FileSystem;
import okhttp3.internal.platform.Platform;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;
import okio.Source;

public final class DiskLruCache
implements Closeable,
Flushable {
    static final String JOURNAL_FILE = "journal";
    static final String JOURNAL_FILE_TEMP = "journal.tmp";
    static final String JOURNAL_FILE_BACKUP = "journal.bkp";
    static final String MAGIC = "libcore.io.DiskLruCache";
    static final String VERSION_1 = "1";
    static final long ANY_SEQUENCE_NUMBER = -1L;
    static final Pattern LEGAL_KEY_PATTERN = Pattern.compile("[a-z0-9_-]{1,120}");
    private static final String CLEAN = "CLEAN";
    private static final String DIRTY = "DIRTY";
    private static final String REMOVE = "REMOVE";
    private static final String READ = "READ";
    final FileSystem fileSystem;
    final File directory;
    private final File journalFile;
    private final File journalFileTmp;
    private final File journalFileBackup;
    private final int appVersion;
    private long maxSize;
    final int valueCount;
    private long size = 0L;
    BufferedSink journalWriter;
    final LinkedHashMap<String, Entry> lruEntries = new LinkedHashMap(0, 0.75f, true);
    int redundantOpCount;
    boolean hasJournalErrors;
    boolean initialized;
    boolean closed;
    boolean mostRecentTrimFailed;
    boolean mostRecentRebuildFailed;
    private long nextSequenceNumber = 0L;
    private final Executor executor;
    private final Runnable cleanupRunnable = new Runnable(){

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void run() {
            DiskLruCache diskLruCache = DiskLruCache.this;
            synchronized (diskLruCache) {
                if (!DiskLruCache.this.initialized | DiskLruCache.this.closed) {
                    return;
                }
                try {
                    DiskLruCache.this.trimToSize();
                }
                catch (IOException ignored) {
                    DiskLruCache.this.mostRecentTrimFailed = true;
                }
                try {
                    if (DiskLruCache.this.journalRebuildRequired()) {
                        DiskLruCache.this.rebuildJournal();
                        DiskLruCache.this.redundantOpCount = 0;
                    }
                }
                catch (IOException e2) {
                    DiskLruCache.this.mostRecentRebuildFailed = true;
                    DiskLruCache.this.journalWriter = Okio.buffer(Okio.blackhole());
                }
            }
        }
    };

    DiskLruCache(FileSystem fileSystem, File directory, int appVersion, int valueCount, long maxSize, Executor executor) {
        this.fileSystem = fileSystem;
        this.directory = directory;
        this.appVersion = appVersion;
        this.journalFile = new File(directory, JOURNAL_FILE);
        this.journalFileTmp = new File(directory, JOURNAL_FILE_TEMP);
        this.journalFileBackup = new File(directory, JOURNAL_FILE_BACKUP);
        this.valueCount = valueCount;
        this.maxSize = maxSize;
        this.executor = executor;
    }

    public synchronized void initialize() throws IOException {
        assert (Thread.holdsLock(this));
        if (this.initialized) {
            return;
        }
        if (this.fileSystem.exists(this.journalFileBackup)) {
            if (this.fileSystem.exists(this.journalFile)) {
                this.fileSystem.delete(this.journalFileBackup);
            } else {
                this.fileSystem.rename(this.journalFileBackup, this.journalFile);
            }
        }
        if (this.fileSystem.exists(this.journalFile)) {
            try {
                this.readJournal();
                this.processJournal();
                this.initialized = true;
                return;
            }
            catch (IOException journalIsCorrupt) {
                Platform.get().log(5, "DiskLruCache " + this.directory + " is corrupt: " + journalIsCorrupt.getMessage() + ", removing", journalIsCorrupt);
                try {
                    this.delete();
                }
                finally {
                    this.closed = false;
                }
            }
        }
        this.rebuildJournal();
        this.initialized = true;
    }

    public static DiskLruCache create(FileSystem fileSystem, File directory, int appVersion, int valueCount, long maxSize) {
        if (maxSize <= 0L) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        if (valueCount <= 0) {
            throw new IllegalArgumentException("valueCount <= 0");
        }
        ThreadPoolExecutor executor = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), Util.threadFactory("OkHttp DiskLruCache", true));
        return new DiskLruCache(fileSystem, directory, appVersion, valueCount, maxSize, executor);
    }

    private void readJournal() throws IOException {
        BufferedSource source = Okio.buffer(this.fileSystem.source(this.journalFile));
        Throwable throwable = null;
        try {
            String magic = source.readUtf8LineStrict();
            String version = source.readUtf8LineStrict();
            String appVersionString = source.readUtf8LineStrict();
            String valueCountString = source.readUtf8LineStrict();
            String blank = source.readUtf8LineStrict();
            if (!(MAGIC.equals(magic) && VERSION_1.equals(version) && Integer.toString(this.appVersion).equals(appVersionString) && Integer.toString(this.valueCount).equals(valueCountString) && "".equals(blank))) {
                throw new IOException("unexpected journal header: [" + magic + ", " + version + ", " + valueCountString + ", " + blank + "]");
            }
            int lineCount = 0;
            try {
                while (true) {
                    this.readJournalLine(source.readUtf8LineStrict());
                    ++lineCount;
                }
            }
            catch (EOFException endOfJournal) {
                this.redundantOpCount = lineCount - this.lruEntries.size();
                if (!source.exhausted()) {
                    this.rebuildJournal();
                } else {
                    this.journalWriter = this.newJournalWriter();
                }
                if (source != null) {
                    DiskLruCache.$closeResource(throwable, source);
                }
            }
        }
        catch (Throwable throwable2) {
            try {
                throwable = throwable2;
                throw throwable2;
            }
            catch (Throwable throwable3) {
                if (source != null) {
                    DiskLruCache.$closeResource(throwable, source);
                }
                throw throwable3;
            }
        }
    }

    private BufferedSink newJournalWriter() throws FileNotFoundException {
        Sink fileSink = this.fileSystem.appendingSink(this.journalFile);
        FaultHidingSink faultHidingSink = new FaultHidingSink(fileSink){

            @Override
            protected void onException(IOException e2) {
                assert (Thread.holdsLock(DiskLruCache.this));
                DiskLruCache.this.hasJournalErrors = true;
            }
        };
        return Okio.buffer(faultHidingSink);
    }

    private void readJournalLine(String line) throws IOException {
        Entry entry;
        String key;
        int firstSpace = line.indexOf(32);
        if (firstSpace == -1) {
            throw new IOException("unexpected journal line: " + line);
        }
        int keyBegin = firstSpace + 1;
        int secondSpace = line.indexOf(32, keyBegin);
        if (secondSpace == -1) {
            key = line.substring(keyBegin);
            if (firstSpace == REMOVE.length() && line.startsWith(REMOVE)) {
                this.lruEntries.remove(key);
                return;
            }
        } else {
            key = line.substring(keyBegin, secondSpace);
        }
        if ((entry = this.lruEntries.get(key)) == null) {
            entry = new Entry(key);
            this.lruEntries.put(key, entry);
        }
        if (secondSpace != -1 && firstSpace == CLEAN.length() && line.startsWith(CLEAN)) {
            String[] parts = line.substring(secondSpace + 1).split(" ");
            entry.readable = true;
            entry.currentEditor = null;
            entry.setLengths(parts);
        } else if (secondSpace == -1 && firstSpace == DIRTY.length() && line.startsWith(DIRTY)) {
            entry.currentEditor = new Editor(entry);
        } else if (secondSpace != -1 || firstSpace != READ.length() || !line.startsWith(READ)) {
            throw new IOException("unexpected journal line: " + line);
        }
    }

    private void processJournal() throws IOException {
        this.fileSystem.delete(this.journalFileTmp);
        Iterator<Entry> i2 = this.lruEntries.values().iterator();
        while (i2.hasNext()) {
            int t2;
            Entry entry = i2.next();
            if (entry.currentEditor == null) {
                for (t2 = 0; t2 < this.valueCount; ++t2) {
                    this.size += entry.lengths[t2];
                }
                continue;
            }
            entry.currentEditor = null;
            for (t2 = 0; t2 < this.valueCount; ++t2) {
                this.fileSystem.delete(entry.cleanFiles[t2]);
                this.fileSystem.delete(entry.dirtyFiles[t2]);
            }
            i2.remove();
        }
    }

    synchronized void rebuildJournal() throws IOException {
        if (this.journalWriter != null) {
            this.journalWriter.close();
        }
        try (BufferedSink writer = Okio.buffer(this.fileSystem.sink(this.journalFileTmp));){
            writer.writeUtf8(MAGIC).writeByte(10);
            writer.writeUtf8(VERSION_1).writeByte(10);
            writer.writeDecimalLong(this.appVersion).writeByte(10);
            writer.writeDecimalLong(this.valueCount).writeByte(10);
            writer.writeByte(10);
            for (Entry entry : this.lruEntries.values()) {
                if (entry.currentEditor != null) {
                    writer.writeUtf8(DIRTY).writeByte(32);
                    writer.writeUtf8(entry.key);
                    writer.writeByte(10);
                    continue;
                }
                writer.writeUtf8(CLEAN).writeByte(32);
                writer.writeUtf8(entry.key);
                entry.writeLengths(writer);
                writer.writeByte(10);
            }
        }
        if (this.fileSystem.exists(this.journalFile)) {
            this.fileSystem.rename(this.journalFile, this.journalFileBackup);
        }
        this.fileSystem.rename(this.journalFileTmp, this.journalFile);
        this.fileSystem.delete(this.journalFileBackup);
        this.journalWriter = this.newJournalWriter();
        this.hasJournalErrors = false;
        this.mostRecentRebuildFailed = false;
    }

    public synchronized Snapshot get(String key) throws IOException {
        this.initialize();
        this.checkNotClosed();
        this.validateKey(key);
        Entry entry = this.lruEntries.get(key);
        if (entry == null || !entry.readable) {
            return null;
        }
        Snapshot snapshot = entry.snapshot();
        if (snapshot == null) {
            return null;
        }
        ++this.redundantOpCount;
        this.journalWriter.writeUtf8(READ).writeByte(32).writeUtf8(key).writeByte(10);
        if (this.journalRebuildRequired()) {
            this.executor.execute(this.cleanupRunnable);
        }
        return snapshot;
    }

    @Nullable
    public Editor edit(String key) throws IOException {
        return this.edit(key, -1L);
    }

    synchronized Editor edit(String key, long expectedSequenceNumber) throws IOException {
        Editor editor;
        this.initialize();
        this.checkNotClosed();
        this.validateKey(key);
        Entry entry = this.lruEntries.get(key);
        if (expectedSequenceNumber != -1L && (entry == null || entry.sequenceNumber != expectedSequenceNumber)) {
            return null;
        }
        if (entry != null && entry.currentEditor != null) {
            return null;
        }
        if (this.mostRecentTrimFailed || this.mostRecentRebuildFailed) {
            this.executor.execute(this.cleanupRunnable);
            return null;
        }
        this.journalWriter.writeUtf8(DIRTY).writeByte(32).writeUtf8(key).writeByte(10);
        this.journalWriter.flush();
        if (this.hasJournalErrors) {
            return null;
        }
        if (entry == null) {
            entry = new Entry(key);
            this.lruEntries.put(key, entry);
        }
        entry.currentEditor = editor = new Editor(entry);
        return editor;
    }

    public File getDirectory() {
        return this.directory;
    }

    public synchronized long getMaxSize() {
        return this.maxSize;
    }

    public synchronized void setMaxSize(long maxSize) {
        this.maxSize = maxSize;
        if (this.initialized) {
            this.executor.execute(this.cleanupRunnable);
        }
    }

    public synchronized long size() throws IOException {
        this.initialize();
        return this.size;
    }

    synchronized void completeEdit(Editor editor, boolean success) throws IOException {
        int i2;
        Entry entry = editor.entry;
        if (entry.currentEditor != editor) {
            throw new IllegalStateException();
        }
        if (success && !entry.readable) {
            for (i2 = 0; i2 < this.valueCount; ++i2) {
                if (!editor.written[i2]) {
                    editor.abort();
                    throw new IllegalStateException("Newly created entry didn't create value for index " + i2);
                }
                if (this.fileSystem.exists(entry.dirtyFiles[i2])) continue;
                editor.abort();
                return;
            }
        }
        for (i2 = 0; i2 < this.valueCount; ++i2) {
            File dirty = entry.dirtyFiles[i2];
            if (success) {
                long newLength;
                if (!this.fileSystem.exists(dirty)) continue;
                File clean = entry.cleanFiles[i2];
                this.fileSystem.rename(dirty, clean);
                long oldLength = entry.lengths[i2];
                entry.lengths[i2] = newLength = this.fileSystem.size(clean);
                this.size = this.size - oldLength + newLength;
                continue;
            }
            this.fileSystem.delete(dirty);
        }
        ++this.redundantOpCount;
        entry.currentEditor = null;
        if (entry.readable | success) {
            entry.readable = true;
            this.journalWriter.writeUtf8(CLEAN).writeByte(32);
            this.journalWriter.writeUtf8(entry.key);
            entry.writeLengths(this.journalWriter);
            this.journalWriter.writeByte(10);
            if (success) {
                entry.sequenceNumber = this.nextSequenceNumber++;
            }
        } else {
            this.lruEntries.remove(entry.key);
            this.journalWriter.writeUtf8(REMOVE).writeByte(32);
            this.journalWriter.writeUtf8(entry.key);
            this.journalWriter.writeByte(10);
        }
        this.journalWriter.flush();
        if (this.size > this.maxSize || this.journalRebuildRequired()) {
            this.executor.execute(this.cleanupRunnable);
        }
    }

    boolean journalRebuildRequired() {
        int redundantOpCompactThreshold = 2000;
        return this.redundantOpCount >= 2000 && this.redundantOpCount >= this.lruEntries.size();
    }

    public synchronized boolean remove(String key) throws IOException {
        this.initialize();
        this.checkNotClosed();
        this.validateKey(key);
        Entry entry = this.lruEntries.get(key);
        if (entry == null) {
            return false;
        }
        boolean removed = this.removeEntry(entry);
        if (removed && this.size <= this.maxSize) {
            this.mostRecentTrimFailed = false;
        }
        return removed;
    }

    boolean removeEntry(Entry entry) throws IOException {
        if (entry.currentEditor != null) {
            entry.currentEditor.detach();
        }
        for (int i2 = 0; i2 < this.valueCount; ++i2) {
            this.fileSystem.delete(entry.cleanFiles[i2]);
            this.size -= entry.lengths[i2];
            entry.lengths[i2] = 0L;
        }
        ++this.redundantOpCount;
        this.journalWriter.writeUtf8(REMOVE).writeByte(32).writeUtf8(entry.key).writeByte(10);
        this.lruEntries.remove(entry.key);
        if (this.journalRebuildRequired()) {
            this.executor.execute(this.cleanupRunnable);
        }
        return true;
    }

    public synchronized boolean isClosed() {
        return this.closed;
    }

    private synchronized void checkNotClosed() {
        if (this.isClosed()) {
            throw new IllegalStateException("cache is closed");
        }
    }

    @Override
    public synchronized void flush() throws IOException {
        if (!this.initialized) {
            return;
        }
        this.checkNotClosed();
        this.trimToSize();
        this.journalWriter.flush();
    }

    @Override
    public synchronized void close() throws IOException {
        if (!this.initialized || this.closed) {
            this.closed = true;
            return;
        }
        for (Entry entry : this.lruEntries.values().toArray(new Entry[this.lruEntries.size()])) {
            if (entry.currentEditor == null) continue;
            entry.currentEditor.abort();
        }
        this.trimToSize();
        this.journalWriter.close();
        this.journalWriter = null;
        this.closed = true;
    }

    void trimToSize() throws IOException {
        while (this.size > this.maxSize) {
            Entry toEvict = this.lruEntries.values().iterator().next();
            this.removeEntry(toEvict);
        }
        this.mostRecentTrimFailed = false;
    }

    public void delete() throws IOException {
        this.close();
        this.fileSystem.deleteContents(this.directory);
    }

    public synchronized void evictAll() throws IOException {
        this.initialize();
        for (Entry entry : this.lruEntries.values().toArray(new Entry[this.lruEntries.size()])) {
            this.removeEntry(entry);
        }
        this.mostRecentTrimFailed = false;
    }

    private void validateKey(String key) {
        Matcher matcher = LEGAL_KEY_PATTERN.matcher(key);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,120}: \"" + key + "\"");
        }
    }

    public synchronized Iterator<Snapshot> snapshots() throws IOException {
        this.initialize();
        return new Iterator<Snapshot>(){
            final Iterator<Entry> delegate;
            Snapshot nextSnapshot;
            Snapshot removeSnapshot;
            {
                this.delegate = new ArrayList<Entry>(DiskLruCache.this.lruEntries.values()).iterator();
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public boolean hasNext() {
                if (this.nextSnapshot != null) {
                    return true;
                }
                DiskLruCache diskLruCache = DiskLruCache.this;
                synchronized (diskLruCache) {
                    if (DiskLruCache.this.closed) {
                        return false;
                    }
                    while (this.delegate.hasNext()) {
                        Entry entry = this.delegate.next();
                        Snapshot snapshot = entry.snapshot();
                        if (snapshot == null) continue;
                        this.nextSnapshot = snapshot;
                        return true;
                    }
                }
                return false;
            }

            @Override
            public Snapshot next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                this.removeSnapshot = this.nextSnapshot;
                this.nextSnapshot = null;
                return this.removeSnapshot;
            }

            @Override
            public void remove() {
                if (this.removeSnapshot == null) {
                    throw new IllegalStateException("remove() before next()");
                }
                try {
                    DiskLruCache.this.remove(this.removeSnapshot.key);
                }
                catch (IOException iOException) {
                }
                finally {
                    this.removeSnapshot = null;
                }
            }
        };
    }

    private final class Entry {
        final String key;
        final long[] lengths;
        final File[] cleanFiles;
        final File[] dirtyFiles;
        boolean readable;
        Editor currentEditor;
        long sequenceNumber;

        Entry(String key) {
            this.key = key;
            this.lengths = new long[DiskLruCache.this.valueCount];
            this.cleanFiles = new File[DiskLruCache.this.valueCount];
            this.dirtyFiles = new File[DiskLruCache.this.valueCount];
            StringBuilder fileBuilder = new StringBuilder(key).append('.');
            int truncateTo = fileBuilder.length();
            for (int i2 = 0; i2 < DiskLruCache.this.valueCount; ++i2) {
                fileBuilder.append(i2);
                this.cleanFiles[i2] = new File(DiskLruCache.this.directory, fileBuilder.toString());
                fileBuilder.append(".tmp");
                this.dirtyFiles[i2] = new File(DiskLruCache.this.directory, fileBuilder.toString());
                fileBuilder.setLength(truncateTo);
            }
        }

        void setLengths(String[] strings) throws IOException {
            if (strings.length != DiskLruCache.this.valueCount) {
                throw this.invalidLengths(strings);
            }
            try {
                for (int i2 = 0; i2 < strings.length; ++i2) {
                    this.lengths[i2] = Long.parseLong(strings[i2]);
                }
            }
            catch (NumberFormatException e2) {
                throw this.invalidLengths(strings);
            }
        }

        void writeLengths(BufferedSink writer) throws IOException {
            for (long length : this.lengths) {
                writer.writeByte(32).writeDecimalLong(length);
            }
        }

        private IOException invalidLengths(String[] strings) throws IOException {
            throw new IOException("unexpected journal line: " + Arrays.toString(strings));
        }

        Snapshot snapshot() {
            if (!Thread.holdsLock(DiskLruCache.this)) {
                throw new AssertionError();
            }
            Source[] sources = new Source[DiskLruCache.this.valueCount];
            long[] lengths = (long[])this.lengths.clone();
            try {
                for (int i2 = 0; i2 < DiskLruCache.this.valueCount; ++i2) {
                    sources[i2] = DiskLruCache.this.fileSystem.source(this.cleanFiles[i2]);
                }
                return new Snapshot(this.key, this.sequenceNumber, sources, lengths);
            }
            catch (FileNotFoundException e2) {
                for (int i3 = 0; i3 < DiskLruCache.this.valueCount && sources[i3] != null; ++i3) {
                    Util.closeQuietly(sources[i3]);
                }
                try {
                    DiskLruCache.this.removeEntry(this);
                }
                catch (IOException iOException) {
                    // empty catch block
                }
                return null;
            }
        }
    }

    public final class Editor {
        final Entry entry;
        final boolean[] written;
        private boolean done;

        Editor(Entry entry) {
            this.entry = entry;
            this.written = entry.readable ? null : new boolean[DiskLruCache.this.valueCount];
        }

        void detach() {
            if (this.entry.currentEditor == this) {
                for (int i2 = 0; i2 < DiskLruCache.this.valueCount; ++i2) {
                    try {
                        DiskLruCache.this.fileSystem.delete(this.entry.dirtyFiles[i2]);
                        continue;
                    }
                    catch (IOException iOException) {
                        // empty catch block
                    }
                }
                this.entry.currentEditor = null;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        public Source newSource(int index) {
            DiskLruCache diskLruCache = DiskLruCache.this;
            synchronized (diskLruCache) {
                if (this.done) {
                    throw new IllegalStateException();
                }
                if (!this.entry.readable || this.entry.currentEditor != this) {
                    return null;
                }
                try {
                    return DiskLruCache.this.fileSystem.source(this.entry.cleanFiles[index]);
                }
                catch (FileNotFoundException e2) {
                    return null;
                }
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        public Sink newSink(int index) {
            DiskLruCache diskLruCache = DiskLruCache.this;
            synchronized (diskLruCache) {
                Sink sink;
                if (this.done) {
                    throw new IllegalStateException();
                }
                if (this.entry.currentEditor != this) {
                    return Okio.blackhole();
                }
                if (!this.entry.readable) {
                    this.written[index] = true;
                }
                File dirtyFile = this.entry.dirtyFiles[index];
                try {
                    sink = DiskLruCache.this.fileSystem.sink(dirtyFile);
                }
                catch (FileNotFoundException e2) {
                    return Okio.blackhole();
                }
                return new FaultHidingSink(sink){

                    /*
                     * WARNING - Removed try catching itself - possible behaviour change.
                     */
                    @Override
                    protected void onException(IOException e2) {
                        DiskLruCache diskLruCache = DiskLruCache.this;
                        synchronized (diskLruCache) {
                            Editor.this.detach();
                        }
                    }
                };
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        public void commit() throws IOException {
            DiskLruCache diskLruCache = DiskLruCache.this;
            synchronized (diskLruCache) {
                if (this.done) {
                    throw new IllegalStateException();
                }
                if (this.entry.currentEditor == this) {
                    DiskLruCache.this.completeEdit(this, true);
                }
                this.done = true;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        public void abort() throws IOException {
            DiskLruCache diskLruCache = DiskLruCache.this;
            synchronized (diskLruCache) {
                if (this.done) {
                    throw new IllegalStateException();
                }
                if (this.entry.currentEditor == this) {
                    DiskLruCache.this.completeEdit(this, false);
                }
                this.done = true;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        public void abortUnlessCommitted() {
            DiskLruCache diskLruCache = DiskLruCache.this;
            synchronized (diskLruCache) {
                if (!this.done && this.entry.currentEditor == this) {
                    try {
                        DiskLruCache.this.completeEdit(this, false);
                    }
                    catch (IOException iOException) {
                        // empty catch block
                    }
                }
            }
        }
    }

    public final class Snapshot
    implements Closeable {
        private final String key;
        private final long sequenceNumber;
        private final Source[] sources;
        private final long[] lengths;

        Snapshot(String key, long sequenceNumber, Source[] sources, long[] lengths) {
            this.key = key;
            this.sequenceNumber = sequenceNumber;
            this.sources = sources;
            this.lengths = lengths;
        }

        public String key() {
            return this.key;
        }

        @Nullable
        public Editor edit() throws IOException {
            return DiskLruCache.this.edit(this.key, this.sequenceNumber);
        }

        public Source getSource(int index) {
            return this.sources[index];
        }

        public long getLength(int index) {
            return this.lengths[index];
        }

        @Override
        public void close() {
            for (Source in2 : this.sources) {
                Util.closeQuietly(in2);
            }
        }
    }
}

