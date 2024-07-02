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
import java.sql.SQLException;
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
import dao.LoaiSachDAO;
import dao.NhaXuatBanDAO;
import dao.NhanVienDAO;
import dao.SachDAO;
import dao.TacGiaDAO;
import entity.LoaiSach;
import entity.NhaXuatBan;
import entity.NhanVien;
import entity.Sach;
import entity.TacGia;

public class FrmCapNhatSach extends JFrame implements ActionListener, MouseListener {

	private JPanel pnlNorth, pnlWest, pnlCTS, pnlTimTacGia;
	private JLabel lblTitle, lblMaSach, lblTenSach, lblISBN, lblSoTrang, lblSoLuong, lblLoaiSach, lblTacGia, lblNhaXB, lblDonGiaBan, lblDonGiaNhap, lblTimTacGia;
	private JTextField txtMaSach, txtTenSach, txtISBN, txtSoTrang, txtTacGia, txtDonGiaBan, txtSoLuong, txtDonGiaNhap, txtTimTacGia;
	private Box bWest, bTimTacGia, bCTS, bEast, bMaSach, bTenSach, bISBN, bSoTrang, bSoLuong, bButton, bTable, bCen, bLoaiSach, bTacGia, bNhaXB, bDonGiaBan, bDonGiaNhap, bTableTacGia;
	private JButton btnThem, btnXoa, btnCapNhat, btnXoaTrang, btnTim;
	private DefaultTableModel tableModelSach, tableModelLoaiSach, tableModelNhaXB, tableModelTacGia;
	private JTable tableSach, tableLoaiSach, tableNhaXB, tableTacGia;
	private JComboBox cboLoaiSach, cboNhaXB;
	NhaXuatBanDAO nhaXuatBanDAO = new NhaXuatBanDAO();
	LoaiSachDAO loaiSachDAO = new LoaiSachDAO();
	SachDAO sachDAO = new SachDAO();
	private JButton btnLamMoi;

	public FrmCapNhatSach() {
		ConnectDatabase.getInstance().connect();
		setTitle("Quản lý sách");
		setExtendedState(MAXIMIZED_BOTH);
		setResizable(true);
		
		//north
		pnlNorth = new JPanel();
		this.add(pnlNorth, BorderLayout.NORTH);
		pnlNorth.add(lblTitle = new JLabel("QUẢN LÝ SÁCH"));
		lblTitle.setFont(new Font("Arial", Font.BOLD, 30));
		lblTitle.setForeground(Color.blue);
		
		//west
		bWest = Box.createVerticalBox();
		this.add(bWest, BorderLayout.WEST);
		
			//chiTietSach
			pnlCTS = new JPanel();
			bCTS = Box.createVerticalBox();
			bCTS.setBorder(BorderFactory.createTitledBorder("Chi tiết sách")); 
			pnlCTS.add(bCTS);
			bWest.add(pnlCTS);
			
				//maSach
				bCTS.add(bMaSach = Box.createHorizontalBox());
				bMaSach.add(lblMaSach = new JLabel("Mã sách:"));
				bMaSach.add(txtMaSach = new JTextField(16));
				bCTS.add(Box.createVerticalStrut(10));
			
				//tenSach
				bCTS.add(bTenSach = Box.createHorizontalBox());
				bTenSach.add(lblTenSach = new JLabel("Tên sách:"));
				bTenSach.add(txtTenSach = new JTextField(16));
				bCTS.add(Box.createVerticalStrut(10));
				
				//iSBN
				bCTS.add(bISBN = Box.createHorizontalBox());
				bISBN.add(lblISBN = new JLabel("ISBN:"));
				bISBN.add(txtISBN = new JTextField(16));
				bCTS.add(Box.createVerticalStrut(10));
				
				//soTrang
				bCTS.add(bSoTrang = Box.createHorizontalBox());
				bSoTrang.add(lblSoTrang = new JLabel("Số trang:"));
				bSoTrang.add(txtSoTrang = new JTextField(16));
				bCTS.add(Box.createVerticalStrut(10));
				
				//soLuong
				bCTS.add(bSoLuong = Box.createHorizontalBox());
				bSoLuong.add(lblSoLuong = new JLabel("Số lượng:"));
				bSoLuong.add(txtSoLuong = new JTextField(16));
				bCTS.add(Box.createVerticalStrut(10));
				
				//loaiSach
				bCTS.add(bLoaiSach = Box.createHorizontalBox());
				bLoaiSach.add(lblLoaiSach = new JLabel("Loại sách:"));
				bLoaiSach.add(cboLoaiSach = new JComboBox<String>());
				cboLoaiSach.setEditable(false);
				loaiSachDAO = new LoaiSachDAO();
				ArrayList<LoaiSach> danhSachLoaiSach = loaiSachDAO.getAllLoaiSach();
				for (LoaiSach loaiSach : danhSachLoaiSach) {
					cboLoaiSach.addItem(loaiSach.getMaLoai());
				}
				bCTS.add(Box.createVerticalStrut(10));
				
				//tacGia
				bCTS.add(bTacGia = Box.createHorizontalBox());
				bTacGia.add(lblTacGia = new JLabel("Tác giả:"));
				bTacGia.add(txtTacGia = new JTextField(16));
				bCTS.add(Box.createVerticalStrut(10));
				
				//nhaXuatBan
				bCTS.add(bNhaXB = Box.createHorizontalBox());
				bNhaXB.add(lblNhaXB = new JLabel("Nhà xuất bản:"));
				bNhaXB.add(cboNhaXB = new JComboBox<String>());
				cboNhaXB.setEditable(false);
				nhaXuatBanDAO = new NhaXuatBanDAO();
				ArrayList<NhaXuatBan> danhSachNhaXuatBan = nhaXuatBanDAO.getAllNhaXuatBan();
				for (NhaXuatBan nhaXB : danhSachNhaXuatBan) {
					cboNhaXB.addItem(nhaXB.getMaNhaXB());
				}
				bCTS.add(Box.createVerticalStrut(10));
				
				//donGiaBan
				bCTS.add(bDonGiaBan = Box.createHorizontalBox());
				bDonGiaBan.add(lblDonGiaBan= new JLabel("Đơn giá bán:"));
				bDonGiaBan.add(txtDonGiaBan = new JTextField(16));
				bCTS.add(Box.createVerticalStrut(10));
				
				//donGiaNhap
				bCTS.add(bDonGiaNhap = Box.createHorizontalBox());
				bDonGiaNhap.add(lblDonGiaNhap= new JLabel("Đơn giá nhập:"));
				bDonGiaNhap.add(txtDonGiaNhap = new JTextField(16));
				bCTS.add(Box.createVerticalStrut(10));
				
				//button
				bCTS.add(bButton = Box.createHorizontalBox());

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
				
				lblMaSach.setPreferredSize(lblDonGiaNhap.getPreferredSize());
				lblTenSach.setPreferredSize(lblDonGiaNhap.getPreferredSize());
				lblISBN.setPreferredSize(lblDonGiaNhap.getPreferredSize());
				lblSoTrang.setPreferredSize(lblDonGiaNhap.getPreferredSize());
				lblSoLuong.setPreferredSize(lblDonGiaNhap.getPreferredSize());
				lblLoaiSach.setPreferredSize(lblDonGiaNhap.getPreferredSize());
				lblTacGia.setPreferredSize(lblDonGiaNhap.getPreferredSize());
				lblDonGiaBan.setPreferredSize(lblDonGiaNhap.getPreferredSize());
			
			//loaiSach
			String [] headersLoaiSach = "Mã loại sách; Tên loại sách".split(";");
			tableModelLoaiSach = new DefaultTableModel(headersLoaiSach, 0);
			JScrollPane scrollLoaiSach = new JScrollPane();
			scrollLoaiSach.setBorder(BorderFactory.createTitledBorder("Danh sách loại sách"));
			scrollLoaiSach.setViewportView(tableLoaiSach = new JTable(tableModelLoaiSach));
			tableLoaiSach.setRowHeight(30);
			tableLoaiSach.setAutoCreateRowSorter(true);
			tableLoaiSach.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
			tableLoaiSach.addMouseListener(this);
			bWest.add(scrollLoaiSach);
			insertDataLoaiSach();
			
		//center
		bCen = Box.createVerticalBox();
		bCen.setBorder(BorderFactory.createTitledBorder("Danh sách sách"));
			//tableSach
			String [] headers = "Mã sách; Tên sách; ISBN; Số trang; Số lượng; Loại sách; Tác giả; Nhà xuất bản; Đơn giá bán; Đơn giá nhập".split(";");
			tableModelSach = new DefaultTableModel(headers, 0);
			JScrollPane scrollSach = new JScrollPane();
			scrollSach.setViewportView(tableSach = new JTable(tableModelSach));
			tableSach.setRowHeight(25);
			tableSach.setAutoCreateRowSorter(true);
			tableSach.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
			tableSach.addMouseListener(this);
			bCen.add(scrollSach);
			insertDataSach();
		this.add(bCen, BorderLayout.CENTER);
		
		//east
		bEast = Box.createVerticalBox();
			//tableTacGia
			pnlTimTacGia = new JPanel();
			bTimTacGia = Box.createHorizontalBox();
			pnlTimTacGia.add(bTimTacGia);
			bTimTacGia.add(lblTimTacGia = new JLabel("Nhập tên tác giả:"));
			bTimTacGia.add(txtTimTacGia = new JTextField(16));
			bTimTacGia.add(btnTim = new JButton("Tìm"));
			btnTim.addActionListener(this);
			bTimTacGia.add(Box.createHorizontalStrut(10));
			bTimTacGia.add(btnLamMoi = new JButton("Làm mới"));
			btnLamMoi.addActionListener(this);
			bEast.add(pnlTimTacGia);
		
			String [] headersTacGia = "Mã tác giả; Tên tác giả".split(";");
			tableModelTacGia = new DefaultTableModel(headersTacGia, 0);
			JScrollPane scrollTacGia = new JScrollPane();
			scrollTacGia.setBorder(BorderFactory.createTitledBorder("Danh sách tác giả"));
			scrollTacGia.setViewportView(tableTacGia = new JTable(tableModelTacGia));
			tableTacGia.setRowHeight(25);
			tableTacGia.setAutoCreateRowSorter(true);
			tableTacGia.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
			tableTacGia.addMouseListener(this);
			bEast.add(scrollTacGia);
			insertDataTacGia();	
			
			//tableNhaXB
			String [] headersNhaXB = "Mã nhà xuất bản; Tên nhà xuất bản".split(";");
			tableModelNhaXB = new DefaultTableModel(headersNhaXB, 0);
			JScrollPane scrollNhaXB = new JScrollPane();
			scrollNhaXB.setBorder(BorderFactory.createTitledBorder("Danh sách nhà xuất bản"));
			scrollNhaXB.setViewportView(tableNhaXB = new JTable(tableModelTacGia));
			tableNhaXB.setRowHeight(25);
			tableNhaXB.setAutoCreateRowSorter(true);
			tableNhaXB.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
			tableNhaXB.addMouseListener(this);
			bEast.add(scrollNhaXB);
			insertDataNhaXB();	
		this.add(bEast, BorderLayout.EAST);		
		
	}
	
	private void insertDataTacGia() {
		// TODO Auto-generated method stub
		TacGiaDAO danhSachTacGia = new TacGiaDAO();
		List<TacGia> list = danhSachTacGia.getAllTacGia();
		for (TacGia tacGia : list) {
			String [] row = {tacGia.getMaTacGia(), tacGia.getTenTacGia()};
			tableModelTacGia.addRow(row);
		}
		tableTacGia.setModel(tableModelTacGia);
	}

	private void insertDataLoaiSach() {
		LoaiSachDAO danhSachLoaiSach = new LoaiSachDAO();
		List<LoaiSach> list = danhSachLoaiSach.getAllLoaiSach();
		for (LoaiSach loaiSach : list) {
			String [] row = {loaiSach.getMaLoai(), loaiSach.getTenLoai()};
			tableModelLoaiSach.addRow(row);
		}
		tableLoaiSach.setModel(tableModelLoaiSach);
	}
	
	private void insertDataNhaXB() {
		NhaXuatBanDAO danhSachNhaXB = new NhaXuatBanDAO();
		List<NhaXuatBan> list = danhSachNhaXB.getAllNhaXuatBan();
		for (NhaXuatBan nhaXB : list) {
			String [] row = {nhaXB.getMaNhaXB(), nhaXB.getTenNhaXB()};
			tableModelNhaXB.addRow(row);
		}
		tableNhaXB.setModel(tableModelNhaXB);
	}
	
	private void insertDataSach() {
		// TODO Auto-generated method stub
		SachDAO danhSachSach = new SachDAO();
		List<Sach> list = danhSachSach.getAllSach();
		for (Sach sach : list) {
			String [] row = {sach.getMaSach(), sach.getTenSach(), sach.getiSBN(), Integer.toString(sach.getSoTrang()), Integer.toString(sach.getSoLuong()), sach.getLoaiSach().getMaLoai(), sach.getTacGia().getMaTacGia(), sach.getNhaXB().getMaNhaXB(), Double.toString(sach.getDonGiaBan()), Double.toString(sach.getDonGiaNhap())};
			tableModelSach.addRow(row);
		}
		tableSach.setModel(tableModelSach);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FrmCapNhatSach().setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tableSach.getSelectedRow();
		txtMaSach.setText(tableModelSach.getValueAt(row, 0).toString());
		txtTenSach.setText(tableModelSach.getValueAt(row, 1).toString());
		txtISBN.setText(tableModelSach.getValueAt(row, 2).toString());
		txtSoTrang.setText(tableModelSach.getValueAt(row, 3).toString());
		txtSoLuong.setText(tableModelSach.getValueAt(row, 4).toString());
		cboLoaiSach.setSelectedItem(tableModelSach.getValueAt(row, 5).toString());
		txtTacGia.setText(tableModelSach.getValueAt(row, 6).toString());
		cboNhaXB.setSelectedItem(tableModelSach.getValueAt(row, 7).toString());
		txtDonGiaBan.setText(tableModelSach.getValueAt(row, 8).toString());
		txtDonGiaNhap.setText(tableModelSach.getValueAt(row, 9).toString());
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
			xoaSach();
		}
		if (src.equals(btnThem)) {
			themSach();
		}
		if (src.equals(btnCapNhat)) {
			capNhat();
		}
		if (src.equals(btnTim)) {
			tim();
		}
		if (src.equals(btnLamMoi)) {
			txtTimTacGia.setText("");
			insertDataTacGia();
		}
	}
	
	void insertData(List<TacGia> danhSachTG) {
        DefaultTableModel modelTacGia = (DefaultTableModel) tableTacGia.getModel();
        modelTacGia.setRowCount(0);
        danhSachTG.forEach((tacGia)->{modelTacGia.addRow(new Object[] {tacGia.getMaTacGia(), tacGia.getTenTacGia()});
        });
    }
	
	private void tim() {
		// TODO Auto-generated method stub
		String tenTacGia = txtTimTacGia.getText();
		TacGia tacGia = new TacGia();
		tacGia.setTenTacGia(tenTacGia);
		if (txtTimTacGia.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "Xin mời nhập thông tin tìm kiếm");
		}
		else
		{
			if (!txtTimTacGia.getText().isEmpty()) {
         		 insertData(TacGiaDAO.FindTenTacGia(tacGia));
           }
		}
	}

	private void capNhat() {
		// TODO Auto-generated method stub
		int loiNhac = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn sửa thông tin?", "Chú ý", JOptionPane.YES_NO_OPTION);
		if (loiNhac == JOptionPane.YES_OPTION)
		{
			int row = tableSach.getSelectedRow();
			String maSach = txtMaSach.getText();
			String tenSach = txtTenSach.getText();
			String ISBN = txtISBN.getText();
			int soTrang = Integer.parseInt(txtSoTrang.getText());
			int soLuong = Integer.parseInt(txtSoLuong.getText());
			String maLoai = cboLoaiSach.getSelectedItem().toString();
			LoaiSach loaiSach = new LoaiSach(maLoai);
			String maTacGia = txtTacGia.getText();
			TacGia tacGia = new TacGia(maTacGia);
			String maNhaXB = cboNhaXB.getSelectedItem().toString();
			NhaXuatBan nhaXB = new NhaXuatBan(maNhaXB);
			double donGiaBan = Double.parseDouble(txtDonGiaBan.getText());
			double donGiaNhap = Double.parseDouble(txtDonGiaNhap.getText());
			Sach sach = new Sach(maSach, tenSach, ISBN, loaiSach, tacGia, nhaXB, soTrang, soLuong, donGiaBan, donGiaNhap);
			if (row >= 0) {
				if (sachDAO.update(sach)) {
					tableSach.setValueAt(txtMaSach.getText(), row, 0);
					tableSach.setValueAt(txtTenSach.getText(), row, 1);
					tableSach.setValueAt(txtISBN.getText(), row, 2);
					tableSach.setValueAt(txtSoTrang.getText(), row, 3);
					tableSach.setValueAt(txtSoLuong.getText(), row, 4);
					tableSach.setValueAt(cboLoaiSach.getSelectedItem().toString(), row, 5);
					tableSach.setValueAt(txtTacGia.getText(), row, 6);
					tableSach.setValueAt(cboNhaXB.getSelectedItem().toString(), row, 7);
					tableSach.setValueAt(txtDonGiaBan.getText(), row, 8);
					tableSach.setValueAt(txtDonGiaNhap.getText(), row, 9);
					JOptionPane.showMessageDialog(this, "Đã sửa thành công!");
				}
				
			}
		}
	}

	private void themSach() {
		// TODO Auto-generated method stub
		if (validData() == true)
		{
			String maSach = txtMaSach.getText();
			String tenSach = txtTenSach.getText();
			String iSBN = txtISBN.getText();
			int soTrang = Integer.parseInt(txtSoTrang.getText());
			int soLuong = Integer.parseInt(txtSoLuong.getText());
			String maLoaiSach = cboLoaiSach.getSelectedItem().toString();
			LoaiSach loaiSach = new LoaiSach(maLoaiSach);
			String maTacGia = txtTacGia.getText();
			TacGia tacGia = new TacGia(maTacGia);
			String maNhaXB = cboNhaXB.getSelectedItem().toString();
			NhaXuatBan nhaXB = new NhaXuatBan(maNhaXB);
			Double donGiaBan = Double.parseDouble(txtDonGiaBan.getText());
			Double donGiaNhap = Double.parseDouble(txtDonGiaNhap.getText());
			Sach sach = new Sach(maSach, tenSach, iSBN, loaiSach, tacGia, nhaXB, soTrang, soLuong, donGiaBan, donGiaNhap);
			try {
				sachDAO.create(sach);
				tableModelSach.addRow(new Object[] {sach.getMaSach(), sach.getTenSach(), sach.getiSBN(), sach.getSoTrang(), sach.getSoLuong(), sach.getLoaiSach().getMaLoai(), sach.getTacGia().getMaTacGia(), sach.getNhaXB().getMaNhaXB(), sach.getDonGiaBan(), sach.getDonGiaNhap()});
				JOptionPane.showMessageDialog(this, "Đã thêm thành công");
			}
			catch (Exception e1) {
				JOptionPane.showMessageDialog(this, "Mã đầu sách này đã tồn tại trong hệ thống!");
			}
			
		}
	}

	private boolean validData() {
		
		//maSach
		String maSach = txtMaSach.getText();
		if (maSach.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Chưa nhập mã sách");
			return false;
		}
		
		//tenSach
		String tenSach = txtTenSach.getText();
		if (tenSach.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Chưa nhập tên sách");
			return false;
		}
		
		//iSBN
		String iSBN = txtISBN.getText();
		if (iSBN.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Chưa nhập mã ISBN");
			return false;
		}
		
		//soTrang
		if (txtSoTrang.getText().trim().length() == 0)
		{
			JOptionPane.showMessageDialog(this, "Chưa nhập số trang");
			return false;
		}
		int soTrang = Integer.parseInt(txtSoTrang.getText());
		if (soTrang < 0)
		{
			JOptionPane.showMessageDialog(this, "Số trang phải là số lớn hơn 0");
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
		
		//tacGia
		String tacGia = txtTacGia.getText();
		if (tacGia.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Chưa nhập tác giả");
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
		return true;
	}
	
	private void xoaSach() {
		// TODO Auto-generated method stub
		int loiNhac = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", "Chú ý", JOptionPane.YES_NO_OPTION);
		if (loiNhac == JOptionPane.YES_OPTION)
		{
			int row = tableSach.getSelectedRow();
			if (row >= 0)
			{
				String maSach = (String) tableSach.getValueAt(row, 0);
				if (sachDAO.delete(maSach)) {
					tableModelSach.removeRow(row);
					xoaTrang();
				}
			}
			JOptionPane.showMessageDialog(this, "Đã xóa đầu sách này!");
		}
	}

	private void xoaTrang() {
		// TODO Auto-generated method stub
		txtMaSach.setText("");
		txtTenSach.setText("");
		txtISBN.setText("");
		txtSoTrang.setText("");
		txtSoLuong.setText("");
		txtTacGia.setText("");
		txtDonGiaBan.setText("");
		txtDonGiaNhap.setText("");
		txtMaSach.requestFocus();
	}

}
