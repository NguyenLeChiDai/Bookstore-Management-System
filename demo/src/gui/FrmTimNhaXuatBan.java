package gui;
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
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
import dao.NhaXuatBanDAO;
import dao.NhanVienDAO;
import dao.SachDAO;
import entity.CaLamViec;
import entity.NhaXuatBan;
import entity.NhanVien;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class FrmTimNhaXuatBan extends JFrame implements ActionListener, MouseListener {
	
	private JPanel pnlNorth, pnlWest;
	private JLabel lblTitle;
	private Box bCTS, bMaNhaXB, bTenNhaXB, bButton, bCen;
	private JLabel lblMaNhaXB, lblTenNhaXB;
	private JTextField txtMaNhaXB, txtTenNhaXB;
	private JButton btnTim, btnLamMoi;
	private DefaultTableModel tableModel;
	private JTable table;
	NhaXuatBanDAO nhaxuatbanDAO = new NhaXuatBanDAO();
	private JTable model;
	private DefaultTableModel model_2;
	
	public FrmTimNhaXuatBan() {
		ConnectDatabase.getInstance().connect();
		setTitle("Tìm nhà xuất bản");
		setExtendedState(MAXIMIZED_BOTH);
		
		//north
		pnlNorth = new JPanel();
		this.add(pnlNorth, BorderLayout.NORTH);
		pnlNorth.add(lblTitle = new JLabel("TÌM THÔNG TIN NHÀ XUẤT BẢN"));
		lblTitle.setFont(new Font("Arial", Font.BOLD, 30));
		lblTitle.setForeground(Color.blue);
		
		//west
		pnlWest = new JPanel();
		this.add(pnlWest, BorderLayout.WEST);
		bCTS = Box.createVerticalBox();
		bCTS.setBorder(BorderFactory.createTitledBorder("Chi tiết nhà xuất bản")); 
		pnlWest.add(bCTS);
			
		//maNhaXuatBan
		bCTS.add(bMaNhaXB = Box.createHorizontalBox());
		bMaNhaXB.add(lblMaNhaXB = new JLabel("Mã nhà xuất bản:"));
		bMaNhaXB.add(txtMaNhaXB = new JTextField(16));
		bCTS.add(Box.createVerticalStrut(10));
		
		//tenNhaXuatBan
		bCTS.add(bTenNhaXB = Box.createHorizontalBox());
		bTenNhaXB.add(lblTenNhaXB = new JLabel("Tên nhà xuất bản:"));
		bTenNhaXB.add(txtTenNhaXB = new JTextField(16));
		bCTS.add(Box.createVerticalStrut(10));
		
		//button
		bCTS.add(bButton = Box.createHorizontalBox());
		bButton.add(btnTim = new JButton("Tìm kiếm"));
		btnTim.addActionListener(this);
		bButton.add(Box.createHorizontalStrut(10));
		


		//button làm mới
		bButton.add(Box.createHorizontalStrut(10));
		bButton.add(btnLamMoi = new JButton("Làm mới"));
		btnLamMoi.addActionListener(this);
		bCTS.add(bButton);
		
		lblMaNhaXB.setPreferredSize(lblTenNhaXB.getPreferredSize());
		//center
		bCen = Box.createVerticalBox();
		bCen.setBorder(BorderFactory.createTitledBorder("Danh sách nhà xuất bản"));
			//table
			String [] headers = "Mã nhà xuất bản; Tên nhà xuất bản".split(";");
			tableModel = new DefaultTableModel(headers, 0);
			JScrollPane scroll = new JScrollPane();
			scroll.setViewportView(table = new JTable(tableModel));
			table.setRowHeight(25);
			table.setAutoCreateRowSorter(true);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
			table.addMouseListener(this);
			bCen.add(scroll);
		this.add(bCen, BorderLayout.CENTER);
		insertData(NhaXuatBanDAO.getAllNhaXuatBan());
	}
	//Hàm insert
	void insertData(List <NhaXuatBan> NhaXuatBan1 ) {
	   
	        List <NhaXuatBan> list1 = new ArrayList<>();
	        list1=NhaXuatBan1;
	        DefaultTableModel model= (DefaultTableModel) table.getModel();
	        model.setRowCount(0);
	        list1.forEach((nxb)->{
	             model.addRow(new Object[] {
	                     nxb.getMaNhaXB(), nxb.getTenNhaXB()
	                   });
	        });
	}
	
	
//Hàm mouseClick
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		txtMaNhaXB.setText(tableModel.getValueAt(row, 0).toString());
		txtTenNhaXB.setText(tableModel.getValueAt(row, 1).toString());
	}
	//Tìm NXB
	 public void TimNXB() {
	     NhaXuatBan nxb = new NhaXuatBan();
	     nxb.setMaNhaXB(txtMaNhaXB.getText());
	     nxb.setTenNhaXB(txtTenNhaXB.getText());          
	                 if (txtMaNhaXB.getText().length()==0
	                     &&txtTenNhaXB.getText().length()==0){
	                     JOptionPane.showMessageDialog(null, "Bạn chưa nhập tên nhà xuất bản");
	                 } else {
	                     if (txtTenNhaXB.getText().length()>0) {
	                    	 insertData(NhaXuatBanDAO.FindTenNhaXuatBan(nxb));
	                   
	                     }
	                     JOptionPane.showMessageDialog(null, "Đã hoàn tất thao tác");
	                 }
	}
	// Hàm làm mới
				private void LamMoi() {
					// TODO Auto-generated method stub
					XoaHetDuLieuTable();
					insertData(NhaXuatBanDAO.getAllNhaXuatBan());
				}
				
	 //Xóa hết dữ liệu table
	 private void XoaHetDuLieuTable() {
			DefaultTableModel dtm = (DefaultTableModel) table.getModel();
			dtm.setRowCount(0);
		}
	 //xóa trắng
	 private void xoaTrang() {
			// TODO Auto-generated method stub
			txtMaNhaXB.setText("");
			txtTenNhaXB.setText("");
			txtMaNhaXB.requestFocus();
		}
	 //xử lý Nút tìm
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object src = e.getSource();
			if (src.equals(btnTim)) {
				TimNXB();
			}
			if (src.equals(btnLamMoi)) {
				LamMoi();
				xoaTrang();
			}
		}
		
		
		
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			new FrmTimNhaXuatBan().setVisible(true);
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
