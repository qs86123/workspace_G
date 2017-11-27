package com.chaonghong.data.test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTest extends RecursiveTask<Long>
{
    static final int THRESHOLD = 100;

    long[] array;
    int start;
    int end;

    ForkJoinTest(long[] array, int start, int end)
    {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    protected Long compute()
    {
        if (end - start <= THRESHOLD)
        {
            // 如果任务足够小,直接计算:
            long sum = 0;
            for (int i = start; i < end; i++)
            {
                sum += array[i];
            }
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)

            {
            }
            System.out.println(String.format("compute %d~%d = %d", start, end, sum));
            return sum;
        }
        // 任务太大,一分为二:
        int middle = (end + start) / 2;
        System.out
            .println(String.format("split %d~%d ==> %d~%d, %d~%d", start, end, start, middle, middle, end));
        ForkJoinTest subtask1 = new ForkJoinTest(this.array, start, middle);
        ForkJoinTest subtask2 = new ForkJoinTest(this.array, middle, end);
        invokeAll(subtask1, subtask2);
        Long subresult1 = subtask1.join();

        Long subresult2 = subtask2.join();
        Long result = subresult1 + subresult2;
        System.out.println("result = " + subresult1 + " + " + subresult2 + " ==> " + result);
        return result;
    }

    public static void main(String[] args)
    {
        // 创建随机数组成的数组:
        long[] array = new long[400];
        fillRandom(array);
        // fork/join task:
        ForkJoinPool fjp = new ForkJoinPool(4); // 最大并发数4
        ForkJoinTask<Long> task = new ForkJoinTest(array, 0, array.length);
        long startTime = System.currentTimeMillis();
        Long result = fjp.invoke(task);
        long endTime = System.currentTimeMillis();
        System.out.println("Fork/join sum: " + result + " in " + (endTime - startTime) + " ms.");
    }

    public static void fillRandom(long[] array)
    {
        for (int i = 0; i < array.length; i++)
        {
            long longValue = (long) (Math.random() * 20 + 1);
            array[i] = longValue;
        }
    }

}
