/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.ByteBufProcessor;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.EncoderException;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.charset.Charset;
import java.util.UUID;

public class em
extends ByteBuf {
    private final ByteBuf a;

    public em(ByteBuf byteBuf) {
        this.a = byteBuf;
    }

    public static int a(int n2) {
        for (\u2603 = 1; \u2603 < 5; ++\u2603) {
            if ((n2 & -1 << \u2603 * 7) != 0) continue;
            return \u2603;
        }
        return 5;
    }

    public void a(byte[] byArray) {
        this.b(byArray.length);
        this.writeBytes(byArray);
    }

    public byte[] a() {
        byte[] byArray = new byte[this.e()];
        this.readBytes(byArray);
        return byArray;
    }

    public cj c() {
        return cj.a(this.readLong());
    }

    public void a(cj cj2) {
        this.writeLong(cj2.g());
    }

    public eu d() throws IOException {
        return eu.a.a(this.c(Short.MAX_VALUE));
    }

    public void a(eu eu2) throws IOException {
        this.a(eu.a.a(eu2));
    }

    public <T extends Enum<T>> T a(Class<T> clazz) {
        return (T)((Enum[])clazz.getEnumConstants())[this.e()];
    }

    public void a(Enum<?> enum_) {
        this.b(enum_.ordinal());
    }

    public int e() {
        int n2 = 0;
        \u2603 = 0;
        do {
            byte by = this.readByte();
            n2 |= (by & 0x7F) << \u2603++ * 7;
            if (\u2603 <= 5) continue;
            throw new RuntimeException("VarInt too big");
        } while ((by & 0x80) == 128);
        return n2;
    }

    public long f() {
        long l2 = 0L;
        int \u26032 = 0;
        do {
            byte by = this.readByte();
            l2 |= (long)(by & 0x7F) << \u26032++ * 7;
            if (\u26032 <= 10) continue;
            throw new RuntimeException("VarLong too big");
        } while ((by & 0x80) == 128);
        return l2;
    }

    public void a(UUID uUID) {
        this.writeLong(uUID.getMostSignificantBits());
        this.writeLong(uUID.getLeastSignificantBits());
    }

    public UUID g() {
        return new UUID(this.readLong(), this.readLong());
    }

    public void b(int n2) {
        while (true) {
            if ((n2 & 0xFFFFFF80) == 0) {
                this.writeByte(n2);
                return;
            }
            this.writeByte(n2 & 0x7F | 0x80);
            n2 >>>= 7;
        }
    }

    public void b(long l2) {
        while (true) {
            if ((l2 & 0xFFFFFFFFFFFFFF80L) == 0L) {
                this.writeByte((int)l2);
                return;
            }
            this.writeByte((int)(l2 & 0x7FL) | 0x80);
            l2 >>>= 7;
        }
    }

    public void a(dn dn2) {
        if (dn2 == null) {
            this.writeByte(0);
        } else {
            try {
                dx.a(dn2, (DataOutput)new ByteBufOutputStream(this));
            }
            catch (IOException iOException) {
                throw new EncoderException(iOException);
            }
        }
    }

    public dn h() throws IOException {
        int n2 = this.readerIndex();
        byte \u26032 = this.readByte();
        if (\u26032 == 0) {
            return null;
        }
        this.readerIndex(n2);
        return dx.a(new ByteBufInputStream(this), new dw(0x200000L));
    }

    public void a(zx zx2) {
        if (zx2 == null) {
            this.writeShort(-1);
        } else {
            this.writeShort(zw.b(zx2.b()));
            this.writeByte(zx2.b);
            this.writeShort(zx2.i());
            dn dn2 = null;
            if (zx2.b().m() || zx2.b().p()) {
                dn2 = zx2.o();
            }
            this.a(dn2);
        }
    }

    public zx i() throws IOException {
        zx \u26034 = null;
        short \u26032 = this.readShort();
        if (\u26032 >= 0) {
            byte by = this.readByte();
            short \u26033 = this.readShort();
            \u26034 = new zx(zw.b(\u26032), (int)by, (int)\u26033);
            \u26034.d(this.h());
        }
        return \u26034;
    }

    public String c(int n2) {
        \u2603 = this.e();
        if (\u2603 > n2 * 4) {
            throw new DecoderException("The received encoded string buffer length is longer than maximum allowed (" + \u2603 + " > " + n2 * 4 + ")");
        }
        if (\u2603 < 0) {
            throw new DecoderException("The received encoded string buffer length is less than zero! Weird string!");
        }
        String string = new String(this.readBytes(\u2603).array(), Charsets.UTF_8);
        if (string.length() > n2) {
            throw new DecoderException("The received string length is longer than maximum allowed (" + \u2603 + " > " + n2 + ")");
        }
        return string;
    }

    public em a(String string) {
        byte[] byArray = string.getBytes(Charsets.UTF_8);
        if (byArray.length > Short.MAX_VALUE) {
            throw new EncoderException("String too big (was " + string.length() + " bytes encoded, max " + Short.MAX_VALUE + ")");
        }
        this.b(byArray.length);
        this.writeBytes(byArray);
        return this;
    }

    @Override
    public int capacity() {
        return this.a.capacity();
    }

    @Override
    public ByteBuf capacity(int n2) {
        return this.a.capacity(n2);
    }

    @Override
    public int maxCapacity() {
        return this.a.maxCapacity();
    }

    @Override
    public ByteBufAllocator alloc() {
        return this.a.alloc();
    }

    @Override
    public ByteOrder order() {
        return this.a.order();
    }

    @Override
    public ByteBuf order(ByteOrder byteOrder) {
        return this.a.order(byteOrder);
    }

    @Override
    public ByteBuf unwrap() {
        return this.a.unwrap();
    }

    @Override
    public boolean isDirect() {
        return this.a.isDirect();
    }

    @Override
    public int readerIndex() {
        return this.a.readerIndex();
    }

    @Override
    public ByteBuf readerIndex(int n2) {
        return this.a.readerIndex(n2);
    }

    @Override
    public int writerIndex() {
        return this.a.writerIndex();
    }

    @Override
    public ByteBuf writerIndex(int n2) {
        return this.a.writerIndex(n2);
    }

    @Override
    public ByteBuf setIndex(int n2, int n3) {
        return this.a.setIndex(n2, n3);
    }

    @Override
    public int readableBytes() {
        return this.a.readableBytes();
    }

    @Override
    public int writableBytes() {
        return this.a.writableBytes();
    }

    @Override
    public int maxWritableBytes() {
        return this.a.maxWritableBytes();
    }

    @Override
    public boolean isReadable() {
        return this.a.isReadable();
    }

    @Override
    public boolean isReadable(int n2) {
        return this.a.isReadable(n2);
    }

    @Override
    public boolean isWritable() {
        return this.a.isWritable();
    }

    @Override
    public boolean isWritable(int n2) {
        return this.a.isWritable(n2);
    }

    @Override
    public ByteBuf clear() {
        return this.a.clear();
    }

    @Override
    public ByteBuf markReaderIndex() {
        return this.a.markReaderIndex();
    }

    @Override
    public ByteBuf resetReaderIndex() {
        return this.a.resetReaderIndex();
    }

    @Override
    public ByteBuf markWriterIndex() {
        return this.a.markWriterIndex();
    }

    @Override
    public ByteBuf resetWriterIndex() {
        return this.a.resetWriterIndex();
    }

    @Override
    public ByteBuf discardReadBytes() {
        return this.a.discardReadBytes();
    }

    @Override
    public ByteBuf discardSomeReadBytes() {
        return this.a.discardSomeReadBytes();
    }

    @Override
    public ByteBuf ensureWritable(int n2) {
        return this.a.ensureWritable(n2);
    }

    @Override
    public int ensureWritable(int n2, boolean bl2) {
        return this.a.ensureWritable(n2, bl2);
    }

    @Override
    public boolean getBoolean(int n2) {
        return this.a.getBoolean(n2);
    }

    @Override
    public byte getByte(int n2) {
        return this.a.getByte(n2);
    }

    @Override
    public short getUnsignedByte(int n2) {
        return this.a.getUnsignedByte(n2);
    }

    @Override
    public short getShort(int n2) {
        return this.a.getShort(n2);
    }

    @Override
    public int getUnsignedShort(int n2) {
        return this.a.getUnsignedShort(n2);
    }

    @Override
    public int getMedium(int n2) {
        return this.a.getMedium(n2);
    }

    @Override
    public int getUnsignedMedium(int n2) {
        return this.a.getUnsignedMedium(n2);
    }

    @Override
    public int getInt(int n2) {
        return this.a.getInt(n2);
    }

    @Override
    public long getUnsignedInt(int n2) {
        return this.a.getUnsignedInt(n2);
    }

    @Override
    public long getLong(int n2) {
        return this.a.getLong(n2);
    }

    @Override
    public char getChar(int n2) {
        return this.a.getChar(n2);
    }

    @Override
    public float getFloat(int n2) {
        return this.a.getFloat(n2);
    }

    @Override
    public double getDouble(int n2) {
        return this.a.getDouble(n2);
    }

    @Override
    public ByteBuf getBytes(int n2, ByteBuf byteBuf) {
        return this.a.getBytes(n2, byteBuf);
    }

    @Override
    public ByteBuf getBytes(int n2, ByteBuf byteBuf, int n3) {
        return this.a.getBytes(n2, byteBuf, n3);
    }

    @Override
    public ByteBuf getBytes(int n2, ByteBuf byteBuf, int n3, int n4) {
        return this.a.getBytes(n2, byteBuf, n3, n4);
    }

    @Override
    public ByteBuf getBytes(int n2, byte[] byArray) {
        return this.a.getBytes(n2, byArray);
    }

    @Override
    public ByteBuf getBytes(int n2, byte[] byArray, int n3, int n4) {
        return this.a.getBytes(n2, byArray, n3, n4);
    }

    @Override
    public ByteBuf getBytes(int n2, ByteBuffer byteBuffer) {
        return this.a.getBytes(n2, byteBuffer);
    }

    @Override
    public ByteBuf getBytes(int n2, OutputStream outputStream, int n3) throws IOException {
        return this.a.getBytes(n2, outputStream, n3);
    }

    @Override
    public int getBytes(int n2, GatheringByteChannel gatheringByteChannel, int n3) throws IOException {
        return this.a.getBytes(n2, gatheringByteChannel, n3);
    }

    @Override
    public ByteBuf setBoolean(int n2, boolean bl2) {
        return this.a.setBoolean(n2, bl2);
    }

    @Override
    public ByteBuf setByte(int n2, int n3) {
        return this.a.setByte(n2, n3);
    }

    @Override
    public ByteBuf setShort(int n2, int n3) {
        return this.a.setShort(n2, n3);
    }

    @Override
    public ByteBuf setMedium(int n2, int n3) {
        return this.a.setMedium(n2, n3);
    }

    @Override
    public ByteBuf setInt(int n2, int n3) {
        return this.a.setInt(n2, n3);
    }

    @Override
    public ByteBuf setLong(int n2, long l2) {
        return this.a.setLong(n2, l2);
    }

    @Override
    public ByteBuf setChar(int n2, int n3) {
        return this.a.setChar(n2, n3);
    }

    @Override
    public ByteBuf setFloat(int n2, float f2) {
        return this.a.setFloat(n2, f2);
    }

    @Override
    public ByteBuf setDouble(int n2, double d2) {
        return this.a.setDouble(n2, d2);
    }

    @Override
    public ByteBuf setBytes(int n2, ByteBuf byteBuf) {
        return this.a.setBytes(n2, byteBuf);
    }

    @Override
    public ByteBuf setBytes(int n2, ByteBuf byteBuf, int n3) {
        return this.a.setBytes(n2, byteBuf, n3);
    }

    @Override
    public ByteBuf setBytes(int n2, ByteBuf byteBuf, int n3, int n4) {
        return this.a.setBytes(n2, byteBuf, n3, n4);
    }

    @Override
    public ByteBuf setBytes(int n2, byte[] byArray) {
        return this.a.setBytes(n2, byArray);
    }

    @Override
    public ByteBuf setBytes(int n2, byte[] byArray, int n3, int n4) {
        return this.a.setBytes(n2, byArray, n3, n4);
    }

    @Override
    public ByteBuf setBytes(int n2, ByteBuffer byteBuffer) {
        return this.a.setBytes(n2, byteBuffer);
    }

    @Override
    public int setBytes(int n2, InputStream inputStream, int n3) throws IOException {
        return this.a.setBytes(n2, inputStream, n3);
    }

    @Override
    public int setBytes(int n2, ScatteringByteChannel scatteringByteChannel, int n3) throws IOException {
        return this.a.setBytes(n2, scatteringByteChannel, n3);
    }

    @Override
    public ByteBuf setZero(int n2, int n3) {
        return this.a.setZero(n2, n3);
    }

    @Override
    public boolean readBoolean() {
        return this.a.readBoolean();
    }

    @Override
    public byte readByte() {
        return this.a.readByte();
    }

    @Override
    public short readUnsignedByte() {
        return this.a.readUnsignedByte();
    }

    @Override
    public short readShort() {
        return this.a.readShort();
    }

    @Override
    public int readUnsignedShort() {
        return this.a.readUnsignedShort();
    }

    @Override
    public int readMedium() {
        return this.a.readMedium();
    }

    @Override
    public int readUnsignedMedium() {
        return this.a.readUnsignedMedium();
    }

    @Override
    public int readInt() {
        return this.a.readInt();
    }

    @Override
    public long readUnsignedInt() {
        return this.a.readUnsignedInt();
    }

    @Override
    public long readLong() {
        return this.a.readLong();
    }

    @Override
    public char readChar() {
        return this.a.readChar();
    }

    @Override
    public float readFloat() {
        return this.a.readFloat();
    }

    @Override
    public double readDouble() {
        return this.a.readDouble();
    }

    @Override
    public ByteBuf readBytes(int n2) {
        return this.a.readBytes(n2);
    }

    @Override
    public ByteBuf readSlice(int n2) {
        return this.a.readSlice(n2);
    }

    @Override
    public ByteBuf readBytes(ByteBuf byteBuf) {
        return this.a.readBytes(byteBuf);
    }

    @Override
    public ByteBuf readBytes(ByteBuf byteBuf, int n2) {
        return this.a.readBytes(byteBuf, n2);
    }

    @Override
    public ByteBuf readBytes(ByteBuf byteBuf, int n2, int n3) {
        return this.a.readBytes(byteBuf, n2, n3);
    }

    @Override
    public ByteBuf readBytes(byte[] byArray) {
        return this.a.readBytes(byArray);
    }

    @Override
    public ByteBuf readBytes(byte[] byArray, int n2, int n3) {
        return this.a.readBytes(byArray, n2, n3);
    }

    @Override
    public ByteBuf readBytes(ByteBuffer byteBuffer) {
        return this.a.readBytes(byteBuffer);
    }

    @Override
    public ByteBuf readBytes(OutputStream outputStream, int n2) throws IOException {
        return this.a.readBytes(outputStream, n2);
    }

    @Override
    public int readBytes(GatheringByteChannel gatheringByteChannel, int n2) throws IOException {
        return this.a.readBytes(gatheringByteChannel, n2);
    }

    @Override
    public ByteBuf skipBytes(int n2) {
        return this.a.skipBytes(n2);
    }

    @Override
    public ByteBuf writeBoolean(boolean bl2) {
        return this.a.writeBoolean(bl2);
    }

    @Override
    public ByteBuf writeByte(int n2) {
        return this.a.writeByte(n2);
    }

    @Override
    public ByteBuf writeShort(int n2) {
        return this.a.writeShort(n2);
    }

    @Override
    public ByteBuf writeMedium(int n2) {
        return this.a.writeMedium(n2);
    }

    @Override
    public ByteBuf writeInt(int n2) {
        return this.a.writeInt(n2);
    }

    @Override
    public ByteBuf writeLong(long l2) {
        return this.a.writeLong(l2);
    }

    @Override
    public ByteBuf writeChar(int n2) {
        return this.a.writeChar(n2);
    }

    @Override
    public ByteBuf writeFloat(float f2) {
        return this.a.writeFloat(f2);
    }

    @Override
    public ByteBuf writeDouble(double d2) {
        return this.a.writeDouble(d2);
    }

    @Override
    public ByteBuf writeBytes(ByteBuf byteBuf) {
        return this.a.writeBytes(byteBuf);
    }

    @Override
    public ByteBuf writeBytes(ByteBuf byteBuf, int n2) {
        return this.a.writeBytes(byteBuf, n2);
    }

    @Override
    public ByteBuf writeBytes(ByteBuf byteBuf, int n2, int n3) {
        return this.a.writeBytes(byteBuf, n2, n3);
    }

    @Override
    public ByteBuf writeBytes(byte[] byArray) {
        return this.a.writeBytes(byArray);
    }

    @Override
    public ByteBuf writeBytes(byte[] byArray, int n2, int n3) {
        return this.a.writeBytes(byArray, n2, n3);
    }

    @Override
    public ByteBuf writeBytes(ByteBuffer byteBuffer) {
        return this.a.writeBytes(byteBuffer);
    }

    @Override
    public int writeBytes(InputStream inputStream, int n2) throws IOException {
        return this.a.writeBytes(inputStream, n2);
    }

    @Override
    public int writeBytes(ScatteringByteChannel scatteringByteChannel, int n2) throws IOException {
        return this.a.writeBytes(scatteringByteChannel, n2);
    }

    @Override
    public ByteBuf writeZero(int n2) {
        return this.a.writeZero(n2);
    }

    @Override
    public int indexOf(int n2, int n3, byte by) {
        return this.a.indexOf(n2, n3, by);
    }

    @Override
    public int bytesBefore(byte by) {
        return this.a.bytesBefore(by);
    }

    @Override
    public int bytesBefore(int n2, byte by) {
        return this.a.bytesBefore(n2, by);
    }

    @Override
    public int bytesBefore(int n2, int n3, byte by) {
        return this.a.bytesBefore(n2, n3, by);
    }

    @Override
    public int forEachByte(ByteBufProcessor byteBufProcessor) {
        return this.a.forEachByte(byteBufProcessor);
    }

    @Override
    public int forEachByte(int n2, int n3, ByteBufProcessor byteBufProcessor) {
        return this.a.forEachByte(n2, n3, byteBufProcessor);
    }

    @Override
    public int forEachByteDesc(ByteBufProcessor byteBufProcessor) {
        return this.a.forEachByteDesc(byteBufProcessor);
    }

    @Override
    public int forEachByteDesc(int n2, int n3, ByteBufProcessor byteBufProcessor) {
        return this.a.forEachByteDesc(n2, n3, byteBufProcessor);
    }

    @Override
    public ByteBuf copy() {
        return this.a.copy();
    }

    @Override
    public ByteBuf copy(int n2, int n3) {
        return this.a.copy(n2, n3);
    }

    @Override
    public ByteBuf slice() {
        return this.a.slice();
    }

    @Override
    public ByteBuf slice(int n2, int n3) {
        return this.a.slice(n2, n3);
    }

    @Override
    public ByteBuf duplicate() {
        return this.a.duplicate();
    }

    @Override
    public int nioBufferCount() {
        return this.a.nioBufferCount();
    }

    @Override
    public ByteBuffer nioBuffer() {
        return this.a.nioBuffer();
    }

    @Override
    public ByteBuffer nioBuffer(int n2, int n3) {
        return this.a.nioBuffer(n2, n3);
    }

    @Override
    public ByteBuffer internalNioBuffer(int n2, int n3) {
        return this.a.internalNioBuffer(n2, n3);
    }

    @Override
    public ByteBuffer[] nioBuffers() {
        return this.a.nioBuffers();
    }

    @Override
    public ByteBuffer[] nioBuffers(int n2, int n3) {
        return this.a.nioBuffers(n2, n3);
    }

    @Override
    public boolean hasArray() {
        return this.a.hasArray();
    }

    @Override
    public byte[] array() {
        return this.a.array();
    }

    @Override
    public int arrayOffset() {
        return this.a.arrayOffset();
    }

    @Override
    public boolean hasMemoryAddress() {
        return this.a.hasMemoryAddress();
    }

    @Override
    public long memoryAddress() {
        return this.a.memoryAddress();
    }

    @Override
    public String toString(Charset charset) {
        return this.a.toString(charset);
    }

    @Override
    public String toString(int n2, int n3, Charset charset) {
        return this.a.toString(n2, n3, charset);
    }

    @Override
    public int hashCode() {
        return this.a.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        return this.a.equals(object);
    }

    @Override
    public int compareTo(ByteBuf byteBuf) {
        return this.a.compareTo(byteBuf);
    }

    @Override
    public String toString() {
        return this.a.toString();
    }

    @Override
    public ByteBuf retain(int n2) {
        return this.a.retain(n2);
    }

    @Override
    public ByteBuf retain() {
        return this.a.retain();
    }

    @Override
    public int refCnt() {
        return this.a.refCnt();
    }

    @Override
    public boolean release() {
        return this.a.release();
    }

    @Override
    public boolean release(int n2) {
        return this.a.release(n2);
    }
}

