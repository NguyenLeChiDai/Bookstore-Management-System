package gui;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDatabase;
import dao.CaLamDAO;
import dao.NhanVienDAO;
import entity.CaLamViec;
import entity.NhanVien;


public class FrmCapNhatNhanVien extends JFrame implements ActionListener, MouseListener {
	private JPanel pnlNorth, pnlWest;
	private Box bTTNV, bMaNV, bTenNV, bSoDT, bEmail, bDiaChi, bButton, bCen, bCaLamViec;
	private JLabel lblTitle, lblMaNV, lblTenNV, lblSoDT, lblEmail, lblDiaChi, lblCa ;
	private JTextField txtMaNV, txtTenNV, txtSoDT, txtEmail, txtDiaChi, txtCa;
	private JButton btnThem, btnXoa, btnCapNhat, btnXoaTrang;
	private DefaultTableModel tableModel;
	private JTable table;
	private JComboBox cboCaLamViec;
	NhanVienDAO nhanvienDAO = new NhanVienDAO();
	CaLamDAO caLamDAO = new CaLamDAO();
	
	public FrmCapNhatNhanVien() {
		ConnectDatabase.getInstance().connect();
		setTitle("Quản lý nhân viên");
		setExtendedState(MAXIMIZED_BOTH);
		
		//north
		pnlNorth = new JPanel();
		this.add(pnlNorth, BorderLayout.NORTH);
		pnlNorth.add(lblTitle = new JLabel("QUẢN LÝ NHÂN VIÊN"));
		lblTitle.setFont(new Font("Arial", Font.BOLD, 30));
		lblTitle.setForeground(Color.blue);
				
		//west
		pnlWest = new JPanel();
		this.add(pnlWest, BorderLayout.WEST);
		bTTNV = Box.createVerticalBox();
		bTTNV.setBorder(BorderFactory.createTitledBorder("Chi tiết nhân viên")); 
		pnlWest.add(bTTNV);
			//maNhanVien
			bTTNV.add(bMaNV = Box.createHorizontalBox());
			bMaNV.add(lblMaNV = new JLabel("Mã nhân viên:"));
			bMaNV.add(txtMaNV = new JTextField(16));
			bTTNV.add(Box.createVerticalStrut(10));
			
			//tenNhanVien
			bTTNV.add(bTenNV = Box.createHorizontalBox());
			bTenNV.add(lblTenNV = new JLabel("Tên nhân viên:"));
			bTenNV.add(txtTenNV = new JTextField(16));
			bTTNV.add(Box.createVerticalStrut(10));
			
			//soDienThoai
			bTTNV.add(bSoDT = Box.createHorizontalBox());
			bSoDT.add(lblSoDT = new JLabel("Số điện thoại:"));
			bSoDT.add(txtSoDT = new JTextField(16));
			bTTNV.add(Box.createVerticalStrut(10));
			
			//email
			bTTNV.add(bEmail = Box.createHorizontalBox());
			bEmail.add(lblEmail = new JLabel("Email:"));
			bEmail.add(txtEmail = new JTextField(16));
			bTTNV.add(Box.createVerticalStrut(10));
			
			//diaChi
			bTTNV.add(bDiaChi = Box.createHorizontalBox());
			bDiaChi.add(lblDiaChi = new JLabel("Địa chỉ:"));
			bDiaChi.add(txtDiaChi = new JTextField(16));
			bTTNV.add(Box.createVerticalStrut(10));
			
			//caLamViec
			bTTNV.add(bCaLamViec = Box.createHorizontalBox());
			bCaLamViec.add(lblCa = new JLabel("Ca làm việc:"));
			bCaLamViec.add(cboCaLamViec = new JComboBox<String>());
			cboCaLamViec.setEditable(false);
			caLamDAO = new CaLamDAO();
			ArrayList<CaLamViec> danhSachCaLamViec = caLamDAO.getAllCaLam();
			for (CaLamViec ca : danhSachCaLamViec) {
				cboCaLamViec.addItem(ca.getMaCalamViec());
			}
			bTTNV.add(Box.createVerticalStrut(10));	
			
			//button
			bTTNV.add(bButton = Box.createHorizontalBox());
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
			
			
			lblMaNV.setPreferredSize(lblTenNV.getPreferredSize());
			lblSoDT.setPreferredSize(lblTenNV.getPreferredSize());
			lblEmail.setPreferredSize(lblTenNV.getPreferredSize());
			lblDiaChi.setPreferredSize(lblTenNV.getPreferredSize());
			lblCa.setPreferredSize(lblTenNV.getPreferredSize());
			
			//center
			bCen = Box.createVerticalBox();
			bCen.setBorder(BorderFactory.createTitledBorder("Danh sách nhân viên"));
				//table
				String [] headers = "Mã nhân viên; Tên nhân viên; Số điện thoại; Email; Địa chỉ; Ca".split(";");
				tableModel = new DefaultTableModel(headers, 0);
				JScrollPane scroll = new JScrollPane();
				scroll.setViewportView(table = new JTable(tableModel));
				table.setRowHeight(25);
				table.setAutoCreateRowSorter(true);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
				table.addMouseListener(this);
				bCen.add(scroll);
			this.add(bCen, BorderLayout.CENTER);
			insertData();
	}
	
	private void insertData() {
		// TODO Auto-generated method stub
		NhanVienDAO danhSachNhanVien = new NhanVienDAO();
		
		List<NhanVien> list = danhSachNhanVien.getAllNhanVien();
		for (NhanVien nhanvien : list) {
			String [] row = {nhanvien.getMaNV(), nhanvien.getTenNV(), nhanvien.getSoDT(), nhanvien.getEmail(), nhanvien.getDiaChi(), nhanvien.getCaLamViec().getMaCalamViec()};
			tableModel.addRow(row);
		}
		table.setModel(tableModel);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		txtMaNV.setText(tableModel.getValueAt(row, 0).toString());
		txtTenNV.setText(tableModel.getValueAt(row, 1).toString());
		txtSoDT.setText(tableModel.getValueAt(row, 2).toString());
		txtEmail.setText(tableModel.getValueAt(row, 3).toString());
		txtDiaChi.setText(tableModel.getValueAt(row, 4).toString());
		cboCaLamViec.setSelectedItem(tableModel.getValueAt(row, 5).toString());
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
		if (src.equals(btnXoaTrang)) {
			xoaTrang();
		}
		if (src.equals(btnXoa)) {
			xoaNhanVien();
		}
		if (src.equals(btnThem)) {
			themNhanVien();
		}
		if (src.equals(btnCapNhat)) {
			capNhat();
		}
	}
	
	private void capNhat() {
		// TODO Auto-generated method stub
		int loiNhac = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn sửa thông tin nhân viên này?", "Chú ý", JOptionPane.YES_NO_OPTION);
		if (loiNhac == JOptionPane.YES_OPTION)
				{
					int row = table.getSelectedRow();
					String maNV = txtMaNV.getText();
					String tenNV = txtTenNV.getText();
					String soDT = txtSoDT.getText();
					String email = txtEmail.getText();
					String diaChi = txtDiaChi.getText();
					String maCaLamViec = cboCaLamViec.getSelectedItem().toString();
					CaLamViec caLamViec = new CaLamViec(maCaLamViec);
					NhanVien nhanvien = new NhanVien(maNV, tenNV, soDT, email, diaChi, caLamViec);
					try {
						if (row >= 0) {
							if (validData() == true) {
								if (nhanvienDAO.update(nhanvien)) {
									table.setValueAt(txtMaNV.getText(), row, 0);
									table.setValueAt(txtTenNV.getText(), row, 1);
									table.setValueAt(txtSoDT.getText(), row, 2);
									table.setValueAt(txtEmail.getText(), row, 3);
									table.setValueAt(txtDiaChi.getText(), row, 4);
									table.setValueAt(cboCaLamViec.getSelectedItem().toString(), row, 5);
									JOptionPane.showMessageDialog(this, "Đã sửa thông tin nhân viên thành công!");
								}
							}
						}
					}
					catch (Exception e) {
						JOptionPane.showMessageDialog(this, "Lỗi");
					}
				}
			}

	//themNhanVien
	private void themNhanVien() {
		if (validData() == true)
		{
			String maNV = txtMaNV.getText();
			String tenNV = txtTenNV.getText();
			String soDT = txtSoDT.getText();
			String email = txtEmail.getText();
			String diachi = txtDiaChi.getText();
			String maCaLamViec = cboCaLamViec.getSelectedItem().toString();
			CaLamViec caLamViec = new CaLamViec(maCaLamViec);
			NhanVien nhanvien = new NhanVien(maNV, tenNV, soDT, email, diachi, caLamViec);
			nhanvien.toString();
			try {
				nhanvienDAO.create(nhanvien);
				tableModel.addRow(new Object[] {nhanvien.getMaNV(), nhanvien.getTenNV(), nhanvien.getSoDT(), nhanvien.getEmail(), nhanvien.getDiaChi(), nhanvien.getCaLamViec().getMaCalamViec()});
				JOptionPane.showMessageDialog(this, "Đã thêm thành công");
			}
			catch (Exception e1) {
				JOptionPane.showMessageDialog(this, "Lỗi!");
			}
		}
	}
	private boolean validData() {
		// TODO Auto-generated method stub
		String maNV = txtMaNV.getText();
		if (maNV.isEmpty())
		{
			JOptionPane.showMessageDialog(this, "Chưa nhập mã nhân viên!");
			return false;
		}
		if (!maNV.matches("^NV-\\d{3}"))
		{
			JOptionPane.showMessageDialog(this, "Mã nhân viên phải theo mẫu NV-XXX, với XXX là ba số bất kì");
			return false;
		}
		String tenNV = txtTenNV.getText();
		if (tenNV.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Chưa nhập tên nhân viên");
			return false;
		}
		String soDT = txtSoDT.getText();
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
	
	private void xoaNhanVien() {
		// TODO Auto-generated method stub
				int loiNhac = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", "Chú ý", JOptionPane.YES_NO_OPTION);
				if (loiNhac == JOptionPane.YES_OPTION)
				{
					int row = table.getSelectedRow();
					if (row >= 0)
					{
						String maNV = (String) table.getValueAt(row, 0);
						if (nhanvienDAO.delete(maNV)) {
							tableModel.removeRow(row);
							xoaTrang();
						}
					}
					JOptionPane.showMessageDialog(this, "Đã xóa nhân viên này!");
				}
			}
		
	private void xoaTrang() {
		// TODO Auto-generated method stub
				txtMaNV.setText("");
				txtTenNV.setText("");
				txtSoDT.setText("");
				txtEmail.setText("");
				txtDiaChi.setText("");
				txtMaNV.requestFocus();
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FrmCapNhatNhanVien().setVisible(true);
	}

}
