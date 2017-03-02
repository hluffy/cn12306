package main;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import javax.net.ssl.TrustManager;

public class DoGet {
//	https://kyfw.12306.cn/otn/leftTicket/queryZ?leftTicketDTO.train_date=2017-02-18&leftTicketDTO.from_station=SHH&leftTicketDTO.to_station=XAY&purpose_codes=ADULT
//	leftTicketDTO.train_date:2017-02-18
//	leftTicketDTO.from_station:SHH
//	leftTicketDTO.to_station:XAY
//	purpose_codes:ADULT
	/**
	 * 向指定url发送get请求的方法
	 * @param args
	 */
	public static void main(String[] args) {
		String url = "http://www.baidu.com";
		String param = "";
		String result = sendGet(url,param);
		System.out.println(result);
	}
	
	public static String sendGet(String url,String param){
		String result = "";
		String activeUrl = url + "?" + param;
		String line;
		BufferedReader in = null;
		try {
			URL getUrl = new URL(activeUrl);
			URLConnection urlConnection = getUrl.openConnection();
			
			//设置请求参数
			urlConnection.setRequestProperty("Accept", "*/*");
			urlConnection.setRequestProperty("Accept-Encoding", "gzip, deflate, sdch");
			urlConnection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
			urlConnection.setRequestProperty("Connection", "keep-alive");
			urlConnection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.130 Safari/537.36");
			
			//实际连接
			urlConnection.connect();
			
			//获取所有响应头字段
			Map<String,List<String>> map = urlConnection.getHeaderFields();
			
			for(String key:map.keySet()){
				System.out.println(key+"-----"+map.get(key));
			}
			in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			while((line=in.readLine()) != null){
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}

}
