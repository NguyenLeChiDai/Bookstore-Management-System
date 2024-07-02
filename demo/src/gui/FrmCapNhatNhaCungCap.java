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
import dao.NhaCungCapDAO;
import dao.NhaXuatBanDAO;
import dao.SachDAO;
import entity.LoaiSach;
import entity.NhaCungCap;
import entity.NhaXuatBan;
import entity.Sach;


public class FrmCapNhatNhaCungCap extends JFrame implements ActionListener, MouseListener {

	private JPanel pnlNorth, pnlWest;
	private JLabel lblTitle;
	
	private Box bCen,bButton, bTTNCC, bMaNhaCungCap, bTenNhaCungCap;
	private JLabel lblMaNhaCungCap, lblTenNhaCungCap;
	private JTextField txtMaNhaCungCap, txtTenNhaCungCap;
	private JButton btnThem, btnXoa, btnCapNhat, btnXoaTrang;
	private DefaultTableModel tableModelNhaCC;
	private JTable tableNhaCC;
	NhaCungCapDAO nhaCungCapDAO = new NhaCungCapDAO();

	public FrmCapNhatNhaCungCap() {
		
		ConnectDatabase.getInstance().connect();
		setTitle("Quản lý nhà cung cấp");
		setExtendedState(MAXIMIZED_BOTH);
		
		//north
		pnlNorth = new JPanel();
		this.add(pnlNorth, BorderLayout.NORTH);
		pnlNorth.add(lblTitle = new JLabel("QUẢN LÝ NHÀ CUNG CẤP"));
		lblTitle.setFont(new Font("Arial", Font.BOLD, 30));
		lblTitle.setForeground(Color.blue);
		
		//west
		pnlWest = new JPanel();
		this.add(pnlWest, BorderLayout.WEST);
		bTTNCC = Box.createVerticalBox();
		bTTNCC.setBorder(BorderFactory.createTitledBorder("Thông tin nhà cung cấp"));
		pnlWest.add(bTTNCC);
			//maNhaCungCap
			bTTNCC.add(bMaNhaCungCap = Box.createHorizontalBox());
			bMaNhaCungCap.add(lblMaNhaCungCap = new JLabel("Mã nhà cung cấp:"));
			bMaNhaCungCap.add(txtMaNhaCungCap = new JTextField(16));
			bTTNCC.add(Box.createVerticalStrut(10));
		
			//tenNhaCungCap
			bTTNCC.add(bTenNhaCungCap = Box.createHorizontalBox());
			bTenNhaCungCap.add(lblTenNhaCungCap = new JLabel("Tên nhà cung cấp:"));
			bTenNhaCungCap.add(txtTenNhaCungCap = new JTextField(16));
			bTTNCC.add(Box.createVerticalStrut(10));
			
			//button
			bTTNCC.add(bButton = Box.createHorizontalBox());
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
			
			lblMaNhaCungCap.setPreferredSize(lblTenNhaCungCap.getPreferredSize());
			
		//center
		bCen = Box.createVerticalBox();
		bCen.setBorder(BorderFactory.createTitledBorder("Danh sách nhà cung cấp"));
			//table
			String [] headers = "Mã nhà cung cấp; Tên nhà cung cấp".split(";");
			tableModelNhaCC = new DefaultTableModel(headers, 0);
			JScrollPane scroll = new JScrollPane();
			scroll.setViewportView(tableNhaCC = new JTable(tableModelNhaCC));
			tableNhaCC.setRowHeight(25);
			tableNhaCC.setAutoCreateRowSorter(true);
			tableNhaCC.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
			tableNhaCC.addMouseListener(this);
			bCen.add(scroll);
		  this.add(bCen, BorderLayout.CENTER);
		insertDatanNhaCungCap();
	}
	
	//insertDateNhaCungCap
	private void insertDatanNhaCungCap() {
		NhaCungCapDAO danhSachNhaCC = new NhaCungCapDAO();
		List<NhaCungCap> list = danhSachNhaCC.getAllNhaCungCap();
		for (NhaCungCap nhaCungCap : list) {
			String [] row = {nhaCungCap.getMaNCC(), nhaCungCap.getTenNCC()};
			tableModelNhaCC.addRow(row);
		}
		tableNhaCC.setModel(tableModelNhaCC);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FrmCapNhatNhaCungCap().setVisible(true);
	}
    
	//mouseClicked
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tableNhaCC.getSelectedRow();
		txtMaNhaCungCap.setText(tableModelNhaCC.getValueAt(row, 0).toString());
		txtTenNhaCungCap.setText(tableModelNhaCC.getValueAt(row, 1).toString());
	    	
	}
	
	//actionPerformed
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
		if (src.equals(btnXoaTrang)) {
			xoaTrang();
		}
		if (src.equals(btnXoa)) {
			xoaNhaCungCap();
		}
		if (src.equals(btnThem)) {
			themNhaCungCap();
		}
		if (src.equals(btnCapNhat)) {
			capNhat();
		}
	}  
	
	//hamCapNhat
	private void capNhat() {
		// TODO Auto-generated method stub
		int loiNhac = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn sửa thông tin?", "Chú ý", JOptionPane.YES_NO_OPTION);
		if (loiNhac == JOptionPane.YES_OPTION)
		{
			int row = tableNhaCC.getSelectedRow();
			String maNhaCC = txtMaNhaCungCap.getText();
			String tenNhaCC = txtTenNhaCungCap.getText();
			NhaCungCap nhaCungCap = new NhaCungCap(maNhaCC, tenNhaCC);
			if (row >= 0) {
				if (NhaCungCapDAO.update(nhaCungCap)) {
					tableNhaCC.setValueAt(txtMaNhaCungCap.getText(), row, 0);
					tableNhaCC.setValueAt(txtTenNhaCungCap.getText(), row, 1);
					
					JOptionPane.showMessageDialog(this, "Đã sửa thành công!");
				}
				
			}
		}
	}

	//themNhaCungCap
	private void themNhaCungCap() {
		// TODO Auto-generated method stub
		if (validData() == true)
		{
			String maNhaCC = txtMaNhaCungCap.getText();
			String tenNhaCC = txtTenNhaCungCap.getText();
			
			NhaCungCap nhaCungCap = new NhaCungCap(maNhaCC, tenNhaCC);
			nhaCungCap.toString();
			try {
				nhaCungCap.create(nhaCungCap);
				tableModelNhaCC.addRow(new Object[] {nhaCungCap.getMaNCC(), nhaCungCap.getTenNCC()});
				JOptionPane.showMessageDialog(this, "Đã thêm thành công");
			}
			catch (Exception e1) {
				JOptionPane.showMessageDialog(this, "Lỗi!");
			}
			
		}
	}

	//validData()
	private boolean validData() {
		// TODO Auto-generated method stub
		String maSach = txtMaNhaCungCap.getText();
		if (maSach.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Chưa nhập mã nhà cung cấp");
			return false;
		}
		if (!maSach.matches("^NCC-\\d{3}$")) {
			JOptionPane.showMessageDialog(this, "Mã nhà cung cấp phải theo mẫu NCC-AAA với AAA là ba chữ số bất kì");
			return false;
		}
		String tenSach = txtTenNhaCungCap.getText();
		if (tenSach.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Chưa nhập tên nhà cung cấp");
			return false;
		}
		
		return true;
	}
	
	//xoaNhaCungCap
	private void xoaNhaCungCap() {
		// TODO Auto-generated method stub
		int loiNhac = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", "Chú ý", JOptionPane.YES_NO_OPTION);
		if (loiNhac == JOptionPane.YES_OPTION)
		{
			int row = tableNhaCC.getSelectedRow();
			if (row >= 0)
			{
				String maNhaCungCap = (String) tableNhaCC.getValueAt(row, 0);
				if (nhaCungCapDAO.delete(maNhaCungCap)) {
					tableModelNhaCC.removeRow(row);
					xoaTrang();
				}
			}
			JOptionPane.showMessageDialog(this, "Đã xóa nhà cung cấp này!");
		}
	}

	//xoaTrang
	private void xoaTrang() {
		// TODO Auto-generated method stub
		txtMaNhaCungCap.setText("");
		txtTenNhaCungCap.setText("");
		txtMaNhaCungCap.requestFocus();
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


}
