package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
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
import entity.KhachHang;
import entity.LoaiSach;

public class FrmCapNhatLoaiSach extends JFrame implements ActionListener, MouseListener {

	private JPanel pnlNorth, pnlWest;
	private JLabel lblTitle, lblMaLoai, lblTenLoai;
	private Box bCTLS, bMaLoai, bTenLoai, bButton;
	private JTextField txtMaLoai, txtTenLoai;
	private JButton btnThem, btnXoa, btnCapNhat, btnXoaTrang;
	private Box bCen;
	private DefaultTableModel tableModel;
	private JTable table;
	private LoaiSachDAO loaiSachDAO = new LoaiSachDAO();

	public FrmCapNhatLoaiSach() {
		// TODO Auto-generated constructor stub
		ConnectDatabase.getInstance().connect();
		setTitle("Cập nhật loại sách");
		setExtendedState(MAXIMIZED_BOTH);
		
		//north
		pnlNorth = new JPanel();
		this.add(pnlNorth, BorderLayout.NORTH);
		pnlNorth.add(lblTitle = new JLabel("QUẢN LÝ LOẠI SÁCH"));
		lblTitle.setFont(new Font("Arial", Font.BOLD, 30));
		lblTitle.setForeground(Color.BLUE);
		
		//west
		pnlWest = new JPanel();
		this.add(pnlWest, BorderLayout.WEST);
		bCTLS = Box.createVerticalBox();
		bCTLS.setBorder(BorderFactory.createTitledBorder("Chi tiết loại sách"));
		pnlWest.add(bCTLS);
		
			//maLoai
			bCTLS.add(bMaLoai = Box.createHorizontalBox());
			bMaLoai.add(lblMaLoai = new JLabel("Mã loại sách:"));
			bMaLoai.add(txtMaLoai = new JTextField(16));
			bCTLS.add(Box.createVerticalStrut(10));
			
			//tenLoai
			bCTLS.add(bTenLoai = Box.createHorizontalBox());
			bTenLoai.add(lblTenLoai = new JLabel("Tên loại sách:"));
			bTenLoai.add(txtTenLoai = new JTextField(16));
			bCTLS.add(Box.createVerticalStrut(10));
			
			lblMaLoai.setPreferredSize(lblTenLoai.getPreferredSize());
			
			//button
			bCTLS.add(bButton = Box.createHorizontalBox());
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
		
		//center
			bCen = Box.createVerticalBox();
			bCen.setBorder(BorderFactory.createTitledBorder("Danh sách khách hàng"));
				//table
				String [] headers = "Mã loại sách; Tên loại sách".split(";");
				tableModel = new DefaultTableModel(headers, 0);
				JScrollPane scroll = new JScrollPane();
				scroll.setViewportView(table = new JTable(tableModel));
				table.setRowHeight(25);
				table.setAutoCreateRowSorter(true);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
				table.addMouseListener(this);
				insertDataLoaiSach();
				bCen.add(scroll);
			this.add(bCen, BorderLayout.CENTER);	
	}

	private void insertDataLoaiSach() {
		// TODO Auto-generated method stub
		LoaiSachDAO danhSachLoaiSach = new LoaiSachDAO();
		List<LoaiSach> list = danhSachLoaiSach.getAllLoaiSach();
		for (LoaiSach loaiSach : list) {
			String [] row = {loaiSach.getMaLoai(), loaiSach.getTenLoai()};
			tableModel.addRow(row);
		}
		table.setModel(tableModel);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FrmCapNhatLoaiSach().setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		txtMaLoai.setText(tableModel.getValueAt(row, 0).toString());
		txtTenLoai.setText(tableModel.getValueAt(row, 1).toString());
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
		if (src.equals(btnThem)) {
			them();
		}
		if (src.equals(btnXoa)) {
			xoa();
		}
		if (src.equals(btnCapNhat)) {
			capNhat();
		}
		if (src.equals(btnXoaTrang)) {
			xoaTrang();
		}
	}

	private void xoaTrang() {
		// TODO Auto-generated method stub
		txtMaLoai.setText("");
		txtTenLoai.setText("");
		txtMaLoai.requestFocus();
	}

	private void capNhat() {
		// TODO Auto-generated method stub
		int loiNhac = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn sửa thông tin?", "Chú ý", JOptionPane.YES_NO_OPTION);
		if (loiNhac == JOptionPane.YES_OPTION)
		{
			int row = table.getSelectedRow();
			String maLoai = txtMaLoai.getText();
			String tenLoai = txtTenLoai.getText();
			LoaiSach loaiSach = new LoaiSach(maLoai, tenLoai);
			if (row >= 0) {
				if (validData() == true) {
					if (LoaiSachDAO.update(loaiSach)) {
						table.setValueAt(txtMaLoai.getText(), row, 0);
						table.setValueAt(txtTenLoai.getText(), row, 1);
						JOptionPane.showMessageDialog(this, "Đã sửa thành công!");
					}
				}				
			}
		}	
	}

	private boolean validData() {
		// TODO Auto-generated method stub
		return true;
	}

	private void xoa() {
		// TODO Auto-generated method stub
		int loiNhac = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", "Chú ý", JOptionPane.YES_NO_OPTION);
		if (loiNhac == JOptionPane.YES_OPTION)
		{
			int row = table.getSelectedRow();
			if (row >= 0)
			{
				String maLoai = (String) table.getValueAt(row, 0);
				if (loaiSachDAO.delete(maLoai)) {
					tableModel.removeRow(row);
					xoaTrang();
				}
			}
			JOptionPane.showMessageDialog(this, "Đã xóa thông tin khách hàng này!");
		}
	}

	private void them() {
		// TODO Auto-generated method stub
		if (validData() == true)
		{
			String maLoai = txtMaLoai.getText();
			String tenLoai = txtTenLoai.getText();
			LoaiSach loaiSach = new LoaiSach(maLoai, tenLoai);
			try {
				loaiSachDAO.create(loaiSach);
				tableModel.addRow(new Object[] {loaiSach.getMaLoai(), loaiSach.getTenLoai()});
				JOptionPane.showMessageDialog(this, "Đã thêm thành công");
			}
			catch (Exception e1) {
				JOptionPane.showMessageDialog(this, "Trùng!");
			}
		}
	}

}
