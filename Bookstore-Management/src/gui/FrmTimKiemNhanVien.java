package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
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
import javax.swing.ImageIcon;
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
import dao.NhaXuatBanDAO;
import dao.NhanVienDAO;
import entity.CaLamViec;
import entity.NhaXuatBan;
import entity.NhanVien;
import dao.CaLamDAO;
public class FrmTimKiemNhanVien extends JFrame implements ActionListener, MouseListener{
	
	private JPanel pnlNorth, pnlWest;
	private Box bTTNV, bMaNV, bTenNV, bSoDT, bEmail, bDiaChi, bButton, bCen, bCa;
	private JLabel lblTitle, lblMaNV, lblTenNV, lblSoDT, lblEmail, lblDiaChi, lblCa ;
	private JTextField txtMaNV, txtTenNV, txtSoDT, txtEmail, txtDiaChi, txtCa;
	private DefaultTableModel tableModel;
	private JButton btnTim, btnXoaTrang;
	private JTable table;
	private JComboBox cboCaLamViec;
	NhanVienDAO nhanvienDAO = new NhanVienDAO();
	CaLamDAO caLamDAO = new CaLamDAO();
	private JButton btnLamMoi;
	
	public FrmTimKiemNhanVien() {
		ConnectDatabase.getInstance().connect();
		setTitle("Tìm kiếm nhân viên");
		setExtendedState(MAXIMIZED_BOTH);
		
		//north
		pnlNorth = new JPanel();
		this.add(pnlNorth, BorderLayout.NORTH);
		pnlNorth.add(lblTitle = new JLabel("TÌM KIẾM THÔNG TIN NHÂN VIÊN"));
		lblTitle.setFont(new Font("Arial", Font.BOLD, 30));
		lblTitle.setForeground(Color.blue);
		
		//west
		pnlWest = new JPanel();
		this.add(pnlWest, BorderLayout.WEST);
		bTTNV = Box.createVerticalBox();
		bTTNV.setBorder(BorderFactory.createTitledBorder("Thông tin tìm kiếm")); 
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
		
		//SoDienThoai
		bTTNV.add(bSoDT = Box.createHorizontalBox());
		bSoDT.add(lblSoDT = new JLabel("Số điện thoại:"));
		bSoDT.add(txtSoDT = new JTextField(16));
		bTTNV.add(Box.createVerticalStrut(10));
		
		//Email
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
		bTTNV.add(bCa = Box.createHorizontalBox());
		bCa.add(lblCa = new JLabel("Ca làm việc:"));
		bCa.add(cboCaLamViec = new JComboBox<String>());
		cboCaLamViec.setEditable(false);
		caLamDAO = new CaLamDAO();
		ArrayList<CaLamViec> danhSachCaLamViec = caLamDAO.getAllCaLam();
		for (CaLamViec ca : danhSachCaLamViec) {
			cboCaLamViec.addItem(ca.getMaCalamViec());
		}
		bTTNV.add(Box.createVerticalStrut(10));
		
		lblMaNV.setPreferredSize(lblTenNV.getPreferredSize());
		//lblTenNV.setPreferredSize(lblTenNV.getPreferredSize());
		lblSoDT.setPreferredSize(lblTenNV.getPreferredSize());
		lblEmail.setPreferredSize(lblTenNV.getPreferredSize());
		lblDiaChi.setPreferredSize(lblTenNV.getPreferredSize());
		lblCa.setPreferredSize(lblTenNV.getPreferredSize());
		
		//button
		bTTNV.add(bButton = Box.createHorizontalBox());
		bButton.add(btnTim = new JButton("Tìm kiếm"));
		btnTim.addActionListener(this);
		bButton.add(Box.createHorizontalStrut(10));
		
		bButton.add(btnXoaTrang = new JButton("Xóa trắng"));
		btnXoaTrang.addActionListener(this);
		bButton.add(Box.createHorizontalStrut(10));
		
		bButton.add(btnLamMoi = new JButton("Làm mới"));
		btnLamMoi.addActionListener(this);
			
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
		insertData(NhanVienDAO.getAllNhanVien());}

		void insertData(List<NhanVien> danhSachNV) {
	        DefaultTableModel modelNhanVien= (DefaultTableModel) table.getModel();
	        modelNhanVien.setRowCount(0);
	        danhSachNV.forEach((nhanVien)->{
	        	modelNhanVien.addRow(new Object[] {
	            nhanVien.getMaNV(), nhanVien.getTenNV(), nhanVien.getSoDT(),nhanVien.getEmail(), nhanVien.getDiaChi(), nhanVien.getCaLamViec().getMaCalamViec()});
	        });
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FrmTimKiemNhanVien().setVisible(true);
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
		if (src.equals(btnTim)) {
			tim();
		}
		if (src.equals(btnLamMoi)) {
			lamMoi();
		}
		
	}
	private void lamMoi() {
		// TODO Auto-generated method stub
		NhanVienDAO danhSachNhanVien = new NhanVienDAO();
		List<NhanVien> list = danhSachNhanVien.getAllNhanVien();
		for (NhanVien nhanVien : list) {
			String [] row = {nhanVien.getMaNV(), nhanVien.getTenNV(), nhanVien.getSoDT(), nhanVien.getEmail(), nhanVien.getDiaChi(), nhanVien.getCaLamViec().getMaCalamViec()};
			tableModel.addRow(row);
		}
		table.setModel(tableModel);
	}

	private void tim() {
		// TODO Auto-generated method stub
		String maNV = txtMaNV.getText();
		String tenNV = txtTenNV.getText();
		String soDT = txtSoDT.getText();
		String email = txtEmail.getText();
		String diaChi = txtDiaChi.getText();
		String maCaLamViec = cboCaLamViec.getSelectedItem().toString();
		CaLamViec caLamViec = new CaLamViec(maCaLamViec);
		NhanVien nhanVien = new NhanVien(maNV, tenNV, soDT, email, diaChi, caLamViec);
        if (txtMaNV.getText().length() == 0
            &&txtTenNV.getText().length() == 0
            &&txtSoDT.getText().length() == 0
            &&txtEmail.getText().length() == 0
            &&txtDiaChi.getText().length() == 0
            &&cboCaLamViec.getSelectedItem().toString().length() == 0) 
        {
            JOptionPane.showMessageDialog(null, "Xin mời nhập thông tin tìm kiếm");
        } 
        else
        {
        	if (!txtTenNV.getText().isEmpty()) {
          		 insertData(NhanVienDAO.FindTenNhanVien(nhanVien));
            }

            if (!txtSoDT.getText().isEmpty()) {
           	 	insertData(NhanVienDAO.FindSoDTNhanVien(nhanVien));
            }
          
            if (!txtEmail.getText().isEmpty()) {
           	 	insertData(NhanVienDAO.FindEmailNhanVien(nhanVien));
            }
            
            if (!txtDiaChi.getText().isEmpty()) {
           	 	insertData(NhanVienDAO.FindDiaChiNhanVien(nhanVien));
            }
        }
        JOptionPane.showMessageDialog(null, "Đã hoàn tất thao tác");
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

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
