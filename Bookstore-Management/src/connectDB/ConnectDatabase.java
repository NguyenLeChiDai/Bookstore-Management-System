package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDatabase {
	public static Connection connection = null;
	public static ConnectDatabase instance = new ConnectDatabase();
	public static ConnectDatabase getInstance() {
		return instance;
	}
	
	public void connect() {
		String url = "jdbc:sqlserver://localhost:1433;databasename=QuanLyHieuSach";
		String user = "sa";
		String pwd = "sapassword";
		try {
			connection = DriverManager.getConnection(url, user, pwd);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void disconnect( ) {
		if (connection != null)
		{
			try {
				connection.close();
			}
			catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	public static Connection getConnection() {
		return connection;
	}
}