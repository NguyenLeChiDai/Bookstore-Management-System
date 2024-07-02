package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
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
import dao.DatSachDAO;
import dao.DatSanPhamDAO;
import dao.KhachHangDAO;
import dao.NhaCungCapDAO;
import dao.NhanVienDAO;
import dao.SachDAO;
import dao.SanPhamKhacDAO;
import entity.Sach;
import entity.SanPhamKhac;
import entity.DatSach;
import entity.DatSanPham;
import entity.KhachHang;
import entity.NhaCungCap;
import entity.NhanVien;
import entity.PhieuDatHang;

public class FrmDatSanPham extends JFrame implements ActionListener, MouseListener {

	private JPanel pnlNorth, pnlWest;
	private JLabel  lblTenSP2, lblSoLuong2, lblDonGiaBan, lblDonGiaNhap, lblNhaCungCap, lblTenSP, lblMaSP, lblTitle, lblTongTien, lblSoLuong;
	private JTextField txtTenSP2, txtSoLuong2, txtDonGiaBan, txtDonGiaNhap, txtTenSP, txtMaSP, txtTongTien, txtSoLuong;
	private Box bTenSP2, bDonGiaBan, bDonGiaNhap, bNhaCungCap, bSoLuong,bTenSP, bMaSP, bCTSP, bWest, bTableKhachHang,
	bLapHoaDon, bDatHang, bEast, bTableGioHang, bTongTien, bButton, bCen, bTableSach, bTableSPK;
	private JButton btnLamMoi, btnTim, btnXoaTrang, btnLapHoaDon, btnDatHang, btnXoa;
	private DefaultTableModel tableModelSach, tableModelSPK, tableModelGioHang, tableModelKH;
	private JTable tableSach, tableSPK, tableGioHang, tableKH;
	NhaCungCapDAO nhaCungCapDAO = new NhaCungCapDAO();
	SanPhamKhacDAO sanPhamKhacDAO = new SanPhamKhacDAO();
	private JComboBox<String> cboNhaCungCap;
	
	
	public FrmDatSanPham() {
		
		ConnectDatabase.getInstance().connect();
		setTitle("Đặt sản phẩm");
		setExtendedState(MAXIMIZED_BOTH);
		
		//north
		pnlNorth = new JPanel();
		this.add(pnlNorth, BorderLayout.NORTH);
		pnlNorth.add(lblTitle = new JLabel("ĐẶT SẢN PHẨM"));
		lblTitle.setFont(new Font("Arial", Font.BOLD, 30));
		lblTitle.setForeground(Color.blue);
		
		//west
		pnlWest = new JPanel();
		this.add(pnlWest, BorderLayout.WEST);
		bCTSP = Box.createVerticalBox();
		bCTSP.setBorder(BorderFactory.createTitledBorder("Chi tiết sản phẩm"));
		//maSP
		bCTSP.add(bMaSP = Box.createHorizontalBox());
		bMaSP.add(lblMaSP = new JLabel("Mã sản phẩm:"));
		bMaSP.add(txtMaSP = new JTextField(16));
		txtMaSP.setEditable(false);
		bCTSP.add(Box.createVerticalStrut(10));
		
		//tenSP
		bCTSP.add(bTenSP = Box.createHorizontalBox());
		bTenSP.add(lblTenSP = new JLabel("Tên sản phẩm:"));
		bTenSP.add(txtTenSP = new JTextField(16));
		txtTenSP.setEditable(false);
		bCTSP.add(Box.createVerticalStrut(10));
		
		//soLuong
		bCTSP.add(bSoLuong = Box.createHorizontalBox());
		bSoLuong.add(lblSoLuong = new JLabel("Số lượng:"));
		bSoLuong.add(txtSoLuong = new JTextField(16));
		txtSoLuong.setEditable(false);
		bCTSP.add(Box.createVerticalStrut(10));
		
		//nhaCungCap
		bCTSP.add(bNhaCungCap = Box.createHorizontalBox());
		bNhaCungCap.add(lblNhaCungCap = new JLabel("Nhà cung cấp:"));
		bNhaCungCap.add(cboNhaCungCap = new JComboBox<String>());
		ArrayList<NhaCungCap> danhSachNhaCungCap = nhaCungCapDAO.getAllNhaCungCap();
		for (NhaCungCap nhaCungCap : danhSachNhaCungCap) {
			cboNhaCungCap.addItem(nhaCungCap.getMaNCC());
		}
		cboNhaCungCap.setEditable(false);
		bCTSP.add(Box.createVerticalStrut(10));
		
		//donGiaNhap
		bCTSP.add(bDonGiaNhap = Box.createHorizontalBox());
		bDonGiaNhap.add(lblDonGiaNhap = new JLabel("Đơn giá nhập:"));
		bDonGiaNhap.add(txtDonGiaNhap = new JTextField(16));
		txtDonGiaNhap.setEditable(false);
		bCTSP.add(Box.createVerticalStrut(10));
		
		//donGiaBan
		bCTSP.add(bDonGiaBan = Box.createHorizontalBox());
		bDonGiaBan.add(lblDonGiaBan = new JLabel("Đơn giá bán:"));
		bDonGiaBan.add(txtDonGiaBan = new JTextField(16));
		txtDonGiaBan.setEditable(false);
		bCTSP.add(Box.createVerticalStrut(10));
		
			// tìm kiếm
		bCTSP.add(bTenSP2 = Box.createHorizontalBox());
		bTenSP2.add(lblTenSP2 = new JLabel("Tìm kiếm theo tên sản phẩm:"));
		bTenSP2.add(txtTenSP2 = new JTextField(16));
		txtTenSP.setEditable(false);
		bCTSP.add(Box.createVerticalStrut(10));
			//button tim
		bButton = Box.createHorizontalBox();
		bButton.add(btnTim = new JButton("Tìm"));
		btnTim.addActionListener(this);
			//button làm mới
		bButton.add(Box.createHorizontalStrut(10));
		bButton.add(btnLamMoi = new JButton("Làm mới"));
		btnLamMoi.addActionListener(this);
		bCTSP.add(bButton);
			
		pnlWest.add(bCTSP);
		    // xét độ dài
		lblMaSP.setPreferredSize(lblTenSP.getPreferredSize());
	    lblSoLuong.setPreferredSize(lblTenSP.getPreferredSize());
		lblNhaCungCap.setPreferredSize(lblTenSP.getPreferredSize());
		lblDonGiaNhap.setPreferredSize(lblTenSP.getPreferredSize());
		lblDonGiaBan.setPreferredSize(lblTenSP.getPreferredSize());
		    
		  //center
	    bCen = Box.createVerticalBox();

			//tableSanPhamKhac
		bTableSPK = Box.createVerticalBox();
		bTableSPK.setBorder(BorderFactory.createTitledBorder("Danh sách sản phẩm không phải sách"));
		String [] headersSPK = "Mã sản phẩm; Tên sản phẩm; Số lượng; Nhà cung cấp;  Đơn giá nhập; Đơn giá bán".split(";");
		tableModelSPK = new DefaultTableModel(headersSPK, 0);
		JScrollPane scrollSPK = new JScrollPane();
		scrollSPK.setViewportView(tableSPK = new JTable(tableModelSPK));
		tableSPK.setRowHeight(25);
		tableSPK.setAutoCreateRowSorter(true);
		tableSPK.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		tableSPK.addMouseListener(this);
		bTableSPK.add(scrollSPK);
		insertSPK(SanPhamKhacDAO.getAllSanPhamKhac());
		bCen.add(bTableSPK);
			
			//datHang, nhập số lượng
		bDatHang = Box.createHorizontalBox();
		JPanel bDatHang=new JPanel();
		bDatHang.add(lblSoLuong2 = new JLabel("Nhập số lượng:"));
		bDatHang.add(txtSoLuong2 = new JTextField(16));
		bDatHang.add(btnDatHang = new JButton("Đặt hàng"));
		btnDatHang.addActionListener(this);
		bCen.add(bDatHang);
		this.add(bCen, BorderLayout.CENTER);
		
		//east
		bEast = Box.createVerticalBox();
		
			//Giỏ hàng
		bTableGioHang = Box.createVerticalBox();
		bTableGioHang.setBorder(BorderFactory.createTitledBorder("Giỏ hàng"));
		String [] headersGioHang = "Mã sản phẩm; Tên sản phẩm; Số lượng; Đơn giá bán; Thành tiền".split(";");
		tableModelGioHang = new DefaultTableModel(headersGioHang, 0);
		JScrollPane scrollGioHang = new JScrollPane();
		scrollGioHang.setViewportView(tableGioHang = new JTable(tableModelGioHang));
		tableGioHang.setRowHeight(25);
		tableGioHang.setAutoCreateRowSorter(true);
		tableGioHang.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		bTableGioHang.add(scrollGioHang);
		insertDataGioHang();
		bEast.add(bTableGioHang);
		
		//Tổng tiền
		bTongTien= Box.createHorizontalBox();
		JPanel bTongTien=new JPanel();
		JLabel lblTongTien=new JLabel("Tổng tiền:");
		txtTongTien=new JTextField(15);
		bTongTien.add(lblTongTien);
		bTongTien.add(txtTongTien);
		bEast.add(bTongTien);
			
			//lapHoaDon + xoa
		bLapHoaDon = Box.createHorizontalBox();
		bLapHoaDon.add(btnLapHoaDon = new JButton("Lập hóa đơn"));
		btnLapHoaDon.addActionListener(this);
		bLapHoaDon.add(Box.createHorizontalStrut(20));
		bLapHoaDon.add(btnXoa = new JButton("Xóa"));
		btnXoa.addActionListener(this);
		bEast.add(bLapHoaDon);
		this.add(bEast, BorderLayout.EAST);
	}


	// insert sản phẩm khác
	void insertSPK(List <SanPhamKhac> DanhSachSanPham ) {
        List <SanPhamKhac> list1 = new ArrayList<>();
        list1=DanhSachSanPham;
        DefaultTableModel modelSanPhamKhac = (DefaultTableModel) tableSPK.getModel();
        modelSanPhamKhac.setRowCount(0);
        list1.forEach((sp)->{
        	modelSanPhamKhac.addRow(new Object[] {
                     sp.getMaSP(), sp.getTenSP(),sp.getSoLuong(), sp.getNhaCC().getMaNCC(),sp.getDonGiaNhap(), sp.getDonGiaBan()
                   });
        });
}
	// insert giỏ hàng
	private void insertDataGioHang() {
		// TODO Auto-generated method stub
		DatSanPhamDAO danhSachDat = new DatSanPhamDAO();
		List<DatSanPham> list = danhSachDat.getAllDatSanPham();
		for (DatSanPham datSanPham : list) {
			String [] row = {datSanPham.getMaSP(), datSanPham.getTenSP(), Integer.toString(datSanPham.getSoLuong()),
					Double.toString(datSanPham.getDonGiaBan()), Double.toString(datSanPham.getThanhTien())};
			tableModelGioHang.addRow(row);
		}
		tableGioHang.setModel(tableModelGioHang);
	}
	
 //Hàm MouseClick
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tableSPK.getSelectedRow();
		txtMaSP.setText(tableModelSPK.getValueAt(row, 0).toString());
		txtTenSP.setText(tableModelSPK.getValueAt(row, 1).toString());
		txtSoLuong.setText(tableModelSPK.getValueAt(row, 2).toString());
		cboNhaCungCap.setSelectedItem(tableModelSPK.getValueAt(row, 3).toString());
		txtDonGiaNhap.setText(tableModelSPK.getValueAt(row, 4).toString());
		txtDonGiaBan.setText(tableModelSPK.getValueAt(row, 4).toString());
	}

	//Hàm Thêm Sản Phẩm
	private void themSPVaoGioHang() {
		// TODO Auto-generated method stub
		//if (validData() == true)
		
		    String maSach = txtMaSP.getText();
			String tenSP = txtTenSP.getText();
		    int soLuong = Integer.parseInt(txtSoLuong2.getText());
			double donGiaBan = Double.parseDouble(txtDonGiaBan.getText());
			double thanhTien = soLuong*donGiaBan;
			DatSanPham datSanPham = new DatSanPham(maSach, tenSP, soLuong, donGiaBan, thanhTien);
			try {
				DatSanPhamDAO datSanPhamDAO = new DatSanPhamDAO();
				datSanPhamDAO.create(datSanPham);
				tableModelGioHang.addRow(new Object[] {datSanPham.getMaSP(), datSanPham.getTenSP(), Integer.toString(datSanPham.getSoLuong())
						, Double.toString(datSanPham.getDonGiaBan()), Double.toString(datSanPham.getThanhTien())});
				JOptionPane.showMessageDialog(this, "Đã thêm sản phẩm thành công");
			}
			catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(this, "Mã đầu sách này đã tồn tại trong hệ thống!");
			}
		}
	//Hàm xóa sản phẩm ra khỏi giỏ hàng
	private void xoaGioHang() {
		// TODO Auto-generated method stub
		int loiNhac = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", "Chú ý", JOptionPane.YES_NO_OPTION);
		if (loiNhac == JOptionPane.YES_OPTION)
		{
			int row = tableGioHang.getSelectedRow();
			if (row >= 0)
			{
				String masanPham = (String) tableGioHang.getValueAt(row, 0);
				DatSanPhamDAO datSanPhamDAO = new DatSanPhamDAO();
				if (datSanPhamDAO.delete(masanPham)) {
					tableModelGioHang.removeRow(row);
					xoaTrang();
				}
			}
			JOptionPane.showMessageDialog(this, "Đã xóa khỏi giỏ hàng này!");
		}
	}
	
	// Hàm làm mới
		private void LamMoi() {
			// TODO Auto-generated method stub
			XoaHetDuLieuTable();
			insertSPK(SanPhamKhacDAO.getAllSanPhamKhac());
		}
		//Hàm xóa trắng
		private void xoaTrang() {
			// TODO Auto-generated method stub
			txtMaSP.setText("");
			txtTenSP.setText("");
			txtSoLuong.setText("");
			txtDonGiaNhap.setText("");
			txtDonGiaBan.setText("");
			
			txtMaSP.requestFocus();
		}
		//Hàm xóa dữ liệu table
		private void XoaHetDuLieuTable() {
			DefaultTableModel dtm = (DefaultTableModel) tableSPK.getModel();
			dtm.setRowCount(0);
		}
		
		// Hàm tìm SP
		  public void TimSP() {
		       SanPhamKhac spk = new SanPhamKhac();
		      spk.setTenSP(txtTenSP2.getText());
		        String name = (txtTenSP.getText().trim());
				String nameTim = (txtTenSP2.getText().trim());
				if (nameTim.length()==0 ){
				     JOptionPane.showMessageDialog(null, "xin mời nhập thông tin tìm kiếm");
				 }
				  else {
					  insertSPK(SanPhamKhacDAO.FindTenSanPhamKhac(spk));
					  JOptionPane.showMessageDialog(null, "Đã hoàn tất tìm kiếm");
				  }
				}
		  //Dat san pham
		  private void capNhatSP() {
				// TODO Auto-generated method stub
			  int soLuong = Integer.parseInt(txtSoLuong.getText());
		      int soLuong2 = Integer.parseInt(txtSoLuong2.getText());
			  int conLai = soLuong - soLuong2;
			  int row = tableSPK.getSelectedRow();
			  String maSP = txtMaSP.getText();
	     	  String tenSP = txtTenSP.getText();
			  String maNhaCungCap = cboNhaCungCap.getSelectedItem().toString();
			  NhaCungCap nhaCungCap = new NhaCungCap(maNhaCungCap);
			  double donGiaNhap = Double.parseDouble(txtDonGiaNhap.getText());
			  double donGiaBan = Double.parseDouble(txtDonGiaBan.getText());
			  SanPhamKhac sanPhamKhac = new SanPhamKhac(maSP, tenSP, conLai, nhaCungCap, donGiaNhap, donGiaBan);
			  if (row >= 0) {
						if (sanPhamKhacDAO.update(sanPhamKhac)) {
							tableSPK.setValueAt(txtMaSP.getText(), row, 0);
							tableSPK.setValueAt(txtTenSP.getText(), row, 1);
							tableSPK.setValueAt(txtSoLuong.getText(), row, 2);
							tableSPK.setValueAt(cboNhaCungCap.getSelectedItem().toString(), row, 3);
							tableSPK.setValueAt(txtDonGiaNhap.getText(), row, 4);
							tableSPK.setValueAt(txtDonGiaBan.getText(), row, 5);
						//	JOptionPane.showMessageDialog(this, "Đã cập nhật lại kho hàng!");
						}
						
					}
				}
		// Xử lý các button
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
		if (src.equals(btnDatHang)) { // button dat hang
			if(txtSoLuong2.getText().length()==0) {
				JOptionPane.showMessageDialog(this, "vui lòng nhập số lượng cần thêm vào giỏ hàng");
			}else { if(txtSoLuong2.getText().hashCode() > txtSoLuong.getText().hashCode()){
				JOptionPane.showMessageDialog(this, "Số Lượng Kho Không Đủ");
			}else {
			themSPVaoGioHang();
			int soLuong = (txtSoLuong.getText().hashCode());
			int soLuong2 = (txtSoLuong2.getText().hashCode());
			soLuong= soLuong - soLuong2;
			capNhatSP();
			//insertSPK(SanPhamKhacDAO.getAllSanPhamKhac());
			
			}
			}
		}
		if (src.equals(btnTim)) {// button Tìm sp
			TimSP();
		}	
		if (src.equals(btnLamMoi)) {// button làm mới
			LamMoi();
			xoaTrang();
		}	
		if (src.equals(btnXoa)) {// button xoa
			xoaGioHang();;
		}	
		if (src.equals(btnLapHoaDon)) {
			FrmLapHoaDonTest.main(null);;
		}
	}
	
   
	//Hàm main
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FrmDatSanPham().setVisible(true);
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
