/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class net_crew_0005fvre_codec_ffmpeg_FFMPEGCodec */

#ifndef _Included_net_crew_0005fvre_codec_ffmpeg_FFMPEGCodec
#define _Included_net_crew_0005fvre_codec_ffmpeg_FFMPEGCodec
#ifdef __cplusplus
extern "C" {
#endif
/* Inaccessible static: sync */
/* Inaccessible static: codecs */
/* Inaccessible static: inputFormats */
/* Inaccessible static: outputFormats */
/*
 * Class:     net_crew_0005fvre_codec_ffmpeg_FFMPEGCodec
 * Method:    openCodec
 * Signature: (ZI)J
 */
JNIEXPORT jlong JNICALL Java_net_crew_1vre_codec_ffmpeg_FFMPEGCodec_openCodec
  (JNIEnv *, jobject, jboolean, jint);

/*
 * Class:     net_crew_0005fvre_codec_ffmpeg_FFMPEGCodec
 * Method:    init
 * Signature: (IIIIIIZ)I
 */
JNIEXPORT jint JNICALL Java_net_crew_1vre_codec_ffmpeg_FFMPEGCodec_init
  (JNIEnv *, jobject, jint, jint, jint, jint, jint, jint, jboolean);

/*
 * Class:     net_crew_0005fvre_codec_ffmpeg_FFMPEGCodec
 * Method:    decodeNative
 * Signature: (Ljavax/media/Buffer;Ljavax/media/Buffer;)I
 */
JNIEXPORT jint JNICALL Java_net_crew_1vre_codec_ffmpeg_FFMPEGCodec_decodeNative
  (JNIEnv *, jobject, jobject, jobject);

/*
 * Class:     net_crew_0005fvre_codec_ffmpeg_FFMPEGCodec
 * Method:    encodeNative
 * Signature: (Ljavax/media/Buffer;Ljavax/media/Buffer;)I
 */
JNIEXPORT jint JNICALL Java_net_crew_1vre_codec_ffmpeg_FFMPEGCodec_encodeNative
  (JNIEnv *, jobject, jobject, jobject);

/*
 * Class:     net_crew_0005fvre_codec_ffmpeg_FFMPEGCodec
 * Method:    closeCodec
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_net_crew_1vre_codec_ffmpeg_FFMPEGCodec_closeCodec
  (JNIEnv *, jobject);

#ifdef __cplusplus
}
#endif
#endif
