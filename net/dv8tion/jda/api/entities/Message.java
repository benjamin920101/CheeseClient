/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.entities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Formattable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.IMentionable;
import net.dv8tion.jda.api.entities.ISnowflake;
import net.dv8tion.jda.api.entities.Icon;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageActivity;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.MessageSticker;
import net.dv8tion.jda.api.entities.MessageType;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.exceptions.HttpException;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Button;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import net.dv8tion.jda.api.requests.restaction.pagination.ReactionPaginationAction;
import net.dv8tion.jda.api.utils.AttachmentOption;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.requests.FunctionalCallback;
import net.dv8tion.jda.internal.requests.Requester;
import net.dv8tion.jda.internal.utils.Checks;
import net.dv8tion.jda.internal.utils.IOUtil;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.apache.commons.collections4.Bag;

public interface Message
extends ISnowflake,
Formattable {
    public static final int MAX_FILE_SIZE = 0x800000;
    public static final int MAX_FILE_SIZE_NITRO = 0x3200000;
    public static final int MAX_FILE_AMOUNT = 10;
    public static final int MAX_CONTENT_LENGTH = 2000;
    public static final int MAX_REACTIONS = 20;
    public static final Pattern INVITE_PATTERN = Pattern.compile("(?:https?://)?(?:\\w+\\.)?discord(?:(?:app)?\\.com/invite|\\.gg)/(?<code>[a-z0-9-]+)(?:\\?\\S*)?(?:#\\S*)?", 2);
    public static final Pattern JUMP_URL_PATTERN = Pattern.compile("(?:https?://)?(?:\\w+\\.)?discord(?:app)?\\.com/channels/(?<guild>\\d+)/(?<channel>\\d+)/(?<message>\\d+)(?:\\?\\S*)?(?:#\\S*)?", 2);

    @Nullable
    public Message getReferencedMessage();

    @Nonnull
    public List<User> getMentionedUsers();

    @Nonnull
    public Bag<User> getMentionedUsersBag();

    @Nonnull
    public List<TextChannel> getMentionedChannels();

    @Nonnull
    public Bag<TextChannel> getMentionedChannelsBag();

    @Nonnull
    public List<Role> getMentionedRoles();

    @Nonnull
    public Bag<Role> getMentionedRolesBag();

    @Nonnull
    public List<Member> getMentionedMembers(@Nonnull Guild var1);

    @Nonnull
    public List<Member> getMentionedMembers();

    @Nonnull
    public List<IMentionable> getMentions(MentionType ... var1);

    public boolean isMentioned(@Nonnull IMentionable var1, MentionType ... var2);

    public boolean mentionsEveryone();

    public boolean isEdited();

    @Nullable
    public OffsetDateTime getTimeEdited();

    @Nonnull
    public User getAuthor();

    @Nullable
    public Member getMember();

    @Nonnull
    public String getJumpUrl();

    @Nonnull
    public String getContentDisplay();

    @Nonnull
    public String getContentRaw();

    @Nonnull
    public String getContentStripped();

    @Nonnull
    public List<String> getInvites();

    @Nullable
    public String getNonce();

    public boolean isFromType(@Nonnull ChannelType var1);

    default public boolean isFromGuild() {
        return this.getChannelType().isGuild();
    }

    @Nonnull
    public ChannelType getChannelType();

    public boolean isWebhookMessage();

    @Nonnull
    public MessageChannel getChannel();

    @Nonnull
    public PrivateChannel getPrivateChannel();

    @Nonnull
    public TextChannel getTextChannel();

    @Nullable
    public Category getCategory();

    @Nonnull
    public Guild getGuild();

    @Nonnull
    public List<Attachment> getAttachments();

    @Nonnull
    public List<MessageEmbed> getEmbeds();

    @Nonnull
    public List<ActionRow> getActionRows();

    @Nonnull
    default public List<Button> getButtons() {
        return this.getActionRows().stream().map(ActionRow::getButtons).flatMap(Collection::stream).collect(Collectors.toList());
    }

    @Nullable
    default public Button getButtonById(@Nonnull String id2) {
        Checks.notNull(id2, "Button ID");
        return this.getButtons().stream().filter(it2 -> id2.equals(it2.getId())).findFirst().orElse(null);
    }

    @Nonnull
    default public List<Button> getButtonsByLabel(@Nonnull String label, boolean ignoreCase) {
        Checks.notNull(label, "Label");
        Predicate<Button> filter = ignoreCase ? b2 -> label.equalsIgnoreCase(b2.getLabel()) : b2 -> label.equals(b2.getLabel());
        return this.getButtons().stream().filter(filter).collect(Collectors.toList());
    }

    @Nonnull
    public List<Emote> getEmotes();

    @Nonnull
    public Bag<Emote> getEmotesBag();

    @Nonnull
    public List<MessageReaction> getReactions();

    @Nonnull
    public List<MessageSticker> getStickers();

    public boolean isTTS();

    @Nullable
    public MessageActivity getActivity();

    @Nonnull
    @CheckReturnValue
    public MessageAction editMessage(@Nonnull CharSequence var1);

    @Nonnull
    @CheckReturnValue
    public MessageAction editMessage(@Nonnull MessageEmbed var1);

    @Nonnull
    @CheckReturnValue
    public MessageAction editMessageFormat(@Nonnull String var1, Object ... var2);

    @Nonnull
    @CheckReturnValue
    public MessageAction editMessage(@Nonnull Message var1);

    @Nonnull
    @CheckReturnValue
    default public MessageAction reply(@Nonnull CharSequence content) {
        return this.getChannel().sendMessage(content).reference(this);
    }

    @Nonnull
    @CheckReturnValue
    default public MessageAction reply(@Nonnull MessageEmbed content) {
        return this.getChannel().sendMessage(content).reference(this);
    }

    @Nonnull
    @CheckReturnValue
    default public MessageAction reply(@Nonnull Message content) {
        return this.getChannel().sendMessage(content).reference(this);
    }

    @Nonnull
    @CheckReturnValue
    default public MessageAction replyFormat(@Nonnull String format, Object ... args) {
        return this.getChannel().sendMessageFormat(format, args).reference(this);
    }

    @Nonnull
    @CheckReturnValue
    default public MessageAction reply(@Nonnull File file, AttachmentOption ... options) {
        return this.getChannel().sendFile(file, options).reference(this);
    }

    @Nonnull
    @CheckReturnValue
    default public MessageAction reply(@Nonnull File data, @Nonnull String name, AttachmentOption ... options) {
        return this.getChannel().sendFile(data, name, options).reference(this);
    }

    @Nonnull
    @CheckReturnValue
    default public MessageAction reply(@Nonnull InputStream data, @Nonnull String name, AttachmentOption ... options) {
        return this.getChannel().sendFile(data, name, options).reference(this);
    }

    @Nonnull
    @CheckReturnValue
    default public MessageAction reply(@Nonnull byte[] data, @Nonnull String name, AttachmentOption ... options) {
        return this.getChannel().sendFile(data, name, options).reference(this);
    }

    @Nonnull
    @CheckReturnValue
    public AuditableRestAction<Void> delete();

    @Nonnull
    public JDA getJDA();

    public boolean isPinned();

    @Nonnull
    @CheckReturnValue
    public RestAction<Void> pin();

    @Nonnull
    @CheckReturnValue
    public RestAction<Void> unpin();

    @Nonnull
    @CheckReturnValue
    public RestAction<Void> addReaction(@Nonnull Emote var1);

    @Nonnull
    @CheckReturnValue
    public RestAction<Void> addReaction(@Nonnull String var1);

    @Nonnull
    @CheckReturnValue
    public RestAction<Void> clearReactions();

    @Nonnull
    @CheckReturnValue
    public RestAction<Void> clearReactions(@Nonnull String var1);

    @Nonnull
    @CheckReturnValue
    public RestAction<Void> clearReactions(@Nonnull Emote var1);

    @Nonnull
    @CheckReturnValue
    public RestAction<Void> removeReaction(@Nonnull Emote var1);

    @Nonnull
    @CheckReturnValue
    public RestAction<Void> removeReaction(@Nonnull Emote var1, @Nonnull User var2);

    @Nonnull
    @CheckReturnValue
    public RestAction<Void> removeReaction(@Nonnull String var1);

    @Nonnull
    @CheckReturnValue
    public RestAction<Void> removeReaction(@Nonnull String var1, @Nonnull User var2);

    @Nonnull
    @CheckReturnValue
    public ReactionPaginationAction retrieveReactionUsers(@Nonnull Emote var1);

    @Nonnull
    @CheckReturnValue
    public ReactionPaginationAction retrieveReactionUsers(@Nonnull String var1);

    @Nullable
    @CheckReturnValue
    public MessageReaction.ReactionEmote getReactionByUnicode(@Nonnull String var1);

    @Nullable
    @CheckReturnValue
    public MessageReaction.ReactionEmote getReactionById(@Nonnull String var1);

    @Nullable
    @CheckReturnValue
    public MessageReaction.ReactionEmote getReactionById(long var1);

    @Nonnull
    @CheckReturnValue
    public AuditableRestAction<Void> suppressEmbeds(boolean var1);

    @Nonnull
    @CheckReturnValue
    public RestAction<Message> crosspost();

    public boolean isSuppressedEmbeds();

    @Nonnull
    public EnumSet<MessageFlag> getFlags();

    @Nonnull
    public MessageType getType();

    public static class Attachment
    implements ISnowflake {
        private static final Set<String> IMAGE_EXTENSIONS = new HashSet<String>(Arrays.asList("jpg", "jpeg", "png", "gif", "webp", "tiff", "svg", "apng"));
        private static final Set<String> VIDEO_EXTENSIONS = new HashSet<String>(Arrays.asList("webm", "flv", "vob", "avi", "mov", "wmv", "amv", "mp4", "mpg", "mpeg", "gifv"));
        private final long id;
        private final String url;
        private final String proxyUrl;
        private final String fileName;
        private final String contentType;
        private final int size;
        private final int height;
        private final int width;
        private final JDAImpl jda;

        public Attachment(long id2, String url, String proxyUrl, String fileName, String contentType, int size, int height, int width, JDAImpl jda) {
            this.id = id2;
            this.url = url;
            this.proxyUrl = proxyUrl;
            this.fileName = fileName;
            this.contentType = contentType;
            this.size = size;
            this.height = height;
            this.width = width;
            this.jda = jda;
        }

        @Nonnull
        public JDA getJDA() {
            return this.jda;
        }

        @Override
        public long getIdLong() {
            return this.id;
        }

        @Nonnull
        public String getUrl() {
            return this.url;
        }

        @Nonnull
        public String getProxyUrl() {
            return this.proxyUrl;
        }

        @Nonnull
        public String getFileName() {
            return this.fileName;
        }

        @Nullable
        public String getFileExtension() {
            int index = this.fileName.lastIndexOf(46) + 1;
            return index == 0 || index == this.fileName.length() ? null : this.fileName.substring(index);
        }

        @Nullable
        public String getContentType() {
            return this.contentType;
        }

        @Nonnull
        public CompletableFuture<InputStream> retrieveInputStream() {
            CompletableFuture<InputStream> future = new CompletableFuture<InputStream>();
            Request req = this.getRequest();
            OkHttpClient httpClient = this.getJDA().getHttpClient();
            httpClient.newCall(req).enqueue(FunctionalCallback.onFailure((call, e2) -> future.completeExceptionally(new UncheckedIOException((IOException)e2))).onSuccess((call, response) -> {
                if (response.isSuccessful()) {
                    InputStream body = IOUtil.getBody(response);
                    if (!future.complete(body)) {
                        IOUtil.silentClose(response);
                    }
                } else {
                    future.completeExceptionally(new HttpException(response.code() + ": " + response.message()));
                    IOUtil.silentClose(response);
                }
            }).build());
            return future;
        }

        @Nonnull
        public CompletableFuture<File> downloadToFile() {
            return this.downloadToFile(this.getFileName());
        }

        @Nonnull
        public CompletableFuture<File> downloadToFile(String path) {
            Checks.notNull(path, "Path");
            return this.downloadToFile(new File(path));
        }

        @Nonnull
        public CompletableFuture<File> downloadToFile(File file) {
            Checks.notNull(file, "File");
            try {
                if (!file.exists()) {
                    file.createNewFile();
                } else {
                    Checks.check(file.canWrite(), "Cannot write to file %s", (Object)file.getName());
                }
            }
            catch (IOException e2) {
                throw new IllegalArgumentException("Cannot create file", e2);
            }
            return this.retrieveInputStream().thenApplyAsync(stream -> {
                try {
                    File file2;
                    FileOutputStream out = new FileOutputStream(file);
                    try {
                        int count;
                        byte[] buf = new byte[1024];
                        while ((count = stream.read(buf)) > 0) {
                            out.write(buf, 0, count);
                        }
                        file2 = file;
                    }
                    catch (Throwable throwable) {
                        try {
                            try {
                                out.close();
                            }
                            catch (Throwable throwable2) {
                                throwable.addSuppressed(throwable2);
                            }
                            throw throwable;
                        }
                        catch (IOException e2) {
                            throw new UncheckedIOException(e2);
                        }
                    }
                    out.close();
                    return file2;
                }
                finally {
                    IOUtil.silentClose(stream);
                }
            }, (Executor)this.getJDA().getCallbackPool());
        }

        @Nonnull
        public CompletableFuture<Icon> retrieveAsIcon() {
            if (!this.isImage()) {
                throw new IllegalStateException("Cannot create an Icon out of this attachment. This is not an image.");
            }
            return this.retrieveInputStream().thenApplyAsync(stream -> {
                try {
                    Icon icon = Icon.from(stream);
                    return icon;
                }
                catch (IOException e2) {
                    throw new UncheckedIOException(e2);
                }
                finally {
                    IOUtil.silentClose(stream);
                }
            }, (Executor)this.getJDA().getCallbackPool());
        }

        protected Request getRequest() {
            return new Request.Builder().url(this.getUrl()).addHeader("user-agent", Requester.USER_AGENT).addHeader("accept-encoding", "gzip, deflate").build();
        }

        public int getSize() {
            return this.size;
        }

        public int getHeight() {
            return this.height;
        }

        public int getWidth() {
            return this.width;
        }

        public boolean isImage() {
            if (this.width < 0) {
                return false;
            }
            String extension = this.getFileExtension();
            return extension != null && IMAGE_EXTENSIONS.contains(extension.toLowerCase());
        }

        public boolean isVideo() {
            if (this.width < 0) {
                return false;
            }
            String extension = this.getFileExtension();
            return extension != null && VIDEO_EXTENSIONS.contains(extension.toLowerCase());
        }

        public boolean isSpoiler() {
            return this.getFileName().startsWith("SPOILER_");
        }
    }

    public static enum MessageFlag {
        CROSSPOSTED(0),
        IS_CROSSPOST(1),
        EMBEDS_SUPPRESSED(2),
        SOURCE_MESSAGE_DELETED(3),
        URGENT(4);

        private final int value;

        private MessageFlag(int offset) {
            this.value = 1 << offset;
        }

        public int getValue() {
            return this.value;
        }

        @Nonnull
        public static EnumSet<MessageFlag> fromBitField(int bitfield) {
            Set set = Arrays.stream(MessageFlag.values()).filter(e2 -> (e2.value & bitfield) > 0).collect(Collectors.toSet());
            return set.isEmpty() ? EnumSet.noneOf(MessageFlag.class) : EnumSet.copyOf(set);
        }

        public static int toBitField(@Nonnull Collection<MessageFlag> coll) {
            Checks.notNull(coll, "Collection");
            int flags = 0;
            for (MessageFlag messageFlag : coll) {
                flags |= messageFlag.value;
            }
            return flags;
        }
    }

    public static enum MentionType {
        USER("<@!?(\\d+)>", "users"),
        ROLE("<@&(\\d+)>", "roles"),
        CHANNEL("<#(\\d+)>", null),
        EMOTE("<a?:([a-zA-Z0-9_]+):([0-9]+)>", null),
        HERE("@here", "everyone"),
        EVERYONE("@everyone", "everyone");

        private final Pattern pattern;
        private final String parseKey;

        private MentionType(String regex, String parseKey) {
            this.pattern = Pattern.compile(regex);
            this.parseKey = parseKey;
        }

        @Nonnull
        public Pattern getPattern() {
            return this.pattern;
        }

        @Nullable
        public String getParseKey() {
            return this.parseKey;
        }
    }
}

