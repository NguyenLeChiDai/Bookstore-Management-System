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
import entity.DatSach;
import entity.LoaiSach;
	import entity.NhaXuatBan;
import entity.NhanVien;
import entity.Sach;

	public class DatSachDAO {
		static ArrayList<DatSach> danhSachDatSach;
		DatSach datSach;
		
		public DatSachDAO() {
			danhSachDatSach = new ArrayList<DatSach>();
			datSach = new DatSach();
		
		}
		
		public static ArrayList<DatSach> getAllDatSach() {
			try {
				Connection connection = ConnectDatabase.getInstance().getConnection();
				String sqlDatSach = "Select * from DatSach";
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(sqlDatSach);
				while (result.next())
				{
					String maSach = result.getString(1);
					String tenSP = result.getString(2);
					int soLuong = result.getInt(3);
					double donGiaBan = result.getDouble(4);
					double thanhTien = result.getDouble(5);

				
					DatSach datSach = new DatSach(maSach, tenSP, soLuong, donGiaBan, thanhTien);
					danhSachDatSach.add(datSach);
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			return danhSachDatSach;
		}
		
		public boolean create(DatSach datSach) {
			Connection connection = ConnectDatabase.getConnection();
			PreparedStatement statement = null;
			int n = 0;
			try {
				statement = connection.prepareStatement("Insert into DatSach values(?, ?, ?, ?, ?)");
				statement.setString(1, datSach.getMaSach());
				statement.setString(2, datSach.getTenSP());
				statement.setInt(3, datSach.getSoLuong());
				statement.setDouble(4, datSach.getDonGiaBan());
				statement.setDouble(5, datSach.getThanhTien());
				n = statement.executeUpdate();
			}
			catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return n > 0;
		}
		
		public  boolean delete(String maSach) {
			Connection connection = ConnectDatabase.getInstance().getConnection();
			PreparedStatement statement = null;
			int n = 0;
			try {
				statement = connection.prepareStatement("Delete from DatSach where maSach=?");
				statement.setString(1, maSach);
				n = statement.executeUpdate();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			return n > 0;
		}
		
		
		
	}
