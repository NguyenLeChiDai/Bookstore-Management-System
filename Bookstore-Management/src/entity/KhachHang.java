package entity;

public class KhachHang {

	//properties
	private String maKH, tenKH, soDT, email, diaChi;
	
	//constructors
	public KhachHang() {
		super();
	}
	
	public KhachHang(String maKH) {
		super();
		setMaKH(maKH);
	}
	
	public KhachHang(String maKH, String tenKH, String soDT, String email, String diaChi) {
		super();
		setMaKH(maKH);
		setTenKH(tenKH);
		setSoDT(soDT);
		setEmail(email);
		setDiaChi(diaChi);
	}

	//getters & setters
	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getTenKH() {
		return tenKH;
	}

	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}

	public String getSoDT() {
		return soDT;
	}

	public void setSoDT(String soDT) {
		this.soDT = soDT;
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
	
	//toString
	@Override
	public String toString() {
		return "KhachHang [maKH=" + maKH + ", tenKH=" + tenKH + ", soDT=" + soDT + ", email=" + email + ", diaChi="
				+ diaChi + "]";
	}
}
