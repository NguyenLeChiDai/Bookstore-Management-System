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
import entity.DatSach;
import entity.DatSanPham;

public class DatSanPhamDAO {
	static ArrayList<DatSanPham> danhSachDatSanPham;
    DatSanPham datSanPham;
	
	
	public DatSanPhamDAO() {
		danhSachDatSanPham = new ArrayList<DatSanPham>();
		datSanPham = new DatSanPham(null);
	
	}
	
	public static ArrayList<DatSanPham> getAllDatSanPham() {
		try {
			Connection connection = ConnectDatabase.getInstance().getConnection();
			String sqlDatSanPham = "Select * from DatSanPham";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sqlDatSanPham);
			while (result.next())
			{
				String maSach = result.getString(1);
				String tenSP = result.getString(2);
				int soLuong = result.getInt(3);
				double donGiaBan = result.getDouble(4);
				double thanhTien = result.getDouble(5);

			
				DatSanPham datSanPham = new DatSanPham(maSach, tenSP, soLuong, donGiaBan, thanhTien);
				danhSachDatSanPham.add(datSanPham);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return danhSachDatSanPham;
	}
		
		public boolean create(DatSanPham datSanPham) {
			Connection connection = ConnectDatabase.getConnection();
			PreparedStatement statement = null;
			int n = 0;
			try {
				statement = connection.prepareStatement("Insert into DatSanPham values(?, ?, ?, ?, ?)");
				statement.setString(1, datSanPham.getMaSP());
				statement.setString(2, datSanPham.getTenSP());
				statement.setInt(3, datSanPham.getSoLuong());
				statement.setDouble(4, datSanPham.getDonGiaBan());
				statement.setDouble(5, datSanPham.getThanhTien());
				n = statement.executeUpdate();
			}
			catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return n > 0;
		}
		
		public  boolean delete(String maSanPham) {
			Connection connection = ConnectDatabase.getInstance().getConnection();
			PreparedStatement statement = null;
			int n = 0;
			try {
				statement = connection.prepareStatement("Delete from DatSanPham where maSanPham=?");
				statement.setString(1, maSanPham);
				n = statement.executeUpdate();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			return n > 0;
		}
		
		
		
	}