package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.addGPS;
import serviceImpl.addGPSImpl;
import entity.MapWatchData;

public class GPSList {
	public static void main(String[] args) throws UnsupportedEncodingException, ParseException {
		final String url = "http://101.37.34.43:6231/API/getGPSList.ashx";
//		String param = "";
//		int page = 1;
		
		
		

		
//		String result = getGPSList(url, param);
//		JSONObject json = JSONObject.fromObject(result);
//		System.out.println(json.get("content"));
//		JSONArray jsons = JSONArray.fromObject(json.get("content").toString());
//		System.out.println(jsons.size());
//		List<MapWatchData> datas = getMapWatchDatas(url, param);
//		System.out.println(datas.size());
//		for(MapWatchData data:datas){
////			System.out.println(data.getId());
//			System.out.println(data.getDateCreated());
//		}
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			String SDate = "20170227000000";
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				addGPS gpsService = new addGPSImpl();
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				String EDate = sdf.format(date);
				System.out.println("SDate:"+SDate+"-------EDate:"+EDate);
				StringBuffer param = new StringBuffer();
				param.append("&SDate="+SDate);
				param.append("&EDate="+EDate);
				int totalElements = getSum(url, param);
				System.out.println(totalElements);
				for(int page = 1;page<=(totalElements/1000)+1;page++){
					param = new StringBuffer();
					param.append("Page="+page);
					param.append("&SDate="+SDate);
					param.append("&EDate="+EDate);
					List<MapWatchData> datas;
					try {
						datas = getMapWatchDatas(url, param);
						for(MapWatchData data:datas){
//							System.out.println(data.getDateCreated());
							gpsService.addGps(data);
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				System.out.println("SDate:"+SDate+"-------EDate:"+EDate+"@@@@@@@@save successed!!");
				SDate = EDate;
			}
		}, 1000,1000*60*5);
		
		
		
//		while(true){
//			
//		}
	}
	
	public static int getSum(String url,StringBuffer param){
		int totalElements = 0;
		String result = getGPSList(url, param.toString());
		JSONObject json = JSONObject.fromObject(result);
		totalElements = json.getInt("totalElements");
		return totalElements;
	}
	
	public static List<MapWatchData> getMapWatchDatas(String url,StringBuffer param) throws ParseException{
		List<MapWatchData> datas = new ArrayList<MapWatchData>();
		String result = getGPSList(url, param.toString());
		JSONObject json = JSONObject.fromObject(result);
		JSONArray jsons = JSONArray.fromObject(json.get("content").toString());
		for(int i =0;i<jsons.size();i++){
//			System.out.println(JSONObject.fromObject(jsons.get(i)).get("LatitudeName"));
			JSONObject maps = JSONObject.fromObject(jsons.get(i));
			MapWatchData data = new MapWatchData();
			data.setLatitudeName(maps.getString("LatitudeName"));
			data.setLongitudeName(maps.getString("LongitudeName"));
//			data.setDateCreated(maps.getInt("DateCreated"));
			String date = maps.getString("DateCreated");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			Date d = sdf.parse(date);
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			data.setDateCreated(Timestamp.valueOf(maps.get("DateCreated").toString()));
			data.setDateCreated(Timestamp.valueOf(sdf.format(d)));
			data.setIsPosition(maps.getString("IsPosition"));
			data.setAddress(maps.getString("Address"));
			data.setId(maps.getInt("Id"));
			data.setType(maps.getString("type"));
			data.setElec(maps.getInt("Elec"));
			data.setImei(maps.getLong("Imei"));
			datas.add(data);
		}
		return datas;
	}

	public static String getGPSList(String url, String param) {
		StringBuffer result = new StringBuffer();
		String activeUrl = url + "?" + param;
		System.out.println(activeUrl);
		String line;
		BufferedReader in = null;
		try {
			URL getUrl = new URL(activeUrl);
			URLConnection urlConnection = getUrl.openConnection();

			// 设置请求参数
//			urlConnection.setRequestProperty("Accept", "*/*");
			urlConnection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			urlConnection.setRequestProperty("Accept-Encoding",
					"gzip, deflate, sdch");
			urlConnection.setRequestProperty("Accept-Language",
					"zh-CN,zh;q=0.8");
			urlConnection.setRequestProperty("Connection", "keep-alive");
			urlConnection
					.setRequestProperty(
							"User-Agent",
							"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.130 Safari/537.36");

			// 实际连接
			urlConnection.connect();

			// 获取所有响应头字段
			Map<String, List<String>> map = urlConnection.getHeaderFields();

//			for (String key : map.keySet()) {
//				System.out.println(key + "-----" + map.get(key));
//			}
			in = new BufferedReader(new InputStreamReader(
					urlConnection.getInputStream(),"utf-8"));
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return result.toString();
	}
}
