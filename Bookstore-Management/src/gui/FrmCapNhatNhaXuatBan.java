package gui;
import java.awt.BorderLayout;





import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.NumberFormat;
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

import entity.NhaXuatBan;
import entity.NhanVien;

public class FrmCapNhatNhaXuatBan extends JFrame implements ActionListener, MouseListener {
	
	private JPanel pnlNorth, pnlWest;
	private JLabel lblTitle;
	private Box bCTS, bMaNhaXB, bTenNhaXB, bButton, bCen;
	private JLabel lblMaNhaXB, lblTenNhaXB;
	private JTextField txtMaNhaXB, txtTenNhaXB;
	private JButton btnThem, btnXoa, btnCapNhat, btnXoaTrang;
	private DefaultTableModel tableModel;
	private JTable table;
	NhaXuatBanDAO nhaxuatbanDAO = new NhaXuatBanDAO();
	
	public FrmCapNhatNhaXuatBan() {
		ConnectDatabase.getInstance().connect();
		setTitle("Quản lý nhà xuất bản");
		setExtendedState(MAXIMIZED_BOTH);
		
		//north
		pnlNorth = new JPanel();
		this.add(pnlNorth, BorderLayout.NORTH);
		pnlNorth.add(lblTitle = new JLabel("QUẢN LÝ NHÀ XUẤT BẢN"));
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
		
		lblMaNhaXB.setPreferredSize(lblTenNhaXB.getPreferredSize());
		//lblTenNV.setPreferredSize(lblTenNV.getPreferredSize());
		
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
		insertData();
	}
	
	//insertData
	private void insertData() {
		// TODO Auto-generated method stub
		NhaXuatBanDAO danhSachNhaXuatBan = new NhaXuatBanDAO();
		
		List<NhaXuatBan> list = danhSachNhaXuatBan.getAllNhaXuatBan();
		for (NhaXuatBan nhaXB : list) {
			Locale localeVN = new Locale("vi", "VN");
			
			String [] row = {nhaXB.getMaNhaXB(), nhaXB.getTenNhaXB()};
			tableModel.addRow(row);
		}
		table.setModel(tableModel);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
				int row = table.getSelectedRow();
				txtMaNhaXB.setText(tableModel.getValueAt(row, 0).toString());
				txtTenNhaXB.setText(tableModel.getValueAt(row, 1).toString());	
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
			xoaNhaXuatBan();
		}
		if (src.equals(btnThem)) {
			themNhaXuatBan();
		}
		if (src.equals(btnCapNhat)) {
			capNhatNhaXuatBan();
		}
	}
	
	private void capNhatNhaXuatBan() {
		// TODO Auto-generated method stub
		int loiNhac = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn sửa thông tin nhà xuất bản này?", "Chú ý", JOptionPane.YES_NO_OPTION);
		if (loiNhac == JOptionPane.YES_OPTION)
		{
			int row = table.getSelectedRow();
			String maNhaXB = txtMaNhaXB.getText();
			String tenNhaXB = txtTenNhaXB.getText();
			
			NhaXuatBan nhaXB = new NhaXuatBan(maNhaXB, tenNhaXB);
			if (row >= 0) {
				if (nhaxuatbanDAO.update(nhaXB)) {
					table.setValueAt(txtMaNhaXB.getText(), row, 0);
					table.setValueAt(txtTenNhaXB.getText(), row, 1);
					JOptionPane.showMessageDialog(this, "Đã sửa thông tin nhà xuất bản thành công!");
				}
			}
		}
	}
			
	private void themNhaXuatBan() {
		if (validData() == true)
		{
			String maNhaXB = txtMaNhaXB.getText();
			String tenNhaXB = txtTenNhaXB.getText();
			NhaXuatBan nhaXB = new NhaXuatBan(maNhaXB, tenNhaXB);
			try {
				nhaxuatbanDAO.create(nhaXB);
				tableModel.addRow(new Object[] {nhaXB.getMaNhaXB(), nhaXB.getTenNhaXB()});
				JOptionPane.showMessageDialog(this, "Đã thêm thành công");
			}
			catch (Exception e1) {
				JOptionPane.showMessageDialog(this, "Lỗi!");
			}
		}
	}
	
	private boolean validData() {
		// TODO Auto-generated method stub
		String maNhaXB = txtMaNhaXB.getText();
		if (maNhaXB.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Chưa nhập mã nhà xuất bản");
			return false;
		}
		if (!maNhaXB.matches("^NXB-\\d{3}$")) {
			JOptionPane.showMessageDialog(this, "Mã nhà xuất bản phải theo mẫu NXBXXX với XXX là ba chữ số bất kì");
			return false;
		}
		String tenNhaXB = txtTenNhaXB.getText();
		if (tenNhaXB.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Chưa nhập tên nhà xuất bản");
			return false;
		}
		return true;
	}

	private void xoaNhaXuatBan() {
		// TODO Auto-generated method stub
		int loiNhac = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", "Chú ý", JOptionPane.YES_NO_OPTION);
		if (loiNhac == JOptionPane.YES_OPTION)
		{
			int row = table.getSelectedRow();
			if (row >= 0)
			{
				String maNhaXB = (String) table.getValueAt(row, 0);
				if (nhaxuatbanDAO.delete(maNhaXB)) {
					tableModel.removeRow(row);
					xoaTrang();
				}
			}
			JOptionPane.showMessageDialog(this, "Đã xóa nhà xuất bản này!");
		}
	}
	
	private void xoaTrang() {
		// TODO Auto-generated method stub
		txtMaNhaXB.setText("");
		txtTenNhaXB.setText("");
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FrmCapNhatNhaXuatBan().setVisible(true);
	}

}
