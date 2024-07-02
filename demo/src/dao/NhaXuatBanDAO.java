package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDatabase;
import entity.CaLamViec;
import entity.NhaXuatBan;
import entity.NhanVien;

public class NhaXuatBanDAO {
	
	ArrayList<NhaXuatBan> danhSachNhaXuatBan;
	NhaXuatBan nhaXB;
	
	public NhaXuatBanDAO() {
		danhSachNhaXuatBan = new ArrayList<NhaXuatBan>();
		nhaXB = new NhaXuatBan(null);
	}
	
	
	public static ArrayList<NhaXuatBan> getAllNhaXuatBan() {
		ArrayList<NhaXuatBan> danhSachNhaXuatBan = new ArrayList<NhaXuatBan>();
		try {
			ConnectDatabase.getInstance();
			Connection connection = ConnectDatabase.getConnection();
			String sql = "Select * from NhaXuatBan";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				String maNhaXB = result.getString(1);
				String tenNhaXB = result.getString(2);
				NhaXuatBan nhaXB = new NhaXuatBan(maNhaXB, tenNhaXB);
				danhSachNhaXuatBan.add(nhaXB);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return danhSachNhaXuatBan;
	}
	public boolean create(NhaXuatBan nhaXB) {
		Connection connection = ConnectDatabase.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = connection.prepareStatement("Insert into NhaXuatBan values(?, ?)");
			statement.setString(1, nhaXB.getMaNhaXB());
			statement.setString(2, nhaXB.getTenNhaXB());
			
			n = statement.executeUpdate();
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	public static boolean update(NhaXuatBan nhaXB) {
		ConnectDatabase.getInstance();
		Connection connection = ConnectDatabase.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = connection.prepareStatement("Update NhaXuatBan set maNhaXB=?, tenNhaXB=? where maNhaXB=?");
			statement.setString(1, nhaXB.getMaNhaXB());
			statement.setString(2, nhaXB.getTenNhaXB());
			statement.setString(3, nhaXB.getMaNhaXB());
			n = statement.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public boolean delete(String maNhaXB) {
		Connection connection = ConnectDatabase.getInstance().getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = connection.prepareStatement("Delete from NhaXuatBan where maNhaXB=?");
			statement.setString(1, maNhaXB);
			n = statement.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public NhaXuatBan getNhaXuatBanTheoMa(String maNhaXB) {
		NhaXuatBan nhaXB = null;
		ConnectDatabase.getInstance();
		Connection connection = ConnectDatabase.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "Select * from NhaXuatBan where maNhaXB=?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, maNhaXB);
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				String maNhaXuatBan = result.getString(1);
				String tenNhaXuatBan = result.getString(2);
				nhaXB = new NhaXuatBan(maNhaXuatBan, tenNhaXuatBan);
			}
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return nhaXB;
	}
	public static List <NhaXuatBan> FindTenNhaXuatBan(NhaXuatBan ten) {
        List < NhaXuatBan > Student2 = new ArrayList <> ();
        ConnectDatabase.getInstance();
        String sql = "Select *from NhaXuatBan where NhaXuatBan.tenNhaXB= N'"+ten.getTenNhaXB()+"'  ";


        try {
            Connection con = ConnectDatabase.getConnection();
            Statement stmt=con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
          while (rs.next()) {
                NhaXuatBan nxb=new NhaXuatBan();
                nxb.setMaNhaXB(rs.getString(1));
                nxb.setTenNhaXB(rs.getString(2));
               
                Student2.add(nxb);
          }
        } catch (SQLException e) {


        }
        return Student2;
      }

}
