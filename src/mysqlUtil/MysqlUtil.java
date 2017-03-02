package mysqlUtil;

import java.sql.Connection;
import java.sql.DriverManager;

public class MysqlUtil {
	public static Connection getConnection(){
		Connection connect = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8","root","hanxiao");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connect;
	}
	
	public static void main(String[] args) {
		Connection conn = getConnection();
		System.out.println(conn);
	}

}
