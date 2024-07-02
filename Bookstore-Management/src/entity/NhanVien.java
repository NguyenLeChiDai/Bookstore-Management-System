package entity;

public class NhanVien {

	//properies
	private String maNV, tenNV, soDT, diaChi, email;
	private CaLamViec caLamViec;
	//constructors

	public NhanVien() {
		super();
	}


/*public NhanVien(String maNV) {
		super();
		setMaNV(maNV);
	}*/
	public NhanVien(String tenNV) {
		super();
		setTenNV(tenNV);
	}
	
	public NhanVien(String maNV, String tenNV, String soDT,String email, String diaChi, CaLamViec caLamViec) {
		super();
		setMaNV(maNV);
		setTenNV(tenNV);
		setSoDT(soDT);
		setEmail(email);
		setDiaChi(diaChi);
		setCaLamViec(caLamViec);
		
	}



	//getters & setters
	
	
	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getTenNV() {
		return tenNV;
	}

	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}

	public String getSoDT() {
		return soDT;
	}

	public void setSoDT(String soDT) {
		this.soDT = soDT;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public CaLamViec getCaLamViec() {
		return caLamViec;
	}


	public void setCaLamViec(CaLamViec caLamViec) {
		this.caLamViec = caLamViec;
	}


	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", tenNV=" + tenNV + ", soDT=" + soDT + ", diaChi=" + diaChi + ", email="
				+ email + ", caLamViec=" + caLamViec + "]";
	}



	
}
