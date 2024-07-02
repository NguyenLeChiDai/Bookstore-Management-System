package entity;

public class LoaiSach {
	
	//properties
	private String maLoai, tenLoai;
	
	//constructors
	public LoaiSach() {
		super();
	}
	
	public LoaiSach(String maLoai) {
		super();
		setMaLoai(maLoai);
	}

	public LoaiSach(String maLoai, String tenLoai) {
		super();
		setMaLoai(maLoai);
		setTenLoai(tenLoai);
	}

	//getters & setters
	public String getMaLoai() {
		return maLoai;
	}

	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}

	public String getTenLoai() {
		return tenLoai;
	}

	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}

	@Override
	public String toString() {
		return "LoaiSach [maLoai=" + maLoai + ", tenLoai=" + tenLoai + "]";
	}	
	
	
}
