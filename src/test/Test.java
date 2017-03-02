package test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import service.addGPS;
import serviceImpl.addGPSImpl;
import entity.MapWatchData;

public class Test {
	public static void main(String[] args) throws ParseException {
//		addGPS gpsService = new addGPSImpl();
//		MapWatchData data = new MapWatchData();
//		data.setId(109385);
//		data.setLatitudeName(34.4369171051531);
//		data.setLongitudeName(108.758528613428);
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
//		Date d = sdf.parse("20170302102055");
//		sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		data.setDateCreated(Timestamp.valueOf(sdf.format(d)));
//		data.setIsPosition("V");
//		data.setAddress("陕西省咸阳市渭城区空港大道");
//		data.setType(null);
//		data.setElec(40);
//		data.setImei(Long.parseLong("356539040318617"));
//		gpsService.addGps(data);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		System.out.println(sdf.format(date));
	}

}
