package com.hundsun.remoterpc.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 类职责：
 * <p>
 * Title: RemoteInvocationHandler.java
 * Description:
 * Copyright: Copyright (c) 2018 杭州云赢网络科技有限公司
 * Company: 杭州云赢网络科技有限公司
 *
 * @author zhanggl10620@hundsun.com
 * @date 2019.04.08 19:12
 */
public class RemoteInvocationHandler implements InvocationHandler {

    String host;

    int port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public RemoteInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * 动态代理
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParameters(args);
        RpcNetTransport rpcNetTransport = new RpcNetTransport(host, port);
        return rpcNetTransport.sendRequest(rpcRequest);
    }
}