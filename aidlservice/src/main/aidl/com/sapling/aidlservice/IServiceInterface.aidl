// IServiceInterface.aidl
package com.sapling.aidlservice;

import com.sapling.aidlservice.IClientInterface;
// Declare any non-default types here with import statements

interface IServiceInterface {
    void registerListener(IClientInterface iClientInterface);
    void unRegisterListener(IClientInterface iClientInterface);
    void request(String name);
}
