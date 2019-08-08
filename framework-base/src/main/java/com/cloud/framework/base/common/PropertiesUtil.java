package com.cloud.framework.base.common;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.MessageFormat;
import java.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class PropertiesUtil {
	protected static HttpServletRequest request;
	protected HttpServletResponse response;

	// 物理地址
	public static String absoluteValue(String filePath, String key) throws IOException {
		Logger log = Logger.getLogger(PropertiesUtil.class);
		Properties props = new Properties();
		String value = "";
		InputStream ips = null;
		try {
			ips = new BufferedInputStream(new FileInputStream(filePath));
			props.load(ips);
			value = props.getProperty(key);
		} catch (IOException e) {
			log.error(e);
		} finally {
			ips.close();
		}
		return value;
	}

	/**
	 * 获取message信息
	 * 
	 * @param filePath
	 * @param paraKey
	 * @param paraValue
	 * @return
	 * @author caroline
	 */
	public static String messageProperties(String filePath, String paraKey) {
		Logger log = Logger.getLogger(PropertiesUtil.class);
		Properties props = new Properties();
		InputStream in = null;
		String value = "";
		try {
			in = PropertiesUtil.class.getResourceAsStream(filePath);
			props.load(in);
			value = props.getProperty(paraKey);
		} catch (IOException e) {
			log.error(e);
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				log.error(e);
				e.printStackTrace();
			}
		}
		return value;
	}

	/**
	 * 获取message信息
	 * 
	 * @param filePath
	 * @param paraKey
	 * @param paraValue
	 * @return
	 * @author caroline
	 */
	public static String messageProperties(String filePath, String paraKey, Object[] paraValue) {
		Logger log = Logger.getLogger(PropertiesUtil.class);
		Properties props = new Properties();
		InputStream in = null;
		String value = "";
		try {
			in = PropertiesUtil.class.getResourceAsStream(filePath);
			props.load(in);
			value = props.getProperty(paraKey);
			value = MessageFormat.format(value, paraValue);
		} catch (IOException e) {
			log.error(e);
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				log.error(e);
				e.printStackTrace();
			}
		}
		return value;
	}

	// 相对地址
	public static String relativeValue(String filePath, String key) throws IOException {
		Logger log = Logger.getLogger(PropertiesUtil.class);
		Properties props = new Properties();
		InputStream in = null;
		String value = "";
		try {
           
			String path = PropertiesUtil.class.getClassLoader().getResource("").getPath();
			log.error("path "+path);
			path = path.substring(0, path.indexOf("class"));
			log.error("path "+path);
			in = new FileInputStream(path + filePath);
			props.load(in);
			log.error("props "+props);
			/*
			 * in = PropertiesUtil.class.getResourceAsStream(filePath);
			 * props.load(in);
			 */
			value = props.getProperty(key);
			log.error("value "+value);
		} catch (IOException e) {
			log.error(e);
		} finally {
			if (in != null) {
				in.close();
			}
		}
		return value.trim();
	}
	// 读取全部信息物理路径

	public static Map absoluteAllValue(String filePath) {
		Logger log = Logger.getLogger(PropertiesUtil.class);
		Properties props = new Properties();
		Map map = new HashMap();
		String value = "";
		InputStream ips = null;
		try {
			ips = new BufferedInputStream(new FileInputStream(filePath));
			props.load(ips);
			Enumeration enums = props.propertyNames();
			while (enums.hasMoreElements()) {
				String key = (String) enums.nextElement();
				value = props.getProperty(key);
				map.put(key, value);
			}
		} catch (FileNotFoundException e) {
			log.error(e);
		} catch (IOException e) {
			log.error(e);
		} finally {
			try {
				ips.close();
			} catch (IOException e) {
				log.error(e);
			}
		}
		return map;
	}

	// 读取全部信息相对路径
	public static Map relativeAllValue(String filePath) {
		Logger log = Logger.getLogger(PropertiesUtil.class);
		Properties props = new Properties();
		Map map = new HashMap();
		String value = "";
		InputStream in = null;
		try {
			in = PropertiesUtil.class.getResourceAsStream(filePath);
			props.load(in);
			Enumeration enums = props.propertyNames();
			while (enums.hasMoreElements()) {
				String key = (String) enums.nextElement();
				value = props.getProperty(key);
				map.put(key, value);
			}
		} catch (FileNotFoundException e) {
			log.error(e);
		} catch (IOException e) {
			log.error(e);
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				log.error(e);
			}
		}
		return map;
	}

	public static void writeProperties(String filePath, String paraKey, String paraValue) {
		Logger log = Logger.getLogger(PropertiesUtil.class);
		Properties props = new Properties();
		OutputStream ops = null;
		try {
			ops = new FileOutputStream(filePath);
			props.setProperty(paraKey, paraValue);
			props.store(ops, "set");
		} catch (IOException e) {
			log.error(e);
		} finally {
			try {
				ops.close();
			} catch (IOException e) {
				log.error(e);
			}
		}
	}

	// 读取ip地址
	public static String relativeFileURL(String filePath) {
		Logger log = Logger.getLogger(PropertiesUtil.class);
		String fileURL = ""; // 文件应用的URL
		String fileServerIP = ""; // 文件服务器的IP
		String fileServerPort = ""; // 文件服务器的服务端口
		String fileContextPath = ""; // 文件应用的ContextPath

		Properties prop = new Properties();
		InputStream in = null;
		try {
			in = PropertiesUtil.class.getResourceAsStream(filePath);
			prop.load(in);
			Set keyValue = prop.keySet();
			for (Iterator it = keyValue.iterator(); it.hasNext();) {
				String key = (String) it.next();
				if (key.equals("file.server.ip")) {
					fileServerIP = (String) prop.getProperty(key);
				} else if (key.equals("file.server.port")) {
					fileServerPort = (String) prop.getProperty(key);
				} else if (key.equals("file.contextPath")) {
					fileContextPath = (String) prop.getProperty(key);
				}
			}
		} catch (Exception e) {
			log.error("IO读取出错，找不到" + filePath + "!");
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				log.error(e);
			}
		}

		if (fileServerIP.trim().equals("")) {
			log.error("请检查配置文件" + filePath + "中的file.server.ip项的值是否正确!");
		}
		if (fileServerPort.trim().equals("")) {
			log.error("请检查配置文件" + filePath + "中的file.server.port项的值是否正确!");
		}
		if (fileContextPath.trim().equals("")) {
			log.error("请检查配置文件" + filePath + "中的file.ContextPath项的值是否正确!");
		}

		fileURL = "http://" + fileServerIP.trim() + ":" + fileServerPort.trim() + fileContextPath.trim();
		log.info("获取的fileURL=" + fileURL);
		return fileURL;
	}

}