package entity;

public class DatHang {
 private String maHang ,maKH, tenHang, tenKH, sDT, email, diaChi;
 private int soLuong;
 private Double donGiaBan, thanhTien;
 
 
public DatHang() {
	super();
}
public DatHang(String maHang) {
	super();
	setMaHang(maHang);
}
public DatHang(String maKH, String tenKH) {
	super();
	setMaKH(maKH);
	setTenKH(tenKH);
	setTenHang(tenHang);
	setSoLuong(soLuong);
	setDonGiaBan(donGiaBan);
	setThanhTien(thanhTien);
}
public String getMaHang() {
	return maHang;
}
public void setMaHang(String maHang) {
	this.maHang = maHang;
}
public String getTenHang() {
	return tenHang;
}
public void setTenHang(String tenHang) {
	this.tenHang = tenHang;
}
public int getSoLuong() {
	return soLuong;
}
public void setSoLuong(int soLuong) {
	this.soLuong = soLuong;
}
public Double getDonGiaBan() {
	return donGiaBan;
}
public void setDonGiaBan(Double donGiaBan) {
	this.donGiaBan = donGiaBan;
}
public double getThanhTien() {
	if(donGiaBan==0) {
		return 0;
	}else
	return thanhTien = donGiaBan*soLuong;
}
public void setThanhTien(Double thanhTien) {
	this.thanhTien = thanhTien;
}

public String getTenKH() {
	return tenKH;
}
public void setTenKH(String tenKH) {
	this.tenKH = tenKH;
}
public String getMaKH() {
	return maKH;
}
public void setMaKH(String maKH) {
	this.maKH = maKH;
}
public String getsDT() {
	return sDT;
}
public void setsDT(String sDT) {
	this.sDT = sDT;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getDiaChi() {
	return diaChi;
}
public void setDiaChi(String diaChi) {
	this.diaChi = diaChi;
}

@Override
public String toString() {
	return "DatHang [maHang=" + maHang + ", maKH=" + maKH + ", tenHang=" + tenHang + ", tenKH=" + tenKH + ", sDT=" + sDT
			+ ", email=" + email + ", diaChi=" + diaChi + ", soLuong=" + soLuong + ", donGiaBan=" + donGiaBan
			+ ", thanhTien=" + thanhTien + "]";
}
 
 
}
