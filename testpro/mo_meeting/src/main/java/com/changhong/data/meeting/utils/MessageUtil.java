package com.changhong.data.meeting.utils;

import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.alibaba.fastjson.JSONObject;
import com.changhong.data.meeting.domain.repsponse.BaseMessage;
import com.changhong.data.meeting.domain.repsponse.TextMessage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;


/**
 * @author wangtao
 *
 */
public class MessageUtil {
	// 请求消息类型：文本
	public static final String REQ_MESSAGE_TYPE_TEXT = "text";

	// 响应消息类型：文本
	public static final String RESP_MESSAGE_TYPE_TEXT = "text";

	// 消息匹配
	public static final Map<String, Object> messageMatcher = new HashMap<String, Object>() {
		private static final long serialVersionUID = 3961172391467157933L;

		{
			put(REQ_MESSAGE_TYPE_TEXT, com.changhong.data.meeting.domain.repsponse.TextMessage.class);

		}
	};

	/**
	 * 解析微信发来的请求（XML）
	 *
	 * @param xmlStr
	 * @return Map<String, String>
	 */
	public static Map<String, String> parseXml(String xmlStr) throws Exception {
		Map<String, String> map = new HashMap<String, String>();

		Document document = null;
		try {
			document = DocumentHelper.parseText(xmlStr);
		} catch (DocumentException e) {
			throw new Exception(e.getMessage());
		}
		// 得到XML根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		@SuppressWarnings("unchecked")
		List<Element> elementList = root.elements();

		// 遍历所有子节点
		for (Element e : elementList) {
			map.put(e.getName(), e.getText());
		}

		return map;
	}

	/**
	 * 扩展xstream使其支持CDATA
	 */
	private static XStream xStream = new XStream(new XppDriver() {
		@SuppressWarnings("unused")
		public HierarchicalStreamWriter creatwWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				// 对所有XML节点的转换都增加CDATA标记
				boolean cdata = true;

				public void startNode(String name, @SuppressWarnings("rawtypes") Class clazz) {
					super.startNode(name, clazz);
				}

				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});

	/**
	 * 文本消息对象转换成XML
	 *
	 * @param textMessage
	 *            文本消息对象
	 * @return xml
	 */
	public static String messageToXml(TextMessage textMessage) {
		xStream.alias("xml", textMessage.getClass());
		return xStream.toXML(textMessage);
	}

	/**
	 * 微信消息json转为对象
	 *
	 * @param json
	 * @return
	 */
	public static BaseMessage jsonToObject(JSONObject json) {
		Class clazz = (Class) messageMatcher.get(json.getString("MsgType"));
		return (BaseMessage) JSONObject.toJavaObject(json, clazz);
	}
}
