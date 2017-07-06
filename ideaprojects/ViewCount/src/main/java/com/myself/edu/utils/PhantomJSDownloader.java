package com.myself.edu.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myself.edu.constants.Constants;

/**
 * this downloader is used to download pages which need to render the javascript
 *
 * @author dolphineor@gmail.com
 * @version 0.5.3
 */
public enum PhantomJSDownloader {
	INS;

    private Logger logger = LoggerFactory.getLogger(PhantomJSDownloader.class);
    private String phantomJSPath;
    private String phantomJSBinPath;

    private int retryNum = 1;
    private int sleepTime = 0 * 1000;
    private int retrySleepTime = 10 * 1000;
    //private int threadNum;
	private String phantomJSParams;

    PhantomJSDownloader() {
		this.phantomJSPath = Constants.PHANTOMJS_JS_PATH;
		this.phantomJSBinPath = Constants.PHANTOMJS_BIN_PATH;
		this.phantomJSParams = Constants.PHANTOMJS_PARAMS;
    }

    public String download(String url) {
        if (logger.isInfoEnabled()) {
            logger.info("downloading page: {}", url);
        }
        String content = getPage(url);
        if (content.contains("HTTP request failed")) {
            for (int i = 1; i <= getRetryNum(); i++) {
            	try {
            		logger.debug("retrySleepTime start");
            		Thread.sleep(retrySleepTime);
					logger.debug("retrySleepTime end");
				} catch (InterruptedException e) {
					logger.error("{}", e);
				}
                content = getPage(url);
                if (!content.contains("HTTP request failed")) {
                    break;
                }
            }
            if (content.contains("HTTP request failed")) {
                //when failed
                return "";
            }
        }

        return content;
    }

    protected String getPage(String url) {
        try {
            Runtime runtime = Runtime.getRuntime();
			Process process = runtime.exec(phantomJSBinPath
							+ (StringUtils.isBlank(phantomJSParams) ? "" : (" " + phantomJSParams))
							+ genOhterParams()
							+ " " + phantomJSPath + " " + url);
            logger.debug("sleepTime start");
            Thread.sleep(sleepTime);
            logger.debug("sleepTime end");
            InputStream is = process.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                stringBuffer.append(line).append("\n");
            }
            return stringBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
			e.printStackTrace();
		}

        return null;
    }

    /**
	 * MethodName：genOhterParams
	 * @author: Administrator
	 * @Date: 2017年2月20日 上午9:47:58
	 * @Description TODO(这里用一句话描述这个方法的作用)
	 * @return
	 */
	private String genOhterParams() {
		String otherParams = "";
		String proxyType = Constants.type;
		if(StringUtils.isNotBlank(proxyType)){
			String proxyParamPrefix = Constants.prefix;
			if("ipport".equals(proxyType)){
				otherParams = proxyParamPrefix + Constants.ipport;
			} else if("pool".equals(proxyType)){
				//otherParams = proxyParamPrefix + Constants.proxyService.getProxyIpPort();
			}
		}
		return otherParams;
	}

	public int getRetryNum() {
        return retryNum;
    }

    public PhantomJSDownloader setRetryNum(int retryNum) {
        this.retryNum = retryNum;
        return this;
    }
	
	public PhantomJSDownloader setSleepTime(int sleepTime){
		this.sleepTime = sleepTime;
		return this;
	}
	
	public PhantomJSDownloader setRetrySleepTime(int retrySleepTime){
		this.retrySleepTime = retrySleepTime;
		return this;
	}
}
