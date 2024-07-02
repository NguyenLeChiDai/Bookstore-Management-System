package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class FrmTimKiemHoaDon extends JFrame implements ActionListener, MouseListener {

	private JPanel pnlNorth, pnlWest;
	private JLabel lblTitle;
	
	private Box bCen,bButton, bTTHD, bMaHoaDon, bMaKhachHang, bMaNhanVien, bTenKhachHang, bNgayLap, bSoDienThoai, bDiaChi, bTongTien;
	private JLabel lblMaHoaDon, lblMaKhachHang, lblMaNhanVien, lblTenKhachHang, lblNgayLap, lblSoDienThoai, lblDiaChi, lblTongTien;
	private JTextField txtMaHoaDon, txtMaKhachHang, txtMaNhanVien, txtTenKhachHang , txtNgayLap, txtSoDienThoai, txtDiaChi, txtTongTien;
	private JButton btnTim, btnXoaTrang;
	private DefaultTableModel tableModel, tableModelCTHD;
	private JTable table, tableCTHD;
	//private JComboBox cboLoaiSach, cboNhaXB;
	//NhaXuatBanDAO nhaXuatBanDAO = new NhaXuatBanDAO();
	//LoaiSachDAO loaiSachDAO = new LoaiSachDAO();
	//SachDAO sachDAO = new SachDAO();
	//String tenLoaiSach, maLoaiSach;
	

	public FrmTimKiemHoaDon() {
		//ConnectDatabase.getInstance().connect();
		setTitle("Quản lý hóa đơn");
		setExtendedState(MAXIMIZED_BOTH);
		
		//north
		pnlNorth = new JPanel();
		this.add(pnlNorth, BorderLayout.NORTH);
		pnlNorth.add(lblTitle = new JLabel("QUẢN LÝ HÓA ĐƠN"));
		lblTitle.setFont(new Font("Arial", Font.BOLD, 30));
		lblTitle.setForeground(Color.blue);
		
		//west
		pnlWest = new JPanel();
		this.add(pnlWest, BorderLayout.WEST);
		bTTHD = Box.createVerticalBox();
		bTTHD.setBorder(BorderFactory.createTitledBorder("Thông tin hóa đơn")); 
		pnlWest.add(bTTHD);
			//maHoaDon
			bTTHD.add(bMaHoaDon = Box.createHorizontalBox());
			bMaHoaDon.add(lblMaHoaDon = new JLabel("Mã hóa đơn:"));
			bMaHoaDon.add(txtMaHoaDon = new JTextField(16));
			bTTHD.add(Box.createVerticalStrut(10));
		
			//maKhachHang
			bTTHD.add(bMaKhachHang = Box.createHorizontalBox());
			bMaKhachHang.add(lblMaKhachHang = new JLabel("Mã khách hàng:"));
			bMaKhachHang.add(txtMaKhachHang = new JTextField(16));
			bTTHD.add(Box.createVerticalStrut(10));
			
			//tenKhachHang
			bTTHD.add(bTenKhachHang = Box.createHorizontalBox());
			bTenKhachHang.add(lblTenKhachHang = new JLabel("Tên khách hàng:"));
			bTenKhachHang.add(txtTenKhachHang = new JTextField(16));
			bTTHD.add(Box.createVerticalStrut(10));
			
			//soDienThoai
			bTTHD.add(bSoDienThoai = Box.createHorizontalBox());
            bSoDienThoai.add(lblSoDienThoai = new JLabel("Số điện thoại:"));
			bSoDienThoai.add(txtSoDienThoai = new JTextField(16));
			bTTHD.add(Box.createVerticalStrut(10));
			
			//diaChi
			bTTHD.add(bDiaChi = Box.createHorizontalBox());
			bDiaChi.add(lblDiaChi = new JLabel("Địa chỉ:"));
			bDiaChi.add(txtDiaChi = new JTextField(16));
			bTTHD.add(Box.createVerticalStrut(10));
			
			//tongTien
			bTTHD.add(bTongTien = Box.createHorizontalBox());
			bTongTien.add(lblTongTien= new JLabel("Tổng Tiền:"));
			bTongTien.add(txtTongTien = new JTextField(16));
			bTTHD.add(Box.createVerticalStrut(10));
			
		
			//button
			bTTHD.add(bButton = Box.createHorizontalBox());
			bButton.add(btnTim= new JButton("Tìm kiếm"));
			btnTim.addActionListener(this);
			bButton.add(Box.createHorizontalStrut(10));
			
			bButton.add(btnXoaTrang = new JButton("Xóa trắng"));
			btnXoaTrang.addActionListener(this);
			
			lblMaHoaDon.setPreferredSize(lblTenKhachHang.getPreferredSize());
			lblMaKhachHang.setPreferredSize(lblTenKhachHang.getPreferredSize());
			lblSoDienThoai.setPreferredSize(lblTenKhachHang.getPreferredSize());
			lblDiaChi.setPreferredSize(lblTenKhachHang.getPreferredSize());
			lblTongTien.setPreferredSize(lblTenKhachHang.getPreferredSize());
		
			
		//center
		bCen = Box.createVerticalBox();
		bCen.setBorder(BorderFactory.createTitledBorder("Danh sách hóa đơn"));
			//table HoaDon
			String [] headers = "Mã hóa đơn; Mã Khách hàng; Tên khách hàng; Số điện thoại; Địa chỉ; Tổng tiền; Ngày lập".split(";");
			tableModel = new DefaultTableModel(headers, 0);
			JScrollPane scroll = new JScrollPane();
			scroll.setViewportView(table = new JTable(tableModel));
			table.setRowHeight(25);
			table.setAutoCreateRowSorter(true);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
			bCen.add(scroll);
		this.add(bCen, BorderLayout.CENTER);
		//insertData();
		
		//tableChiTietHoaDon
		String [] headersCTHD = "Mã sản phảm; Tên sản phẩm; Đơn giá; Số lượng; Thành tiền".split(";");
		tableModelCTHD = new DefaultTableModel(headersCTHD, 0);
		JScrollPane scrollCTHD = new JScrollPane();
		scrollCTHD.setBorder(BorderFactory.createTitledBorder("Danh sách nhà xuất bản"));
		scrollCTHD.setViewportView(tableCTHD = new JTable(tableModelCTHD));
		tableCTHD.setRowHeight(25);
		tableCTHD.setAutoCreateRowSorter(true);
		tableCTHD.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		tableCTHD.addMouseListener(this);
		bCen.add(scrollCTHD);
	this.add(bCen, BorderLayout.CENTER);		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FrmTimKiemHoaDon().setVisible(true);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
