package com.changhong.data.meeting.utils;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.alibaba.fastjson.JSONObject;

/**
 * xml工具
 * @author wangyang@broadengate.com
 * 2016年10月20日
 */
public class XmlUtil extends DefaultHandler {

	private String nodeName;
	private JSONObject res;

	// 只调用一次 初始化list集合
	@Override
	public void startDocument() throws SAXException {
		res = new JSONObject();
	}

	// 调用多次 开始解析
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		nodeName = qName;
	}

	// 调用多次
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		nodeName = null;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String value = new String(ch, start, length);
		if(nodeName!=null && !nodeName.equals("")){
		    res.put(nodeName, value);
		}
	}

	// 只调用一次
	@Override
	public void endDocument() throws SAXException {

	}

	public static JSONObject ParseToJson(String xml) throws Exception {
		SAXParserFactory parserFactory = SAXParserFactory.newInstance();
		try {
			SAXParser parser = parserFactory.newSAXParser();
			XmlUtil xmlutil = new XmlUtil();
			parser.parse(new InputSource(new StringReader(xml)), xmlutil);
			return xmlutil.res;
		} catch (ParserConfigurationException e) {
			throw new Exception("xml解析出錯",e);
		} catch (SAXException e) {
			throw new Exception("xml解析出錯",e);
		} catch (IOException e) {
			throw new Exception("xml解析出錯",e);
		}
	}

}
