package com.chaonghong.data.test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Sums
{

    static class Sum implements Callable<Long>
    {
        private final long from;
        private final long to;

        Sum(long from, long to)
        {
            this.from = from;
            this.to = to;
        }

        @Override
        public Long call()
        {
            long acc = 0;
            for (long i = from; i <= to; i++)
            {
                acc = acc + i;
            }
            return acc;
        }
    }

    public static void main(String[] args) throws Exception
    {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<Long> submit = executor.submit(new Sum(10, 20));
        System.out.println(submit.get(1, TimeUnit.MILLISECONDS));
        System.out.println(submit.isCancelled());

        List<Future<Long>> results = executor
            .invokeAll(Arrays.asList(new Sum(0, 10), new Sum(100, 1_000), new Sum(10_000, 1_000_000)));

        for (Future<Long> result : results)
        {
            System.out.println(result.get());
        }
    }
}
