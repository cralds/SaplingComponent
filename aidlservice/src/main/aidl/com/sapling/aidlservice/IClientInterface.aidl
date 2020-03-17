
// IClientInterface.aidl
package com.sapling.aidlservice;

// Declare any non-default types here with import statements

interface IClientInterface {
    void requestSuccess(String result);
    void requestFail(String msg);
}
