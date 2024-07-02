package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDatabase;
import entity.PhanQuyen;
import entity.TaiKhoan;

public class TaiKhoanDAO {
	
	ArrayList<TaiKhoan> danhSachTaiKhoan;
	TaiKhoan taiKhoan;
	
	public TaiKhoanDAO() {
		danhSachTaiKhoan = new ArrayList<TaiKhoan>();
		taiKhoan = new TaiKhoan();
	}
	
	public ArrayList<TaiKhoan> getAllTaiKhoan() {
		try {
			Connection connection = ConnectDatabase.getInstance().getConnection();
			String sql = "Select * from TaiKhoan";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next())
			{
				String maTK = result.getString(1);
				String tenTK = result.getString(2);
				String matKhau = result.getString(3);
				PhanQuyen phanQuyen = new PhanQuyen(result.getString(4));
				TaiKhoan taiKhoan = new TaiKhoan(maTK, tenTK, matKhau, phanQuyen);
				danhSachTaiKhoan.add(taiKhoan);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return danhSachTaiKhoan;
	}
	
	public boolean create(TaiKhoan taiKhoan) {
		Connection connection = ConnectDatabase.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = connection.prepareStatement("Insert into TaiKhoan values(?, ?, ?, ?)");
			statement.setString(1, taiKhoan.getMaTK());
			statement.setString(2, taiKhoan.getTenTK());
			statement.setString(3, taiKhoan.getMatKhau());
			statement.setString(4, taiKhoan.getPhanQuyen().getMaPhanQuyen());
			n = statement.executeUpdate();
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public static boolean update(TaiKhoan taiKhoan) {
		ConnectDatabase.getInstance();
		Connection connection = ConnectDatabase.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = connection.prepareStatement("Update TaiKhoan set maTK=?, tenTK=?, matKhau=?, maPhanQuyen=? where maTK=?");
			statement.setString(1, taiKhoan.getMaTK());
			statement.setString(2, taiKhoan.getTenTK());
			statement.setString(3, taiKhoan.getMatKhau());
			statement.setString(4, taiKhoan.getPhanQuyen().getMaPhanQuyen());
			n = statement.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean delete(String maTK) {
		Connection connection = ConnectDatabase.getInstance().getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = connection.prepareStatement("Delete from TaiKhoan where maTK=?");
			statement.setString(1, maTK);
			n = statement.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return n > 0;
	}
	
}
