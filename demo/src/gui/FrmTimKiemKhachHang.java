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
import dao.KhachHangDAO;
import dao.NhaCungCapDAO;
import dao.SanPhamKhacDAO;
import entity.KhachHang;
import entity.NhaCungCap;
public class FrmTimKiemKhachHang extends JFrame implements ActionListener, MouseListener{
	
	private JPanel pnlNorth, pnlWest;
	private JLabel lblTitle;
	private Box bCen,bButton, bTTKH, bMaKhachHang, bTenKhachHang, bSoDienThoai, bEmail, bDiaChi;
	private JButton btnThem, btnXoa, btnCapNhat, btnXoaTrang;
	private DefaultTableModel tableModel;
	private JTable table;
	private JLabel lblMaKhachHang, lblTenKhachHang, lblSoDienThoai, lblEmail, lblDiaChi;
	private JTextField txtMaKhachHang, txtTenKhachHang, txtSoDienThoai, txtEmail, txtDiaChi;
	KhachHangDAO khachHangDAO = new KhachHangDAO();
	private AbstractButton btnTim;
	private JButton btnLamMoi;
	
	public FrmTimKiemKhachHang() {
		ConnectDatabase.getInstance().connect();
		setTitle("Quản Lý Hiệu Sách Tư Nhân");
		setExtendedState(MAXIMIZED_BOTH);
		
		//north
		pnlNorth = new JPanel();
		this.add(pnlNorth, BorderLayout.NORTH);
		pnlNorth.add(lblTitle = new JLabel("TÌM KIẾM KHÁCH HÀNG"));
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
					bButton.add(btnLamMoi= new JButton("Làm Mới"));
					btnLamMoi.addActionListener(new ActionListener() {
						

						public void actionPerformed(ActionEvent e) {
							Object o = e.getSource();
							if (o.equals(btnLamMoi)) {
								XoaHetDuLieuTable();
								xoaTrang();
								insertData(KhachHangDAO.getAllKhachHang());
							}
						}
						

					});
						
					
					
					
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
				this.add(bCen, BorderLayout.CENTER);
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
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FrmTimKiemKhachHang().setVisible(true);
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
	// Hàm làm mới
				private void LamMoi() {
					// TODO Auto-generated method stub
					XoaHetDuLieuTable();
					insertData(khachHangDAO.getAllKhachHang());
				}
				//Hàm xóa trắng
				private void xoaTrang() {
					// TODO Auto-generated method stub
					txtMaKhachHang.setText("");
					txtTenKhachHang.setText("");
					txtSoDienThoai.setText("");
					txtEmail.setText("");
					txtDiaChi.setText("");
					txtMaKhachHang.requestFocus();
				}
				//Hàm xóa dữ liệu table
				private void XoaHetDuLieuTable() {
					DefaultTableModel dtm = (DefaultTableModel) table.getModel();
					dtm.setRowCount(0);
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
		   
}

	}
