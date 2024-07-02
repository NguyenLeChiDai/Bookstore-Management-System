package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import connectDB.ConnectDatabase;
import dao.NhaCungCapDAO;
import dao.SanPhamKhacDAO;
import entity.NhaCungCap;
import entity.SanPhamKhac;

import javax.swing.JFrame;

public class FrmCapNhatSanPhamKhac extends JFrame implements ActionListener, MouseListener{

	private JLabel lblTitle, lblMaSP, lblTenSP, lblSoLuong, lblNhaCungCap, lblDonGiaNhap, lblDonGiaBan;
	private JPanel pnlNorth, pnlWest;
	private Box bCTSP, bMaSP, bTenSP, bSoLuong, bNhaCungCap, bDonGiaNhap, bDonGiaBan, bButton, bCen, bEast;
	private JTextField txtMaSP, txtTenSP, txtSoLuong, txtDonGiaNhap, txtDonGiaBan;
	private JComboBox<String> cboNhaCungCap;
	private JButton btnThem, btnXoa, btnCapNhat, btnXoaTrang;
	private DefaultTableModel tableModelSP, tableModelNCC;
	private JTable tableSP, tableNCC;
	NhaCungCapDAO nhaCungCapDAO = new NhaCungCapDAO();
	SanPhamKhacDAO sanPhamKhacDAO = new SanPhamKhacDAO();
	
	public FrmCapNhatSanPhamKhac() {
		ConnectDatabase.getInstance().connect();
		setTitle("Quản lý sản phẩm khác");
		setExtendedState(MAXIMIZED_BOTH);
		
		//north
		pnlNorth = new JPanel();
		this.add(pnlNorth, BorderLayout.NORTH);
		pnlNorth.add(lblTitle = new JLabel("Quản Lý Sản Phẩm Khác"));
		Font fontTitle = new Font ("Arial", Font.BOLD, 25);
		lblTitle.setForeground(Color.BLUE);
		lblTitle.setFont(fontTitle);
		
		//west
		pnlWest = new JPanel();
		this.add(pnlWest, BorderLayout.WEST);
		bCTSP = Box.createVerticalBox();
		bCTSP.setBorder(BorderFactory.createTitledBorder("Chi tiết sản phẩm"));
			//maSP
			bCTSP.add(bMaSP = Box.createHorizontalBox());
			bMaSP.add(lblMaSP = new JLabel("Mã sản phẩm:"));
			bMaSP.add(txtMaSP = new JTextField(16));
			bCTSP.add(Box.createVerticalStrut(10));
			
			//tenSP
			bCTSP.add(bTenSP = Box.createHorizontalBox());
			bTenSP.add(lblTenSP = new JLabel("Tên sản phẩm:"));
			bTenSP.add(txtTenSP = new JTextField(16));
			bCTSP.add(Box.createVerticalStrut(10));
			
			//soLuong
			bCTSP.add(bSoLuong = Box.createHorizontalBox());
			bSoLuong.add(lblSoLuong = new JLabel("Số lượng:"));
			bSoLuong.add(txtSoLuong = new JTextField(16));
			bCTSP.add(Box.createVerticalStrut(10));
			
			//nhaCungCap
			bCTSP.add(bNhaCungCap = Box.createHorizontalBox());
			bNhaCungCap.add(lblNhaCungCap = new JLabel("Nhà cung cấp:"));
			bNhaCungCap.add(cboNhaCungCap = new JComboBox<String>());
			ArrayList<NhaCungCap> danhSachNhaCungCap = nhaCungCapDAO.getAllNhaCungCap();
			for (NhaCungCap nhaCungCap : danhSachNhaCungCap) {
				cboNhaCungCap.addItem(nhaCungCap.getMaNCC());
			}
			bCTSP.add(Box.createVerticalStrut(10));
			
			//donGiaNhap
			bCTSP.add(bDonGiaNhap = Box.createHorizontalBox());
			bDonGiaNhap.add(lblDonGiaNhap = new JLabel("Đơn giá nhập:"));
			bDonGiaNhap.add(txtDonGiaNhap = new JTextField(16));
			bCTSP.add(Box.createVerticalStrut(10));
			
			//donGiaBan
			bCTSP.add(bDonGiaBan = Box.createHorizontalBox());
			bDonGiaBan.add(lblDonGiaBan = new JLabel("Đơn giá bán:"));
			bDonGiaBan.add(txtDonGiaBan = new JTextField(16));
			bCTSP.add(Box.createVerticalStrut(10));
			
			//button
			bCTSP.add(bButton = Box.createHorizontalBox());
			
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
			
			lblMaSP.setPreferredSize(lblTenSP.getPreferredSize());
			lblSoLuong.setPreferredSize(lblTenSP.getPreferredSize());
			lblNhaCungCap.setPreferredSize(lblTenSP.getPreferredSize());
			lblDonGiaNhap.setPreferredSize(lblTenSP.getPreferredSize());
			lblDonGiaBan.setPreferredSize(lblTenSP.getPreferredSize());
		pnlWest.add(bCTSP);
		
		//center
		bCen = Box.createVerticalBox();
		bCen.setBorder(BorderFactory.createTitledBorder("Danh sách sản phẩm"));
			//tableSP
			String [] headersSP = "Mã sản phẩm; Tên sản phẩm; Số lượng; Nhà cung cấp; Đơn giá nhập; Đơn giá bán".split(";");
			tableModelSP = new DefaultTableModel(headersSP, 0);
			JScrollPane scrollSP = new JScrollPane();
			scrollSP.setViewportView(tableSP = new JTable(tableModelSP));
			tableSP.setRowHeight(25);
			tableSP.setAutoCreateRowSorter(true);
			tableSP.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
			tableSP.addMouseListener(this);
			insertSPK();
			bCen.add(scrollSP);
		this.add(bCen, BorderLayout.CENTER);
		
		//east
		bEast = Box.createVerticalBox();
			//tableNCC
			String [] headersNCC = "Mã nhà cung cấp; Tên nhà cung cấp".split(";");
			tableModelNCC = new DefaultTableModel(headersNCC, 0);
			JScrollPane scrollNCC = new JScrollPane();
			scrollNCC.setViewportView(tableNCC = new JTable(tableModelNCC));
			scrollNCC.setBorder(BorderFactory.createTitledBorder("Danh sách nhà cung cấp"));
			tableNCC.setRowHeight(25);
			tableNCC.setAutoCreateRowSorter(true);
			tableNCC.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
			tableNCC.addMouseListener(this);
			insertNhaCungCap();
			bEast.add(scrollNCC);
		this.add(bEast, BorderLayout.EAST);
	}
		
	private void insertSPK() {
		// TODO Auto-generated method stub
		SanPhamKhacDAO danhSachSP = new SanPhamKhacDAO();
		List<SanPhamKhac> list = danhSachSP.getAllSanPhamKhac();
		for (SanPhamKhac sanPhamKhac : list) {
			String [] row = {sanPhamKhac.getMaSP(), sanPhamKhac.getTenSP(), Integer.toString(sanPhamKhac.getSoLuong()), sanPhamKhac.getNhaCC().getMaNCC(), Double.toString(sanPhamKhac.getDonGiaNhap()), Double.toString(sanPhamKhac.getDonGiaBan())};
			tableModelSP.addRow(row);
		}
		tableSP.setModel(tableModelSP);
	}

	private void insertNhaCungCap() {
		// TODO Auto-generated method stub
		NhaCungCapDAO danhSachNhaCungCap = new NhaCungCapDAO();
		List<NhaCungCap> list = danhSachNhaCungCap.getAllNhaCungCap();
		for (NhaCungCap nhaCungCap : list) {
			String [] row = {nhaCungCap.getMaNCC(), nhaCungCap.getTenNCC()};
			tableModelNCC.addRow(row);
		}
		tableNCC.setModel(tableModelNCC);
	}

	public static void main(String[] args) {
		new FrmCapNhatSanPhamKhac().setVisible(true);
	}
		
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tableSP.getSelectedRow();
		txtMaSP.setText(tableModelSP.getValueAt(row, 0).toString());
		txtTenSP.setText(tableModelSP.getValueAt(row, 1).toString());
		txtSoLuong.setText(tableModelSP.getValueAt(row, 2).toString());
		cboNhaCungCap.setSelectedItem(tableModelSP.getValueAt(row, 3).toString());
		txtDonGiaNhap.setText(tableModelSP.getValueAt(row, 4).toString());
		txtDonGiaBan.setText(tableModelSP.getValueAt(row, 5).toString());
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
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
		if (src.equals(btnThem)) {
			themSP();
		}
		if (src.equals(btnCapNhat)) {
			capNhat();
		}
		if (src.equals(btnXoa)) {
			xoaSP();
		}
		if (src.equals(btnXoaTrang)) {
			xoaTrang();
		}
	}

	private void xoaTrang() {
		// TODO Auto-generated method stub
		txtMaSP.setText("");
		txtTenSP.setText("");
		txtSoLuong.setText("");
		txtDonGiaNhap.setText("");
		txtDonGiaBan.setText("");
		txtMaSP.requestFocus();
	}

	private void xoaSP() {
		// TODO Auto-generated method stub
				int loiNhac = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", "Chú ý", JOptionPane.YES_NO_OPTION);
				if (loiNhac == JOptionPane.YES_OPTION)
				{
					int row = tableSP.getSelectedRow();
					if (row >= 0)
					{
						String maSP = (String) tableSP.getValueAt(row, 0);
						if (sanPhamKhacDAO.delete(maSP)) {
							tableModelSP.removeRow(row);
							xoaTrang();
						}
					}
					JOptionPane.showMessageDialog(this, "Đã xóa thông tin sản phẩm này!");
				}
	}

	private void capNhat() {
		// TODO Auto-generated method stub
		int loiNhac = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn sửa thông tin?", "Chú ý", JOptionPane.YES_NO_OPTION);
		if (loiNhac == JOptionPane.YES_OPTION)
		{
			int row = tableSP.getSelectedRow();
			String maSP = txtMaSP.getText();
			String tenSP = txtTenSP.getText();
			int soLuong = Integer.parseInt(txtSoLuong.getText());
			String maNhaCungCap = cboNhaCungCap.getSelectedItem().toString();
			NhaCungCap nhaCungCap = new NhaCungCap(maNhaCungCap);
			double donGiaNhap = Double.parseDouble(txtDonGiaNhap.getText());
			double donGiaBan = Double.parseDouble(txtDonGiaBan.getText());
			SanPhamKhac sanPhamKhac = new SanPhamKhac(maSP, tenSP, soLuong, nhaCungCap, donGiaNhap, donGiaBan);
			if (row >= 0) {
				if (sanPhamKhacDAO.update(sanPhamKhac)) {
					tableSP.setValueAt(txtMaSP.getText(), row, 0);
					tableSP.setValueAt(txtTenSP.getText(), row, 1);
					tableSP.setValueAt(txtSoLuong.getText(), row, 2);
					tableSP.setValueAt(cboNhaCungCap.getSelectedItem().toString(), row, 3);
					tableSP.setValueAt(txtDonGiaNhap.getText(), row, 4);
					tableSP.setValueAt(txtDonGiaBan.getText(), row, 5);
					JOptionPane.showMessageDialog(this, "Đã sửa thành công!");
				}
				
			}
		}
	}

	private void themSP() {
		// TODO Auto-generated method stub
		if (validData() == true) {
			String maSP = txtMaSP.getText();
			String tenSP = txtTenSP.getText();
			int soLuong = Integer.parseInt(txtSoLuong.getText());
			String maNhaCungCap = cboNhaCungCap.getSelectedItem().toString();
			NhaCungCap nhaCungCap = new NhaCungCap(maNhaCungCap);
			Double donGiaNhap = Double.parseDouble(txtDonGiaNhap.getText());
			Double donGiaBan = Double.parseDouble(txtDonGiaBan.getText());
			SanPhamKhac sanPhamKhac = new SanPhamKhac(maSP, tenSP, soLuong, nhaCungCap, donGiaNhap, donGiaBan);
			try {
				sanPhamKhacDAO.create(sanPhamKhac);
				tableModelSP.addRow(new Object[] {sanPhamKhac.getMaSP(), sanPhamKhac.getTenSP(), Integer.toString(sanPhamKhac.getSoLuong()), sanPhamKhac.getNhaCC().getMaNCC(), Double.toString(sanPhamKhac.getDonGiaNhap()), Double.toString(sanPhamKhac.getDonGiaBan())});
				JOptionPane.showMessageDialog(this, "Đã thêm thành công");
			}
			catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(this, "Mã đầu sách này đã tồn tại trong hệ thống!");
			}
		}
	}

	private boolean validData() {
		//maSP
		String maSP = txtMaSP.getText();
		if (maSP.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Chưa nhập mã sản phẩm!");
			return false;
		}
		if (!maSP.matches("^SP-\\d{3}$")) {
			JOptionPane.showMessageDialog(this, "Mã sản phẩm phải theo mẫu SP-XXX với XXX là ba chữ số bất kì");
			return false;
		}
		
		//tenSP
		String tenSP = txtTenSP.getText();
		if (tenSP.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Chưa nhập tên sản phẩm");
			return false;
		}
		
		//soLuong
		if (txtSoLuong.getText().trim().length() == 0) {
			JOptionPane.showMessageDialog(this, "Chưa nhập số lượng");
			return false;
		}
		int soLuong = Integer.parseInt(txtSoLuong.getText());
		if (soLuong < 0)
		{
			JOptionPane.showMessageDialog(this, "Số lượng phải là số lớn hơn 0");
			return false;
		}
		
		//donGiaNhap
		if (txtDonGiaNhap.getText().trim().length() == 0) {
			JOptionPane.showMessageDialog(this, "Chưa nhập đơn giá nhập");
			return false;
		}
		double donGiaNhap = Double.parseDouble(txtDonGiaNhap.getText());
		if (donGiaNhap < 0) {
			JOptionPane.showMessageDialog(this, "Đơn giá nhập phải là số lớn hơn 0");
			return false;
		}
		
		//donGiaBan
		if (txtDonGiaBan.getText().trim().length() == 0) {
			JOptionPane.showMessageDialog(this, "Chưa nhập đơn giá bán");
			return false;
		}
		double donGiaBan = Double.parseDouble(txtDonGiaBan.getText());
		if (donGiaBan < 0) {
			JOptionPane.showMessageDialog(this, "Đơn giá bán phải là số lớn hơn 0");
			return false;
		}
		return true;
	}
	

}
