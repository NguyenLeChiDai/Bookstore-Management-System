package entity;

public class TaiKhoan {
	
	//properties
	private String maTK, tenTK, matKhau;
	private PhanQuyen phanQuyen;
	
	//constructors
	public TaiKhoan() {
		super();
	}
	
	public TaiKhoan(String maTK, String tenTK, String matKhau, PhanQuyen phanQuyen) {
		super();
		setMaTK(matKhau);
		setTenTK(tenTK);
		setMatKhau(matKhau);
		setPhanQuyen(phanQuyen);
	}
	
	//getters & setters
	public String getMaTK() {
		return maTK;
	}

	public void setMaTK(String maTK) {
		this.maTK = maTK;
	}

	public String getTenTK() {
		return tenTK;
	}

	public void setTenTK(String tenTK) {
		this.tenTK = tenTK;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public PhanQuyen getPhanQuyen() {
		return phanQuyen;
	}

	public void setPhanQuyen(PhanQuyen phanQuyen) {
		this.phanQuyen = phanQuyen;
	}

	//toString
	@Override
	public String toString() {
		return "TaiKhoan [maTK=" + maTK + ", tenTK=" + tenTK + ", matKhau=" + matKhau + ", phanQuyen=" + phanQuyen
				+ "]";
	}

}
