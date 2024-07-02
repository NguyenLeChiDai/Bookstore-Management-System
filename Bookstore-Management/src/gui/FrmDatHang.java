package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDatabase;
import dao.KhachHangDAO;
import dao.SachDAO;
import dao.SanPhamKhacDAO;
import entity.Sach;
import entity.SanPhamKhac;
import entity.KhachHang;
import entity.PhieuDatHang;

public class FrmDatHang extends JFrame implements ActionListener, MouseListener {

	private JPanel pnlNorth;
	private JLabel lblTitle, lblTongTien, lblSoLuong, lblSDT;
	private JTextField txtTongTien, txtSoLuong, txtSDT;
	private Box bWest, bTableKhachHang, bLapHoaDon, bDatHang, bTimSDT, bEast, bTableGioHang, bTongTien, bButton, bCen, bTableSach, bTableSPK;
	private JButton btnLapHoaDon, btnDatHang, btnXoa, btnTim;
	private DefaultTableModel tableModelSach, tableModelSPK, tableModelGioHang, tableModelKH;
	private JTable tableSach, tableSPK, tableGioHang, tableKH;
	KhachHangDAO khachHangDAO = new KhachHangDAO();
	SachDAO sacDAO = new SachDAO();
	SanPhamKhacDAO sanPhamKhacDAO = new SanPhamKhacDAO();
	
	public FrmDatHang() {
		
		ConnectDatabase.getInstance().connect();
		setTitle("Đặt hàng");
		setExtendedState(MAXIMIZED_BOTH);
		
		//north
		pnlNorth = new JPanel();
		this.add(pnlNorth, BorderLayout.NORTH);
		pnlNorth.add(lblTitle = new JLabel("Đặt hàng"));
		lblTitle.setFont(new Font("Arial", Font.BOLD, 30));
		lblTitle.setForeground(Color.blue);
		
		//west
		bWest = Box.createVerticalBox();
			//tableKhachHang
			bTableKhachHang = Box.createHorizontalBox();
			bTableKhachHang.setBorder(BorderFactory.createTitledBorder("Danh sách khách hàng"));
			String [] headersKH = "Mã khách hàng; Tên khách hàng; Số điện thoại; Email; Địa chỉ".split(";");
			tableModelKH = new DefaultTableModel(headersKH, 0);
			JScrollPane scrollKhachHang = new JScrollPane();
			scrollKhachHang.setViewportView(tableKH = new JTable(tableModelKH));
			tableKH.setRowHeight(25);
			tableKH.setAutoCreateRowSorter(true);
			tableKH.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
			bTableKhachHang.add(scrollKhachHang);
			insertDataKH();
			bWest.add(bTableKhachHang);
			
			//timSDT
			bTimSDT = Box.createHorizontalBox();
			JPanel bTimSDT=new JPanel();
			bTimSDT.add(lblSDT = new JLabel("Nhập số điện thoại:"));
			bTimSDT.add(txtSDT = new JTextField(16));
			bTimSDT.add(btnTim = new JButton("Tìm kiếm"));
			btnTim.addActionListener(this);
			bWest.add(bTimSDT);
		this.add(bWest, BorderLayout.WEST);
		
		//center
		bCen = Box.createVerticalBox();
		
			//tableSach
			bTableSach = Box.createVerticalBox();
			bTableSach.setBorder(BorderFactory.createTitledBorder("Danh sách sách"));
			String [] headersSach = "Mã sách; Tên sách; ISBN; Số trang; Số lượng; Loại sách; Tác giả; Nhà xuất bản; Đơn giá".split(";");
			tableModelSach = new DefaultTableModel(headersSach, 0);
			JScrollPane scrollSach = new JScrollPane();
			scrollSach.setViewportView(tableSach = new JTable(tableModelSach));
			tableSach.setRowHeight(25);
			tableSach.setAutoCreateRowSorter(true);
			tableSach.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
			bTableSach.add(scrollSach);
			insertDataSach();
			bCen.add(bTableSach);
			
			//tableSanPhamKhac
			bTableSPK = Box.createVerticalBox();
			bTableSPK.setBorder(BorderFactory.createTitledBorder("Danh sách sản phẩm không phải sách"));
			String [] headersSPK = "Mã sản phẩm; Tên sản phẩm; Số lượng; Nhà sản xuất; Đơn giá".split(";");
			tableModelSPK = new DefaultTableModel(headersSPK, 0);
			JScrollPane scrollSPK = new JScrollPane();
			scrollSPK.setViewportView(tableSPK = new JTable(tableModelSPK));
			tableSPK.setRowHeight(25);
			tableSPK.setAutoCreateRowSorter(true);
			tableSPK.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
			bTableSPK.add(scrollSPK);
			insertDataSPK();
			bCen.add(bTableSPK);
			
			//datHang
			bDatHang = Box.createHorizontalBox();
			bDatHang.add(lblSoLuong = new JLabel("Nhập số lượng:"));
			bDatHang.add(txtSoLuong = new JTextField(16));
			bDatHang.add(btnDatHang = new JButton("Đặt hàng"));
			btnDatHang.addActionListener(this);
			bCen.add(bDatHang);
		this.add(bCen, BorderLayout.CENTER);
		
		//east
		bEast = Box.createVerticalBox();
		
			//gioHang
			bTableGioHang = Box.createVerticalBox();
			bTableGioHang.setBorder(BorderFactory.createTitledBorder("Giỏ hàng"));
			String [] headersGioHang = "Tên sản phẩm; Đơn giá; Số lượng; Thành tiền".split(";");
			tableModelGioHang = new DefaultTableModel(headersGioHang, 0);
			JScrollPane scrollGioHang = new JScrollPane();
			scrollGioHang.setViewportView(tableGioHang = new JTable(tableModelGioHang));
			tableGioHang.setRowHeight(25);
			tableGioHang.setAutoCreateRowSorter(true);
			tableGioHang.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
			bTableGioHang.add(scrollGioHang);
			bEast.add(bTableGioHang);
			
			//lapHoaDon + xoa
			bLapHoaDon = Box.createHorizontalBox();
			bLapHoaDon.add(btnLapHoaDon = new JButton("Lập hóa đơn"));
			bLapHoaDon.add(Box.createHorizontalStrut(20));
			bLapHoaDon.add(btnXoa = new JButton("Xóa"));
			bEast.add(bLapHoaDon);
		this.add(bEast, BorderLayout.EAST);
	}

	private void insertDataKH() {
		// TODO Auto-generated method stub
		KhachHangDAO danhSachKhachHang = new KhachHangDAO();
		List<KhachHang> list = danhSachKhachHang.getAllKhachHang();
		for (KhachHang khachHang : list) {
			String [] row = {khachHang.getMaKH(), khachHang.getTenKH(), khachHang.getSoDT(), khachHang.getEmail(), khachHang.getDiaChi()};
			tableModelKH.addRow(row);
		}
		tableKH.setModel(tableModelKH);
	}

	private void insertDataSPK() {
		// TODO Auto-generated method stub
		SanPhamKhacDAO danhSachSP = new SanPhamKhacDAO();
		List<SanPhamKhac> list = danhSachSP.getAllSanPhamKhac();
		for (SanPhamKhac sanPhamKhac : list) {
			String [] row = {sanPhamKhac.getMaSP(), sanPhamKhac.getTenSP(), Integer.toString(sanPhamKhac.getSoLuong()), sanPhamKhac.getNhaCC().getMaNCC(), Double.toString(sanPhamKhac.getDonGiaNhap()), Double.toString(sanPhamKhac.getDonGiaBan())};
			tableModelSPK.addRow(row);
		}
		tableSPK.setModel(tableModelSPK);
	}

	private void insertDataSach() {
		// TODO Auto-generated method stub
		SachDAO danhSachSach = new SachDAO();
		List<Sach> list = danhSachSach.getAllSach();
		for (Sach sach : list) {
			String [] row = {sach.getMaSach(), sach.getTenSach(), sach.getiSBN(), Integer.toString(sach.getSoTrang()), Integer.toString(sach.getSoLuong()),
					sach.getLoaiSach().getMaLoai(), sach.getTacGia().getMaTacGia(), sach.getNhaXB().getMaNhaXB(), Double.toString(sach.getDonGiaBan())};
			tableModelSach.addRow(row);
		}
		tableSach.setModel(tableModelSach);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FrmDatHang().setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
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
		Object src = e.getSource();
		if (src.equals(btnDatHang)) {
			datHang();
		}
	}

	private void datHang() {
		// TODO Auto-generated method stub
	
	}
}
