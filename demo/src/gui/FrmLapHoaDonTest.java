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
import dao.ThemKHHoaDonDAO;
import dao.DatSachDAO;
import dao.KhachHangDAO;
import dao.NhanVienDAO;
import entity.DatHang;
import entity.DatSach;
import entity.KhachHang;
import entity.NhanVien;


public class FrmLapHoaDonTest extends JFrame implements ActionListener, MouseListener {

	private JPanel pnlNorth, pnlWest;
	private JLabel lblTitle;
	private Box bCen,bButton, bTTKH, bMaKhachHang, bTenKhachHang, bSoDienThoai, bEmail, bDiaChi;
	private JButton btnThem, btnXoa, btnCapNhat, btnXoaTrang;
	private DefaultTableModel tableModel;
	private JTable table;
	private JLabel lblMaKhachHang, lblTenKhachHang, lblSoDienThoai, lblEmail, lblDiaChi;
	private JTextField txtMaKhachHang, txtTenKhachHang, txtSoDienThoai, txtEmail, txtDiaChi;
	KhachHangDAO khachHangDAO = new KhachHangDAO();
	private JButton btnTim;
	private Box bTableGioHang;
	private DefaultTableModel tableModelGioHang;
	private JTable tableGioHang;
	private Box bEast;
	private Box bTableHoaDon;
	private DefaultTableModel tableModelHoaDon;
	private JTable tableHoaDon;
	private Box bInHoaDon;
	private JButton btnInHoaDon;
	private JButton btnThemKH;
	private JButton btnXoa1;

	public FrmLapHoaDonTest() {
		ConnectDatabase.getInstance().connect();
		setTitle("Quản lý hóa đơn");
		setExtendedState(MAXIMIZED_BOTH);
		
		//north
		pnlNorth = new JPanel();
		this.add(pnlNorth, BorderLayout.NORTH);
		pnlNorth.add(lblTitle = new JLabel("LẬP HÓA ĐƠN"));
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
			bButton.add(btnTim = new JButton("Tìm kiếm"));
			btnTim.addActionListener(this);
			bButton.add(Box.createHorizontalStrut(10));
			bButton.add(btnThem = new JButton("Thêm"));
			btnThem.addActionListener(this);
			bTTKH.add(bButton = Box.createHorizontalBox());
			
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
			insertData(KhachHangDAO.getAllKhachHang());
			bCen.add(scroll);
			
			
				//gioHang
				bTableGioHang = Box.createVerticalBox();
				bTableGioHang.setBorder(BorderFactory.createTitledBorder("Giỏ hàng"));
				String [] headersGioHang = "Mã sách; Tên sách; Số lượng; Đơn giá; Thành tiền".split(";");
				tableModelGioHang = new DefaultTableModel(headersGioHang, 0);
				JScrollPane scrollGioHang = new JScrollPane();
				scrollGioHang.setViewportView(tableGioHang = new JTable(tableModelGioHang));
				tableGioHang.setRowHeight(25);
				tableGioHang.setAutoCreateRowSorter(true);
				tableGioHang.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
				bTableGioHang.add(scrollGioHang);
				insertGioHang();
				bCen.add(bTableGioHang);
				
				bTableGioHang.add(btnThemKH = new JButton("Thêm khách hàng"));
				btnThemKH.addActionListener(this);
				bCen.add(bTableGioHang);
		this.add(bCen, BorderLayout.CENTER);
		
		//east
				bEast = Box.createVerticalBox();
					//hoaDon
					bTableHoaDon = Box.createVerticalBox();
					bTableHoaDon.setBorder(BorderFactory.createTitledBorder("Thông tin hóa đơn"));
					String [] headersHoaDon = "Sản phẩm; Số lượng; Đơn giá; Thành tiền".split(";");
					tableModelHoaDon = new DefaultTableModel(headersHoaDon, 0);
					JScrollPane scrollHoaDon = new JScrollPane();
					scrollHoaDon.setViewportView(tableHoaDon = new JTable(tableModelHoaDon));
					tableHoaDon.setRowHeight(25);
					tableHoaDon.setAutoCreateRowSorter(true);
					tableHoaDon.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
					bTableHoaDon.add(scrollHoaDon);
					insertGioHang2();
					insertKH();
					
					bEast.add(bTableHoaDon);
					
					//lapHoaDon + xoa
					bInHoaDon = Box.createHorizontalBox();
					bInHoaDon.add(btnInHoaDon = new JButton("In hóa đơn"));
					btnInHoaDon.addActionListener(this);
					bInHoaDon.add(Box.createHorizontalStrut(20));
					bInHoaDon.add(btnXoa1 = new JButton("Xóa"));
					btnXoa1.addActionListener(this);
					bEast.add(bInHoaDon);
					this.add(bEast, BorderLayout.EAST);
	}
	void insertData(List <KhachHang> KhachHang1 ) {
		   
        List <KhachHang> list1 = new ArrayList<>();
        list1=KhachHang1;
        DefaultTableModel modelKhachHang= (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        list1.forEach((kh)->{
        	tableModel.addRow(new Object[] {
                     kh.getMaKH(), kh.getTenKH(), kh.getSoDT(), kh.getEmail(), kh.getDiaChi()
                   });
        });
}
	
	private void insertGioHang() {
		// TODO Auto-generated method stub
		DatSachDAO danhSachDatSach = new DatSachDAO();
		List<DatSach> list = danhSachDatSach.getAllDatSach();
		for (DatSach datSach : list) {
			String [] row = { datSach.getMaSach(), datSach.getTenSP(), Integer.toString(datSach.getSoLuong()), Double.toString(datSach.getDonGiaBan()),  Double.toString(datSach.getThanhTien())};
			tableModelGioHang.addRow(row);
		}
		tableGioHang.setModel(tableModelGioHang);
	}
	
	
	private void insertKH() {
		// TODO Auto-generated method stub
		ThemKHHoaDonDAO danhSachHoaDon = new ThemKHHoaDonDAO();
		List<DatHang> list = danhSachHoaDon.getAllDatHang();
		for (DatHang datHang : list) {
			String [] row = { datHang.getMaKH(), datHang.getTenHang()};
			tableModelHoaDon.addRow(row);
		}
		tableHoaDon.setModel(tableModelHoaDon);
	}
	
	private void insertGioHang2() {
		// TODO Auto-generated method stub
		DatSachDAO danhSachDatSach = new DatSachDAO();
		List<DatSach> list = danhSachDatSach.getAllDatSach();
		for (DatSach datSach : list) {
			String [] row = {  datSach.getTenSP(), Integer.toString(datSach.getSoLuong()), Double.toString(datSach.getDonGiaBan()),  Double.toString(datSach.getThanhTien())};
			tableModelHoaDon.addRow(row);
		}
		tableHoaDon.setModel(tableModelHoaDon);
	}
	
	// Hàm thêm KH vào hóa đơn
		private void themSachVaoHoaDon() {
			// TODO Auto-generated method stub
			//if (validData() == true)
			
			    String maKH = txtMaKhachHang.getText();
				String tenKH = txtTenKhachHang.getText();

				DatHang datHang = new DatHang(maKH, tenKH);
				try {
					ThemKHHoaDonDAO datHangDao = new ThemKHHoaDonDAO();
					datHangDao.create(datHang);
					tableModelHoaDon.addRow(new Object[] {datHang.getMaKH(), datHang.getTenKH()});
					JOptionPane.showMessageDialog(this, "Đã thêm thành công vào giỏ hàng");
				}
				catch (Exception e1) {
					JOptionPane.showMessageDialog(this, "Mã đầu sách này đã tồn tại trong hệ thống!");
				}
			}
	
		private void themKH() {
			// TODO Auto-generated method stub
			
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
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FrmLapHoaDonTest().setVisible(true);
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
		if (src.equals(btnTim)) {
			TimKH();
		}
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
		if (src.equals(btnThemKH)) {
			themSachVaoHoaDon();
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
	
	//Tìm
	 public void TimKH() {
	     KhachHang kh = new KhachHang();
	     kh.setMaKH(txtMaKhachHang.getText());
	     kh.setTenKH(txtTenKhachHang.getText());
	     kh.setSoDT(txtSoDienThoai.getText());
	     kh.setEmail(txtEmail.getText());
	     kh.setDiaChi(txtDiaChi.getText());
	     if (txtMaKhachHang.getText().length() == 0
	         &&txtTenKhachHang.getText().length() == 0
	         &&txtSoDienThoai.getText().length() == 0
	         &&txtEmail.getText().length() == 0
	         &&txtDiaChi.getText().length() == 0)
	     {
	    	 JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin cần tìm");
	     } 
	     else 
	     {
	    	 if (txtSoDienThoai.getText().length() > 0) 
	    	 {
	    		 insertData(KhachHangDAO.FindTheoSDT(kh)); 
	         }
	    	 if (txtTenKhachHang.getText().length() > 0) 
	    	 {
	    		 insertData(KhachHangDAO.FindTheoTen(kh)); 
	         }
	    	 if (txtEmail.getText().length() > 0) 
	    	 {
	    		 insertData(KhachHangDAO.FindTheoEmail(kh)); 
	         }
	    	 if (txtDiaChi.getText().length() > 0) 
	    	 {
	    		 insertData(KhachHangDAO.FindTheoDiaChi(kh)); 
	         }

	    }
	     JOptionPane.showMessageDialog(null, "Đã hoàng tất thao tác");
	   }
	

}
