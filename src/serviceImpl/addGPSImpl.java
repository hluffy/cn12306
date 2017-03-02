package serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import mysqlUtil.MysqlUtil;
import service.addGPS;
import entity.MapWatchData;

public class addGPSImpl implements addGPS{

	@Override
	public void addGps(MapWatchData data) {
		// TODO Auto-generated method stub
		Connection conn = MysqlUtil.getConnection();
		PreparedStatement ps = null;
		String sql = "insert into map_watch_data(id,IMEI,Xloc,Yloc,Bat,create_date,is_position,type,address) values(?,?,?,?,?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, data.getId());
			ps.setLong(2, data.getImei());
			ps.setString(3, data.getLongitudeName());
			ps.setString(4, data.getLatitudeName());
			ps.setInt(5, data.getElec());
			ps.setTimestamp(6, data.getDateCreated());
			ps.setString(7, data.getIsPosition());
			ps.setString(8, data.getType());
			ps.setString(9, data.getAddress());
			
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
				}
			}
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
