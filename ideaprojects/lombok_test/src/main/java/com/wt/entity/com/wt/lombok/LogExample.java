package com.wt.entity.com.wt.lombok;

import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

/**
 * @description
 * @author: wangtao
 * @date:14:56 2018/1/23
 * @email:tao8.wang@changhong.com
 */
@Log
public class LogExample {

    public static void main(String... args) {
        log.info("Something's wrong here");
    }
}

@Slf4j
class LogExampleOther {

    public static void main(String... args) {
        log.error("Something else is wrong here");
    }
}

@CommonsLog(topic = "CounterLog")
class LogExampleCategory {

    public static void main(String... args) {
        log.error("Calling the 'CounterLog' with a message");
    }
}

