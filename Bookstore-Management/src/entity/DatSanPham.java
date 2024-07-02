package entity;

public class DatSanPham {
	private String maSP, tenSP;
	private int soLuong;
	private double donGiaBan;
	private double thanhTien;
	
	
	public DatSanPham() {
		super();
	}
	public DatSanPham(String maSP) {
		super();
		setMaSP(maSP);
	}
	public DatSanPham(String maSP, String tenSP, int soLuong, double donGiaBan, double thanhTien) {
		super();
		setMaSP(maSP);
		setTenSP(tenSP);
		setSoLuong(soLuong);
		setDonGiaBan(donGiaBan);
		setThanhTien(thanhTien);
	}
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
	public double getDonGiaBan() {
		return donGiaBan;
	}
	public void setDonGiaBan(double donGiaBan) {
		this.donGiaBan = donGiaBan;
	}
	public double getThanhTien() {
		if(donGiaBan ==  0) {
			return thanhTien = 0;
		}
		else {
			return thanhTien = donGiaBan * soLuong;
		}
			
		
	}
	public void setThanhTien(double thanhTien) {
		this.thanhTien = thanhTien;
	}
	@Override
	public String toString() {
		return "DatSanPham [maSP=" + maSP + ", tenSP=" + tenSP + ", soLuong=" + soLuong + ", donGiaBan=" + donGiaBan
				+ ", thanhTien=" + thanhTien + "]";
	}
	
	
	
	
	
	

}

