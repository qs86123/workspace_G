package com.changhong.data.meeting.service.wapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author iplus
 * @date 16/10/21
 * @e-mail iplus.wjy@gmail.com
 * @description
 */
@Service
public class WapiMediaService {

	@Value("${wapi.media_add_perm}")
	private String addPermMediaUrl;

	@Value("${wapi.media_upload}")
	private String uploadMediaUrl;

	@Autowired
	private WXTokenService tkService;

	/**
	 * 上传永久素材
	 *
	 * @param in
	 *            输入流
	 * @param mime
	 *            mime类型如 image/jpeg...
	 * @param fileName
	 *            文件名称
	 * @param title
	 *            标题
	 * @param introduction
	 *            描述
	 * @return 除了图片素材返回为media_id和url 其他素材都返回media
	 */
	public JSONObject pushMedia(InputStream in, String mime, String fileName, String title, String introduction,
			boolean temp, String type) {
		JSONObject jsonObject = new JSONObject();
		try {
			String accessToken = tkService.getAccessToken();
			// 这块是用来处理如果上传的类型是video的类型的
			JSONObject params = new JSONObject();
			String uri = null;
			if (temp) {
				uri = MessageFormat.format(uploadMediaUrl, accessToken, type);
			} else {
				uri = MessageFormat.format(addPermMediaUrl, accessToken, type);
			}
			URL url = new URL(uri);
			String result = null;
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(false); // post方式不能使用缓存
			// 设置请求头信息
			con.setRequestProperty("Connection", "Keep-Alive");
			con.setRequestProperty("Charset", "UTF-8");

			// 设置边界,这里的boundary是http协议里面的分割符
			String BOUNDARY = "----------" + System.currentTimeMillis();
			con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
			// 请求正文信息
			// 第一部分：

			StringBuilder sb = new StringBuilder();

			// 这块是post提交type的值也就是文件对应的mime类型值
			sb.append("--"); // 必须多两道线
			// 这里说明下，这两个横杠是http协议要求的，用来分隔提交的参数用的，参照http协议头
			sb.append(BOUNDARY);
			sb.append("\r\n");

			// 这块是上传video是必须的参数，可以在这里根据文件类型做if/else 判断
			if (mime.contains("video")) {
				params.put("title", title);
				params.put("introduction", introduction);
				sb.append("Content-Disposition: form-data;name=\"description\" \r\n\r\n");
				sb.append(params.toString() + "\r\n");
				sb.append("--"); // 必须多两道线
				sb.append(BOUNDARY);
				sb.append("\r\n");
			}
			// 这里是media参数相关的信息
			sb.append("Content-Disposition: form-data; name=\"media\"; filename=\"" + fileName + "\"\r\n"); // 这里是参数名，参数名和值之间要用两次
			sb.append("Content-Type: " + mime + "\r\n\r\n"); // 参数的值

			System.out.println(sb.toString());
			byte[] head = sb.toString().getBytes("utf-8");
			// 获得输出流
			OutputStream out = con.getOutputStream();
			// 输出表头
			out.write(head);
			// 文件正文部分
			// 把文件已流文件的方式 推入到url中
			int bytes = 0;
			byte[] bufferOut = new byte[1024];
			while ((bytes = in.read(bufferOut)) != -1) {
				out.write(bufferOut, 0, bytes);
			}
			in.close();
			// 结尾部分，这里结尾表示整体的参数的结尾，结尾要用"--"作为结束，这些都是http协议的规定
			byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
			out.write(foot);
			out.flush();
			out.close();

			InputStream inputStream = con.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			StringBuffer buffer = new StringBuffer();
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			con.disconnect();

			if (result == null) {
				result = buffer.toString();
			}

			jsonObject = JSONObject.parseObject(result);
			System.out.println(jsonObject);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return jsonObject;
	}

}
