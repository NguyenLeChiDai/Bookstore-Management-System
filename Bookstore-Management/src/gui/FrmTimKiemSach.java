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
import entity.LoaiSach;
import entity.NhaXuatBan;
import entity.NhanVien;
import entity.Sach;

public class FrmTimKiemSach extends JFrame implements ActionListener, MouseListener {

	private JPanel pnlNorth, pnlWest;
	private JLabel lblTitle, lblMaSach, lblTenSach, lblISBN, lblSoTrang, lblSoLuong, lblLoaiSach, lblTacGia, lblNhaXB, lblDonGia;
	private JTextField txtMaSach, txtTenSach, txtISBN, txtSoTrang, txtTacGia, txtDonGia, txtSoLuong;
	private Box bCTS, bEast, bMaSach, bTenSach, bISBN, bSoTrang, bSoLuong, bButton, bTable, bCen, bLoaiSach, bTacGia, bNhaXB, bDonGia;
	private JButton btnTim, btnXoaTrang;
	private DefaultTableModel tableModelSach, tableModelLoaiSach, tableModelNhaXB;
	private JTable tableSach, tableLoaiSach, tableNhaXB;
	private JComboBox cboLoaiSach, cboNhaXB;
	NhaXuatBanDAO nhaXuatBanDAO = new NhaXuatBanDAO();
	LoaiSachDAO loaiSachDAO = new LoaiSachDAO();
	SachDAO sachDAO = new SachDAO();
	private JButton btnLamMoi;

	public FrmTimKiemSach() {
		ConnectDatabase.getInstance().connect();
		setTitle("Tìm kiếm sách");
		setExtendedState(MAXIMIZED_BOTH);
		
		//north
		pnlNorth = new JPanel();
		this.add(pnlNorth, BorderLayout.NORTH);
		pnlNorth.add(lblTitle = new JLabel("TÌM KIẾM SÁCH"));
		lblTitle.setFont(new Font("Arial", Font.BOLD, 30));
		lblTitle.setForeground(Color.blue);
		
		//west
		pnlWest = new JPanel();
		this.add(pnlWest, BorderLayout.WEST);
		bCTS = Box.createVerticalBox();
		bCTS.setBorder(BorderFactory.createTitledBorder("Chi tiết sách")); 
		pnlWest.add(bCTS);
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
			
			//donGia
			bCTS.add(bDonGia = Box.createHorizontalBox());
			bDonGia.add(lblDonGia= new JLabel("Đơn giá:"));
			bDonGia.add(txtDonGia = new JTextField(16));
			bCTS.add(Box.createVerticalStrut(10));
			
			//button
			bCTS.add(bButton = Box.createHorizontalBox());
			
			bCTS.add(bButton = Box.createHorizontalBox());
			bButton.add(btnTim= new JButton("Tìm kiếm"));
			btnTim.addActionListener(this);
			bButton.add(Box.createHorizontalStrut(10));
			
			//button làm mới
			bButton.add(Box.createHorizontalStrut(10));
			bButton.add(btnLamMoi = new JButton("Làm mới"));
			btnLamMoi.addActionListener(this);
			bCTS.add(bButton);
			
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
		bCen.setBorder(BorderFactory.createTitledBorder("Danh sách đầu sách"));
			//tableSach
			String [] headers = "Mã sách; Tên sách; ISBN; Số trang; Số lượng; Loại sách; Tác giả; Nhà xuất bản; Đơn giá".split(";");
			tableModelSach = new DefaultTableModel(headers, 0);
			JScrollPane scrollSach = new JScrollPane();
			scrollSach.setViewportView(tableSach = new JTable(tableModelSach));
			tableSach.setRowHeight(25);
			tableSach.setAutoCreateRowSorter(true);
			tableSach.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
			tableSach.addMouseListener(this);
			bCen.add(scrollSach);
			insertDataSach(SachDAO.getAllSach());
		this.add(bCen, BorderLayout.CENTER);
		
		//east
		bEast = Box.createVerticalBox();
			//tableLoaiSach
			String [] headersLoaiSach = "Mã loại sách; Tên loại sách".split(";");
			tableModelLoaiSach = new DefaultTableModel(headersLoaiSach, 0);
			JScrollPane scrollLoaiSach = new JScrollPane();
			scrollLoaiSach.setBorder(BorderFactory.createTitledBorder("Danh sách loại sách"));
			scrollLoaiSach.setViewportView(tableLoaiSach = new JTable(tableModelLoaiSach));
			tableLoaiSach.setRowHeight(25);
			tableLoaiSach.setAutoCreateRowSorter(true);
			tableLoaiSach.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
			tableLoaiSach.addMouseListener(this);
			bEast.add(scrollLoaiSach);
			insertDataLoaiSach();
			
			//tableNhaXB
			String [] headersNhaXB = "Mã nhà xuất bản; Tên nhà xuất bản".split(";");
			tableModelNhaXB = new DefaultTableModel(headersNhaXB, 0);
			JScrollPane scrollNhaXB = new JScrollPane();
			scrollNhaXB.setBorder(BorderFactory.createTitledBorder("Danh sách nhà xuất bản"));
			scrollNhaXB.setViewportView(tableNhaXB = new JTable(tableModelNhaXB));
			tableNhaXB.setRowHeight(25);
			tableNhaXB.setAutoCreateRowSorter(true);
			tableNhaXB.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
			tableNhaXB.addMouseListener(this);
			bEast.add(scrollNhaXB);
			insertDataNhaXB();	
		this.add(bEast, BorderLayout.EAST);		
		
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
	
	private void insertDataSach(List <Sach> Sach1) {
		// TODO Auto-generated method stub
		Sach sach=new Sach();
		List<Sach> list1 = new ArrayList<>();
		list1 = Sach1;
		DefaultTableModel modelSach= (DefaultTableModel) tableSach.getModel();
        modelSach.setRowCount(0);
        list1.forEach((S)->{
             modelSach.addRow(new Object[] {
            		 S.getMaSach(), S.getTenSach(),S.getiSBN(), Integer.toString(S.getSoTrang()), Integer.toString(S.getSoLuong()), S.getLoaiSach().getMaLoai()
            		 , S.getTacGia().getMaTacGia(), S.getNhaXB().getMaNhaXB(), Double.toString(S.getDonGiaBan()), Double.toString(S.getDonGiaNhap())
                   });
        });
}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FrmTimKiemSach().setVisible(true);
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
		txtDonGia.setText(tableModelSach.getValueAt(row, 8).toString());
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
	// Hàm tìm sách
		 public void TimSach() {
		     Sach sa = new Sach();
		     			 sa.setTenSach(txtTenSach.getText());     
		                 if (txtMaSach.getText().length()==0
		                     &&txtTenSach.getText().length()==0){
		                     JOptionPane.showMessageDialog(null, "xin mời nhập thông tin tìm kiếm");
		                 } else  {
		                     if (txtTenSach.getText().length()>0) {
		                    	 insertDataSach(SachDAO.FindTenSach(sa));                    	 
		                 }
		                   JOptionPane.showMessageDialog(null, "Đã hoàn tất thao tác");      
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
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
		if (src.equals(btnTim)) {
			TimSach();
			}
		if (src.equals(btnLamMoi)) {
			LamMoi();;
			xoaTrang();
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
		txtDonGia.setText("");
		txtMaSach.requestFocus();
	}

}
