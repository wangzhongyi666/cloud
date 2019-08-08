package com.cloud.framework.base.utils;

import java.util.regex.Pattern;

/**
 * 字符串工具类
 * 
 * @author Eric
 */
public class StringUtils {

	/**
	 * ""
	 */
	public static final String EMPTY = "";

	/**
	 * 字符串为null 或 空字符串-true 否则-false
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNullOrEmpty(String str) {

		return str == null || EMPTY.equals(str.trim()) ? true : false;
	}

	/**
	 * 字符串为null-true 否则-false
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str) {

		return str == null ? true : false;
	}

	/**
	 * 字符串去两边空格
	 * 
	 * @param str
	 * @return
	 */
	public static String trim(String str) {

		return isNull(str) ? str : str.trim();
	}

	/**
	 * 字符串大写
	 * 
	 * @param str
	 * @return
	 */
	public static String toUpperCase(String str) {

		return isNullOrEmpty(str) ? str : str.trim().toUpperCase();
	}

	/**
	 * 字符串小写
	 * 
	 * @param str
	 * @return
	 */
	public static String toLowerCase(String str) {

		return isNullOrEmpty(str) ? str : str.trim().toLowerCase();
	}

	/**
	 * 分割字符串
	 * 
	 * @param str
	 * @param regex
	 * @return
	 */
	public static String[] split(String str, String regex) {

		return isNull(str) ? null : str.split(regex);
	}

	/**
	 * Pascal命名法则：所有单词首字母大写
	 * 
	 * @param str
	 * @return
	 */
	public static String toPascal(String str) {

		if (isNullOrEmpty(str))
			return str;

		String s = str.substring(0, 1).toUpperCase();
		if (str.length() > 1) {
			s += str.substring(1);
		}
		return s;
	}

	/**
	 * camel命名法则：第一个单词首字母小写，其他单词首字母大写
	 * 
	 * @param str
	 * @return
	 */
	public static String toCamel(String str) {

		if (isNullOrEmpty(str))
			return str;
		String s = str.substring(0, 1).toLowerCase();
		if (str.length() > 1) {
			s += str.substring(1);
		}
		return s;
	}
	
	/**
	 * 判断是否为日期
	 * @param str 输入字符串
	 * @return 判断结果
	 */
	public static boolean isDate(String str){
		String regex = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
		return isMatch(regex, str);
	}
	
	/**
	 * 是否带有is
	 * @param str
	 * @return
	 */
	public static boolean isDecide(String str){
		String regex = "^is[A-Z]{1}[a-zA-Z0-9_]*$";
		return isMatch(regex, str);
	}
	
	/**
	 * 配置正则表达式
	 * @param regex 正则表达式
	 * @param str 字符串
	 * @return
	 */
	public static boolean isMatch(String regex, String str){
		if(isNullOrEmpty(regex) || isNullOrEmpty(str)) return false;
		return Pattern.compile(regex).matcher(str).matches();
	}
	
	/**
	 * null转""串
	 * @param str 需要转换的字符串
	 * @return 转换后的字符串
	 */
	public static String nullConvertNullString(String str) {
		return str == null ? "" : str;
	}
}
