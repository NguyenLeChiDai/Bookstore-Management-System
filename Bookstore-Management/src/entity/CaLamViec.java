package entity;

public class CaLamViec {
	
	//properties
	private String maCalamViec, tenCaLamViec;

	//constructors
	public CaLamViec() {
		super();
	}

	public CaLamViec(String maCalamViec) {
		super();
		this.maCalamViec = maCalamViec;
	}

	public CaLamViec(String maCalamViec, String tenCaLamViec) {
		super();
		setMaCalamViec(maCalamViec);
		setTenCaLamViec(tenCaLamViec);
	}

	//getters & setters
	public String getMaCalamViec() {
		return maCalamViec;
	}

	public void setMaCalamViec(String maCalamViec) {
		this.maCalamViec = maCalamViec;
	}

	public String getTenCaLamViec() {
		return tenCaLamViec;
	}

	public void setTenCaLamViec(String tenCaLamViec) {
		this.tenCaLamViec = tenCaLamViec;
	}

	//toString
	@Override
	public String toString() {
		return "CaLamViec [maCalamViec=" + maCalamViec + ", tenCaLamViec=" + tenCaLamViec + "]";
	}
	
}
