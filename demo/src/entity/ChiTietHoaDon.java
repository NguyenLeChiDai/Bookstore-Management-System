package entity;

public class ChiTietHoaDon {
	
	//properties
	private String maHD, maSP;
	int soLuong;
	double donGiaBan, thanhTien;
	
	//constructors
	public ChiTietHoaDon() {
		super();
	}

	public ChiTietHoaDon(String maHD) {
		super();
		this.maHD = maHD;
	}

	public ChiTietHoaDon(String maHD, String maSP, int soLuong, double donGiaBan, double thanhTien) {
		super();
		this.maHD = maHD;
		this.maSP = maSP;
		this.soLuong = soLuong;
		this.donGiaBan = donGiaBan;
		this.thanhTien = thanhTien;
	}

	//getters & setters
	public String getMaHD() {
		return maHD;
	}

	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}

	public String getMaSP() {
		return maSP;
	}

	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public double getDonGiaBan() {
		return donGiaBan;
	}

	public void setDonGiaBan(double donGiaBan) {
		this.donGiaBan = donGiaBan;
	}

	public double getThanhTien() {
		return thanhTien;
	}

	public void setThanhTien(double thanhTien) {
		this.thanhTien = thanhTien;
	}

	//toString
	@Override
	public String toString() {
		return "ChiTietHoaDon [maHD=" + maHD + ", maSP=" + maSP + ", soLuong=" + soLuong + ", donGiaBan=" + donGiaBan
				+ ", thanhTien=" + thanhTien + "]";
	}
	
	
}
