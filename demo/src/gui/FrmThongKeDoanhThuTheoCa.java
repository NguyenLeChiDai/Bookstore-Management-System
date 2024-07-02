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

public class FrmThongKeDoanhThuTheoCa extends JFrame implements ActionListener, MouseListener{
	
	private JComboBox cboChonNgay, cboCa;
	private JButton btnThongKe, btnInBaoCao;
	private DefaultTableModel tableModel;
	private JTable table;
	private JLabel lblTitle;

	public FrmThongKeDoanhThuTheoCa() {
		
		setTitle("Quản Lý Hiệu Sách");
		setExtendedState(MAXIMIZED_BOTH);
		
		//north
		JPanel pNorth = new JPanel();
		pNorth.add(lblTitle = new JLabel("Thống Kê Doanh Thu"));
		Font fontTitle = new Font ("Arial", Font.BOLD, 30);
		lblTitle.setForeground(Color.BLUE);
		lblTitle.setFont(fontTitle);
		this.add(pNorth, BorderLayout.NORTH);
		
				//CENTER
				JPanel pCen;
				add(pCen = new JPanel(), BorderLayout.CENTER);
				pCen.setPreferredSize(new Dimension(350,500));
				pCen.setLayout(null); // Absolute layout
				JLabel lblChonNgay, lblCa;
				pCen.setBorder(BorderFactory.createTitledBorder("Chi tiết"));
				int w1 = 125, w2 = 230, h = 30;
				//JLable
				pCen.add(lblChonNgay = new JLabel("Chọn ngày: "));
				String[] ngay= {"Thứ 2", "Thứ 3", "Thứ 4", "Thứ 5", "Thứ 6","Thứ 7", "Chủ nhật"};
				pCen.add(cboChonNgay=new JComboBox(ngay));
				
				lblChonNgay.setBounds(20, 20, w1, h);
				cboChonNgay.setBounds(120, 20, w2, h);
				
				pCen.add(lblCa = new JLabel("Ca: "));
				String[] ca= {"Ca 1(7h30-14h)"," Ca 2(14h-22h)"};
				pCen.add(cboCa=new JComboBox(ca));
				
				lblCa.setBounds(20, 80, w1, h);
				cboCa.setBounds(120, 80, w2, h);
				
				
				//Button
				pCen.add(btnThongKe = new JButton("Thống kê"));
				pCen.add(btnInBaoCao = new JButton("In báo cáo"));
				
				btnThongKe.setBounds(400, 80, 110, 30);
				btnInBaoCao.setBounds(550, 80, 140, 30);
				
				//SOUTH
				
				JPanel pSouth;
				add(pSouth = new JPanel(), BorderLayout.SOUTH);
				pSouth.setPreferredSize(new Dimension(350,500));
				pSouth.setBorder(BorderFactory.createTitledBorder("Danh sách thống kê theo ca"));
				pSouth.setLayout(null); // Absolute layout
				
				
				JScrollPane scroll;
				String [] headers = "Mã sản phẩm;Tên sản phẩm;Nhà cung cấp;Số lượng;Giá nhập;Giá bán".split(";");
				
				tableModel = new DefaultTableModel(headers,0);
				add(scroll = new JScrollPane(table = new JTable(tableModel), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
						JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.SOUTH);
				
				scroll.setBorder(BorderFactory.createTitledBorder("Danh sách thống kê"));
				table.setRowHeight(25);
				scroll.setPreferredSize(new Dimension(530, 350));
	}
	
	
	public static void main(String[] args) {
		new FrmThongKeDoanhThuTheoCa().setVisible(true);
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
