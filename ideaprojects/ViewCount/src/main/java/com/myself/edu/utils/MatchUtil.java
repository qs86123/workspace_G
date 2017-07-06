package com.myself.edu.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.commons.lang3.StringUtils;

public enum MatchUtil {
	INS;
	MatchUtil(){}

	/**
	 * 判断字符串是否符合正则表达,匹配则返回true,其他返回false
	 * @param string 被检查字符串
	 * @param patternStr 正则表达式
	 * @return
	 */
	public boolean contain(String string, String patternStr){
		try {
			Pattern regex = Pattern.compile(patternStr);
			Matcher regexMatcher = regex.matcher(string);
			if (regexMatcher.matches()) {
				return true;
			}
		} catch (PatternSyntaxException ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 判断字符串是否符合正则表达,匹配则返回true,其他返回false
	 * @param string 被检查字符串
	 * @param patternStr 正则表达式
	 * @return
	 */
	public boolean has(String string, String patternStr){
		try {
			Pattern regex = Pattern.compile(patternStr);
			Matcher regexMatcher = regex.matcher(string);
			if (regexMatcher.find()) {
				return true;
			}
		} catch (PatternSyntaxException ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 在字符串中查找满足正则表达式的内容,找到则返回所有的,否则返回传入默认字符串.
	 * @param string 被检查字符串
	 * @param patternStr 正则表达式
	 * @param defaultStr 默认字符串
	 * @return
	 */
	public String find(String string, String patternStr, String defaultStr){
		if (StringUtils.isBlank(string)) {
			return defaultStr;
		}
		try {
			Pattern regex = Pattern.compile(patternStr);
			Matcher regexMatcher = regex.matcher(string);
			if (regexMatcher.find()) {
				return regexMatcher.group();
			}
		} catch (PatternSyntaxException ex) {
			ex.printStackTrace();
		}
		return defaultStr;
	}
	
	/**
	 * 在字符串中查找满足正则表达式的内容,找到则返回所有的,否则返回传入默认字符串.
	 * @param string 被检查字符串
	 * @param patternStr 正则表达式
	 * @param defaultStr 默认字符串
	 * @return
	 */
	public String find(String string, String patternStr, String defaultStr, int groupNum){
		if (StringUtils.isBlank(string)) {
			return defaultStr;
		}
		try {
			Pattern regex = Pattern.compile(patternStr);
			Matcher regexMatcher = regex.matcher(string);
			if (regexMatcher.find()) {
				return regexMatcher.group(groupNum);
			}
		} catch (PatternSyntaxException ex) {
			ex.printStackTrace();
		}
		return defaultStr;
	}
	
	/**
	 * 在字符串中查找满足正则表达式的内容,找到则返回所有的,否则返回传入默认字符串.
	 * @param string 被检查字符串
	 * @param patternStr 正则表达式
	 * @param defaultStr 默认字符串
	 * @return
	 */
	public String find(String string, Pattern regex, String defaultStr, int groupNum){
		if (StringUtils.isBlank(string)) {
			return defaultStr;
		}
		try {
			Matcher regexMatcher = regex.matcher(string);
			if (regexMatcher.find()) {
				return regexMatcher.group(groupNum);
			}
		} catch (PatternSyntaxException ex) {
			ex.printStackTrace();
		}
		return defaultStr;
	}

	/**
	 * 在字符串中查找满足正则表达式的内容,找到则返回所有的,否则返回传入默认字符串.
	 * @param string 被检查字符串
	 * @param patternStr 正则表达式
	 * @param defaultStr 默认字符串
	 * @param contactStr 连接字符串
	 * @return
	 */
	public String findAll(String string, String patternStr, String defaultStr, String contactStr){
		if (StringUtils.isBlank(string)) {
			return defaultStr;
		}
		if(contactStr == null){
			contactStr = " ";//默认是空格连接
		}
		try {
			StringBuffer resultStr = new StringBuffer();
			Pattern regex = Pattern.compile(patternStr);
			Matcher regexMatcher = regex.matcher(string);
			//if (regexMatcher.find()) {
			int index = 0;
			while (regexMatcher.find()) {
				resultStr.append(index==0?regexMatcher.group():contactStr.concat(regexMatcher.group()));
				index ++;
			}
			return resultStr.toString();
			//}
		} catch (PatternSyntaxException ex) {
			ex.printStackTrace();
		}
		return defaultStr;
	}
	
	/**
	 * 在字符串中查找满足正则表达式的内容,找到则返回所有的,否则返回传入默认字符串.
	 * @param string 被检查字符串
	 * @param patternStr 正则表达式
	 * @param defaultStr 默认字符串
	 * @param contactStr 连接字符串
	 * @return
	 */
	public int findAllCount(String string, String patternStr){
		if (StringUtils.isBlank(string)) {
			return 0;
		}
		int index = 0;
		try {
			Pattern regex = Pattern.compile(patternStr);
			Matcher regexMatcher = regex.matcher(string);
			//if (regexMatcher.find()) {
			while (regexMatcher.find()) {
				index ++;
			}
			//}
		} catch (PatternSyntaxException ex) {
			ex.printStackTrace();
		}
		return index;
	}

	/**
	 * 在字符串中查找正则表达式匹配的子串，并返回List
	 * @param string 被检查字符串
	 * @param patternStr 正则表达式
	 * @return
	 */
	public List<String> findAll(String string, String patternStr){
		if (StringUtils.isBlank(string)) {
			return new ArrayList<String>();
		}
		try {
			List<String> resultList = new ArrayList<String>();
			Pattern regex = Pattern.compile(patternStr);
			Matcher regexMatcher = regex.matcher(string);
			//if (regexMatcher.find()) {
			while (regexMatcher.find()) {
				resultList.add(regexMatcher.group());
			}
			return resultList;
			//}
		} catch (PatternSyntaxException ex) {
			ex.printStackTrace();
		}
		return new ArrayList<String>();
	}
}

