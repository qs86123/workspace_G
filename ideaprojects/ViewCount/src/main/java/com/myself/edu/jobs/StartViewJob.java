package com.myself.edu.jobs;

import com.myself.edu.utils.Calculator;
import com.myself.edu.utils.Logger;
import com.myself.edu.utils.QuickCrawlUtil;
import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * Created by Administrator on 2017/5/24 0024.
 */
public class StartViewJob implements Job {
    private final String url = "http://1s1k.eduyun.cn/portal/redesign/index/index.jsp?t=2&sdResIdCaseId=ff8080815b14015f015b1775904d0283";
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        run();
    }

    private void run() {
        String pHost = "";
        Integer pPort = 0;
        //计算延迟
        String dateStr = DateUtil.formatDate(new Date(), "ddHHmm");
        dateStr = StringUtils.join(dateStr.split(""), "+").toString();
        int delay = Integer.parseInt(Calculator.INS.calculate(dateStr));
        try {
            Logger.INS.info("sleep start.");
            Thread.sleep(delay * 1000);
            Logger.INS.info("sleep end.");
        } catch (Exception e) {
        }
        //下载
        QuickCrawlUtil.INS.setRequestMethod("GET")
                .addExtraHeader("Accept", "image/gif, image/jpeg, image/pjpeg, application/x-ms-application, application/xaml+xml, application/x-ms-xbap, */*")
                .addExtraHeader("Content-Type", "text/html; charset=UTF-8")
                .addExtraHeader("Cookie", "JSESSIONID=B39B40BF2128C92D378BEBE6BFA26E63; JSESSIONID=B39B40BF2128C92D378BEBE6BFA26E63; UM_distinctid=15c3a9fde252af-0146f83c161c0e-293e1d4e-100200-15c3a9fde26492; CNZZDATA1253416210=1288786434-1495631094-%7C1495631094; CNZZDATA1253333710=2064436886-1495627016-%7C1495632416; 1s1kweb12=1s1kweb01")
                .download(url, pHost, pPort);
    }
}
