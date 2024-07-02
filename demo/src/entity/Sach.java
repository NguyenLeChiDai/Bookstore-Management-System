package entity;

public class Sach {
	
	//properties
	private String maSach, tenSach, iSBN;
	private LoaiSach loaiSach;
	private TacGia tacGia;
	private NhaXuatBan nhaXB;
	private int soTrang, soLuong;
	private double donGiaNhap, donGiaBan;
	
	//constructors
	public Sach() {
		super();
	}

	public Sach(String maSach) {
		super();
		setMaSach(maSach);
	}

	public Sach(String maSach, String tenSach, String iSBN, LoaiSach loaiSach, TacGia tacGia, NhaXuatBan nhaXB,
			int soTrang, int soLuong, double donGiaBan, double donGiaNhap) {
		super();
		setMaSach(maSach);
		setTenSach(tenSach);
		setiSBN(iSBN);
		setLoaiSach(loaiSach);
		setTacGia(tacGia);
		setNhaXB(nhaXB);
		setSoTrang(soTrang);
		setSoLuong(soLuong);
		setDonGiaBan(donGiaBan);
		setDonGiaNhap(donGiaNhap);
	}

	//getters & setters
	public String getMaSach() {
		return maSach;
	}

	public void setMaSach(String maSach) {
		this.maSach = maSach;
	}

	public String getTenSach() {
		return tenSach;
	}

	public void setTenSach(String tenSach) {
		this.tenSach = tenSach;
	}

	public String getiSBN() {
		return iSBN;
	}

	public void setiSBN(String iSBN) {
		this.iSBN = iSBN;
	}

	public LoaiSach getLoaiSach() {
		return loaiSach;
	}

	public void setLoaiSach(LoaiSach loaiSach) {
		this.loaiSach = loaiSach;
	}

	public NhaXuatBan getNhaXB() {
		return nhaXB;
	}

	public void setNhaXB(NhaXuatBan nhaXB) {
		this.nhaXB = nhaXB;
	}

	public int getSoTrang() {
		return soTrang;
	}

	public void setSoTrang(int soTrang) {
		this.soTrang = soTrang;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public TacGia getTacGia() {
		return tacGia;
	}

	public void setTacGia(TacGia tacGia) {
		this.tacGia = tacGia;
	}

	public double getDonGiaNhap() {
		return donGiaNhap;
	}

	public void setDonGiaNhap(double donGiaNhap) {
		this.donGiaNhap = donGiaNhap;
	}

	public double getDonGiaBan() {
		return donGiaBan;
	}

	public void setDonGiaBan(double donGiaBan) {
		this.donGiaBan = donGiaBan;
	}

	//toString
	@Override
	public String toString() {
		return "Sach [maSach=" + maSach + ", tenSach=" + tenSach + ", iSBN=" + iSBN + ", loaiSach=" + loaiSach
				+ ", tacGia=" + tacGia + ", nhaXB=" + nhaXB + ", soTrang=" + soTrang + ", soLuong=" + soLuong
				+ ", donGiaNhap=" + donGiaNhap + ", donGiaBan=" + donGiaBan + "]";
	}	
	
}
