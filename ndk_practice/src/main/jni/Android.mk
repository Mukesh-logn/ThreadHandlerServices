LOCAL_PATH := $(call my-dir)

# Create Swatch Generator library

include $(CLEAR_VARS)

LOCAL_MODULE    := fib
LOCAL_SRC_FILES := fib.c


include $(BUILD_SHARED_LIBRARY)


