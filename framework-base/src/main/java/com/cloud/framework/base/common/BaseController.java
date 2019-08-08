package com.cloud.framework.base.common;

import com.cloud.framework.base.utils.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * Package: com.dongdao.api.controller.common
 *
 * File: BaseController.java
 *
 * Author:
 *
 * Copyright @ 2015 Corpration Name
 * 
 */

public abstract class BaseController {
	public Log log = LogFactory.getLog(this.getClass().getSimpleName());
	 
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	protected Map<String, String> params;
	protected Model model;

	@SuppressWarnings("unchecked")
	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
		params = new HashMap<String, String>();
		this.request = request;
		this.response = response;
		this.session = request.getSession();
		Map<String, String[]> map = request.getParameterMap();
		Iterator<Entry<String, String[]>> it = map.entrySet().iterator();
		Entry<String, String[]> entry = null;
		String[] value = null;
		while (it.hasNext()) {
			entry = it.next();
			value = entry.getValue();
			if (value.length >= 1) {
				params.put(entry.getKey(), value[0]);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response, Model model) {
		params = new HashMap<String, String>();
		this.request = request;
		this.response = response;
		this.model = model;
		this.session = request.getSession();
		String base = request.getContextPath();
		String fullPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + base;
		String requestPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getRequestURI();
		request.setAttribute("base", base);
		request.setAttribute("fullPath", fullPath);
		request.setAttribute("requestPath", requestPath);
		Map<String, String[]> map = request.getParameterMap();
		Iterator<Entry<String, String[]>> it = map.entrySet().iterator();
		Entry<String, String[]> entry = null;
		String[] value = null;
		while (it.hasNext()) {
			entry = it.next();
			value = entry.getValue();
			if (value.length >= 1) {
				params.put(entry.getKey(), value[0]);
			}
		}
	}

	/**
	 * request取值
	 * 
	 * @param name
	 * @return
	 */
	public String getPara(String name) {
		return request.getParameter(name);
	}

	public String getPara(String name, String defaultValue) {
		String value = request.getParameter(name);
		return (value == null || "".equals(value)) ? defaultValue : value.trim();
	}

	/**
	 * 图片上传
	 * 
	 *
	 * @param folder
	 *            上传保存的文件的文件夹
	 * @return
	 */
	public String uploadFile(String folder) {
		String imgName = "";
		String imageNamePath = "";
		// 上传图片
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				// 取得上传文件
				MultipartFile file = multiRequest.getFile(iter.next());
				if (file != null) {
					// 取得当前上传文件的文件名称
					String myFileName = file.getOriginalFilename();
					// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
					if (BaseUtils.notNull(myFileName)) {
						// 获取图片的扩展名
						String extensionName = myFileName.substring(myFileName.lastIndexOf(".") + 1);

						if (!(extensionName.equals("jpg") || extensionName.equals("png") || extensionName.equals("bmp") || extensionName.equals("gif")
								|| extensionName.equals("zip"))) {
							return CommonString.FORMAT_ERROR;
						}

						// 重命名上传后的文件名
						String fileName = MD5.encryption(file.getOriginalFilename()+System.currentTimeMillis());
						// 定义上传路径
						File localFile;
						try {
							imageNamePath = PropertiesUtil.relativeValue("config/config.properties", "upload.path") + File.separator
									+ PropertiesUtil.relativeValue("config/config.properties", "upload.folder") + File.separator + folder;
							/*imageNamePath="d:\\zgyy" + File.separator
									+ "uploads" + File.separator + folder;*/
							localFile = new File(imageNamePath);
							// 判断文件夹
							if (!localFile.exists()) {// 如果目录不存在
								localFile.mkdirs();// 创建文件夹
							}
							localFile = new File(imageNamePath + File.separator + fileName + "." + extensionName);
							System.err.println(imageNamePath);
							file.transferTo(localFile);
						} catch (IOException e) {
							e.printStackTrace();
						}
						// 图片名字拼接
						imgName = folder + "/" + fileName + "." + extensionName;
					}
				}
			}
		}
		return imgName;
	}

	/***
	 * 保存文件
	 * @param file
	 * @return
	 */
	private String saveFile(MultipartFile file) {
		// 判断文件是否为空
		if (!file.isEmpty()) {
			try {
				// 文件保存路径
				String filePath = request.getSession().getServletContext().getRealPath("/") + "upload/" + file.getOriginalFilename();
				// 转存文件
				file.transferTo(new File(filePath));
				return filePath;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "";
	}

	/**
	 * 多文件上传
	 * @param files
	 * @return
	 */
	public StringBuffer filesUpload(MultipartFile[] files) {
		// 判断file数组不能为空并且长度大于0
		 StringBuffer mBuffer = new StringBuffer();
		if (files != null && files.length > 0) {
			// 循环获取file数组中得文件
			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				// 保存文件
				mBuffer.append(saveFile(file));
				System.err.println(i);
			}
			return mBuffer;
		}
		// 重定向
		return  null;
	}

	/**
	 * 单文件上传
	 * @param is 文件输入流
	 * @param fileName 文件名
	 * @param folder 文件仓库下的二级目录
	 * @return
	 */
	public String uploadFile(InputStream is, String fileName, String folder) {
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		BufferedInputStream bis = null;
		File file = null;
		String filePath = "";
		try {
			String path = PropertiesUtil.relativeValue("config/config.properties", "FTP.PATH");
		/*	String path = "D:\\youyang\\FTP_file";*/
			file = new File(path + File.separator + folder);
			if (!file.exists()) {
				file.mkdirs();
			}
			filePath = "/" + folder + "/" + fileName;
			File new_file = new File(path + filePath);
			bis = new BufferedInputStream(is);
			fos = new FileOutputStream(new_file);
			bos = new BufferedOutputStream(fos);
			byte[] bt = new byte[4096];
			int len;
			while ((len = bis.read(bt)) > 0) {
				bos.write(bt, 0, len);
			}
			// System.err.println("f:" + file.length());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != bos) {
					bos.close();
					bos = null;
				}
				if (null != fos) {
					fos.close();
					fos = null;
				}

				if (null != is) {
					is.close();
					is = null;
				}
				if (null != bis) {
					bis.close();
					bis = null;
				}

			} catch (Exception e) {
				e.printStackTrace();
				return "";
			}

		}
		return filePath;

	}
	

	/*********************uploadify上传方法***************************/
	public DataResult uploadify(CommonsMultipartFile file, String folder) throws IOException {
		// 文件原名
		String fileName = file.getOriginalFilename().toLowerCase();
		// 文件扩展名
		String fileType = fileName.substring(fileName.lastIndexOf(".")+1);
		// 验证后缀
		if (!VerifyFormat.verifyFormat(fileType)) {
			return BaseUtils.returnResult(false, "文件格式不正确");
		}
		String newname = DateUtils.getTodayString("yyyyMMddHHmmss") + "." + fileType;
		// 将文件保存到服务器
		String filePath = uploadFile(file.getInputStream(), newname, folder);

		if (!"".equals(filePath)) {
			return BaseUtils.returnResult(true, filePath);
		} else {
			return BaseUtils.returnResult(false, "上传失败");
		}

	}
	 public String getIpAddr(){  
	        String ipAddress = request.getHeader("x-forwarded-for");  
	            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
	                ipAddress = request.getHeader("Proxy-Client-IP");  
	            }  
	            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
	                ipAddress = request.getHeader("WL-Proxy-Client-IP");  
	            }  
	            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
	                ipAddress = request.getRemoteAddr();  
	                if(ipAddress!=null&&(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1"))){  
	                    //根据网卡取本机配置的IP  
	                    InetAddress inet=null;  
	                    try {  
	                        inet = InetAddress.getLocalHost();  
	                    } catch (UnknownHostException e) {  
	                        e.printStackTrace();  
	                    }  
	                    ipAddress= inet.getHostAddress();  
	                }  
	            }  
	            //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割  
	            if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15  
	                if(ipAddress.indexOf(",")>0){  
	                    ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));  
	                }  
	            }  
	            return ipAddress;   
	    }
	  /**
	     * 获取32位随机字符串
	     * @return
	     */
	    public static String getNonceStr(){
	        String str = "0123456789";
	        StringBuilder sb = new StringBuilder();
	        Random rd = new Random();
	        for(int i = 0 ; i < 6 ; i ++ ){
	            sb.append(str.charAt(rd.nextInt(str.length())));
	        }
	        return sb.toString();
	    }
	    
	    
	    
	    public static String getSubUtilSimple(String soap,String rgex){  
	        Pattern pattern = Pattern.compile(rgex);// 匹配的模式  
	        Matcher m = pattern.matcher(soap);  
	        while(m.find()){  
	            return m.group(1);  
	        }  
	        return "";  
	    }  
	    
	    /**
	     * 获取ip地址
	     * @param request
	     * @return
	     */
	    public static String getIpAddr(HttpServletRequest request) {  
	        InetAddress addr = null;  
	        try {  
	            addr = InetAddress.getLocalHost();  
	        } catch (UnknownHostException e) {  
	            return request.getRemoteAddr();  
	        }  
	        byte[] ipAddr = addr.getAddress();  
	        String ipAddrStr = "";  
	        for (int i = 0; i < ipAddr.length; i++) {  
	            if (i > 0) {  
	                ipAddrStr += ".";  
	            }  
	            ipAddrStr += ipAddr[i] & 0xFF;  
	        }  
	        return ipAddrStr;  
	    }
	   
	    
	    //获取访问来源（pc or phone）
	    public static boolean  isMobileDevice(String requestHeader){
	        /**
	         * android : 所有android设备
	         * mac os : iphone ipad
	         * windows phone:Nokia等windows系统的手机
	         */
	        String[] deviceArray = new String[]{"android","mac os","windows phone"};
	        if(requestHeader == null)
	            return false;
	        requestHeader = requestHeader.toLowerCase();
	        for(int i=0;i<deviceArray.length;i++){
	            if(requestHeader.indexOf(deviceArray[i])>0){
	                return true;
	            }
	        }
	        return false;
	}
		public static String getParamByUrl(String url, String name) {
		    url += "&";
		    String pattern = "(\\?|&){1}#{0,1}" + name + "=[a-zA-Z0-9]*(&{1})";
		    Pattern r = Pattern.compile(pattern);
		    Matcher m = r.matcher(url);
		    if (m.find( )) {
		        System.out.println(m.group(0));
		        return m.group(0).split("=")[1].replace("&", "");
		    } else {
		        return null;
		    }
		}
		
}





