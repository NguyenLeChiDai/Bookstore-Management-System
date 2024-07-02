package dao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDatabase;

public class FrmQuanLyPhieuDatHang extends JFrame implements ActionListener, MouseListener {

	//properties
	private JPanel pnlNorth, pnlWest;
	private JLabel lblTitle, lblMaPhieu, lblMaKH, lblNgayLap;
	private Box bChiTiet, bMaPhieu, bMaKH, bNgayLap, bCenter, bPhieuDatHang, bChiTietPhieu, bButton;
	private JTextField txtMaPhieu, txtMaKH, txtNgayLap;
	private DefaultTableModel tableModelPhieuDatHang, tableModelChiTietPhieuDatHang;
	private JTable tablePhieuDathang, tableChiTietPhieuDathang;
	private JButton btnXoa;
	
	//constructors
	public FrmQuanLyPhieuDatHang() {
		ConnectDatabase.getInstance().connect();
		setTitle("Quản lý phiếu đặt hàng");
		setSize(1200, 700);
		setResizable(false);
		setLocationRelativeTo(null);
		
		//north
		pnlNorth = new JPanel();
		this.add(pnlNorth, BorderLayout.NORTH);
		pnlNorth.add(lblTitle = new JLabel("QUẢN LÝ PHIẾU ĐẶT HÀNG"));
		lblTitle.setFont(new Font("Arial", Font.BOLD, 30));
		lblTitle.setForeground(Color.blue);
		
		//west
		pnlWest = new JPanel();
		bChiTiet = Box.createVerticalBox();
		bChiTiet.setBorder(BorderFactory.createTitledBorder("Thông tin phiếu"));
		pnlWest.add(bChiTiet);
			//maPhieu
			bChiTiet.add(bMaPhieu = Box.createHorizontalBox());
			bMaPhieu.add(lblMaPhieu = new JLabel("Mã phiếu:"));
			bMaPhieu.add(txtMaPhieu = new JTextField(16));
			bChiTiet.add(Box.createVerticalStrut(10));
			
			//maKH
			bChiTiet.add(bMaKH = Box.createHorizontalBox());
			bMaKH.add(lblMaKH = new JLabel("Mã khách hàng:"));
			bMaKH.add(txtMaKH = new JTextField(16));
			bChiTiet.add(Box.createVerticalStrut(10));
			
			//ngayLap
			bChiTiet.add(bNgayLap = Box.createHorizontalBox());
			bNgayLap.add(lblNgayLap= new JLabel("Ngày lập:"));
			bNgayLap.add(txtNgayLap = new JTextField(16));
			bChiTiet.add(Box.createVerticalStrut(20));
			
			lblMaPhieu.setPreferredSize(lblMaKH.getPreferredSize());
			lblMaKH.setPreferredSize(lblMaKH.getPreferredSize());
			lblNgayLap.setPreferredSize(lblMaKH.getPreferredSize());
			
			//button
			bChiTiet.add(bButton = Box.createHorizontalBox());
			bButton.add(btnXoa = new JButton("Xóa"));
			
		this.add(pnlWest, BorderLayout.WEST);
		
		//center
		bCenter = Box.createVerticalBox();
		this.add(bCenter, BorderLayout.CENTER);
		
			//tablePhieuDatHang
			bPhieuDatHang = Box.createHorizontalBox();
			bPhieuDatHang.setBorder(BorderFactory.createTitledBorder("Danh sách phiếu đặt hàng"));
			String [] headersPhieuDatHang = "Mã phiếu; Mã khách hàng; Ngày Lập".split(";");
			tableModelPhieuDatHang = new DefaultTableModel(headersPhieuDatHang, 0);
			JScrollPane scrollPhieuDatHang = new JScrollPane();
			scrollPhieuDatHang.setViewportView(tablePhieuDathang = new JTable(tableModelPhieuDatHang));
			tablePhieuDathang.setRowHeight(25);
			tablePhieuDathang.setAutoCreateRowSorter(true);
			tablePhieuDathang.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
			tablePhieuDathang.addMouseListener(this);
			bPhieuDatHang.add(scrollPhieuDatHang);
			bCenter.add(bPhieuDatHang);
		
			//tableChiTietPhieu
			bChiTietPhieu = Box.createHorizontalBox();
			bChiTietPhieu.setBorder(BorderFactory.createTitledBorder("Chi tiết phiếu đặt hàng"));
			String [] headersChiTietPhieu = "Mã sản phẩm; Tên sản phẩm; Số lượng; Thành tiền".split(";");
			tableModelChiTietPhieuDatHang = new DefaultTableModel(headersChiTietPhieu, 0);
			JScrollPane scrollChiTietPhieuDatHang = new JScrollPane();
			scrollChiTietPhieuDatHang.setViewportView(tableChiTietPhieuDathang = new JTable(tableModelChiTietPhieuDatHang));
			tableChiTietPhieuDathang.setRowHeight(25);
			tableChiTietPhieuDathang.setAutoCreateRowSorter(true);
			tableChiTietPhieuDathang.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
			tableChiTietPhieuDathang.addMouseListener(this);
			bChiTietPhieu.add(scrollChiTietPhieuDatHang);
			bCenter.add(bChiTietPhieu);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FrmQuanLyPhieuDatHang().setVisible(true);
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
