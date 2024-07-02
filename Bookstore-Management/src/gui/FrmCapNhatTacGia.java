package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
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
import dao.TacGiaDAO;
import entity.KhachHang;
import entity.TacGia;

public class FrmCapNhatTacGia extends JFrame implements ActionListener, MouseListener {

	private JPanel pnlNorth, pnlWest;
	private JLabel lblTitle, lblMaTacGia, lblTenTacGia;
	private Box bTTTG, bMaTacGia, bTenTacGia, bButton, bCen;
	private JTextField txtMaTacGia, txtTenTacGia;
	private JButton btnThem, btnXoa, btnCapNhat, btnXoaTrang;
	private DefaultTableModel tableModel;
	private JTable table;
	private TacGiaDAO tacGiaDAO = new TacGiaDAO();

	public FrmCapNhatTacGia() {
		ConnectDatabase.getInstance().connect();
		setTitle("Quản lý tác giả");
		setExtendedState(MAXIMIZED_BOTH);
		setResizable(true);
		
		//north
		pnlNorth = new JPanel();
		this.add(pnlNorth, BorderLayout.NORTH);
		pnlNorth.add(lblTitle = new JLabel("QUẢN LÝ TÁC GIẢ"));
		lblTitle.setFont(new Font("Arial", Font.BOLD, 30));
		lblTitle.setForeground(Color.BLUE);
		
		//west
		pnlWest = new JPanel();
		this.add(pnlWest, BorderLayout.WEST);
		bTTTG = Box.createVerticalBox();
		bTTTG.setBorder(BorderFactory.createTitledBorder("Thông tin tác giả")); 
		pnlWest.add(bTTTG);
		
				//maTacGia
				bTTTG.add(bMaTacGia = Box.createHorizontalBox());
				bMaTacGia.add(lblMaTacGia = new JLabel("Mã khách hàng:"));
				bMaTacGia.add(txtMaTacGia = new JTextField(16));
				bTTTG.add(Box.createVerticalStrut(10));
				
				//tenKhachHang
				bTTTG.add(bTenTacGia = Box.createHorizontalBox());
				bTenTacGia.add(lblTenTacGia = new JLabel("Tên khách hàng:"));
				bTenTacGia.add(txtTenTacGia = new JTextField(16));
				bTTTG.add(Box.createVerticalStrut(10));
					
				//button
				bTTTG.add(bButton = Box.createHorizontalBox());
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
		
				lblMaTacGia.setPreferredSize(lblTenTacGia.getPreferredSize());

		//center
		bCen = Box.createVerticalBox();
		bCen.setBorder(BorderFactory.createTitledBorder("Danh sách tác giả"));
			//table
			String [] headers = "Mã tác giả; Tên tác giả".split(";");
			tableModel = new DefaultTableModel(headers, 0);
			JScrollPane scroll = new JScrollPane();
			scroll.setViewportView(table = new JTable(tableModel));
			table.setRowHeight(25);
			table.setAutoCreateRowSorter(true);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
			table.addMouseListener(this);
			insertDataTacGia();
			bCen.add(scroll);
		this.add(bCen, BorderLayout.CENTER);
	}
	
	private void insertDataTacGia() {
		// TODO Auto-generated method stub
		TacGiaDAO danhSachTacGia = new TacGiaDAO();
		List<TacGia> list = danhSachTacGia.getAllTacGia();
		for (TacGia tacGia : list) {
			String [] row = {tacGia.getMaTacGia(), tacGia.getTenTacGia()};
			tableModel.addRow(row);
		}
		table.setModel(tableModel);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FrmCapNhatTacGia().setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		txtMaTacGia.setText(tableModel.getValueAt(row, 0).toString());
		txtTenTacGia.setText(tableModel.getValueAt(row, 1).toString());
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
			themTacGia();
		}
		if (src.equals(btnXoa)) {
			xoaTG();
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
		txtMaTacGia.setText("");
		txtTenTacGia.setText("");
		txtMaTacGia.requestFocus();
	}

	private void capNhat() {
		// TODO Auto-generated method stub
		int loiNhac = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn sửa thông tin?", "Chú ý", JOptionPane.YES_NO_OPTION);
		if (loiNhac == JOptionPane.YES_OPTION)
		{
			int row = table.getSelectedRow();
			String maTG = txtMaTacGia.getText();
			String tenTG = txtTenTacGia.getText();
			TacGia tacGia = new TacGia(maTG, tenTG);
			if (row >= 0) {
				if (validData() == true) {
					if (tacGiaDAO.update(tacGia)) {
						table.setValueAt(txtMaTacGia.getText(), row, 0);
						table.setValueAt(txtTenTacGia.getText(), row, 1);
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

	private void xoaTG() {
		// TODO Auto-generated method stub
		int loiNhac = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", "Chú ý", JOptionPane.YES_NO_OPTION);
		if (loiNhac == JOptionPane.YES_OPTION)
		{
			int row = table.getSelectedRow();
			if (row >= 0)
			{
				String maTG = (String) table.getValueAt(row, 0);
				if (tacGiaDAO.delete(maTG)) {
					tableModel.removeRow(row);
					xoaTrang();
				}
			}
			JOptionPane.showMessageDialog(this, "Đã xóa thông tin tác giả này!");
		}
	}

	private void themTacGia() {
		// TODO Auto-generated method stub
		if (validData() == true)
		{
			String maTG = txtMaTacGia.getText();
			String tenTG = txtTenTacGia.getText();
			TacGia tacGia = new TacGia(maTG, tenTG);
			try {
				tacGiaDAO.create(tacGia);
				tableModel.addRow(new Object[] {tacGia.getMaTacGia(), tacGia.getTenTacGia()});
				JOptionPane.showMessageDialog(this, "Đã thêm thành công");
			}
			catch (Exception e1) {
				JOptionPane.showMessageDialog(this, "Trùng!");
			}
			
		}
	}

}
