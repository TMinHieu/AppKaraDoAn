package entity;

import java.util.Objects;

public class LoaiDichVu {
	private String maLoaiDV;
	private String tenLoaiDV;

	@Override
	public String toString() {
		return "LoaiDichVu [maLoaiDV=" + maLoaiDV + ", tenLoaiDV=" + tenLoaiDV + "]";
	}

	public String getMaLoaiDV() {
		return maLoaiDV;
	}

	public void setMaLoaiDV(String maLoaiDV) {
		this.maLoaiDV = maLoaiDV;
	}

	public String getTenLoaiDV() {
		return tenLoaiDV;
	}

	public void setTenLoaiDV(String tenLoaiDV) {
		this.tenLoaiDV = tenLoaiDV;
	}

	public LoaiDichVu(String maLoaiDV, String tenLoaiDV) {
		super();
		this.maLoaiDV = maLoaiDV;
		this.tenLoaiDV = tenLoaiDV;
	}

	public LoaiDichVu() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(maLoaiDV);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoaiDichVu other = (LoaiDichVu) obj;
		return Objects.equals(maLoaiDV, other.maLoaiDV);
	}

}
