package com.example.ndk_practice;

/**
 * Created by M1030452 on 3/1/2018.
 */

public class FibLib {
    public static long fabIterative(long n) {
        long previous = -1;
        long result = 1;
        for (int i = 0; i <= n; i++) {
            long sum = result + previous;
            previous = result;
            result = sum;
        }
        return result;
    }

    ;

    public static long fabRecursion(long n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        } else {
            return fabRecursion(n - 1) + fabIterative(n - 2);
        }
    }

    static {
        System.loadLibrary("fib");
    }

    public static native long fabIterativeNative(long n);

    public static native long fabRecursionNative(long n);
}
