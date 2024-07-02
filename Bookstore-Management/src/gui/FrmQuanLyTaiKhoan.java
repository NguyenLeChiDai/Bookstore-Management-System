package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import dao.NhanVienDAO;
import dao.PhanQuyenDAO;
import dao.TaiKhoanDAO;
import entity.NhanVien;
import entity.PhanQuyen;
import entity.TaiKhoan;

public class FrmQuanLyTaiKhoan extends JFrame implements ActionListener, MouseListener {

	private JPanel pnlNorth, pnlWest;
	private JLabel lblTitle, lblMaTK, lblTenTK, lblMatKhau, lblPhanQuyen;
	private Box bCen;
	private JTextField txtMaTK, txtTenTK, txtMatKhau;
	private Box bCTTK, bMaTK, bTenTK, bMatKhau, bButton, bTable, bPhanQuyen;
	private JButton btnThem, btnXoa, btnCapNhat;
	private DefaultTableModel tableModel;
	private JTable table;
	private JComboBox<String> cboPhanQuyen;
	private PhanQuyenDAO phanQuyenDAO;
	

	public FrmQuanLyTaiKhoan() {
		ConnectDatabase.getInstance().connect();
		
		setTitle("Quản lý tài khoản");
		setExtendedState(MAXIMIZED_BOTH);
		
		//north
		pnlNorth = new JPanel();
		this.add(pnlNorth, BorderLayout.NORTH);
		pnlNorth.add(lblTitle = new JLabel("QUẢN LÝ TÀI KHOẢN"));
		lblTitle.setFont(new Font("Arial", Font.BOLD, 30));
		lblTitle.setForeground(Color.blue);
		
		//west
		pnlWest = new JPanel();
		this.add(pnlWest, BorderLayout.WEST);
		bCTTK = Box.createVerticalBox();
		bCTTK.setBorder(BorderFactory.createTitledBorder("Chi tiết tài khoản")); 
		pnlWest.add(bCTTK);
			//maTK
			bCTTK.add(bMaTK = Box.createHorizontalBox());
			bMaTK.add(lblMaTK = new JLabel("Mã tài khoản:"));
			bMaTK.add(txtMaTK = new JTextField(16));
			bCTTK.add(Box.createVerticalStrut(10));
		
			//tenTK
			bCTTK.add(bTenTK = Box.createHorizontalBox());
			bTenTK.add(lblTenTK = new JLabel("Tên tài khoản:"));
			bTenTK.add(txtTenTK = new JTextField(16));
			bCTTK.add(Box.createVerticalStrut(10));
			
			//matKhau
			bCTTK.add(bMatKhau = Box.createHorizontalBox());
			bMatKhau.add(lblMatKhau = new JLabel("Mật khẩu:"));
			bMatKhau.add(txtMatKhau = new JTextField(16));
			bCTTK.add(Box.createVerticalStrut(10));
			
			//phanQuyen
			bCTTK.add(bPhanQuyen = Box.createHorizontalBox());
			bPhanQuyen.add(lblPhanQuyen = new JLabel("Phân quyền:"));
			bPhanQuyen.add(cboPhanQuyen = new JComboBox<String>());
			cboPhanQuyen.setEditable(false);
			phanQuyenDAO = new PhanQuyenDAO();
			ArrayList<PhanQuyen> danhSachPhanQuyen = phanQuyenDAO.getAllPhanQuyen();
			for (PhanQuyen phanQuyen : danhSachPhanQuyen) {
				cboPhanQuyen.addItem(phanQuyen.getTenPhanQuyen());
			}
			bCTTK.add(Box.createVerticalStrut(20));
			
			lblMaTK.setPreferredSize(lblTenTK.getPreferredSize());
			lblMatKhau.setPreferredSize(lblTenTK.getPreferredSize());
			lblPhanQuyen.setPreferredSize(lblTenTK.getPreferredSize());
			
			//button
			bCTTK.add(bButton = Box.createHorizontalBox());
			bButton.add(btnThem = new JButton("Thêm"));
			btnThem.addActionListener(this);
			bButton.add(Box.createHorizontalStrut(10));
			bButton.add(btnXoa = new JButton("Xóa"));
			btnXoa.addActionListener(this);
			bButton.add(Box.createHorizontalStrut(10));
			bButton.add(btnCapNhat = new JButton("Cập nhật"));
			btnCapNhat.addActionListener(this);
			
		//center
		bCen = Box.createVerticalBox();
		bCen.setBorder(BorderFactory.createTitledBorder("Danh sách tài khoản"));
			//table
			String [] headers = "Mã tài khoản;Tên tài khoản;Mật khẩu;Phân quyền".split(";");
			tableModel = new DefaultTableModel(headers,0);
			JScrollPane scroll = new JScrollPane();
			scroll.setViewportView(table = new JTable(tableModel));
			table.setRowHeight(25);
			table.setAutoCreateRowSorter(true);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
			scroll.setPreferredSize(new Dimension(520, 350));
			bCen.add(scroll);
		this.add(bCen, BorderLayout.CENTER);
		
		insertData();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FrmQuanLyTaiKhoan().setVisible(true);
	}
	
	private void insertData() {
		// TODO Auto-generated method stub
		TaiKhoanDAO danhSachTaiKhoan = new TaiKhoanDAO();
		List<TaiKhoan> list = danhSachTaiKhoan.getAllTaiKhoan();
		for (TaiKhoan taiKhoan : list) {
			String [] row = {taiKhoan.getMaTK(), taiKhoan.getTenTK(), taiKhoan.getMatKhau(), taiKhoan.getPhanQuyen().getMaPhanQuyen()};
			tableModel.addRow(row);
		}
		table.setModel(tableModel);
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
