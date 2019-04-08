package com.hundsun.remoterpc.proxy;

import java.lang.reflect.Proxy;

/**
 * 类职责：
 * <p>
 * Title: RpcClientProxy.java
 * Description:
 * Copyright: Copyright (c) 2018 杭州云赢网络科技有限公司
 * Company: 杭州云赢网络科技有限公司
 *
 * @author zhanggl10620@hundsun.com
 * @date 2019.04.08 19:07
 */
public class RpcClientProxy {

    /**
     * 代理模式
     *
     * @param interfaceCls
     * @param host
     * @param port
     * @param <T>
     * @return
     */
    public <T> T clientProxy(Class<T> interfaceCls, String host, int port) {
        return (T) Proxy.newProxyInstance(interfaceCls.getClassLoader(), new Class<?>[]{interfaceCls}, new RemoteInvocationHandler(host, port));
    }
}