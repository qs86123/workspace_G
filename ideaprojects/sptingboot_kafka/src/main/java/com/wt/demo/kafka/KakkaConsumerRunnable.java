package com.wt.demo.kafka;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.message.MessageAndMetadata;

/**
 * @Description
 * @Author: wangtao
 * @Date:13:42 2017/7/21
 * @Email:tao8.wang@changhong.com
 */
public class KakkaConsumerRunnable implements Runnable {

    private KafkaStream<byte[], byte[]> stream;

    public KakkaConsumerRunnable(KafkaStream<byte[], byte[]> stream) {
        this.stream = stream;
    }


    @Override
    public void run() {
        ConsumerIterator<byte[], byte[]> it = stream.iterator();
        while (it.hasNext()) {
            MessageAndMetadata<byte[], byte[]> mam = it.next();
            byte[] message = mam.message();
            if (message == null || message.length < 1) {
                continue;
            }
            System.out.println(Thread.currentThread().getName() + ": partition[" + mam.partition() + "],"
                    + "offset[" + mam.offset() + "], " + new String(mam.message()));
        }
    }
}
