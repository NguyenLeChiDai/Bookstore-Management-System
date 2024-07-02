package entity;

public class PhanQuyen {
	
	//properties
	private String maPhanQuyen, tenPhanQuyen;

	//constructors
	public PhanQuyen() {
		super();
	}

	public PhanQuyen(String maPhanQuyen) {
		super();
		setMaPhanQuyen(maPhanQuyen);
	}

	public PhanQuyen(String maPhanQuyen, String tenPhanQuyen) {
		super();
		setMaPhanQuyen(maPhanQuyen);
		setTenPhanQuyen(tenPhanQuyen);
	}
	
	//getters & setters
	public String getMaPhanQuyen() {
		return maPhanQuyen;
	}

	public void setMaPhanQuyen(String maPhanQuyen) {
		this.maPhanQuyen = maPhanQuyen;
	}

	public String getTenPhanQuyen() {
		return tenPhanQuyen;
	}

	public void setTenPhanQuyen(String tenPhanQuyen) {
		this.tenPhanQuyen = tenPhanQuyen;
	}

	//toString
	@Override
	public String toString() {
		return "PhanQuyen [maPhanQuyen=" + maPhanQuyen + ", tenPhanQuyen=" + tenPhanQuyen + "]";
	}
	
	
}
