package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import connectDB.ConnectDatabase;
import entity.ChiTietHoaDon;
import entity.Sach;

public class ChiTietHoaDonDAO {
	ArrayList<ChiTietHoaDon> danhSachCTHD;
	ChiTietHoaDon cTHD;
	
	public ChiTietHoaDonDAO() {
		danhSachCTHD = new ArrayList<ChiTietHoaDon>();
		cTHD = new ChiTietHoaDon();
	}
	
	public ArrayList<ChiTietHoaDon> getChiTietHoaDonTheoMaHD(String maHD) {
		ArrayList<ChiTietHoaDon> danhSachChiTietHoaDon = new ArrayList<ChiTietHoaDon>();
		ConnectDatabase.getInstance();
		Connection connection = ConnectDatabase.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "Select * from ChiTietHoaDon where maHD=?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, maHD);
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				String maHoaDon = result.getString(1);
				String maSP = result.getString(2);
				int soLuong = result.getInt(3);
				double donGiaBan = result.getDouble(4);
				double thanhTien = result.getDouble(5);
				ChiTietHoaDon cthd = new ChiTietHoaDon(maHD, maSP, soLuong, donGiaBan, thanhTien);
				danhSachChiTietHoaDon.add(cthd);
			}
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return danhSachChiTietHoaDon;
	}
}
