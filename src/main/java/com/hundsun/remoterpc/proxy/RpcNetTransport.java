package com.hundsun.remoterpc.proxy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 类职责：
 * <p>
 * Title: RpcNetTransport.java
 * Description:
 * Copyright: Copyright (c) 2018 杭州云赢网络科技有限公司
 * Company: 杭州云赢网络科技有限公司
 *
 * @author zhanggl10620@hundsun.com
 * @date 2019.04.08 19:15
 */
public class RpcNetTransport {

    String host;

    int port;

    public RpcNetTransport(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private Socket newSocket() {
        System.out.println("创建一个新的Socket连接");
        Socket socket = null;
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            throw new RuntimeException("建立连接失败");
        }
        return socket;
    }

    /**
     * 发送一个消息，返回一个Object
     *
     * @return
     */
    public Object sendRequest(RpcRequest rpcRequest) {
        Socket socket = null;
        socket = newSocket();
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outputStream.writeObject(rpcRequest);
            outputStream.flush();

            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            try {
                Object result = inputStream.readObject();

                inputStream.close();
                outputStream.close();
                return result;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

        return null;
    }
}