package com.changhong.data.meeting.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author WangYang
 * 2016年5月23日
 * email:wangyang@broadengate.com 
 * description:
 */
public class ExController {
	
    private final Logger logger = LoggerFactory.getLogger(ExController.class);

	
	/**
	 * 异常处理
	 * @param ex
	 * @return
	 * @throws IOException
	 */
	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)  
    public Object allExceptionHandler(Exception ex) throws IOException {
        JSONObject exJson = new JSONObject();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        exJson.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
        exJson.put("errMsg", ex.getMessage());
        logger.error("exception handler,message is {}", ex.getMessage());
        return exJson;
    }
	
}
