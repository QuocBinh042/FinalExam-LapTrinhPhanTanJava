package entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "DocGia")
public class DocGia implements Serializable {
	@Id
	@Column(name = "MaDG", columnDefinition = "varchar(13)", nullable = false)
	private String maDG;
	@Column(name = "HoTenDG", columnDefinition = "nvarchar(100)", nullable = false)
	private String hoTenDG;
	@Column(name = "Email", columnDefinition = "varchar(100)", nullable = true)
	private String email;
	@Column(name = "DienThoai", columnDefinition = "varchar(100)", nullable = true)
	private String dienThoai;

	@OneToMany (mappedBy = "docGia")
	private List<ChiTietMuonSach> dsMuon;
	
	public DocGia() {

	}

	public DocGia(String maDG, String hoTenDG, String email, String dienThoai) {
		super();
		this.maDG = maDG;
		this.hoTenDG = hoTenDG;
		this.email = email;
		this.dienThoai = dienThoai;
	}

	public String getMaDG() {
		return maDG;
	}

	public void setMaDG(String maDG) {
		this.maDG = maDG;
	}

	public String getHoTenDG() {
		return hoTenDG;
	}

	public void setHoTenDG(String hoTenDG) {
		this.hoTenDG = hoTenDG;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDienThoai() {
		return dienThoai;
	}

	public void setDienThoai(String dienThoai) {
		this.dienThoai = dienThoai;
	}

	@Override
	public String toString() {
		return "DocGia [maDG=" + maDG + ", hoTenDG=" + hoTenDG + ", email=" + email + ", dienThoai=" + dienThoai + "]";
	}

}
