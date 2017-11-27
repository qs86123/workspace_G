package com.chaonghong.data.test;

public class ThreadTest
{

    public static void main(String[] args) throws Exception
    {
        Thread thread = new Thread()
        {
            private int num = 0;

            @Override
            public void run()
            {
                System.out.println("I am running in a separate thread!");
                this.num = changeNum(20);
            }
        };
        thread.start();
        System.out.println("MAIN BBB");
        thread.join();
        System.out.println("MAIN");

    }

    private static int changeNum(int num)
    {
        return num;
    }
}
