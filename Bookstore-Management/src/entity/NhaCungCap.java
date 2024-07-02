package entity;

public class NhaCungCap {
	
	//properties
	private String maNCC, tenNCC;

	//constructors
	public NhaCungCap() {
		super();
	}
	
	public NhaCungCap(String maNCC) {
		super();
		setMaNCC(maNCC);
	}

	public NhaCungCap(String maNCC, String tenNCC) {
		super();
		setMaNCC(maNCC);
		setTenNCC(tenNCC);
	}

	//getters & setters
	public String getMaNCC() {
		return maNCC;
	}

	public void setMaNCC(String maNCC) {
		this.maNCC = maNCC;
	}

	public String getTenNCC() {
		return tenNCC;
	}

	public void setTenNCC(String tenNCC) {
		this.tenNCC = tenNCC;
	}

	@Override
	public String toString() {
		return "NhaCungCap [maNCC=" + maNCC + ", tenNCC=" + tenNCC + "]";
	}

	public void create(NhaCungCap nhaCungCap) {
		// TODO Auto-generated method stub
		
	}
}
