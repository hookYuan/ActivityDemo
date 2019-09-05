// IAIDLInterface.aidl
package cn.yuan.demo.ipcdemo.aidl;

import cn.yuan.demo.ipcdemo.aidl.NotificationListener;

// Declare any non-default types here with import statements

interface IAIDLInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void send(String str);

    /**
     * 回调，（ server to client ）进程双向通讯
     */
    void addCallback(NotificationListener listener);
}
