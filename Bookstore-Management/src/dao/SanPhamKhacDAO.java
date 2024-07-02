package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDatabase;
import entity.DatSanPham;
import entity.NhaCungCap;
import entity.SanPhamKhac;

public class SanPhamKhacDAO {
	static ArrayList<SanPhamKhac> danhSachSPK;
	SanPhamKhac sanPhamKhac;
	
	public SanPhamKhacDAO() {
		danhSachSPK = new ArrayList<SanPhamKhac>();
		sanPhamKhac = new SanPhamKhac();
	}
	
	public static ArrayList<SanPhamKhac> getAllSanPhamKhac() {
		try {
			Connection connection = ConnectDatabase.getInstance().getConnection();
			String sqlSanPhamKhac = "Select * from SanPhamKhac";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sqlSanPhamKhac);
			while (result.next())
			{
				String maSPK = result.getString(1);
				String tenSPK = result.getString(2);
				int soLuong = result.getInt(3);
				NhaCungCap nhaCC = new NhaCungCap(result.getString(4));
				double donGiaNhap = result.getDouble(5);
				double donGiaBan = result.getDouble(6);
				SanPhamKhac sanPham = new SanPhamKhac(maSPK, tenSPK, soLuong, nhaCC, donGiaNhap, donGiaBan);
				danhSachSPK.add(sanPham);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return danhSachSPK;
	}
	
	public boolean create(SanPhamKhac sanPhamKhac) {
		Connection connection = ConnectDatabase.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = connection.prepareStatement("Insert into SanPhamKhac values(?, ?, ?, ?, ?, ?)");
			statement.setString(1, sanPhamKhac.getMaSP());
			statement.setString(2, sanPhamKhac.getTenSP());
			statement.setInt(3, sanPhamKhac.getSoLuong());
			statement.setString(4, sanPhamKhac.getNhaCC().getMaNCC());
			statement.setDouble(5, sanPhamKhac.getDonGiaNhap());
			statement.setDouble(6, sanPhamKhac.getDonGiaBan());
			n = statement.executeUpdate();
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public static boolean update(SanPhamKhac sanPhamKhac) {
		ConnectDatabase.getInstance();
		Connection connection = ConnectDatabase.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = connection.prepareStatement("Update SanPhamKhac set maSPK=?, tenSPK=?, soLuong=?, maNhaCungCap=?, donGiaNhap=?, donGiaBan=? where maSPK=?");
			statement.setString(1, sanPhamKhac.getMaSP());
			statement.setString(2, sanPhamKhac.getTenSP());
			statement.setInt(3, sanPhamKhac.getSoLuong());
			statement.setString(4, sanPhamKhac.getNhaCC().getMaNCC());
			statement.setDouble(5, sanPhamKhac.getDonGiaNhap());
			statement.setDouble(6, sanPhamKhac.getDonGiaBan());
			statement.setString(7, sanPhamKhac.getMaSP());
			n = statement.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean delete(String maSP) {
		Connection connection = ConnectDatabase.getInstance().getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = connection.prepareStatement("Delete from SanPhamKhac where maSPK=?");
			statement.setString(1, maSP);
			n = statement.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return n > 0;
	}

	public static List <SanPhamKhac> FindTenSanPhamKhac(SanPhamKhac ten) {
        List <SanPhamKhac> danhSachSPK = new ArrayList <> ();
        ConnectDatabase.getInstance();
        String sql = "Select *from SanPhamKhac where SanPhamKhac.tenSPK= N'"+ten.getTenSP()+"'  ";
        try {
            Connection con = ConnectDatabase.getConnection();
            Statement stmt=con.createStatement();
            ResultSet result = stmt.executeQuery(sql);
            while (result.next())
			{
				String maSPK = result.getString(1);
				String tenSPK = result.getString(2);
				int soLuong = result.getInt(3);
				NhaCungCap nhaCC = new NhaCungCap(result.getString(4));
				double donGiaNhap = result.getDouble(5);
				double donGiaBan = result.getDouble(6);
				SanPhamKhac sanPham = new SanPhamKhac(maSPK, tenSPK, soLuong, nhaCC, donGiaNhap, donGiaBan);
				danhSachSPK.add(sanPham);
			}
        } catch (SQLException e) {
        	e.printStackTrace();


        }
        return danhSachSPK;
      }

	public void create(DatSanPham datSanPham) {
		// TODO Auto-generated method stub
		
	}
}
