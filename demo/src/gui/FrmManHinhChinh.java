package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;


public class FrmManHinhChinh extends JFrame implements ActionListener, MenuListener, MouseListener {
	
	private JMenuBar mnuBar;
	private JMenu mnuTrangChu, mnuSach, mnuVPP, mnuNhanVien, mnuHoaDon, mnuTaiKhoan, mnuThongKeDoanhThu, mnuKhachHang;
	private JMenuItem mniCapNhatTacGia, mniCapNhatLoaiSach, mniCapNhatTK, mniTimKiemKH, mniQLHD, mniCapNhatSach, mniThongKeSanPhamNhap, mniThongKeSanPhamBan, mniCapNhatNXB, mniTimKiemSach, mniCapNhatNCC, mniCapNhatNV, mniTimKiemNV, mniBaoCaoThongKe, mniThongKeDoanhThuTheoCa, mniCapNhatSPK, mniTimKiemSPK, mniThongKeDoanhThuTheoNV, mniCapNhatKH, mniDatHang;
	private JPanel pnlCen;
	private JLabel lblBackground;
	private JMenuItem mniDatSach;
	private JMenuItem mniDatSanPham;
	private JMenuItem mniTimKiemTacGia;
	private JMenuItem mniTimKiemNhaCungCap;
	private JMenuItem mniTimKiemNhaXuatBan;
	private JMenuItem mniLapHoaDon;

	public FrmManHinhChinh() {
		setTitle("Quản lý hiệu sách");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		
		//menu
		mnuBar = new JMenuBar();
		
			//menuTrangChu
			mnuTrangChu = new JMenu("Trang chủ");
		
			//menuSach
	        mnuSach = new JMenu("Sách");
	        	mniCapNhatSach = new JMenuItem("Cập nhật sách");
	        	mniCapNhatSach.addActionListener(this);
	        	mnuSach.add(mniCapNhatSach);
	     
	        	mniTimKiemSach = new JMenuItem("Tìm kiếm sách");
	        	mniTimKiemSach.addActionListener(this);
	        	mnuSach.add(mniTimKiemSach);
	        	
	        	mniCapNhatNXB = new JMenuItem("Cập nhật nhà xuất bản");
	        	mniCapNhatNXB.addActionListener(this);
	        	mnuSach.add(mniCapNhatNXB);
	        	
	        	mniCapNhatTacGia = new JMenuItem("Cập nhật tác giả");
	        	mniCapNhatTacGia.addActionListener(this);
	        	mnuSach.add(mniCapNhatTacGia);
	        	
	        	mniTimKiemTacGia = new JMenuItem("Tìm kiếm tác giả");
	        	mniTimKiemTacGia.addActionListener(this);
	        	mnuSach.add(mniTimKiemTacGia);
	        	
	        	mniTimKiemNhaXuatBan = new JMenuItem("Tìm kiếm nhà xuất bản");
	        	mniTimKiemNhaXuatBan.addActionListener(this);
	        	mnuSach.add(mniTimKiemNhaXuatBan);
	        
	        	
	        	mniCapNhatLoaiSach = new JMenuItem("Cập nhật loại sách");
	        	mniCapNhatLoaiSach.addActionListener(this);
	        	mnuSach.add(mniCapNhatLoaiSach);
	        mnuBar.add(mnuSach);
	        
	        //menuSPK
	        mnuVPP = new JMenu("Sản phẩm khác");
	        	mniCapNhatSPK = new JMenuItem("Cập nhật sản phẩm khác");
	        	mniCapNhatSPK.addActionListener(this);
	        	mnuVPP.add(mniCapNhatSPK);
	   
	        	mniTimKiemSPK = new JMenuItem("Tìm kiếm sản phẩm khác");
	        	mniTimKiemSPK.addActionListener(this);
	        	mnuVPP.add(mniTimKiemSPK);
	        	
	        	mniCapNhatNCC = new JMenuItem("Cập nhật nhà cung cấp");
	        	mniCapNhatNCC.addActionListener(this);
	        	mnuVPP.add(mniCapNhatNCC);
	        	
	        	mniTimKiemNhaCungCap = new JMenuItem("Tìm kiếm nhà cung cấp");
	        	mniTimKiemNhaCungCap.addActionListener(this);
	        	mnuVPP.add(mniTimKiemNhaCungCap);
	        mnuBar.add(mnuVPP);
	        
	        //menuNhanVien  
	        mnuNhanVien = new JMenu("Nhân viên");
	        	mniCapNhatNV = new JMenuItem("Cập nhật nhân viên");
	        	mniCapNhatNV.addActionListener(this);
	        	mnuNhanVien.add(mniCapNhatNV);
	        	
	        	mniTimKiemNV = new JMenuItem("Tìm kiếm nhân viên");
	        	mniTimKiemNV.addActionListener(this);
	        	mnuNhanVien.add(mniTimKiemNV);
	        	
	        	mniBaoCaoThongKe = new JMenu("Báo cáo - thống kê");
	        	
	        		//menuThongKeDoanhThu
	        		mnuThongKeDoanhThu = new JMenu("Thống kê doanh thu");
	        			//menuThongKeDoanhThuTheoCa
	        			mniThongKeDoanhThuTheoCa = new JMenuItem("Thống kê doanh thu theo ca");
	        			mniThongKeDoanhThuTheoCa.addActionListener(this);
	        			mnuThongKeDoanhThu.add(mniThongKeDoanhThuTheoCa);
	        			
	        			//menuThongKeDoanhThuTheoNhanVien
	        			mniThongKeDoanhThuTheoNV = new JMenuItem("Thống kê doanh thu theo nhân viên");
	        			mniThongKeDoanhThuTheoNV.addActionListener(this);
	        			mnuThongKeDoanhThu.add(mniThongKeDoanhThuTheoNV);
	        		mniBaoCaoThongKe.add(mnuThongKeDoanhThu);
	        		
	        		//menuThongKeSanPhamNhap
	        		mniThongKeSanPhamNhap = new JMenuItem("Thống kê sản phẩm nhập");
	        		mniThongKeSanPhamNhap.addActionListener(this);
	        		mniBaoCaoThongKe.add(mniThongKeSanPhamNhap);
	        		
	        		//menuThongKeSanPhamBan
	        		mniThongKeSanPhamBan = new JMenuItem("Thống kê sản phẩm bán");
	        		mniThongKeSanPhamBan.addActionListener(this);
	        		mniBaoCaoThongKe.add(mniThongKeSanPhamBan);
	        		
	        	mnuNhanVien.add(mniBaoCaoThongKe);
	        mnuBar.add(mnuNhanVien);
	        
	        //menuKhachHang
	        mnuKhachHang = new JMenu("Khách hàng");
	        	mniCapNhatKH = new JMenuItem("Cập nhật khách hàng");
	        	mniCapNhatKH.addActionListener(this);
	        	mnuKhachHang.add(mniCapNhatKH);
	        	
	        	mniTimKiemKH = new JMenuItem("Tìm kiếm khách hàng");
	        	mniTimKiemKH.addActionListener(this);
	        	mnuKhachHang.add(mniTimKiemKH);
	        	
	        	//menuDatHang
	        	mniDatHang = new JMenu("Đặt hàng");
	        	
	        	//menuconDatSach
        		mniDatSach = new JMenuItem("Đặt sách");
        		mniDatSach.addActionListener(this);
        		mniDatHang.add(mniDatSach);
        		
        		//menuconDatSanPham
        		mniDatSanPham = new JMenuItem("Đặt sản phẩm");
        		mniDatSanPham.addActionListener(this);
        		mniDatHang.add(mniDatSanPham);
        		
        	mnuKhachHang.add(mniDatHang);
	        	//mniDatHang.addActionListener(this);
	        	//mnuKhachHang.add(mniDatHang);
	        mnuBar.add(mnuKhachHang);
	        
	        //menuHoaDon
	        mnuHoaDon = new JMenu("Hóa đơn");
	        	//menuQuanLyHD
	        	mniQLHD = new JMenuItem("Quản lý hóa đơn");
	        	mniQLHD.addActionListener(this);
	        	mnuHoaDon.add(mniQLHD);
	        	
	        	mniLapHoaDon = new JMenuItem("Lập hóa đơn");
	        	mniLapHoaDon.addActionListener(this);
	        	mnuHoaDon.add(mniLapHoaDon);
	        mnuBar.add(mnuHoaDon);
	        
	        /*//menuTaiKhoan
	        mnuTaiKhoan = new JMenu("Tài khoản");
	        	//capNhatTK
	        	mniCapNhatTK = new JMenuItem("Cập nhật tài khoản");
	        	mnuTaiKhoan.add(mniCapNhatTK);
	        mnuBar.add(mnuTaiKhoan);*/
	        
	        //menuThoat
	        JMenu mnuThoat = new JMenu("Thoát");
	        mnuBar.add(mnuThoat);
	    this.add(mnuBar, BorderLayout.NORTH);
	    
	    //Center
	    pnlCen = new JPanel();
	    pnlCen.add(lblBackground = new JLabel(new ImageIcon("images/background.jpg")));
	    this.add(pnlCen, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FrmManHinhChinh frmManHinhChinh = new FrmManHinhChinh();
                    frmManHinhChinh.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
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
	public void menuSelected(MenuEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void menuDeselected(MenuEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void menuCanceled(MenuEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
			//sach
			if (src.equals(mniCapNhatSach)) {
				FrmCapNhatSach.main(null);;
			}
			if (src.equals(mniTimKiemSach)) {
				FrmTimKiemSach.main(null);
			}
			if (src.equals(mniCapNhatNXB)) {
				FrmCapNhatNhaXuatBan.main(null);
			}
			if (src.equals(mniCapNhatTacGia)) {
				FrmCapNhatTacGia.main(null);
			}
			if (src.equals(mniCapNhatLoaiSach)) {
				FrmCapNhatLoaiSach.main(null);
			}
			if (src.equals(mniTimKiemTacGia)) {
				FrmCapNhatSach.main(null);
			}
			if (src.equals(mniTimKiemNhaXuatBan)) {
				FrmTimNhaXuatBan.main(null);
			}
			
			
			//sanPhamKhac
			if (src.equals(mniCapNhatSPK)) {
				FrmCapNhatSanPhamKhac.main(null);
			}
			if (src.equals(mniTimKiemSPK)) {
				FrmTimKiemSanPhamKhac.main(null);
			}
			if (src.equals(mniCapNhatNCC)) {
				FrmCapNhatNhaCungCap.main(null);
			}
			if (src.equals(mniTimKiemNhaCungCap)) {
				FrmTimKiemNhaCungCap.main(null);
			}
			//nhanVien
			if (src.equals(mniCapNhatNV)) {
				FrmCapNhatNhanVien.main(null);
			}
			if (src.equals(mniTimKiemNV)) {
				FrmTimKiemNhanVien.main(null);
			}
			
			//khachHang
			if (src.equals(mniCapNhatKH)) {
				FrmCapNhatKhachHang.main(null);
			}
			if (src.equals(mniTimKiemKH)) {
				FrmTimKiemKhachHang.main(null);
			}
			if (src.equals(mniDatSach)) {
				FrmDatSach.main(null);
			}
			if (src.equals(mniDatSanPham)) {
				FrmDatSanPham.main(null);
			}
			
			//thongKe
			if (src.equals(mniThongKeDoanhThuTheoCa)) {
				FrmThongKeDoanhThuTheoCa.main(null);
			}
			
			if (src.equals(mniThongKeSanPhamNhap)) {
				FrmThongKeSanPhamNhap.main(null);
			}
			
			//hoaDon
			if (src.equals(mniQLHD)) {
				FrmQuanLyHoaDon.main(null);
			}
			if (src.equals(mniLapHoaDon)) {
				FrmLapHoaDonTest.main(null);
			}
	}

}
