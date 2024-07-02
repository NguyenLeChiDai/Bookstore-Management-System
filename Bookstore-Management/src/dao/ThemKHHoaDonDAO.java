package dao;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import connectDB.ConnectDatabase;
import entity.CaLamViec;
import entity.DatHang;
import entity.DatSach;
import entity.LoaiSach;
import entity.NhaXuatBan;
import entity.NhanVien;
import entity.Sach;

	public class ThemKHHoaDonDAO {
		static ArrayList<DatHang> danhSachDatHang;
		DatHang datHang;
		
		public ThemKHHoaDonDAO() {
			danhSachDatHang = new ArrayList<DatHang>();
			datHang = new DatHang();
		
		}
		
		public static ArrayList<DatHang> getAllDatHang() {
			try {
				Connection connection = ConnectDatabase.getInstance().getConnection();
				String sqlDatHang = "Select * from DatHangKH";
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(sqlDatHang);
				while (result.next())
				{
					String maKH = result.getString(1);
					String tenKH = result.getString(2);
					//int soLuong = result.getInt(3);
					//double donGiaBan = result.getDouble(4);
					//double thanhTien = result.getDouble(5);

				
					DatHang datHang = new DatHang(maKH, tenKH);
					danhSachDatHang.add(datHang);
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			return danhSachDatHang;
		}
		
		public boolean create(DatHang datHang) {
			Connection connection = ConnectDatabase.getConnection();
			PreparedStatement statement = null;
			int n = 0;
			try {
				statement = connection.prepareStatement("Insert into DatHangKH values(?, ?, ?, ?, ?)");
				statement.setString(1, datHang.getMaKH());
				statement.setString(2, datHang.getMaKH());
				//statement.setInt(3, datHang.getSoLuong());
				//statement.setDouble(4, datHang.getDonGiaBan());
				//statement.setDouble(5, datHang.getThanhTien());
				n = statement.executeUpdate();
			}
			catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return n > 0;
		}
		
		public  boolean delete(String maHang) {
			Connection connection = ConnectDatabase.getInstance().getConnection();
			PreparedStatement statement = null;
			int n = 0;
			try {
				statement = connection.prepareStatement("Delete from DatSach where maSach=?");
				statement.setString(1, maHang);
				n = statement.executeUpdate();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			return n > 0;
		}
		
		
		
	}

