package com.myself.edu.constants;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * core、common模块的常量定义
 */
public class Constants {
	public static final String DEFAULT_USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36";
	
	public static final AtomicInteger QUARTZ_TRIGGER_INDEX = new AtomicInteger(1);
	public static final AtomicInteger QUARTZ_JOB_INDEX = new AtomicInteger(1);
	public static final String QUARTZ_GROUP_NAME = "Group1";

	public static final String PHANTOMJS_JS_PATH = "C:\\Users\\Administrator\\Desktop\\phantomjs\\crawl.js";

	public static final String PHANTOMJS_BIN_PATH = "C:\\Users\\Administrator\\Desktop\\phantomjs\\phantomjs.exe";

	public static final String PHANTOMJS_PARAMS = null;//"--cookies-file=/home/ch/conf/GDNews/cookies";

	public static String type = null;//"ipport";

	public static final String prefix = "--proxy=";

	public static String ipport = null;//"--proxy=192.168.2.45:8088";
}
