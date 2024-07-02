package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDatabase;
import entity.KhachHang;
import entity.NhaCungCap;

public class KhachHangDAO {
	
	static ArrayList<KhachHang> danhSachKhachHang;
	KhachHang khachHang;
	
	public KhachHangDAO() {
		danhSachKhachHang = new ArrayList<KhachHang>();
		khachHang = new KhachHang();
	}
	
	public static ArrayList<KhachHang> getAllKhachHang() {
		try {
			Connection connection = ConnectDatabase.getInstance().getConnection();
			String sqlKhachHang = "Select * from KhachHang";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sqlKhachHang);
			while (result.next())
			{
				String maKH = result.getString(1);
				String tenKH = result.getString(2);
				String soDT = result.getString(3);
				String email = result.getString(4);
				String diaChi = result.getString(5);
				KhachHang khachHang = new KhachHang(maKH, tenKH, soDT, email, diaChi);
				danhSachKhachHang.add(khachHang);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return danhSachKhachHang;
	}
	
	
	public boolean create(KhachHang khachHang) {
		Connection connection = ConnectDatabase.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = connection.prepareStatement("Insert into KhachHang values(?, ?, ?, ?, ?)");
			statement.setString(1, khachHang.getMaKH());
			statement.setString(2, khachHang.getTenKH());
			statement.setString(3, khachHang.getSoDT());
			statement.setString(4, khachHang.getEmail());
			statement.setString(5, khachHang.getDiaChi());
			n = statement.executeUpdate();
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public static boolean update(KhachHang khachHang) {
		ConnectDatabase.getInstance();
		Connection connection = ConnectDatabase.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = connection.prepareStatement("Update KhachHang set maKH=?, hoTen=?, soDT=?, email=?, diaChi=? where maKH=?");
			statement.setString(1, khachHang.getMaKH());
			statement.setString(2, khachHang.getTenKH());
			statement.setString(3, khachHang.getSoDT());
			statement.setString(4, khachHang.getEmail());
			statement.setString(5, khachHang.getEmail());
			statement.setString(6, khachHang.getMaKH());
			n = statement.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean delete(String maKH) {
		Connection connection = ConnectDatabase.getInstance().getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = connection.prepareStatement("Delete from KhachHang where maKH=?");
			statement.setString(1, maKH);
			n = statement.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public KhachHang getKhachHangTheoSDT(String soDT) {
		ConnectDatabase.getInstance();
		Connection connection = ConnectDatabase.getConnection();
		PreparedStatement statement = null;
		KhachHang khachHang = new KhachHang();
		try {
			String sql = "Select * from KhachHang where soDT=?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, soDT);
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				String maKH = result.getString(1);
				String hoTen = result.getString(2);
				String soDienThoai = result.getString(3);
				String email = result.getString(4);
				String diaChi = result.getString(5);
				khachHang.setMaKH(maKH);
				khachHang.setTenKH(hoTen);
				khachHang.setSoDT(soDT);
				khachHang.setEmail(email);
				khachHang.setDiaChi(diaChi);
			}
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return khachHang;
	}
	
	//timKHTheoMa
	public static List <KhachHang> getKHTheoMa(KhachHang khachHang) {
        List <KhachHang> danhSachKH = new ArrayList<>();
        ConnectDatabase.getInstance();
        String sql = "Select * from KhachHang where maKH='" + khachHang.getMaKH()+ "'";
        try {
            Connection con = ConnectDatabase.getConnection();
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(sql);
          while (result.next()) {
        	  String maKH = result.getString(1);
				String tenKH = result.getString(2);
				String soDT = result.getString(3);
				String email = result.getString(4);
				String diaChi = result.getString(5);
				KhachHang khacHang = new KhachHang(maKH, tenKH, soDT, email, diaChi);
                danhSachKH.add(khacHang);
          }
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return danhSachKH;
      }
	  
	//timKHTheoSDT
	public static List <KhachHang> FindTheoSDT(KhachHang khachHang) {
        List <KhachHang> danhSachKH = new ArrayList<>();
        ConnectDatabase.getInstance();
        String sql = "Select * from KhachHang where soDT='" + khachHang.getSoDT()+ "'";
        try {
            Connection con = ConnectDatabase.getConnection();
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(sql);
          while (result.next()) {
        	  String maKH = result.getString(1);
				String tenKH = result.getString(2);
				String soDT = result.getString(3);
				String email = result.getString(4);
				String diaChi = result.getString(5);
				KhachHang khacHang = new KhachHang(maKH, tenKH, soDT, email, diaChi);
                danhSachKH.add(khacHang);
          }
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return danhSachKH;
      }
	
	public static List <KhachHang> FindTheoTen(KhachHang khachHang1) {
        List <KhachHang> danhSachKH = new ArrayList<>();
        ConnectDatabase.getInstance();
        String sql = "Select * from KhachHang where hoTen= N'" + khachHang1.getTenKH()+ "'";
        try {
            Connection con = ConnectDatabase.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
          while (rs.next()) {
        	  	KhachHang kh = new KhachHang();
                kh.setMaKH(rs.getString(1));
                kh.setTenKH(rs.getString(2));
                kh.setSoDT(rs.getString(3));
                kh.setEmail(rs.getString(4));
                kh.setDiaChi(rs.getString(5));
                danhSachKH.add(kh);
          }
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return danhSachKH;
      }
	public static List <KhachHang> FindTheoEmail(KhachHang khachHang2) {
        List <KhachHang> danhSachKH = new ArrayList<>();
        ConnectDatabase.getInstance();
        String sql = "Select * from KhachHang where email='" + khachHang2.getEmail()+ "'";
        try {
            Connection con = ConnectDatabase.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
          while (rs.next()) {
        	  	KhachHang kh = new KhachHang();
                kh.setMaKH(rs.getString(1));
                kh.setTenKH(rs.getString(2));
                kh.setSoDT(rs.getString(3));
                kh.setEmail(rs.getString(4));
                kh.setDiaChi(rs.getString(5));
                danhSachKH.add(kh);
          }
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return danhSachKH;
      }

      public static List <KhachHang> FindTheoDiaChi(KhachHang khachHang3) {
          List <KhachHang> danhSachKH = new ArrayList<>();
          ConnectDatabase.getInstance();
          String sql = "Select * from KhachHang where diaChi= N'" + khachHang3.getDiaChi()+ "'";
          try {
              Connection con = ConnectDatabase.getConnection();
              Statement stmt = con.createStatement();
              ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
          	  	KhachHang kh = new KhachHang();
                  kh.setMaKH(rs.getString(1));
                  kh.setTenKH(rs.getString(2));
                  kh.setSoDT(rs.getString(3));
                  kh.setEmail(rs.getString(4));
                  kh.setDiaChi(rs.getString(5));
                  danhSachKH.add(kh);
            }
          } catch (SQLException e) {
          	e.printStackTrace();
          }
          return danhSachKH;
        }
}
