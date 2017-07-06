package com.changhong.data.meeting.service.wapi;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.WxMpMassNews;
import me.chanjar.weixin.mp.bean.WxMpMassOpenIdsMessage;
import me.chanjar.weixin.mp.bean.result.WxMpMassSendResult;
import me.chanjar.weixin.mp.bean.result.WxMpMassUploadResult;

@Service
public class WXTokenService {

	@Autowired
	private WxMpServiceImpl wmService;

	public String getAccessToken() {
		String token = "";
		try {
			token = wmService.getAccessToken();
		} catch (WxErrorException e) {
			e.printStackTrace();
		}
		return token;
	}

	/**
	 * 上传图片素材获得media_id
	 * 
	 * @param fileName
	 * @param mediaType
	 * @param in
	 * @return
	 */
	public String uploadMedia(String fileName, String mediaType, InputStream in) {
		try {
			WxMediaUploadResult result = wmService.mediaUpload(mediaType,
					fileName.substring(fileName.lastIndexOf(".") + 1), in);
			return result.getMediaId();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (WxErrorException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 上传图文素材获得图文素材的media_id
	 * 
	 * @param news
	 * @return
	 */
	public String uploadNews(WxMpMassNews news) {
		try {
			WxMpMassUploadResult result = wmService.massNewsUpload(news);
			return result.getMediaId();
		} catch (WxErrorException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 根据图文消息素材群发消息 群发消息成功，但是点击进去后无图片
	 * 
	 * @param openids
	 * @param msgType
	 * @param content
	 * @param mediaId
	 * @return
	 */
	public String pushMassNews(List<String> openids, String msgType, String content, String mediaId) {
		WxMpMassOpenIdsMessage message = new WxMpMassOpenIdsMessage();
		for (String str : openids) {
			message.addUser(str);
		}
		message.setMsgType(msgType);
		message.setContent(content);
		message.setMediaId(mediaId);
		try {
			WxMpMassSendResult result = wmService.massOpenIdsMessageSend(message);
			return result.getErrorCode();
		} catch (WxErrorException e) {
			e.printStackTrace();
		}
		return "1";
	}

}
