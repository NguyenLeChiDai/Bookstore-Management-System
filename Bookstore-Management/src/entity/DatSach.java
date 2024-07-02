package entity;

public class DatSach {
private String maSach, tenSP;
private int soLuong;
private double donGiaBan, thanhTien;


public DatSach() {
	super();
}
public DatSach(String maSach) {
	super();
	setMaSach(maSach);
}
public DatSach( String maSach,String tenSP, int soLuong, double donGiaBan, double thanhTien) {
	super();
	setMaSach(maSach);
	setTenSP(tenSP);
	setSoLuong(soLuong);
	setDonGiaBan(donGiaBan);
	setThanhTien(thanhTien);
}

public String getMaSach() {
	return maSach;
}
public void setMaSach(String maSach) {
	this.maSach = maSach;
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
	if(donGiaBan==0) {
		return 0;
	}else
	return thanhTien = donGiaBan*soLuong;
}
public void setThanhTien(double thanhTien) {
	this.thanhTien = thanhTien;
}
@Override
public String toString() {
	return "DatSach [maSach=" + maSach + ", tenSP=" + tenSP + ", soLuong=" + soLuong + ", donGiaBan=" + donGiaBan
			+ ", thanhTien=" + thanhTien + "]";
}



}
