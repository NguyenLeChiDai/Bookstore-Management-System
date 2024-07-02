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

import connectDB.ConnectDatabase;
import dao.KhachHangDAO;
import entity.KhachHang;


public class FrmCapNhatKhachHang extends JFrame implements ActionListener, MouseListener {

	private JPanel pnlNorth, pnlWest;
	private JLabel lblTitle;
	private Box bCen,bButton, bTTKH, bMaKhachHang, bTenKhachHang, bSoDienThoai, bEmail, bDiaChi;
	private JButton btnThem, btnXoa, btnCapNhat, btnXoaTrang;
	private DefaultTableModel tableModel;
	private JTable table;
	private JLabel lblMaKhachHang, lblTenKhachHang, lblSoDienThoai, lblEmail, lblDiaChi;
	private JTextField txtMaKhachHang, txtTenKhachHang, txtSoDienThoai, txtEmail, txtDiaChi;
	KhachHangDAO khachHangDAO = new KhachHangDAO();

	public FrmCapNhatKhachHang() {
		ConnectDatabase.getInstance().connect();
		setTitle("Quản lý khách hàng");
		setExtendedState(MAXIMIZED_BOTH);
		
		//north
		pnlNorth = new JPanel();
		this.add(pnlNorth, BorderLayout.NORTH);
		pnlNorth.add(lblTitle = new JLabel("QUẢN LÝ KHÁCH HÀNG"));
		lblTitle.setFont(new Font("Arial", Font.BOLD, 30));
		lblTitle.setForeground(Color.blue);
		
		//west
		pnlWest = new JPanel();
		this.add(pnlWest, BorderLayout.WEST);
		bTTKH = Box.createVerticalBox();
		bTTKH.setBorder(BorderFactory.createTitledBorder("Thông tin khách hàng")); 
		pnlWest.add(bTTKH);
			//maKhachHang
			bTTKH.add(bMaKhachHang = Box.createHorizontalBox());
			bMaKhachHang.add(lblMaKhachHang = new JLabel("Mã khách hàng:"));
			bMaKhachHang.add(txtMaKhachHang = new JTextField(16));
			bTTKH.add(Box.createVerticalStrut(10));
		
			//tenKhachHang
			bTTKH.add(bTenKhachHang = Box.createHorizontalBox());
			bTenKhachHang.add(lblTenKhachHang = new JLabel("Tên khách hàng:"));
			bTenKhachHang.add(txtTenKhachHang = new JTextField(16));
			bTTKH.add(Box.createVerticalStrut(10));
			
			//soDienThoai
			bTTKH.add(bSoDienThoai = Box.createHorizontalBox());
            bSoDienThoai.add(lblSoDienThoai = new JLabel("Số điện thoại:"));
			bSoDienThoai.add(txtSoDienThoai = new JTextField(16));
			bTTKH.add(Box.createVerticalStrut(10));
			
			//email
			bTTKH.add(bEmail= Box.createHorizontalBox());
			bEmail.add(lblEmail = new JLabel("Email:"));
			bEmail.add(txtEmail = new JTextField(16));
			bTTKH.add(Box.createVerticalStrut(10));
			
			//diaChi
			bTTKH.add(bDiaChi = Box.createHorizontalBox());
			bDiaChi.add(lblDiaChi = new JLabel("Địa chỉ:"));
			bDiaChi.add(txtDiaChi = new JTextField(16));
			bTTKH.add(Box.createVerticalStrut(10));
		
			//button
			bTTKH.add(bButton = Box.createHorizontalBox());
			bButton.add(btnThem = new JButton("Thêm"));
			btnThem.addActionListener(this);
			bButton.add(Box.createHorizontalStrut(10));
			bButton.add(btnXoa = new JButton("Xóa"));
			btnXoa.addActionListener(this);
			bButton.add(Box.createHorizontalStrut(10));
			bButton.add(btnCapNhat = new JButton("Cập nhật"));
			btnCapNhat.addActionListener(this);
			bButton.add(Box.createHorizontalStrut(10));
			bButton.add(btnXoaTrang = new JButton("Xóa trắng"));
			btnXoaTrang.addActionListener(this);
			
			lblMaKhachHang.setPreferredSize(lblTenKhachHang.getPreferredSize());
			lblTenKhachHang.setPreferredSize(lblTenKhachHang.getPreferredSize());
			lblSoDienThoai.setPreferredSize(lblTenKhachHang.getPreferredSize());
			lblEmail.setPreferredSize(lblTenKhachHang.getPreferredSize());
			lblDiaChi.setPreferredSize(lblTenKhachHang.getPreferredSize());
		
		//center
		bCen = Box.createVerticalBox();
		bCen.setBorder(BorderFactory.createTitledBorder("Danh sách khách hàng"));
			//table
			String [] headers = "Mã khách hàng; Tên Khách hàng; Số điện thoại; Email; Địa chỉ".split(";");
			tableModel = new DefaultTableModel(headers, 0);
			JScrollPane scroll = new JScrollPane();
			scroll.setViewportView(table = new JTable(tableModel));
			table.setRowHeight(25);
			table.setAutoCreateRowSorter(true);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
			table.addMouseListener(this);
			insertDataKhachHang();
			bCen.add(scroll);
		this.add(bCen, BorderLayout.CENTER);
	}
	private void insertDataKhachHang() {
		// TODO Auto-generated method stub
		KhachHangDAO danhSachKhachHang = new KhachHangDAO();
		List<KhachHang> list = danhSachKhachHang.getAllKhachHang();
		for (KhachHang khachHang : list) {
			String [] row = {khachHang.getMaKH(), khachHang.getTenKH(), khachHang.getSoDT(), khachHang.getEmail(), khachHang.getDiaChi()};
			tableModel.addRow(row);
		}
		table.setModel(tableModel);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FrmCapNhatKhachHang().setVisible(true);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		txtMaKhachHang.setText(tableModel.getValueAt(row, 0).toString());
		txtTenKhachHang.setText(tableModel.getValueAt(row, 1).toString());
		txtSoDienThoai.setText(tableModel.getValueAt(row, 2).toString());
		txtEmail.setText(tableModel.getValueAt(row, 3).toString());
		txtDiaChi.setText(tableModel.getValueAt(row, 4).toString());
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
		if (src.equals(btnThem)) {
			themKH();
		}
		if (src.equals(btnXoa)) {
			xoaKH();
		}
		if (src.equals(btnCapNhat)) {
			capNhat();
		}
		if (src.equals(btnXoaTrang)) {
			xoaTrang();
		}
	}
	private void xoaTrang() {
		// TODO Auto-generated method stub
		txtMaKhachHang.setText("");
		txtTenKhachHang.setText("");
		txtSoDienThoai.setText("");
		txtEmail.setText("");
		txtDiaChi.setText("");
		txtMaKhachHang.requestFocus();
	}
	private void capNhat() {
		// TODO Auto-generated method stub
		int loiNhac = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn sửa thông tin?", "Chú ý", JOptionPane.YES_NO_OPTION);
		if (loiNhac == JOptionPane.YES_OPTION)
		{
			int row = table.getSelectedRow();
			String maKH = txtMaKhachHang.getText();
			String tenKH = txtTenKhachHang.getText();
			String soDT = txtSoDienThoai.getText();
			String email = txtEmail.getText();
			String diaChi = txtDiaChi.getText();
			KhachHang khachHang = new KhachHang(maKH, tenKH, soDT, email, diaChi);
			if (row >= 0) {
				if (validData() == true) {
					if (khachHangDAO.update(khachHang)) {
						table.setValueAt(txtMaKhachHang.getText(), row, 0);
						table.setValueAt(txtTenKhachHang.getText(), row, 1);
						table.setValueAt(txtSoDienThoai.getText(), row, 2);
						table.setValueAt(txtEmail.getText(), row, 3);
						table.setValueAt(txtDiaChi.getText(), row, 4);
						JOptionPane.showMessageDialog(this, "Đã sửa thành công!");
					}
				}				
			}
		}	
	}
	
	private void xoaKH() {
		// TODO Auto-generated method stub
		int loiNhac = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", "Chú ý", JOptionPane.YES_NO_OPTION);
		if (loiNhac == JOptionPane.YES_OPTION)
		{
			int row = table.getSelectedRow();
			if (row >= 0)
			{
				String maKH = (String) table.getValueAt(row, 0);
				if (khachHangDAO.delete(maKH)) {
					tableModel.removeRow(row);
					xoaTrang();
				}
			}
			JOptionPane.showMessageDialog(this, "Đã xóa thông tin khách hàng này!");
		}
	}
	private void themKH() {
		// TODO Auto-generated method stub
		if (validData() == true)
		{
			String maKH = txtMaKhachHang.getText();
			String hoTen = txtTenKhachHang.getText();
			String soDT = txtSoDienThoai.getText();
			String email = txtEmail.getText();
			String diaChi = txtDiaChi.getText();
			KhachHang khachHang = new KhachHang(maKH, hoTen, soDT, email, diaChi);
			try {
				khachHangDAO.create(khachHang);
				tableModel.addRow(new Object[] {khachHang.getMaKH(), khachHang.getTenKH(), khachHang.getSoDT(), khachHang.getEmail(), khachHang.getDiaChi()});
				JOptionPane.showMessageDialog(this, "Đã thêm thành công");
			}
			catch (Exception e1) {
				JOptionPane.showMessageDialog(this, "Trùng!");
			}
			
		}
	}
	private boolean validData() {
		// TODO Auto-generated method stub
		String maKH = txtMaKhachHang.getText();
		if (maKH.isEmpty())
		{
			JOptionPane.showMessageDialog(this, "Chưa nhập mã khách hàng!");
			return false;
		}
		if (!maKH.matches("^KH-\\d{3}"))
		{
			JOptionPane.showMessageDialog(this, "Mã khách hàng phải theo mẫu KH-XXX, với XXX là ba số bất kì");
			return false;
		}
		String tenKH = txtTenKhachHang.getText();
		if (tenKH.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Chưa nhập tên khách hàng");
			return false;
		}
		String soDT = txtSoDienThoai.getText();
		if (soDT.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Chưa nhập số điện thoại");
			return false;
		}
		if (!soDT.matches("(09|07|08)\\d{8}")) {
			JOptionPane.showMessageDialog(this, "Số điện thoại phải có 10 chữ số và bắt đầu bằng 09, 08 hoặc 07");
			return false;
		}
		String email = txtEmail.getText();
		if (email.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Chưa nhập email");
			return false;
		}
		if (!email.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$")) {
			JOptionPane.showMessageDialog(this, "Email không hợp lệ");
			return false;
		}
		String diachi = txtDiaChi.getText();
		if (diachi.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Chưa nhập địa chỉ");
			return false;
		}
		return true;
	}

}
