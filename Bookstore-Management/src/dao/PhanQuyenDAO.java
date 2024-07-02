package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDatabase;
import entity.PhanQuyen;

public class PhanQuyenDAO {
	
	public ArrayList<PhanQuyen> getAllPhanQuyen() {
		ArrayList<PhanQuyen> danhSachPhanQuyen = new ArrayList<PhanQuyen>();
		try {
			ConnectDatabase.getInstance();
			Connection connection = ConnectDatabase.getConnection();
			String sql = "Select * from PhanQuyen";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				String maPQ = result.getString(1);
				String tenPQ = result.getString(2);
				PhanQuyen phanQuyen = new PhanQuyen(maPQ, tenPQ);
				danhSachPhanQuyen.add(phanQuyen);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return danhSachPhanQuyen;
	}
}
