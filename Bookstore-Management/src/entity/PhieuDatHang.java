package entity;

import java.util.Date;

public class PhieuDatHang {
	
	//properties
	private String maPhieuDatHang;
	private ChiTietPhieuDatHang chiTietPhieuDatHang;
	private KhachHang khachHang;
	private Date ngayLap;
	
	//constructors
	public PhieuDatHang(String maPhieuDatHang, ChiTietPhieuDatHang chiTietPhieuDatHang, KhachHang khachHang,
			Date ngayLap) {
		super();
		setMaPhieuDatHang(maPhieuDatHang);
		setChiTietPhieuDatHang(chiTietPhieuDatHang);
		setKhachHang(khachHang);
		setNgayLap(ngayLap);
	}

	public PhieuDatHang() {
		super();
	}

	public PhieuDatHang(String maPhieuDatHang) {
		super();
		this.maPhieuDatHang = maPhieuDatHang;
	}

	//getters & setters
	public ChiTietPhieuDatHang getChiTietPhieuDatHang() {
		return chiTietPhieuDatHang;
	}

	public String getMaPhieuDatHang() {
		return maPhieuDatHang;
	}

	public void setMaPhieuDatHang(String maPhieuDatHang) {
		this.maPhieuDatHang = maPhieuDatHang;
	}

	public void setChiTietPhieuDatHang(ChiTietPhieuDatHang chiTietPhieuDatHang) {
		this.chiTietPhieuDatHang = chiTietPhieuDatHang;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
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
		return "PhieuDatHang [maPhieuDatHang=" + maPhieuDatHang + ", chiTietPhieuDatHang=" + chiTietPhieuDatHang
				+ ", khachHang=" + khachHang + ", ngayLap=" + ngayLap + "]";
	}
	
	
}
