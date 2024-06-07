package enties;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "DocGia")
public class DocGia implements Serializable {
	@Id
	@Column(name = "MaDG", columnDefinition = "varchar(13)")
	private String maDG;
	@Column(name = "HoTenDG", columnDefinition = "nvarchar(50)")
	private String hoTenDG;
	@Column(name = "Email", columnDefinition = "nvarchar(50)")
	private String email;
	@Column(name = "DienThoai", columnDefinition = "nvarchar(50)")
	private String dienThoai;

	@OneToMany(mappedBy = "docGia")
	private List<ChiTietMuonSach> dsMuon;

	public DocGia() {
		super();
		// TODO Auto-generated constructor stub
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

	public List<ChiTietMuonSach> getDsMuon() {
		return dsMuon;
	}

	public void setDsMuon(List<ChiTietMuonSach> dsMuon) {
		this.dsMuon = dsMuon;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maDG);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DocGia other = (DocGia) obj;
		return Objects.equals(maDG, other.maDG);
	}

	@Override
	public String toString() {
		return "DocGia [maDG=" + maDG + ", hoTenDG=" + hoTenDG + ", email=" + email + ", dienThoai=" + dienThoai + "]";
	}

}
