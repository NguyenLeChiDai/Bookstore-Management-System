package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import connectDB.ConnectDatabase;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;

public class HoaDonDAO {
	
	ArrayList<HoaDon> danhSachHoaDon;
	HoaDon hoaDon;
	
	public HoaDonDAO() {
		danhSachHoaDon = new ArrayList<HoaDon>();
		hoaDon = new HoaDon();
	}
	
	public ArrayList<HoaDon> getAllHoaDon() {
		try {
			Connection connection = ConnectDatabase.getInstance().getConnection();
			String sqlHoaDon = "Select * from HoaDon";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sqlHoaDon);
			while (result.next())
			{
				String maHoaDon = result.getString(1);
				String maKH = result.getString(2);
				KhachHang khachHang = new KhachHang(maKH);
				String maNV = result.getString(3);
				NhanVien nhanVien = new NhanVien(maNV);
				Date ngayLap = result.getDate(4);
				double tongTien = result.getDouble(5);
				HoaDon hoaDon = new HoaDon(maHoaDon, khachHang, nhanVien, ngayLap, tongTien);
				danhSachHoaDon.add(hoaDon);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return danhSachHoaDon;
	}
	
	public boolean delete(String maHD) {
		Connection connection = ConnectDatabase.getInstance().getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = connection.prepareStatement("Delete from ChiTietHoaDon where maHD=?");
			statement.setString(1, maHD);
			n = statement.executeUpdate();
			statement = connection.prepareStatement("Delete from HoaDon where maHD=?");
			statement.setString(1, maHD);
			n = statement.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return n > 0;
	}
}
