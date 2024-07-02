package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;

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
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


import connectDB.ConnectDatabase;

public class FrmThongKeSanPhamBan extends JFrame implements ActionListener, MouseListener {

	private JPanel pnlNorth, pnlWest;
	private JLabel lblTitle, lblThongKeTheo, lblTuNgay;
	private Box bButton, bCen, bThongKeTheo, bTable, bTuNgay;
	private JComboBox cboThongKeTheo;
	private DefaultTableModel tableModel;
	private JTable table;

	public FrmThongKeSanPhamBan() {
		ConnectDatabase.getInstance().connect();
		setTitle("Thống kê sản phẩm nhập");
		setExtendedState(MAXIMIZED_BOTH);
		
		//north
		pnlNorth = new JPanel();
		this.add(pnlNorth, BorderLayout.NORTH);
		pnlNorth.add(lblTitle = new JLabel("THỐNG KÊ SÁCH BÁN CHẠY"));
		lblTitle.setFont(new Font("Arial", Font.BOLD, 30));
		lblTitle.setForeground(Color.blue);
		
		//west
		pnlWest = new JPanel();
		this.add(pnlWest, BorderLayout.WEST);
		bButton = Box.createVerticalBox();
		bButton.setBorder(BorderFactory.createTitledBorder(""));
		pnlWest.add(bButton);
		
			//thongKeTheo
			bButton.add(bThongKeTheo = Box.createHorizontalBox());
			bThongKeTheo.add(lblThongKeTheo = new JLabel("Thống Kê Theo:"));
			String [] thongKeTheo = {"Tùy chỉnh", "Ngày hôm nay", "Ngày hôm qua", "7 ngày qua", "Theo tháng", "Theo năm"};
			bThongKeTheo.add(cboThongKeTheo = new JComboBox(thongKeTheo));
		
			//tuNgay
			bButton.add(bTuNgay = Box.createHorizontalBox());
			bTuNgay.add(lblTuNgay = new JLabel("Từ ngày:"));
		//	bTuNgay.add(dchThuNgay = new jDateChooser());
		//center
		bCen = Box.createVerticalBox();
		this.add(bCen, BorderLayout.CENTER);
		
			//table
			bTable = Box.createVerticalBox();
			String [] headers = "Mã sách; Tên sách; ISBN; Số trang; Số lượng; Loại sách; Tác giả; Nhà xuất bản; Đơn giá".split(";");
			tableModel = new DefaultTableModel(headers, 0);
			JScrollPane scroll = new JScrollPane();
			scroll.setViewportView(table = new JTable(tableModel));
			table.setRowHeight(25);
			table.setAutoCreateRowSorter(true);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
			bTable.add(scroll);
			bCen.add(bTable);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FrmThongKeSanPhamBan().setVisible(true);
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
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
