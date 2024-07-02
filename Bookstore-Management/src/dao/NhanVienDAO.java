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
import entity.KhachHang;
import entity.LoaiSach;
import entity.NhaXuatBan;
import entity.NhanVien;
import entity.Sach;

public class NhanVienDAO {
	static ArrayList<NhanVien> danhSachNhanVien;
	NhanVien nhanvien;
	
	public NhanVienDAO() {
		danhSachNhanVien = new ArrayList<NhanVien>();
		nhanvien = new NhanVien(null);
	}
	public static ArrayList<NhanVien> getAllNhanVien() {
		try {
			Connection connection = ConnectDatabase.getInstance().getConnection();
			String sqlNhanVien = "Select * from NhanVien";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sqlNhanVien);
			while (result.next())
			{
				String maNV = result.getString(1);
				String hoTen = result.getString(2);
				String soDT = result.getString(3);
				String email = result.getString(4);
				String diaChi = result.getString(5);
				CaLamViec caLamViec = new CaLamViec(result.getString(6));

			
				NhanVien nhanVien = new NhanVien(maNV, hoTen, soDT, email, diaChi, caLamViec);
				danhSachNhanVien.add(nhanVien);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return danhSachNhanVien;
	}
	public boolean create(NhanVien nhanvien) {
		Connection connection = ConnectDatabase.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = connection.prepareStatement("Insert into NhanVien values(?, ?, ?, ?, ?, ?)");
			statement.setString(1, nhanvien.getMaNV());
			statement.setString(2, nhanvien.getTenNV());
			statement.setString(3, nhanvien.getSoDT());
			statement.setString(4, nhanvien.getEmail());
			statement.setString(5, nhanvien.getDiaChi());
			statement.setString(6, nhanvien.getCaLamViec().getMaCalamViec());
			n = statement.executeUpdate();
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public static boolean update(NhanVien nhanVien) {
		ConnectDatabase.getInstance();
		Connection connection = ConnectDatabase.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = connection.prepareStatement("Update NhanVien set maNV=?, hoTen=?, soDT=?, email=?, diaChi=?, caLamViec=? where maNV=?");
			statement.setString(1, nhanVien.getMaNV());
			statement.setString(2, nhanVien.getTenNV());
			statement.setString(3, nhanVien.getSoDT());
			statement.setString(4, nhanVien.getEmail());
			statement.setString(5, nhanVien.getDiaChi());
			statement.setString(6, nhanVien.getCaLamViec().getMaCalamViec());
			statement.setString(7, nhanVien.getMaNV());
			n = statement.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean delete(String maNV) {
		Connection connection = ConnectDatabase.getInstance().getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = connection.prepareStatement("Delete from NhanVien where maNV=?");
			statement.setString(1, maNV);
			n = statement.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return n > 0;
	}

	public static List <NhanVien> FindTenNhanVien(NhanVien nhanVien) {
        List <NhanVien> danhSachNV = new ArrayList<>();
        ConnectDatabase.getInstance();
        String sql = "Select *from NhanVien where NhanVien.hoTen = N'"+ nhanVien.getTenNV() +"'";
        try {
            Connection con = ConnectDatabase.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
            	String maNV = rs.getString(1);
            	String tenNV = rs.getString(2);
            	String soDT = rs.getString(3);
            	String email = rs.getString(4);
            	String diaChi = rs.getString(5);
            	String maCaLamViec = rs.getString(6);
            	CaLamViec caLV = new CaLamViec(maCaLamViec);
            	NhanVien nv = new NhanVien(maNV, tenNV, soDT, email, diaChi, caLV);
            	danhSachNV.add(nv);
            }
        } 
        catch (SQLException e) {
        	e.printStackTrace();
        }
        return danhSachNV;
      }
	
	public static List <NhanVien> FindSoDTNhanVien(NhanVien nv) {
        List<NhanVien> danhSachNV = new ArrayList<>();
        ConnectDatabase.getInstance();
        String sql = "Select *from NhanVien where NhanVien.soDT='"+ nv.getSoDT() +"'";
        try {
            Connection con = ConnectDatabase.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
            	String maNV = rs.getString(1);
              	String tenNV = rs.getString(2);
              	String soDT = rs.getString(3);
              	String email = rs.getString(4);
              	String diaChi = rs.getString(5);
              	String maCaLamViec = rs.getString(6);
              	CaLamViec caLamViec = new CaLamViec(maCaLamViec);
              	NhanVien nhanVien = new NhanVien(maNV, tenNV, soDT, email, diaChi, caLamViec);
                danhSachNV.add(nhanVien);
            }
        } 
        catch (SQLException e) {
        	e.printStackTrace();
        }
        return danhSachNV;
    }
	
	public static List <NhanVien> FindEmailNhanVien(NhanVien nv) {
        List<NhanVien> danhSachNV = new ArrayList<>();
        ConnectDatabase.getInstance();
        String sql = "Select * from NhanVien where NhanVien.email='"+ nv.getEmail() +"'";
        try {
            Connection con = ConnectDatabase.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
            	String maNV = rs.getString(1);
              	String tenNV = rs.getString(2);
              	String soDT = rs.getString(3);
              	String email = rs.getString(4);
              	String diaChi = rs.getString(5);
              	String maCaLamViec = rs.getString(6);
              	CaLamViec caLamViec = new CaLamViec(maCaLamViec);
              	NhanVien nhanVien = new NhanVien(maNV, tenNV, soDT, email, diaChi, caLamViec);
                danhSachNV.add(nhanVien);
            }
        } 
        catch (SQLException e) {
        	e.printStackTrace();
        }
        return danhSachNV;
    }
	
	public static List <NhanVien> FindDiaChiNhanVien(NhanVien nv) {
        List<NhanVien> danhSachNV = new ArrayList<>();
        ConnectDatabase.getInstance();
        String sql = "Select * from NhanVien where NhanVien.diaChi=N'"+ nv.getDiaChi() +"'";
        try {
            Connection con = ConnectDatabase.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
            	String maNV = rs.getString(1);
              	String tenNV = rs.getString(2);
              	String soDT = rs.getString(3);
              	String email = rs.getString(4);
              	String diaChi = rs.getString(5);
              	String maCaLamViec = rs.getString(6);
              	CaLamViec caLamViec = new CaLamViec(maCaLamViec);
              	NhanVien nhanVien = new NhanVien(maNV, tenNV, soDT, email, diaChi, caLamViec);
                danhSachNV.add(nhanVien);
            }
        } 
        catch (SQLException e) {
        	e.printStackTrace();
        }
        return danhSachNV;
    }
	public static List<KhachHang> findSDTKhachHang(KhachHang kh) {
		// TODO Auto-generated method stub
		return null;
	}
}
