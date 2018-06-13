// IPrintService.aidl
package com.example.serviceaidl;

// Declare any non-default types here with import statements

interface IPrintService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt);
    void printS(String s);
}
