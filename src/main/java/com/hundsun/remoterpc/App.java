package com.hundsun.remoterpc;

import com.hundsun.remoterpc.entity.User;
import com.hundsun.remoterpc.proxy.RpcClientProxy;
import com.hundsun.remoterpc.service.IHelloService;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        RpcClientProxy proxy = new RpcClientProxy();
        IHelloService helloService = proxy.clientProxy(IHelloService.class, "localhost", 8080);
        System.out.println(helloService.sayHello("hundsun"));
        User user = new User();
        user.setName("小领子");
        System.out.println(helloService.saveUser(user));
    }
}
