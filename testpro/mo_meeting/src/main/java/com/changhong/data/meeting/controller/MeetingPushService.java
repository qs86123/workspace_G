package com.changhong.data.meeting.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.MessageFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.changhong.data.meeting.service.wapi.WXTokenService;
import com.changhong.data.meeting.service.wapi.WapiMediaService;
import com.changhong.data.meeting.utils.HttpQueryUtil;
import com.changhong.data.meeting.utils.MeetingService;

import me.chanjar.weixin.mp.bean.WxMpMassNews;
import me.chanjar.weixin.mp.bean.WxMpMassNews.WxMpMassNewsArticle;

/**
 * @author wangtao
 *
 */
@Service
public class MeetingPushService {

	@Value("${wapi.push.message.openid}")
	private String pushUrl;

	@Value("${wapi.push.message.image}")
	private String pushImageUrl;

	@Value("${wapi.push.message.getsendid}")
	private String sendidUrl;

	@Value("${wapi.push.message.delsendmsg}")
	private String delsendmsgUrl;

	@Value("${wapi.appid}")
	private String appid;

	@Autowired
	private WXTokenService tkService;

	@Autowired
	private WapiMediaService apimediaService;

	/**
	 * 推送消息
	 * 
	 * @param openids
	 *            群发列表
	 * @param author
	 *            消息作者
	 * @param title
	 *            消息标题
	 * @param content
	 *            内容
	 * @param contentUrl
	 *            点击阅读全文之后的链接
	 * @param digest
	 *            图片介绍
	 * @return
	 */
	public Object pushMeetingInfo(String imgMediaId, List<String> openids, String author, String title, String content,
			String contentUrl, String digest) {
		JSONObject json = null;
//		imgMediaId = uploadMpnewsFile();// 测试时候指定图片直接上传
		try {
			String access_token = tkService.getAccessToken();
			String url = MessageFormat.format(pushUrl, access_token);
			JSONObject jsonStr = new JSONObject();
			jsonStr.put("touser", openids);
			JSONObject jmp = new JSONObject();
			json = getSendMediaId(imgMediaId, author, title, content, contentUrl, digest);
			if (!json.containsKey("media_id")) {
				json.put("error3", "没有获取到media_id");
				return json;
			}
			jmp.put("media_id", json.getString("media_id"));
			jsonStr.put("mpnews", jmp);
			jsonStr.put("msgtype", "mpnews");
			System.out.println(jsonStr.toJSONString());
			String response = HttpQueryUtil.postWithJSON(url, jsonStr.toJSONString());
			System.out.println("推送返回结果：" + response);
			return response;
		} catch (Exception e) {
			json.put("error3", "推送过程出错");
			e.printStackTrace();
			return json;
		}
	}

	public Object uploadMpnewsFile(MultipartFile file) {
		JSONObject json = null;
		try {
			if (file == null) {
				System.out.println("file is null");
				return "file is null";
			}
			InputStream in = file.getInputStream();
			// 上传临时素材
			json = apimediaService.pushMedia(in, "image", file.getOriginalFilename(), "title", "no", true, "image");
			System.out.println("上传推送图片返回结果:" + json.toJSONString());
		} catch (IOException e) {
			json = new JSONObject();
			json.put("error1", "图片上传IO流异常");
			e.printStackTrace();
		}
		return json;
	}

	public JSONObject getSendMediaId(String imgMediaId, String author, String title, String content, String contentUrl,
			String digest) {
		JSONObject json = null;
		try {
			String access_token = tkService.getAccessToken();
			String url = MessageFormat.format(sendidUrl, access_token);
			String contenturlPrex = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appid
					+ "&redirect_uri=";
			JSONObject jsonStr = new JSONObject();
			String str = "[{\"thumb_media_id\":\"" + imgMediaId + "\"," + "\"author\":\"" + author + "\","
					+ "\"title\":\"" + title + "\"," + "\"content\":\"" + content + "\"," + "\"content_source_url\":\""
					+ contenturlPrex + contentUrl + "\"," + "\"digest\":\"" + digest + "\"," + "}]";
			JSONArray jaArticle = JSONArray.parseArray(str);
			jsonStr.put("articles", jaArticle);
			System.out.println(jsonStr.toJSONString());
			String response = HttpQueryUtil.postWithJSON(url, jsonStr.toJSONString());
			json = JSONObject.parseObject(response);
			System.out.println("根据推送上传图片media_id获取推送素材的media_id返回结果：" + json.toJSONString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	// public JSONObject delmeetingMsg(long msgid) {
	// JSONObject result = new JSONObject();
	// try {
	// String access_token = tokenService.getAccessToken(appid, secret);
	// System.out.println("删除群发消息：accesstoken=" + access_token);
	// String url = MessageFormat.format(delsendmsgUrl, access_token);
	// JSONObject json = new JSONObject();
	// json.put("msg_id", msgid);
	// String respose = HttpQueryUtil.postWithJSON(url, json.toJSONString());
	// JSONObject jsonRes = JSONObject.parseObject(respose);
	// System.out.println(jsonRes.toJSONString());
	// if (jsonRes.getInteger("errcode") == 0) {
	// result.put("msg", "删除群发消息成功！");
	// // 如果删除成功，则从数据库删除该条记录
	// try {
	// miService.deleteByMsgId(msgid);
	// } catch (Exception e) {
	// result.put("msgdatabase", "数据库记录删除失败");
	// e.printStackTrace();
	// }
	// } else {
	// return jsonRes;
	// }
	// } catch (Exception e) {
	// result.put("msg", "删除失败");
	// e.printStackTrace();
	// }
	// return result;
	// }

	// 上传图片并获得url
	public Object getuploadImgUrl() {
		try {
			String access_token = tkService.getAccessToken();
			System.out.println(access_token);
			String url = MessageFormat.format(pushImageUrl, access_token);
			File f = new File("C:/Users/The_kid/Desktop/test.png");
			// 这里使用群发上传图片的url上传图片后获得url，填写在article的content-url上
			// String response = MeetingService.uploadFile(url,new
			// FileInputStream(f) , "fileName");
			// String response = HttpQueryUtil.uploadFile(url, f);
			// apimediaService.pushMedia(, mime, fileName, title, introduction,
			// temp, type)
			JSONObject json = MeetingService.pushMedia(new FileInputStream(f), url, access_token, f.getName());
			// meetingInfo.setImgUrl(json.getString("url"));
			return json;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error";
	}

	//////////////////////// 测试直接上传图片/////////////////////////////
	public String uploadMpnewsFile() {
		URL url = ClassLoader.getSystemResource("webpage/center.png");
		File file = new File(url.getPath());
		try {
			InputStream in = new FileInputStream(file);
			// 上传临时素材
			JSONObject json = apimediaService.pushMedia(in, "image", file.getName(), "title", "no", true, "image");
			System.out.println("上传推送图片返回结果:" + json.toJSONString());
			if (json.containsKey("media_id"))
				return json.getString("media_id");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	////////////////////////////////////////////////////////////////////////////
	public Object pushMeetingInfo2(List<String> openids, String author, String title, String content, String contentUrl,
			String digest) {
		JSONObject result = new JSONObject();
		File file = new File("D:\\test\\test.jpg");
		String fileName = file.getName();
		try {
			String mediaId = tkService.uploadMedia(fileName, "image", new FileInputStream(file));
			WxMpMassNews news = new WxMpMassNews();
			WxMpMassNewsArticle article = new WxMpMassNewsArticle();
			article.setAuthor(author);
			article.setContent(content);
			article.setContentSourceUrl(contentUrl);
			article.setTitle(title);
			article.setDigest(digest);
			article.setThumbMediaId(mediaId);
			news.addArticle(article);
			String sendMediaId = tkService.uploadNews(news);
			String response = tkService.pushMassNews(openids, "mpnews", null, sendMediaId);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			result.put("error", "内部错误");
			return result;
		}
	}

	public String uploadMpnewsFile2(MultipartFile file) {
		String fileName = file.getOriginalFilename();
		InputStream in = null;
		String result = "";
		try {
			in = file.getInputStream();
			result = tkService.uploadMedia(fileName, "image", in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public String testuploadfile(String fileName, InputStream in) {
		String result = "";
		result = tkService.uploadMedia(fileName, "image", in);
		return result;
	}

}
