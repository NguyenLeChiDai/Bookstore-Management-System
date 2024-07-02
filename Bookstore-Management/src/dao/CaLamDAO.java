package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDatabase;
import entity.CaLamViec;

public class CaLamDAO {
	
	public ArrayList<CaLamViec> getAllCaLam() {
		ArrayList<CaLamViec> danhSachCa = new ArrayList<CaLamViec>();
		try {
			ConnectDatabase.getInstance();
			Connection connection = ConnectDatabase.getConnection();
			String sql = "Select * from CaLamViec";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				String maCaLamViec = result.getString(1);
				String tenCaLamViec = result.getString(2);
				CaLamViec caLam = new CaLamViec(maCaLamViec, tenCaLamViec);
				danhSachCa.add(caLam);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return danhSachCa;
	}
	
	public CaLamViec getCaLamTheoMa(String maCa) {
		CaLamViec caLam = null;
		ConnectDatabase.getInstance();
		Connection connection = ConnectDatabase.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "Select * from caLamViec where maCaLamViec=?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, maCa);
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				String maCaLamViec = result.getString(1);
				String tenCaLamViec = result.getString(2);
				caLam = new CaLamViec(maCaLamViec, tenCaLamViec);
			}
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return caLam;
	}


}