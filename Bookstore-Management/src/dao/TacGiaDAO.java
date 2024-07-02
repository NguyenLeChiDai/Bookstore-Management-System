package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDatabase;
import entity.TacGia;

public class TacGiaDAO {
	
	private ArrayList<TacGia> danhSachTacGia;
	private TacGia tacGia;

	public TacGiaDAO() {
		danhSachTacGia = new ArrayList<TacGia>();
		tacGia = new TacGia();
	}
	
	public ArrayList<TacGia> getAllTacGia() {
		ArrayList<TacGia> danhSachTacGia = new ArrayList<TacGia>();
		try {
			ConnectDatabase.getInstance();
			Connection connection = ConnectDatabase.getConnection();
			String sql = "Select * from TacGia";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				String maTacGia = result.getString(1);
				String tenTacGia = result.getString(2);
				TacGia tacGia = new TacGia(maTacGia, tenTacGia);
				danhSachTacGia.add(tacGia);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return danhSachTacGia;
	}
	
	public boolean create(TacGia tacGia) {
		Connection connection = ConnectDatabase.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = connection.prepareStatement("Insert into TacGia values(?, ?)");
			statement.setString(1, tacGia.getMaTacGia());
			statement.setString(2, tacGia.getTenTacGia());
			n = statement.executeUpdate();
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public static boolean update(TacGia tacGia) {
		ConnectDatabase.getInstance();
		Connection connection = ConnectDatabase.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = connection.prepareStatement("Update TacGia set maTacGia=?, tenTacGia=? where maTacGia=?");
			statement.setString(1, tacGia.getMaTacGia());
			statement.setString(2, tacGia.getTenTacGia());
			statement.setString(3, tacGia.getMaTacGia());
			n = statement.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean delete(String maTacGia) {
		Connection connection = ConnectDatabase.getInstance().getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = connection.prepareStatement("Delete from TacGia where maTacGia=?");
			statement.setString(1, maTacGia);
			n = statement.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return n > 0;
	}
	public static List <TacGia> FindTenTacGia(TacGia tacGia) {
        List <TacGia> danhSachTacGia = new ArrayList<>();
        ConnectDatabase.getInstance();
        String sql = "Select * from TacGia where TacGia.tenTacGia = N'"+ tacGia.getTenTacGia() +"'";
        try {
            Connection con = ConnectDatabase.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
            	String maTacGia = rs.getString(1);
            	String tenTacGia = rs.getString(2);
            	TacGia tg = new TacGia(maTacGia, tenTacGia);
            	danhSachTacGia.add(tg);
            }
        } 
        catch (SQLException e) {
        	e.printStackTrace();
        }
        return danhSachTacGia;
      }
}
