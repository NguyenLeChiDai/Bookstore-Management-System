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
import entity.LoaiSach;
import entity.NhaXuatBan;
import entity.Sach;
import entity.TacGia;

public class SachDAO {
	static ArrayList<Sach> danhSachSach;
	Sach sach;
	
	public SachDAO() {
		danhSachSach = new ArrayList<Sach>();
		sach = new Sach();
	}
	
	public static ArrayList<Sach> getAllSach() {
		try {
			Connection connection = ConnectDatabase.getInstance().getConnection();
			String sqlSach = "Select * from Sach";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sqlSach);
			while (result.next())
			{
				String maSach = result.getString(1);
				String tenSach = result.getString(2);
				String iSBN = result.getString(3);
				int soTrang = result.getInt(4);
				int soLuong = result.getInt(5);
				LoaiSach loaiSach = new LoaiSach(result.getString(6));
				String maTacGia = result.getString(7);
				TacGia tacGia = new TacGia(maTacGia);
				NhaXuatBan nhaXB = new NhaXuatBan(result.getString(8));
				double donGiaBan = result.getDouble(9);
				double donGiaNhap = result.getDouble(10);
				Sach sach = new Sach(maSach, tenSach, iSBN, loaiSach, tacGia, nhaXB, soTrang, soLuong, donGiaBan, donGiaNhap);
				danhSachSach.add(sach);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return danhSachSach;
	}
	
	public boolean create(Sach sach) {
		Connection connection = ConnectDatabase.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = connection.prepareStatement("Insert into Sach values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			statement.setString(1, sach.getMaSach());
			statement.setString(2, sach.getTenSach());
			statement.setString(3, sach.getiSBN());
			statement.setInt(4, sach.getSoTrang());
			statement.setInt(5, sach.getSoLuong());
			statement.setString(6, sach.getLoaiSach().getMaLoai());
			statement.setString(7, sach.getTacGia().getMaTacGia());
			statement.setString(8, sach.getNhaXB().getMaNhaXB());
			statement.setDouble(9, sach.getDonGiaBan());
			statement.setDouble(10, sach.getDonGiaNhap());
			n = statement.executeUpdate();
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public static boolean update(Sach sach) {
		ConnectDatabase.getInstance();
		Connection connection = ConnectDatabase.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = connection.prepareStatement("Update Sach set maSach=?, tenSach=?, ISBN=?, soTrang=?, soLuong=?, maLoai=?, tacGia=?, maNhaXB=?, donGiaBan=?, donGiaNhap=? where maSach=?");
			statement.setString(1, sach.getMaSach());
			statement.setString(2, sach.getTenSach());
			statement.setString(3, sach.getiSBN());
			statement.setInt(4, sach.getSoTrang());
			statement.setInt(5, sach.getSoLuong());
			statement.setString(6, sach.getLoaiSach().getMaLoai());
			statement.setString(7, sach.getTacGia().getMaTacGia());
			statement.setString(8, sach.getNhaXB().getMaNhaXB());
			statement.setDouble(9, sach.getDonGiaBan());
			statement.setDouble(10, sach.getDonGiaNhap());
			statement.setString(11, sach.getMaSach());
			n = statement.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean delete(String maSach) {
		Connection connection = ConnectDatabase.getInstance().getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = connection.prepareStatement("Delete from Sach where maSach=?");
			statement.setString(1, maSach);
			n = statement.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return n > 0;
	}

	public static List < Sach > FindTenSach(Sach TenSach) {
        List <Sach> timSach = new ArrayList<>();
        ConnectDatabase.getInstance();
        String sql = "Select *from Sach where Sach.tenSach = N'"+TenSach.getTenSach()+"'";
        try 
        {	
        	Connection con = ConnectDatabase.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);   
            while (rs.next()) {
            	String maSach = rs.getString(1);
            	String tenSach = rs.getString(2);
            	String iSBN = rs.getString(3);
            	int soTrang = rs.getInt(4);
            	int soLuong = rs.getInt(5);
            	String maLoai = rs.getString(6);
            	LoaiSach loaiSach = new LoaiSach(maLoai);
            	String maTacGia = rs.getString(7);
            	TacGia tacGia = new TacGia(maTacGia);
            	String maNXB = rs.getString(8);
            	NhaXuatBan nhaXB = new NhaXuatBan(maNXB);
            	double donGiaNhap = rs.getDouble(9);
            	double donGiaBan = rs.getDouble(10);
            	Sach sach = new Sach(maSach, tenSach, iSBN, loaiSach, tacGia, nhaXB, soTrang, soLuong, donGiaBan, donGiaNhap);
                timSach.add(sach);
            }
        } 
        catch (SQLException e) {
        	e.printStackTrace();
        }
        return timSach;
      }
	public List<Sach> getAllDatSach() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}