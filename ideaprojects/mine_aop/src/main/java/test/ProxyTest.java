package test;

import com.wt.aop.HandlerType;
import com.wt.aop.invoke.hanler.DefaultInvokeHandler;
import com.wt.aop.invoke.hanler.HandlerConfig;
import com.wt.aop.invoke.hanler.ProxyConfig;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @description
 * @author: wangtao
 * @date:10:31 2018/8/4
 * @email:386427665@qq.com
 */
public class ProxyTest {
    public static void main(String[] args) throws NoSuchMethodException {
        Method m=TestInterface.class.getMethod("hello");
        HandlerConfig handlerConfig = HandlerConfig.getIncetence();
        handlerConfig.addHandler(m,new ProxyConfig(new BeforeTest(),"beforeHello", HandlerType.BEFORE));
        handlerConfig.addHandler(m,new ProxyConfig(new BeforeTest(),"beforeHello2", HandlerType.BEFORE));
        handlerConfig.addHandler(m,new ProxyConfig(new BeforeTest(),"beforeHello2", HandlerType.BEFORE));
        handlerConfig.addHandler(m,new ProxyConfig(new BeforeTest(),"beforeHello2", HandlerType.BEFORE));
        handlerConfig.addHandler(m,new ProxyConfig(new BeforeTest(),"beforeHello2", HandlerType.BEFORE));
        handlerConfig.addHandler(m,new ProxyConfig(new BeforeTest(),"beforeHello2", HandlerType.BEFORE));
        handlerConfig.addHandler(m,new ProxyConfig(new AfterTest(),"afterHello", HandlerType.AFTER));
        handlerConfig.addHandler(m,new ProxyConfig(new AfterTest(),"afterHello2", HandlerType.AFTER));
        TestInterface o = (TestInterface)Proxy.newProxyInstance(Test.class.getClassLoader(), new Class[]{TestInterface.class}, new DefaultInvokeHandler(null,new Test()));
        System.out.println(o.hello());
    }
}
