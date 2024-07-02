package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDatabase;
import entity.LoaiSach;
import entity.Sach;

public class LoaiSachDAO {
	public ArrayList<LoaiSach> getAllLoaiSach() {
		ArrayList<LoaiSach> danhSachLoaiSach = new ArrayList<LoaiSach>();
		try {
			ConnectDatabase.getInstance();
			Connection connection = ConnectDatabase.getConnection();
			String sql = "Select * from LoaiSach";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				String maLoai = result.getString(1);
				String tenLoai = result.getString(2);
				LoaiSach loaiSach = new LoaiSach(maLoai, tenLoai);
				danhSachLoaiSach.add(loaiSach);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return danhSachLoaiSach;
	}
	
	//create 
			public boolean create(LoaiSach loaiSach) {
				Connection connection = ConnectDatabase.getConnection();
				PreparedStatement statement = null;
				int n = 0;
				try {
					statement = connection.prepareStatement("Insert into LoaiSach values(?, ?)");
					statement.setString(1, loaiSach.getMaLoai());
					statement.setString(2, loaiSach.getTenLoai());
					n = statement.executeUpdate();
				}
				catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				return n > 0;
			}
			
			//update
			public static boolean update(LoaiSach loaiSach) {
				ConnectDatabase.getInstance();
				Connection connection = ConnectDatabase.getConnection();
				PreparedStatement statement = null;
				int n = 0;
				try {
					
					statement = connection.prepareStatement("Update LoaiSach set maLoai=?, tenLoai=? where maLoai=?");
					statement.setString(1, loaiSach.getMaLoai());
					statement.setString(2, loaiSach.getTenLoai());
					statement.setString(3, loaiSach.getMaLoai());
					n = statement.executeUpdate();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
				return n > 0;
			}
			
			//delete 
			public boolean delete(String maLoai) {
				Connection connection = ConnectDatabase.getInstance().getConnection();
				PreparedStatement statement = null;
				int n = 0;
				try {
					statement = connection.prepareStatement("Delete from LoaiSach where maLoai=?");
					statement.setString(1, maLoai);
					n = statement.executeUpdate();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
				return n > 0;
			}
}
