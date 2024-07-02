package entity;

import java.util.Date;

public class HoaDon {
	
	//properties
	private String maHD;
	private KhachHang khachHang;
	private NhanVien nhanVien;
	private Date ngayLap;
	private double tongTien;
	
	//constructors
	public HoaDon() {
		super();
	}

	public HoaDon(String maHD) {
		super();
		this.maHD = maHD;
	}

	public HoaDon(String maHD, KhachHang khachHang, NhanVien nhanVien, Date ngayLap, double tongTien) {
		super();
		this.maHD = maHD;
		this.khachHang = khachHang;
		this.nhanVien = nhanVien;
		this.ngayLap = ngayLap;
		this.tongTien = tongTien;
	}

	//getters & setters
	public String getMaHD() {
		return maHD;
	}


	public double getTongTien() {
		return tongTien;
	}

	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}

	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}


	public KhachHang getKhachHang() {
		return khachHang;
	}


	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}


	public NhanVien getNhanVien() {
		return nhanVien;
	}


	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}


	public Date getNgayLap() {
		return ngayLap;
	}


	public void setNgayLap(Date ngayLap) {
		this.ngayLap = ngayLap;
	}

	//toString
	@Override
	public String toString() {
		return "HoaDon [maHD=" + maHD + ", khachHang=" + khachHang + ", nhanVien=" + nhanVien + ", ngayLap=" + ngayLap
				+ "]";
	}
	
	
}
