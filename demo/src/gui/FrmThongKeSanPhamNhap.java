package gui;

import java.awt.event.ActionListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JFrame;

public class FrmThongKeSanPhamNhap extends JFrame implements ActionListener, MouseListener{
	
	private JLabel lblTitle;
	private JComboBox cboThongKe;
	private JTextField txtLoaiSach;
	private JButton btnThongKe, btnLamMoi;
	private DefaultTableModel tableModel;
	private JTable table;
	private JPanel pCen, pNorth;
	
	public FrmThongKeSanPhamNhap() {
		setTitle("Quản Lý Hiệu Sách");
		setExtendedState(MAXIMIZED_BOTH);
		
		//north
		pNorth = new JPanel();
		pNorth.add(lblTitle = new JLabel("THỐNG KÊ SÁCH NHẬP"));
		Font fontTitle = new Font ("Arial", Font.BOLD, 30);
		lblTitle.setForeground(Color.BLUE);
		lblTitle.setFont(fontTitle);
		this.add(pNorth, BorderLayout.NORTH);
		
		//center
		pCen = new JPanel();
		this.add(pCen , BorderLayout.CENTER);
		pCen.setPreferredSize(new Dimension(350,500));
		pCen.setLayout(null); // Absolute layout
		JLabel lblThongKeTheo, lblLoaiSach;
		pCen.setBorder(BorderFactory.createTitledBorder("Chi tiết"));
		int w1 = 125, w2 = 230, h = 30;
		//JLable
		pCen.add(lblThongKeTheo = new JLabel("Thống kê theo: "));
		String[] thongke= {"Ngày", "Tuần", "Tháng", "Năm"};
		pCen.add(cboThongKe=new JComboBox(thongke));
		
		lblThongKeTheo.setBounds(20, 20, w1, h);
		cboThongKe.setBounds(120, 20, w2, h);
		
		pCen.add(lblLoaiSach = new JLabel("Loại sách: "));
		
		//TEXT
		pCen.add(txtLoaiSach = new JTextField());
		
		//Setbound
		
		lblLoaiSach.setBounds(20, 70, w1, h);
		txtLoaiSach.setBounds(120, 70, w2, h);
		
		//Button
		pCen.add(btnThongKe = new JButton("Thống kê"));
		pCen.add(btnLamMoi = new JButton("Làm mới dữ liệu"));
		
		btnThongKe.setBounds(400, 70, 110, 30);
		btnLamMoi.setBounds(550, 70, 140, 30);
		
		//SOUTH
		
		JPanel pSouth;
		add(pSouth = new JPanel(), BorderLayout.SOUTH);
		pSouth.setPreferredSize(new Dimension(350,500));
		pSouth.setBorder(BorderFactory.createTitledBorder("Danh sách thống kê"));
		pSouth.setLayout(null); // Absolute layout
		
		
		JScrollPane scroll;
		String [] headers = "Số thứ tự;Mã sách;Tên sách;Số lượng;ISBN;Tác giả;Nhà xuất bản;Đơn giá;Số Trang".split(";");
		
		tableModel = new DefaultTableModel(headers,0);
		add(scroll = new JScrollPane(table = new JTable(tableModel), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.SOUTH);
		
		scroll.setBorder(BorderFactory.createTitledBorder("Danh sách thống kê"));
		table.setRowHeight(25);
		scroll.setPreferredSize(new Dimension(530, 350));
	
	}
		
	public static void main(String[] args) {
		new FrmThongKeSanPhamNhap().setVisible(true);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
}
