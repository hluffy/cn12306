package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import net.sf.json.JSONObject;


/**
 * 向指定url发送https请求
 */
public class HttpsGet {
	private static class TrustAnyTrustManager implements X509TrustManager{

		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			// TODO Auto-generated method stub
			return new X509Certificate[]{};
		}
		
	}
	
	private static class TrustAnyHostnameVerifier implements HostnameVerifier{

		@Override
		public boolean verify(String hostname, SSLSession session) {
			// TODO Auto-generated method stub
			return true;
		}
		
	}
	
	public static String sendHttpsGet(String url,String param){
		String result = "";
		String activeUrl = url + "?" + param;
		System.out.println("实际url为："+activeUrl);
		BufferedReader bf = null;
		try {
			SSLContext context = SSLContext.getInstance("SSL");
			context.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom());
			
			
			URL useUrl = new URL(activeUrl);
			URLConnection urlConnection = useUrl.openConnection();
			HttpsURLConnection connection = (HttpsURLConnection)urlConnection;
			
			//设置https相关属性
			connection.setSSLSocketFactory(context.getSocketFactory());
			connection.setHostnameVerifier(new TrustAnyHostnameVerifier());
			connection.setDoOutput(true);
			
			connection.setRequestProperty("Accept", "*/*");
//			connection.setRequestProperty("Accept-Encoding", "gzip, deflate, sdch");
//			connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
			connection.setRequestProperty("Connection", "keep-alive");
			connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.130 Safari/537.36");
			
			connection.connect();
			
			Map<String,List<String>> map = connection.getHeaderFields();
			for(String key:map.keySet()){
				System.out.println(key+"----"+map.get(key));
			}
			
			bf = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
			String line;
			while((line=bf.readLine()) != null){
				result += line;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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
	
	public static void main(String[] args) {
		String url = "https://kyfw.12306.cn/otn/leftTicket/queryZ";
		String param = "leftTicketDTO.train_date=2017-02-27&leftTicketDTO.from_station=SHH&leftTicketDTO.to_station=XAY&purpose_codes=ADULT";
		String result = sendHttpsGet(url, param);
		JSONObject json = JSONObject.fromObject(result);
		System.out.println(json.get("data"));
//		System.out.println(result);
	}

}
