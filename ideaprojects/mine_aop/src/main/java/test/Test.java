package test;

/**
 * @description
 * @author: wangtao
 * @date:9:57 2018/8/4
 * @email:386427665@qq.com
 */
public class Test implements TestInterface{
    public String hello(){
        System.out.println("I was proxyed");
        return "hello";
    }
}
