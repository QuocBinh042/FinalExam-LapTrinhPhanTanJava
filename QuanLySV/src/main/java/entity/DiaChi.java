package entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class DiaChi {
	private String duong;
	private String phuong;
	private String quan;
	private String soNha;
	private String thanhPho;

	public DiaChi() {
	}

	public DiaChi(String duong, String phuong, String quan, String soNha, String thanhPho) {
		this.duong = duong;
		this.phuong = phuong;
		this.quan = quan;
		this.soNha = soNha;
		this.thanhPho = thanhPho;
	}

	public String getDuong() {
		return duong;
	}

	public void setDuong(String duong) {
		this.duong = duong;
	}

	public String getPhuong() {
		return phuong;
	}

	public void setPhuong(String phuong) {
		this.phuong = phuong;
	}

	public String getQuan() {
		return quan;
	}

	public void setQuan(String quan) {
		this.quan = quan;
	}

	public String getSoNha() {
		return soNha;
	}

	public void setSoNha(String soNha) {
		this.soNha = soNha;
	}

	public String getThanhPho() {
		return thanhPho;
	}

	public void setThanhPho(String thanhPho) {
		this.thanhPho = thanhPho;
	}

	@Override
	public String toString() {
		return "DiaChi [duong=" + duong + ", phuong=" + phuong + ", quan=" + quan + ", soNha=" + soNha + ", thanhPho="
				+ thanhPho + "]";
	}

}
