package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDatabase;
import entity.NhaCungCap;
import entity.NhaXuatBan;
import entity.Sach;

public class NhaCungCapDAO {
	public static ArrayList<NhaCungCap> getAllNhaCungCap() {
		ArrayList<NhaCungCap> danhSachNhaCC = new ArrayList<NhaCungCap>();
		try {
			ConnectDatabase.getInstance();
			Connection connection = ConnectDatabase.getConnection();
			String sql = "Select * from NhaCungCap";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				String maNCC = result.getString(1);
				String tenNCC = result.getString(2);
				NhaCungCap nhaCungCap = new NhaCungCap(maNCC, tenNCC);
				danhSachNhaCC.add(nhaCungCap);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return danhSachNhaCC;
	}

	//create nhaCungCap
	public boolean create(NhaCungCap nhaCungCap) {
		Connection connection = ConnectDatabase.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = connection.prepareStatement("Insert into NhaCungCap values(?, ?)");
			statement.setString(1, nhaCungCap.getMaNCC());
			statement.setString(2, nhaCungCap.getTenNCC());
			n = statement.executeUpdate();
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	//update nhaCungCap
	public static boolean update(NhaCungCap nhaCungCap) {
		ConnectDatabase.getInstance();
		Connection connection = ConnectDatabase.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			
			statement = connection.prepareStatement("Update NhaCungCap set maNhaCungCap=?, tenNhaCungCap=? where maNhaCungCap=?");
			statement.setString(1, nhaCungCap.getMaNCC());
			statement.setString(2, nhaCungCap.getTenNCC());
			statement.setString(3, nhaCungCap.getMaNCC());
			n = statement.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	//delete nha cung cap
	public boolean delete(String maNCC) {
		Connection connection = ConnectDatabase.getInstance().getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = connection.prepareStatement("Delete from NhaCungCap where maNhaCungCap=?");
			statement.setString(1, maNCC);
			n = statement.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return n > 0;
	}
	//timKhachHang
	public static List <NhaCungCap> FindTenNhaCungCap(NhaCungCap ten) {
        List < NhaCungCap > NhaCungCap2 = new ArrayList <> ();
        ConnectDatabase.getInstance();
        String sql = "Select *from NhaCungCap where NhaCungCap.tenNhaCungCap= N'"+ten.getTenNCC()+"'  ";


        try {
            Connection con = ConnectDatabase.getConnection();
            Statement stmt=con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
          while (rs.next()) {
                NhaCungCap ncc=new NhaCungCap();
                ncc.setMaNCC(rs.getString(1));
                ncc.setTenNCC(rs.getString(2));
               
                NhaCungCap2.add(ncc);
          }
        } catch (SQLException e) {


        }
        return NhaCungCap2;
      }







}

