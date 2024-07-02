package entity;

public class ChiTietPhieuDatHang {

	//properties
	private PhieuDatHang phieuDatHang;
	private String maSP;
	int soLuong;
	double donGiaBan, thanhTien;
	
	//constructors
	public ChiTietPhieuDatHang() {
		super();
	}

	public ChiTietPhieuDatHang(PhieuDatHang phieuDatHang) {
		super();
		setPhieuDatHang(phieuDatHang);
	}

	public ChiTietPhieuDatHang(PhieuDatHang phieuDatHang, String maSP, int soLuong, double thanhTien) {
		super();
		setPhieuDatHang(phieuDatHang);
		setMaSP(maSP);
		setSoLuong(soLuong);
		setThanhTien(thanhTien);
	}

	//getters & setters
	public PhieuDatHang getPhieuDatHang() {
		return phieuDatHang;
	}

	public void setPhieuDatHang(PhieuDatHang phieuDatHang) {
		this.phieuDatHang = phieuDatHang;
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

	public double getThanhTien() {
		return thanhTien;
	}

	public void setThanhTien(double thanhTien) {
		this.thanhTien = thanhTien;
	}

	//toString
	@Override
	public String toString() {
		return "ChiTietPhieuDatHang [phieuDatHang=" + phieuDatHang + ", maSP=" + maSP + ", soLuong=" + soLuong
				+ ", thanhTien=" + thanhTien + "]";
	}
	
	
	
}
