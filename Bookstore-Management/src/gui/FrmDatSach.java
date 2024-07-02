package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDatabase;
import dao.DatSachDAO;
import dao.KhachHangDAO;
import dao.LoaiSachDAO;
import dao.NhaXuatBanDAO;
import dao.NhanVienDAO;
import dao.SachDAO;
import dao.SanPhamKhacDAO;
import entity.Sach;
import entity.SanPhamKhac;
import entity.TacGia;
import entity.CaLamViec;
import entity.DatSach;
import entity.KhachHang;
import entity.LoaiSach;
import entity.NhaXuatBan;
import entity.NhanVien;
import entity.PhieuDatHang;

public class FrmDatSach extends JFrame implements ActionListener, MouseListener {

	private JPanel pnlNorth, pnlWest;
	private JLabel lblSoTrang, lblTenSach2, lblSoLuong2, lblTacGia, lblSoLuong1, lblLoaiSach, lblISBN, 
					lblTenSach, lblTitle, lblTongTien, lblSoLuong, lblSDT, lblMaSach, lblNhaXB, lblDonGia;
	private JTextField txtDonGia, txtSoLuong2, txtTenSach2,txtTacGia, txtSoLuong1, txtISBN, 
					txtSoTrang, txtTenSach, txtMaSach, txtTongTien, txtSoLuong, txtSDT;
	private Box bTacGia, bISBN, bMaSach, bCTS, bWest, bTableKhachHang, bLapHoaDon, bDatHang, bTimSDT, bEast, 
					bTenSach2, bTableGioHang, bTongTien, bButton, bCen, bTableSach, bTableSPK, bTenSach, bNhaXB, bDonGia,
					bSoTrang, bSoLuong, bLoaiSach;
	private JButton btnLamMoi, btnLapHoaDon, btnDatHang, btnXoa, btnTim;
	private DefaultTableModel tableModelSach, tableModelSPK, tableModelGioHang, tableModelKH;
	private JTable tableSach, tableSPK, tableGioHang, tableKH;
	KhachHangDAO khachHangDAO = new KhachHangDAO();
	SachDAO sachDAO = new SachDAO();
	SanPhamKhacDAO sanPhamKhacDAO = new SanPhamKhacDAO();
	private LoaiSachDAO loaiSachDAO;
	private JComboBox<String> cboNhaXB,  cboLoaiSach;
	private NhaXuatBanDAO nhaXuatBanDAO;
	DatSachDAO datSachDAO = new DatSachDAO();

	public FrmDatSach() {
		
		ConnectDatabase.getInstance().connect();
		setTitle("ĐẶT SÁCH");
		setExtendedState(MAXIMIZED_BOTH);
		
		//north
		pnlNorth = new JPanel();
		this.add(pnlNorth, BorderLayout.NORTH);
		pnlNorth.add(lblTitle = new JLabel("Đặt Sách"));
		lblTitle.setFont(new Font("Arial", Font.BOLD, 30));
		lblTitle.setForeground(Color.blue);
		
		//west
		bWest = Box.createVerticalBox();
		pnlWest = new JPanel();
	    this.add(pnlWest, BorderLayout.WEST);
		bCTS = Box.createVerticalBox();
		bCTS.setBorder(BorderFactory.createTitledBorder("Chi tiết sách")); 
		pnlWest.add(bCTS);
					//maSach
					bCTS.add(bMaSach = Box.createHorizontalBox());
					bMaSach.add(lblMaSach = new JLabel("Mã sách:"));
					bMaSach.add(txtMaSach = new JTextField(16));
					txtMaSach.setEditable(false);
					bCTS.add(Box.createVerticalStrut(10));
				
					//tenSach
					bCTS.add(bTenSach = Box.createHorizontalBox());
					bTenSach.add(lblTenSach = new JLabel("Tên sách:"));
					bTenSach.add(txtTenSach = new JTextField(16));
					txtTenSach.setEditable(false);
					bCTS.add(Box.createVerticalStrut(10));
					
					//iSBN
					bCTS.add(bISBN = Box.createHorizontalBox());
					bISBN.add(lblISBN = new JLabel("ISBN:"));
					bISBN.add(txtISBN = new JTextField(16));
					txtISBN.setEditable(false);
					bCTS.add(Box.createVerticalStrut(10));
					
					//soTrang
					bCTS.add(bSoTrang = Box.createHorizontalBox());
					bSoTrang.add(lblSoTrang = new JLabel("Số trang:"));
					bSoTrang.add(txtSoTrang = new JTextField(16));
					txtSoTrang.setEditable(false);
					bCTS.add(Box.createVerticalStrut(10));
					
					//soLuong
					bCTS.add(bSoLuong = Box.createHorizontalBox());
					bSoLuong.add(lblSoLuong = new JLabel("Số lượng:"));
					bSoLuong.add(txtSoLuong = new JTextField(16));
					txtSoLuong.setEditable(false);
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
					txtTacGia.setEditable(false);
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
					
					//donGia
					bCTS.add(bDonGia = Box.createHorizontalBox());
					bDonGia.add(lblDonGia= new JLabel("Đơn giá:"));
					bDonGia.add(txtDonGia = new JTextField(16));
					txtDonGia.setEditable(false);
					bCTS.add(Box.createVerticalStrut(10));
					
					//Tìm kiếm 
					bCTS.add(bTenSach2 = Box.createHorizontalBox());
					bTenSach2.add(lblTenSach2 = new JLabel("Tìm kiếm theo tên sách:"));
					bTenSach2.add(txtTenSach2 = new JTextField(16));
					bCTS.add(Box.createVerticalStrut(10));
					//button tìm kiếm
					bCTS.add(bButton = Box.createHorizontalBox());
					bButton.add(btnTim= new JButton("Tìm kiếm"));
					btnTim.addActionListener(this);
					//button làm mới
					bButton.add(Box.createHorizontalStrut(10));
					bButton.add(btnLamMoi = new JButton("Làm mới"));
					btnLamMoi.addActionListener(this);
					bCTS.add(bButton);
					
				   //xét theo dòng dài nhất
					bCTS.add(Box.createVerticalStrut(10));
					lblMaSach.setPreferredSize(lblNhaXB.getPreferredSize());
					lblTenSach.setPreferredSize(lblNhaXB.getPreferredSize());
					lblISBN.setPreferredSize(lblNhaXB.getPreferredSize());
					lblSoTrang.setPreferredSize(lblNhaXB.getPreferredSize());
					lblSoLuong.setPreferredSize(lblNhaXB.getPreferredSize());
					lblLoaiSach.setPreferredSize(lblNhaXB.getPreferredSize());
					lblTacGia.setPreferredSize(lblNhaXB.getPreferredSize());
					lblDonGia.setPreferredSize(lblNhaXB.getPreferredSize());
					
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
			tableSach.addMouseListener(this);
			insertDataSach(SachDAO.getAllSach());
			bCen.add(bTableSach);

			//Đặt hàng và nhập số lượng
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

	// hàm insert giỏ hàng
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

	
  //Hàm insert sách
	private void insertDataSach(List <Sach> Sach1) {
		// TODO Auto-generated method stub
		
		
		DefaultTableModel modelSach= (DefaultTableModel) tableSach.getModel();
        modelSach.setRowCount(0);
        Sach1.forEach((S)->{
             modelSach.addRow(new Object[] {
            		 S.getMaSach(), S.getTenSach(),S.getiSBN(), Integer.toString(S.getSoTrang()), Integer.toString(S.getSoLuong()), S.getLoaiSach().getMaLoai()
            		 , S.getTacGia().getMaTacGia(), S.getNhaXB().getMaNhaXB(), Double.toString(S.getDonGiaBan())
                   });
        });
}

//Hàm mouseClick
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
		txtDonGia.setText(tableModelSach.getValueAt(row, 8).toString());
		
	}
	// Hàm thêm vào giỏ hàng
	private void themSachVaoGioHang() {
		// TODO Auto-generated method stub
		//if (validData() == true)
		
		    String maSach = txtMaSach.getText();
			String tenSP = txtTenSach.getText();
		    int soLuong = Integer.parseInt(txtSoLuong2.getText());
			double donGiaBan = Double.parseDouble(txtDonGia.getText());
			double thanhTien = soLuong*donGiaBan;
			
			DatSach datSach = new DatSach(maSach, tenSP, soLuong, donGiaBan, thanhTien);
			try {
				DatSachDAO datSachDAO = new DatSachDAO();
				datSachDAO.create(datSach);
				tableModelGioHang.addRow(new Object[] {datSach.getMaSach(), datSach.getTenSP(), datSach.getSoLuong(), datSach.getDonGiaBan(), datSach.getThanhTien()});
				JOptionPane.showMessageDialog(this, "Đã thêm thành công vào giỏ hàng");
			}
			catch (Exception e1) {
				JOptionPane.showMessageDialog(this, "Mã đầu sách này đã tồn tại trong hệ thống!");
			}
		}
	
	//Hàm xóa giỏ hàng
	private void xoaGioHang() {
		// TODO Auto-generated method stub
		int loiNhac = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", "Chú ý", JOptionPane.YES_NO_OPTION);
		if (loiNhac == JOptionPane.YES_OPTION)
		{
			int row = tableGioHang.getSelectedRow();
			if (row >= 0)
			{
				String maSach = (String) tableGioHang.getValueAt(row, 0);
				if (datSachDAO.delete(maSach)) {
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
		insertDataSach(SachDAO.getAllSach());
	}
	
	//Hàm xóa dữ liệu table
	private void XoaHetDuLieuTable() {
		DefaultTableModel dtm = (DefaultTableModel) tableSach.getModel();
		dtm.setRowCount(0);
	}
	//xóa trắng
	private void xoaTrang() {
		// TODO Auto-generated method stub
		txtMaSach.setText("");
		txtTenSach.setText("");
		txtISBN.setText("");
		txtSoTrang.setText("");
		txtSoLuong.setText("");
		txtTacGia.setText("");
		txtDonGia.setText("");
		txtMaSach.requestFocus();
	}
	//Cập Nhật
/*	private void capNhatSoLuong() {
		// TODO Auto-generated method stub
		{
			int row = tableSach.getSelectedRow();
			String maSach = txtMaSach.getText();
			String tenSach = txtTenSach.getText();
			String ISBN = txtISBN.getText();
			int soTrang = Integer.parseInt(txtSoTrang.getText());
		    int soLuong = Integer.parseInt(txtSoLuong.getText());
		    int soLuong2 = Integer.parseInt(txtSoLuong2.getText());
			
			int conLai = soLuong - soLuong2;
			String maLoai = cboLoaiSach.getSelectedItem().toString();
			LoaiSach loaiSach = new LoaiSach(maLoai);
			String maNhaXB = cboNhaXB.getSelectedItem().toString();
			String tacGia = txtTacGia.getText();
			NhaXuatBan nhaXB = new NhaXuatBan(maNhaXB);
			double donGia = Double.parseDouble(txtDonGia.getText());
			Sach sach = new Sach(maSach, tenSach, ISBN,loaiSach, tacGia, nhaXB, soTrang, conLai, donGia);
			if (row >= 0) {
					//if (validData() == true) {
						if (sachDAO.update(sach)) {
							tableSach.setValueAt(txtMaSach.getText(), row, 0);
							tableSach.setValueAt(txtTenSach.getText(), row, 1);
							tableSach.setValueAt(txtISBN.getText(), row, 2);
							tableSach.setValueAt(txtSoTrang.getText(), row, 3);
							tableSach.setValueAt(txtSoLuong.getText(), row, 4);
							tableSach.setValueAt(cboLoaiSach.getSelectedItem().toString(), row, 5);
							tableSach.setValueAt(txtTacGia.getText(), row, 6);
							tableSach.setValueAt(cboNhaXB.getSelectedItem().toString(), row, 7);
							tableSach.setValueAt(txtDonGia.getText(), row, 8);
							JOptionPane.showMessageDialog(this, "Đã sửa thành công!");
				
				}
				
			}
		}
	}
*/
	// Hàm tìm sách
	 public void TimSach() {
	     Sach sa = new Sach();
	     			 sa.setTenSach(txtTenSach2.getText());     
	                 if (txtMaSach.getText().length()==0
	                     &&txtTenSach2.getText().length()==0){
	                     JOptionPane.showMessageDialog(null, "xin mời nhập thông tin tìm kiếm");
	                 } else  {
	                     if (txtTenSach2.getText().length()>0) {
	                    	 insertDataSach(SachDAO.FindTenSach(sa));                    	 
	                 }
	                   JOptionPane.showMessageDialog(null, "Đã hoàn tất thao tác");      
	              } 
	         }                           
	   
	//Cập Nhật
		private void capNhatSoLuongDat() {
			// TODO Auto-generated method stub
			{
				
			    int soLuong = Integer.parseInt(txtSoLuong.getText());
			    int soLuong2 = Integer.parseInt(txtSoLuong2.getText());
				
				int conLai = soLuong - soLuong2;
				int row = tableSach.getSelectedRow();
				String maSach = txtMaSach.getText();
				String tenSach = txtTenSach.getText();
				String ISBN = txtISBN.getText();
				int soTrang = Integer.parseInt(txtSoTrang.getText());
				//int soLuong = Integer.parseInt(txtSoLuong.getText());
				String maLoai = cboLoaiSach.getSelectedItem().toString();
				LoaiSach loaiSach = new LoaiSach(maLoai);
				String maTacGia = txtTacGia.getText();
				TacGia tacGia = new TacGia(maTacGia);
				String maNhaXB = cboNhaXB.getSelectedItem().toString();
				NhaXuatBan nhaXB = new NhaXuatBan(maNhaXB);
				double donGiaBan = Double.parseDouble(txtDonGia.getText());
				//double donGiaNhap = Double.parseDouble(txtDonGiaNhap.getText());
				Sach sach = new Sach(maSach, tenSach, ISBN, loaiSach, tacGia, nhaXB, soTrang, conLai, donGiaBan, donGiaBan);
				if (row >= 0) {
						//if (validData() == true) {
					if (sachDAO.update(sach)) {
						tableSach.setValueAt(txtMaSach.getText(), row, 0);
						tableSach.setValueAt(txtTenSach.getText(), row, 1);
						tableSach.setValueAt(txtISBN.getText(), row, 2);
						tableSach.setValueAt(txtSoTrang.getText(), row, 3);
						tableSach.setValueAt(txtSoLuong.getText(), row, 4);
						tableSach.setValueAt(cboLoaiSach.getSelectedItem().toString(), row, 5);
						tableSach.setValueAt(txtTacGia.getText(), row, 6);
						tableSach.setValueAt(cboNhaXB.getSelectedItem().toString(), row, 7);
						tableSach.setValueAt(txtDonGia.getText(), row, 8);
						//tableSach.setValueAt(txtDonGiaNhap.getText(), row, 9);
						//JOptionPane.showMessageDialog(this, "Đã cập nhật lại kho sách!");
					}
				}
			}
		}
	// Xử lý các button
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
		if (src.equals(btnDatHang)) {
			if(txtSoLuong2.getText().length()==0) {
				JOptionPane.showMessageDialog(this, "vui lòng nhập số lượng cần thêm vào giỏ hàng");
			}else { if(txtSoLuong2.getText().hashCode() > txtSoLuong.getText().hashCode()){
				JOptionPane.showMessageDialog(this, "Số Lượng Kho Không Đủ");
			}else {
			themSachVaoGioHang();
			int soLuong = (txtSoLuong.getText().hashCode());
			int soLuong2 = (txtSoLuong2.getText().hashCode());
			soLuong= soLuong - soLuong2;
			capNhatSoLuongDat();
			insertDataSach(SachDAO.getAllSach());
			
			}
			
		}
	}
			if (src.equals(btnXoa)) {
				xoaGioHang();
			}
			
			if (src.equals(btnTim)) {
				TimSach();;
			}
			if (src.equals(btnLamMoi)) {
				LamMoi();
				xoaTrang();
			}
			if (src.equals(btnLapHoaDon)) {
				//themSachVaoGioHang();
				//capNhatSoLuongDat();
				FrmLapHoaDonTest.main(null);;
			}
}
	
   
	// Hàm main
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FrmDatSach().setVisible(true);
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
