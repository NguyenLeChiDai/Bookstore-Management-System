package entity;

public class TongTien {
	private int tongSoSanPham;
	private double tongTien;
	
	
	public TongTien() {
		super();
	}
	public TongTien(int tongSoSanPham, double tongTien) {
		super();
		setTongSoSanPham(tongSoSanPham);
		setTongTien(tongTien);
	}
	public int getTongSoSanPham() {
		return tongSoSanPham;
	}
	public void setTongSoSanPham(int tongSoSanPham) {
		this.tongSoSanPham = tongSoSanPham;
	}
	public double getTongTien() {
		return tongTien;
	}
	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}
	@Override
	public String toString() {
		return "TongTien [tongSoSanPham=" + tongSoSanPham + ", tongTien=" + tongTien + "]";
	}
	
}
