package com.myself.edu;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.lang3.StringUtils;

import com.myself.edu.constants.Constants;
import com.myself.edu.utils.Calculator;
import com.myself.edu.utils.Logger;
import com.myself.edu.utils.MatchUtil;
import com.myself.edu.utils.PhantomJSDownloader;
import com.myself.edu.utils.QuickCrawlUtil;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {	
        String cron = "com.myself.edu.jobs.StartViewJob:0 0 9,10,11,14,15,16,17 * * ?";
        cron = "com.myself.edu.jobs.StartViewJob:0 0 9,10,11,14,15,16,17 * * ?";
        List<String> container = new ArrayList<String>();
        //http://1s1k.eduyun.cn/portal/redesign/index/index.jsp?t=2&xueduan=3&s=subject.0032&v=ff8080814371757b014386580101443f&c=000803&code=-1&yearMark=&isCountriesGood=&isProvinceGood=&isCityGood=&isCountyGood=&isCaseJxsj=&isCaseEvl=&isCaseKj=&isCaseRes=&creTime=&cSchoolId=&condition=&sflag=0&vflag=0&cflag=0
        final String url = "http://1s1k.eduyun.cn/portal/redesign/index/index.jsp?sdResIdCaseId=ff8080815b14015f015b1775904d0283&t=2&sessionKey=usOR3qtybi5QOLA2dRWy";
        final String url2 = "http://1s1k.eduyun.cn/portal/redesign/index/index.jsp?sdResIdCaseId=ff8080815bfbf0cf015c1e5e04293047&t=2&sessionKey=bFlqD3e7CCZAZ2MAeLOu";
        final String proxyUrl = "";
        int delay;
        while(true){
        	try {
        		//getProxys(proxyUrl, container);
        		delay = 0;
        		if(container.isEmpty()) {
        			run(url, delay);
        			run(url2, delay);
        			delay = getDelay();
        		} else {
        			for (String ipPort : container) {
            			if(StringUtils.isNotBlank(ipPort)){
            				Constants.type = "ipport";
                			Constants.ipport = StringUtils.join(Constants.prefix, ipPort);
            			}
            			run(url, delay);
            			run(url2, delay);
            			delay = getDelay();
            		}
        			Constants.type = null;
        		}
			} catch (Exception e) {
				Logger.INS.error("{}", e);
			}
        }
    }

	private static void run(String url, int delay) throws Exception {
		Thread.sleep(delay);
        Logger.INS.info("hs is:{}", PhantomJSDownloader.INS.download(url));
	}

	private static int getDelay() {
		String dateStr = DateUtil.formatDate(new Date(), "ddHHmm");
        dateStr = StringUtils.join(dateStr.split(""), "+").toString();
        Logger.INS.info("dateStr is :{}", dateStr);
        int delay = Integer.parseInt(Calculator.INS.calculate(dateStr));
        Logger.INS.info("delay is :{}", delay);
		return delay;
	}
	
	//代理
	private static void getProxys(final String proxyUrl, List<String> proxyContainer) {
		//http://www.xdaili.cn/ipagent//newExclusive/getIp?spiderId=1fc67828f3f543c0a4f2901565b92c10&orderno=MF2017629257dx9s9a&returnType=1&count=1
		proxyContainer.clear();
		String proxysStr = QuickCrawlUtil.INS.download(proxyUrl);
		//proxysStr = "180.115.15.175:47255";
		proxyContainer.addAll(MatchUtil.INS.findAll(proxysStr, "\\d{1,3}(\\.\\d{1,3}){3}:\\d+"));
	}
	private static void getProxys(final String proxyUrl, Map<String, Integer> proxyContainer) {
		//http://www.xdaili.cn/ipagent//newExclusive/getIp?spiderId=1fc67828f3f543c0a4f2901565b92c10&orderno=MF2017629257dx9s9a&returnType=1&count=1
		String proxysStr = QuickCrawlUtil.INS.download(proxyUrl);
		//proxysStr = "180.115.15.175:47255";
		List<String> pList = MatchUtil.INS.findAll(proxysStr, "\\d{1,3}(\\.\\d{1,3}){3}:\\d+");
		for(String p : pList){
			addProxyIntoMap(proxyContainer, p);
		}
	}

	private static void addProxyIntoMap(Map<String, Integer> proxyContainer, String p) {
		String[] pArr = p.split(":");
		proxyContainer.put(pArr[0], Integer.parseInt(pArr[1]));
	}
}
