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
import dao.ChiTietHoaDonDAO;
import dao.HoaDonDAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;

public class FrmQuanLyHoaDon extends JFrame implements ActionListener, MouseListener {	

	private JPanel pnlNorth,  pnlWest;
	private JLabel lblTitle, lblMaNV;
	private Box bWest, bMaHD, bMaKH, bMaNV, bNgayLap, bButton, bCen, bTableHoaDon;
	private JLabel lblMaHD, lblMaKH, lblNgayLap;
	private JTextField txtMaHD, txtMaKH, txtMaNV, txtNgayLap;
	private JButton btnXoa;
	private DefaultTableModel tableModelHoaDon;
	private JTable tableHoaDon;
	private Box bTableCTHoaDon;
	private DefaultTableModel tableModelCTHoaDon;
	private JTable tableCTHD;
	HoaDonDAO hoaDonDAO = new HoaDonDAO();
	
	public FrmQuanLyHoaDon() {
		ConnectDatabase.getInstance().connect();
		setTitle("Quản lý hóa đơn");
		setExtendedState(MAXIMIZED_BOTH);
		
		//north
		pnlNorth = new JPanel();
		pnlNorth.add(lblTitle = new JLabel("QUẢN LÝ HÓA ĐƠN"));
		lblTitle.setFont(new Font("Arial", Font.BOLD, 30));
		lblTitle.setForeground(Color.BLUE);
		this.add(pnlNorth, BorderLayout.NORTH);
		
		//west
		pnlWest = new JPanel();
		pnlWest.add(bWest = Box.createVerticalBox());
		bWest.setBorder(BorderFactory.createTitledBorder("Chi tiết"));
		
			//maHoaDon
			bWest.add(bMaHD = Box.createHorizontalBox());
			bMaHD.add(lblMaHD = new JLabel("Mã hóa đơn:"));
			bMaHD.add(txtMaHD = new JTextField(16));
			txtMaHD.setEditable(false);
			bWest.add(Box.createVerticalStrut(10));
			
			//maKH
			bWest.add(bMaKH = Box.createHorizontalBox());
			bMaKH.add(lblMaKH = new JLabel("Mã khách hàng:"));
			bMaKH.add(txtMaKH = new JTextField(16));
			txtMaKH.setEditable(false);
			bWest.add(Box.createVerticalStrut(10));
			
			//maNV
			bWest.add(bMaNV = Box.createHorizontalBox());
			bMaNV.add(lblMaNV = new JLabel("Mã nhân viên:"));
			bMaNV.add(txtMaNV = new JTextField(16));
			txtMaNV.setEditable(false);
			bWest.add(Box.createVerticalStrut(10));
			
			//ngayLap
			bWest.add(bNgayLap = Box.createHorizontalBox());
			bNgayLap.add(lblNgayLap = new JLabel("Ngày Lập:"));
			bNgayLap.add(txtNgayLap = new JTextField(16));
			txtNgayLap.setEditable(false);
			bWest.add(Box.createVerticalStrut(10));
			
			//button
			bWest.add(bButton = Box.createHorizontalBox());
			bButton.add(btnXoa = new JButton("Xóa"));
			btnXoa.addActionListener(this);
			bWest.add(Box.createVerticalStrut(10));
			
			lblMaHD.setPreferredSize(lblMaKH.getPreferredSize());
			lblMaNV.setPreferredSize(lblMaKH.getPreferredSize());
			lblNgayLap.setPreferredSize(lblMaKH.getPreferredSize());
		this.add(pnlWest, BorderLayout.WEST);
		
		//center
		bCen = Box.createVerticalBox();
			//tableHoaDon
			bTableHoaDon = Box.createVerticalBox();
			bTableHoaDon.setBorder(BorderFactory.createTitledBorder("Danh sách hóa đơn"));
			String [] headersHoaDon = "Mã hóa đơn; Mã khách hàng; Mã nhân viên; Ngày Lập; Tổng tiền".split(";");
			tableModelHoaDon = new DefaultTableModel(headersHoaDon, 0);
			JScrollPane scrollHoaDon = new JScrollPane();
			scrollHoaDon.setViewportView(tableHoaDon = new JTable(tableModelHoaDon));
			tableHoaDon.setRowHeight(25);
			tableHoaDon.setAutoCreateRowSorter(true);
			tableHoaDon.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
			tableHoaDon.addMouseListener(this);
			bTableHoaDon.add(scrollHoaDon);
			insertDataHoaDon();
			bCen.add(bTableHoaDon);
			
			//tableCTHD
			bTableCTHoaDon = Box.createVerticalBox();
			bTableCTHoaDon.setBorder(BorderFactory.createTitledBorder("Chi tiết hóa đơn"));
			String [] headersCTHD = "Mã sản phẩm; Tên sản phẩm; Số lượng; Đơn giá bán; Thành tiền".split(";");
			tableModelCTHoaDon = new DefaultTableModel(headersCTHD, 0);
			JScrollPane scrollCTHD = new JScrollPane();
			scrollCTHD.setViewportView(tableCTHD = new JTable(tableModelCTHoaDon));
			tableCTHD.setRowHeight(25);
			tableCTHD.setAutoCreateRowSorter(true);
			tableCTHD.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
			tableCTHD.addMouseListener(this);
			bTableCTHoaDon.add(scrollCTHD);
			bCen.add(bTableCTHoaDon);
		this.add(bCen, BorderLayout.CENTER);
		
	}

	private void insertDataHoaDon() {
		// TODO Auto-generated method stub
		HoaDonDAO danhSachHoaDon = new HoaDonDAO();
		List<HoaDon> list = danhSachHoaDon.getAllHoaDon();
		for (HoaDon hoaDon : list) {
			String [] row = {hoaDon.getMaHD(), hoaDon.getKhachHang().getMaKH(), hoaDon.getNhanVien().getMaNV(), hoaDon.getNgayLap().toString(), Double.toString(hoaDon.getTongTien())};
			tableModelHoaDon.addRow(row);
		}
		tableHoaDon.setModel(tableModelHoaDon);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FrmQuanLyHoaDon().setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tableHoaDon.getSelectedRow();
		txtMaHD.setText(tableModelHoaDon.getValueAt(row, 0).toString());
		txtMaKH.setText(tableModelHoaDon.getValueAt(row, 1).toString());
		txtMaNV.setText(tableModelHoaDon.getValueAt(row, 2).toString());
		txtNgayLap.setText(tableModelHoaDon.getValueAt(row, 3).toString());
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
		if (src.equals(btnXoa)) {
			xoa();
		}
	}

	private void xoa() {
		// TODO Auto-generated method stub
				int loiNhac = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", "Chú ý", JOptionPane.YES_NO_OPTION);
				if (loiNhac == JOptionPane.YES_OPTION)
				{
					int row = tableHoaDon.getSelectedRow();
					if (row >= 0)
					{
						String maHD = (String) tableHoaDon.getValueAt(row, 0);
						if (hoaDonDAO.delete(maHD)) {
							tableModelHoaDon.removeRow(row);
							txtMaHD.setText("");
							txtMaKH.setText("");
							txtMaNV.setText("");
							txtNgayLap.setText("");
							txtMaHD.requestFocus();
						}
					}
					JOptionPane.showMessageDialog(this, "Đã xóa hóa đơn này!");
				}
	}

}
