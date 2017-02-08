package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

public class DoPost {
	/**
	 * 向指定url发送post请求
	 */
	public static String sendPost(String url,String param){
		String result = "";
		BufferedReader bf = null;
		PrintWriter pw = null;
		try {
			URL activeUrl = new URL(url);
			System.out.println("实际url为："+activeUrl);
			URLConnection connection = activeUrl.openConnection();
			
			connection.setRequestProperty("Accept", "*/*");
			connection.setRequestProperty("Connection", "keep-alive");
			connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.130 Safari/537.36");
			
			connection.setDoInput(true);
			connection.setDoOutput(true);
			
			pw = new PrintWriter(connection.getOutputStream());
			pw.print(param);
			pw.flush();
			
			bf = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			
			while((line=bf.readLine()) != null){
				result += line;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(pw != null){
				pw.close();
			}
			if(bf != null){
				try {
					bf.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}

}
