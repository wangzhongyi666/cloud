package com.cloud.framework.base.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class BaseUtils {
	/**
	 * 生成返回对象
	 * 
	 * @return
	 */
	public static DataResult getDataResult() {
		return new DataResult();
	}

	/**
	 * 返回数组对象
	 * @param list
	 * @return
	 */
	public static DataResult returnResult(List list) {
		DataResult dataResult = getDataResult();
		
		dataResult.setRows(list);
		return dataResult;
	}
	public static DataResult returnResult(boolean flag,String remark) {
		DataResult dataResult = getDataResult();
		dataResult.setResult(flag);
		dataResult.setRemark(remark);
		return dataResult;
	}
	/**
	 * 判断返回结果
	 * 
	 * @param num
	 * @return
	 */
	public static DataResult returnResult(int num) {
		DataResult dataResult = getDataResult();
		if (num > 0) {
			dataResult.setResult(true);
		} else {
			dataResult.setResult(false);
		}
		return dataResult;
	}

	/**
	 * 分页返回结果
	 * 
	 * @return
	 */
	public static DataResult pageResult(List list, long total) {
		DataResult dataResult = BaseUtils.getDataResult();
		dataResult.setRows(list);
		dataResult.setTotal(total);
		return dataResult;
	}

	/**
	 * 验证String非空
	 * 
	 * @param string
	 * @return
	 */
	public static boolean notNull(String string) {
		if (string != null & !"".equals(string)) {
			return true;
		}
		return false;
	}
	
	
	
	 /**
     * 向指定 URL 发送POST方法的请求
     * @param url 发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //1.获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            //2.中文有乱码的需要将PrintWriter改为如下
            //out=new OutputStreamWriter(conn.getOutputStream(),"UTF-8")
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        System.out.println("post推送结果："+result);
        return result;
    }


}
