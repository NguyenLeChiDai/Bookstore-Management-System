package entity;

public class TacGia {
	
	//properties
	private String maTacGia, tenTacGia;
	
	//constructors
	public TacGia() {
		super();
	}

	public TacGia(String maTacGia) {
		super();
		this.maTacGia = maTacGia;
	}

	public TacGia(String maTacGia, String tenTacGia) {
		super();
		this.maTacGia = maTacGia;
		this.tenTacGia = tenTacGia;
	}

	//getters & setters
	public String getMaTacGia() {
		return maTacGia;
	}

	public void setMaTacGia(String maTacGia) {
		this.maTacGia = maTacGia;
	}

	public String getTenTacGia() {
		return tenTacGia;
	}

	public void setTenTacGia(String tenTacGia) {
		this.tenTacGia = tenTacGia;
	}

	//toString
	@Override
	public String toString() {
		return "TacGia [maTacGia=" + maTacGia + ", tenTacGia=" + tenTacGia + "]";
	}
	
	
}

