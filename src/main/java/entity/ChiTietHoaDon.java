package entity;

public class ChiTietHoaDon {
	private HoaDon hoaDon;
	private Phong phong;
	private DichVu sanPham;
	private int soLuong;

	public ChiTietHoaDon() {
		super();
	}

	public ChiTietHoaDon(HoaDon hoaDon, Phong phong, DichVu sanPham, int soLuong) {
		super();
		this.hoaDon = hoaDon;
		this.phong = phong;
		this.sanPham = sanPham;
		this.soLuong = soLuong;
	}

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	public Phong getPhong() {
		return phong;
	}

	public void setPhong(Phong phong) {
		this.phong = phong;
	}

	public DichVu getSanPham() {
		return sanPham;
	}

	public void setSanPham(DichVu sanPham) {
		this.sanPham = sanPham;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	@Override
	public String toString() {
		return "ChiTietHoaDon [hoaDon=" + hoaDon + ", phong=" + phong + ", sanPham=" + sanPham + ", soLuong=" + soLuong
				+ "]";
	}
	
	public double getThanhTien() {
		return soLuong * sanPham.getDonGia();
	}
}
