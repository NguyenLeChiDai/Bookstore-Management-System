package entity;

public class SanPhamKhac {
	
	//properties
	private String maSP, tenSP;
	private int soLuong;
	private NhaCungCap nhaCC;
	private double donGiaNhap, donGiaBan;
	
	//constructors
	public SanPhamKhac() {
		super();
	}

	public SanPhamKhac(String maSP) {
		super();
		this.maSP = maSP;
	}

	public SanPhamKhac(String maSP, String tenSP, int soLuong, NhaCungCap nhaCC, double donGiaNhap, double donGiaBan) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.soLuong = soLuong;
		this.nhaCC = nhaCC;
		this.donGiaNhap = donGiaNhap;
		this.donGiaBan = donGiaBan;
	}

	//getters & setters
	public String getMaSP() {
		return maSP;
	}

	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}

	public String getTenSP() {
		return tenSP;
	}

	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public NhaCungCap getNhaCC() {
		return nhaCC;
	}

	public void setNhaCC(NhaCungCap nhaCC) {
		this.nhaCC = nhaCC;
	}

	public double getDonGiaNhap() {
		return donGiaNhap;
	}

	public void setDonGiaNhap(double donGiaNhap) {
		this.donGiaNhap = donGiaNhap;
	}

	public double getDonGiaBan() {
		return donGiaBan;
	}

	public void setDonGiaBan(double donGiaBan) {
		this.donGiaBan = donGiaBan;
	}

	//toString
	@Override
	public String toString() {
		return "SanPhamKhac [maSP=" + maSP + ", tenSP=" + tenSP + ", soLuong=" + soLuong + ", nhaCC=" + nhaCC
				+ ", donGiaNhap=" + donGiaNhap + ", donGiaBan=" + donGiaBan + "]";
	}	
	
}
