/*
 * Decompiled with CFR 0.152.
 */
package tomp2p.opuswrapper;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.PointerType;
import com.sun.jna.ptr.FloatByReference;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import com.sun.jna.ptr.ShortByReference;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

public interface Opus
extends Library {
    public static final Opus INSTANCE = (Opus)Native.loadLibrary(System.getProperty("opus.lib"), Opus.class);
    public static final int OPUS_GET_LSB_DEPTH_REQUEST = 4037;
    public static final int OPUS_GET_APPLICATION_REQUEST = 4001;
    public static final int OPUS_GET_FORCE_CHANNELS_REQUEST = 4023;
    public static final int OPUS_GET_VBR_REQUEST = 4007;
    public static final int OPUS_GET_BANDWIDTH_REQUEST = 4009;
    public static final int OPUS_SET_BITRATE_REQUEST = 4002;
    public static final int OPUS_SET_BANDWIDTH_REQUEST = 4008;
    public static final int OPUS_SIGNAL_MUSIC = 3002;
    public static final int OPUS_RESET_STATE = 4028;
    public static final int OPUS_FRAMESIZE_2_5_MS = 5001;
    public static final int OPUS_GET_COMPLEXITY_REQUEST = 4011;
    public static final int OPUS_FRAMESIZE_40_MS = 5005;
    public static final int OPUS_SET_PACKET_LOSS_PERC_REQUEST = 4014;
    public static final int OPUS_GET_VBR_CONSTRAINT_REQUEST = 4021;
    public static final int OPUS_SET_INBAND_FEC_REQUEST = 4012;
    public static final int OPUS_APPLICATION_RESTRICTED_LOWDELAY = 2051;
    public static final int OPUS_BANDWIDTH_FULLBAND = 1105;
    public static final int OPUS_SET_VBR_REQUEST = 4006;
    public static final int OPUS_BANDWIDTH_SUPERWIDEBAND = 1104;
    public static final int OPUS_SET_FORCE_CHANNELS_REQUEST = 4022;
    public static final int OPUS_APPLICATION_VOIP = 2048;
    public static final int OPUS_SIGNAL_VOICE = 3001;
    public static final int OPUS_GET_FINAL_RANGE_REQUEST = 4031;
    public static final int OPUS_BUFFER_TOO_SMALL = -2;
    public static final int OPUS_SET_COMPLEXITY_REQUEST = 4010;
    public static final int OPUS_FRAMESIZE_ARG = 5000;
    public static final int OPUS_GET_LOOKAHEAD_REQUEST = 4027;
    public static final int OPUS_GET_INBAND_FEC_REQUEST = 4013;
    public static final int OPUS_BITRATE_MAX = -1;
    public static final int OPUS_FRAMESIZE_5_MS = 5002;
    public static final int OPUS_BAD_ARG = -1;
    public static final int OPUS_GET_PITCH_REQUEST = 4033;
    public static final int OPUS_SET_SIGNAL_REQUEST = 4024;
    public static final int OPUS_FRAMESIZE_20_MS = 5004;
    public static final int OPUS_APPLICATION_AUDIO = 2049;
    public static final int OPUS_GET_DTX_REQUEST = 4017;
    public static final int OPUS_FRAMESIZE_10_MS = 5003;
    public static final int OPUS_SET_LSB_DEPTH_REQUEST = 4036;
    public static final int OPUS_UNIMPLEMENTED = -5;
    public static final int OPUS_GET_PACKET_LOSS_PERC_REQUEST = 4015;
    public static final int OPUS_INVALID_STATE = -6;
    public static final int OPUS_SET_EXPERT_FRAME_DURATION_REQUEST = 4040;
    public static final int OPUS_FRAMESIZE_60_MS = 5006;
    public static final int OPUS_GET_BITRATE_REQUEST = 4003;
    public static final int OPUS_INTERNAL_ERROR = -3;
    public static final int OPUS_SET_MAX_BANDWIDTH_REQUEST = 4004;
    public static final int OPUS_SET_VBR_CONSTRAINT_REQUEST = 4020;
    public static final int OPUS_GET_MAX_BANDWIDTH_REQUEST = 4005;
    public static final int OPUS_BANDWIDTH_NARROWBAND = 1101;
    public static final int OPUS_SET_GAIN_REQUEST = 4034;
    public static final int OPUS_SET_PREDICTION_DISABLED_REQUEST = 4042;
    public static final int OPUS_SET_APPLICATION_REQUEST = 4000;
    public static final int OPUS_SET_DTX_REQUEST = 4016;
    public static final int OPUS_BANDWIDTH_MEDIUMBAND = 1102;
    public static final int OPUS_GET_SAMPLE_RATE_REQUEST = 4029;
    public static final int OPUS_GET_EXPERT_FRAME_DURATION_REQUEST = 4041;
    public static final int OPUS_AUTO = -1000;
    public static final int OPUS_GET_SIGNAL_REQUEST = 4025;
    public static final int OPUS_GET_LAST_PACKET_DURATION_REQUEST = 4039;
    public static final int OPUS_GET_PREDICTION_DISABLED_REQUEST = 4043;
    public static final int OPUS_GET_GAIN_REQUEST = 4045;
    public static final int OPUS_BANDWIDTH_WIDEBAND = 1103;
    public static final int OPUS_INVALID_PACKET = -4;
    public static final int OPUS_ALLOC_FAIL = -7;
    public static final int OPUS_OK = 0;
    public static final int OPUS_MULTISTREAM_GET_DECODER_STATE_REQUEST = 5122;
    public static final int OPUS_MULTISTREAM_GET_ENCODER_STATE_REQUEST = 5120;

    public int opus_encoder_get_size(int var1);

    public PointerByReference opus_encoder_create(int var1, int var2, int var3, IntBuffer var4);

    public int opus_encoder_init(PointerByReference var1, int var2, int var3, int var4);

    public int opus_encode(PointerByReference var1, ShortBuffer var2, int var3, ByteBuffer var4, int var5);

    public int opus_encode(PointerByReference var1, ShortByReference var2, int var3, Pointer var4, int var5);

    public int opus_encode_float(PointerByReference var1, float[] var2, int var3, ByteBuffer var4, int var5);

    public int opus_encode_float(PointerByReference var1, FloatByReference var2, int var3, Pointer var4, int var5);

    public void opus_encoder_destroy(PointerByReference var1);

    public int opus_encoder_ctl(PointerByReference var1, int var2, Object ... var3);

    public int opus_decoder_get_size(int var1);

    public PointerByReference opus_decoder_create(int var1, int var2, IntBuffer var3);

    public int opus_decoder_init(PointerByReference var1, int var2, int var3);

    public int opus_decode(PointerByReference var1, byte[] var2, int var3, ShortBuffer var4, int var5, int var6);

    public int opus_decode(PointerByReference var1, Pointer var2, int var3, ShortByReference var4, int var5, int var6);

    public int opus_decode_float(PointerByReference var1, byte[] var2, int var3, FloatBuffer var4, int var5, int var6);

    public int opus_decode_float(PointerByReference var1, Pointer var2, int var3, FloatByReference var4, int var5, int var6);

    public int opus_decoder_ctl(PointerByReference var1, int var2, Object ... var3);

    public void opus_decoder_destroy(PointerByReference var1);

    public int opus_packet_parse(byte[] var1, int var2, ByteBuffer var3, byte[] var4, ShortBuffer var5, IntBuffer var6);

    public int opus_packet_get_bandwidth(byte[] var1);

    public int opus_packet_get_samples_per_frame(byte[] var1, int var2);

    public int opus_packet_get_nb_channels(byte[] var1);

    public int opus_packet_get_nb_frames(byte[] var1, int var2);

    public int opus_packet_get_nb_samples(byte[] var1, int var2, int var3);

    public int opus_decoder_get_nb_samples(PointerByReference var1, byte[] var2, int var3);

    public int opus_decoder_get_nb_samples(PointerByReference var1, Pointer var2, int var3);

    public void opus_pcm_soft_clip(FloatBuffer var1, int var2, int var3, FloatBuffer var4);

    public int opus_repacketizer_get_size();

    public PointerByReference opus_repacketizer_init(PointerByReference var1);

    public PointerByReference opus_repacketizer_create();

    public void opus_repacketizer_destroy(PointerByReference var1);

    public int opus_repacketizer_cat(PointerByReference var1, byte[] var2, int var3);

    public int opus_repacketizer_cat(PointerByReference var1, Pointer var2, int var3);

    public int opus_repacketizer_out_range(PointerByReference var1, int var2, int var3, ByteBuffer var4, int var5);

    public int opus_repacketizer_out_range(PointerByReference var1, int var2, int var3, Pointer var4, int var5);

    public int opus_repacketizer_get_nb_frames(PointerByReference var1);

    public int opus_repacketizer_out(PointerByReference var1, ByteBuffer var2, int var3);

    public int opus_repacketizer_out(PointerByReference var1, Pointer var2, int var3);

    public int opus_packet_pad(ByteBuffer var1, int var2, int var3);

    public int opus_packet_unpad(ByteBuffer var1, int var2);

    public int opus_multistream_packet_pad(ByteBuffer var1, int var2, int var3, int var4);

    public int opus_multistream_packet_unpad(ByteBuffer var1, int var2, int var3);

    public String opus_strerror(int var1);

    public String opus_get_version_string();

    public int opus_multistream_encoder_get_size(int var1, int var2);

    public int opus_multistream_surround_encoder_get_size(int var1, int var2);

    public PointerByReference opus_multistream_encoder_create(int var1, int var2, int var3, int var4, byte[] var5, int var6, IntBuffer var7);

    public PointerByReference opus_multistream_surround_encoder_create(int var1, int var2, int var3, IntBuffer var4, IntBuffer var5, ByteBuffer var6, int var7, IntBuffer var8);

    public int opus_multistream_encoder_init(PointerByReference var1, int var2, int var3, int var4, int var5, byte[] var6, int var7);

    public int opus_multistream_encoder_init(PointerByReference var1, int var2, int var3, int var4, int var5, Pointer var6, int var7);

    public int opus_multistream_surround_encoder_init(PointerByReference var1, int var2, int var3, int var4, IntBuffer var5, IntBuffer var6, ByteBuffer var7, int var8);

    public int opus_multistream_surround_encoder_init(PointerByReference var1, int var2, int var3, int var4, IntByReference var5, IntByReference var6, Pointer var7, int var8);

    public int opus_multistream_encode(PointerByReference var1, ShortBuffer var2, int var3, ByteBuffer var4, int var5);

    public int opus_multistream_encode(PointerByReference var1, ShortByReference var2, int var3, Pointer var4, int var5);

    public int opus_multistream_encode_float(PointerByReference var1, float[] var2, int var3, ByteBuffer var4, int var5);

    public int opus_multistream_encode_float(PointerByReference var1, FloatByReference var2, int var3, Pointer var4, int var5);

    public void opus_multistream_encoder_destroy(PointerByReference var1);

    public int opus_multistream_encoder_ctl(PointerByReference var1, int var2, Object ... var3);

    public int opus_multistream_decoder_get_size(int var1, int var2);

    public PointerByReference opus_multistream_decoder_create(int var1, int var2, int var3, int var4, byte[] var5, IntBuffer var6);

    public int opus_multistream_decoder_init(PointerByReference var1, int var2, int var3, int var4, int var5, byte[] var6);

    public int opus_multistream_decoder_init(PointerByReference var1, int var2, int var3, int var4, int var5, Pointer var6);

    public int opus_multistream_decode(PointerByReference var1, byte[] var2, int var3, ShortBuffer var4, int var5, int var6);

    public int opus_multistream_decode(PointerByReference var1, Pointer var2, int var3, ShortByReference var4, int var5, int var6);

    public int opus_multistream_decode_float(PointerByReference var1, byte[] var2, int var3, FloatBuffer var4, int var5, int var6);

    public int opus_multistream_decode_float(PointerByReference var1, Pointer var2, int var3, FloatByReference var4, int var5, int var6);

    public int opus_multistream_decoder_ctl(PointerByReference var1, int var2, Object ... var3);

    public void opus_multistream_decoder_destroy(PointerByReference var1);

    public PointerByReference opus_custom_mode_create(int var1, int var2, IntBuffer var3);

    public void opus_custom_mode_destroy(PointerByReference var1);

    public int opus_custom_encoder_get_size(PointerByReference var1, int var2);

    public PointerByReference opus_custom_encoder_create(PointerByReference var1, int var2, IntBuffer var3);

    public PointerByReference opus_custom_encoder_create(PointerByReference var1, int var2, IntByReference var3);

    public void opus_custom_encoder_destroy(PointerByReference var1);

    public int opus_custom_encode_float(PointerByReference var1, float[] var2, int var3, ByteBuffer var4, int var5);

    public int opus_custom_encode_float(PointerByReference var1, FloatByReference var2, int var3, Pointer var4, int var5);

    public int opus_custom_encode(PointerByReference var1, ShortBuffer var2, int var3, ByteBuffer var4, int var5);

    public int opus_custom_encode(PointerByReference var1, ShortByReference var2, int var3, Pointer var4, int var5);

    public int opus_custom_encoder_ctl(PointerByReference var1, int var2, Object ... var3);

    public int opus_custom_decoder_get_size(PointerByReference var1, int var2);

    public int opus_custom_decoder_init(PointerByReference var1, PointerByReference var2, int var3);

    public PointerByReference opus_custom_decoder_create(PointerByReference var1, int var2, IntBuffer var3);

    public PointerByReference opus_custom_decoder_create(PointerByReference var1, int var2, IntByReference var3);

    public void opus_custom_decoder_destroy(PointerByReference var1);

    public int opus_custom_decode_float(PointerByReference var1, byte[] var2, int var3, FloatBuffer var4, int var5);

    public int opus_custom_decode_float(PointerByReference var1, Pointer var2, int var3, FloatByReference var4, int var5);

    public int opus_custom_decode(PointerByReference var1, byte[] var2, int var3, ShortBuffer var4, int var5);

    public int opus_custom_decode(PointerByReference var1, Pointer var2, int var3, ShortByReference var4, int var5);

    public int opus_custom_decoder_ctl(PointerByReference var1, int var2, Object ... var3);

    public static class OpusCustomMode
    extends PointerType {
        public OpusCustomMode(Pointer address) {
            super(address);
        }

        public OpusCustomMode() {
        }
    }

    public static class OpusCustomEncoder
    extends PointerType {
        public OpusCustomEncoder(Pointer address) {
            super(address);
        }

        public OpusCustomEncoder() {
        }
    }

    public static class OpusCustomDecoder
    extends PointerType {
        public OpusCustomDecoder(Pointer address) {
            super(address);
        }

        public OpusCustomDecoder() {
        }
    }

    public static class OpusMSDecoder
    extends PointerType {
        public OpusMSDecoder(Pointer address) {
            super(address);
        }

        public OpusMSDecoder() {
        }
    }

    public static class OpusMSEncoder
    extends PointerType {
        public OpusMSEncoder(Pointer address) {
            super(address);
        }

        public OpusMSEncoder() {
        }
    }

    public static class OpusRepacketizer
    extends PointerType {
        public OpusRepacketizer(Pointer address) {
            super(address);
        }

        public OpusRepacketizer() {
        }
    }

    public static class OpusEncoder
    extends PointerType {
        public OpusEncoder(Pointer address) {
            super(address);
        }

        public OpusEncoder() {
        }
    }

    public static class OpusDecoder
    extends PointerType {
        public OpusDecoder(Pointer address) {
            super(address);
        }

        public OpusDecoder() {
        }
    }
}

