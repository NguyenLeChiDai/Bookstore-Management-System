package entity;

public class NhaXuatBan {
	
	//properties
	private String maNhaXB, tenNhaXB;

	//constructors
	public NhaXuatBan() {
		super();
	}

	public NhaXuatBan(String maNhaXB) {
		super();
		this.maNhaXB = maNhaXB;
	}

	public NhaXuatBan(String maNhaXB, String tenNhaXB) {
		super();
		setMaNhaXB(maNhaXB);
		setTenNhaXB(tenNhaXB);
	}
	
	//getters & setters
	public String getMaNhaXB() {
		return maNhaXB;
	}

	public void setMaNhaXB(String maNhaXB) {
		this.maNhaXB = maNhaXB;
	}

	public String getTenNhaXB() {
		return tenNhaXB;
	}

	public void setTenNhaXB(String tenNhaXB) {
		this.tenNhaXB = tenNhaXB;
	}
}
