#include <jni.h>
#include <string.h>
#include <math.h>
#include <stdio.h>
#include <android/log.h>
#include <android/bitmap.h>

jlong fibN(jlong n){
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        } else {
            return fibN(n - 1) + fibN(n - 2);
        }
}

JNIEXPORT jlong JNICALL Java_com_example_ndk_1practice_FibLib_fabIterativeNative
  (JNIEnv *env, jclass jc, jlong n){
  return fibN(n);
  }


JNIEXPORT jlong JNICALL Java_com_example_ndk_1practice_FibLib_fabRecursionNative
  (JNIEnv *env, jclass jc, jlong n){
       return fibN(n);
  }