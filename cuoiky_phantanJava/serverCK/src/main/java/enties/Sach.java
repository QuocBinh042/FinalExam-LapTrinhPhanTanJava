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
@Table(name = "Sach")
public class Sach implements Serializable {
	@Id
	@Column(name = "MaSach", columnDefinition = "varchar(11)")
	private String maSach;
	@Column(name = "TuaSach", columnDefinition = "nvarchar(50)")
	private String tuaSach;
	@Column(name = "TacGia", columnDefinition = "nvarchar(50)")
	private String tacGia;
	@Column(name = "NamXB", columnDefinition = "int")
	private int namXB;
	@Column(name = "GiaBia", columnDefinition = "bigint")
	private long giaBia;

	@OneToMany(mappedBy = "sach")
	private List<ChiTietMuonSach> dsMuon;

	public Sach() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Sach(String maSach, String tuaSach, String tacGia, int namXB, long giaBia) {
		super();
		this.maSach = maSach;
		this.tuaSach = tuaSach;
		this.tacGia = tacGia;
		this.namXB = namXB;
		this.giaBia = giaBia;
	}

	public String getMaSach() {
		return maSach;
	}

	public void setMaSach(String maSach) {
		this.maSach = maSach;
	}

	public String getTuaSach() {
		return tuaSach;
	}

	public void setTuaSach(String tuaSach) {
		this.tuaSach = tuaSach;
	}

	public String getTacGia() {
		return tacGia;
	}

	public void setTacGia(String tacGia) {
		this.tacGia = tacGia;
	}

	public int getNamXB() {
		return namXB;
	}

	public void setNamXB(int namXB) {
		this.namXB = namXB;
	}

	public long getGiaBia() {
		return giaBia;
	}

	public void setGiaBia(long giaBia) {
		this.giaBia = giaBia;
	}

	public List<ChiTietMuonSach> getDsMuon() {
		return dsMuon;
	}

	public void setDsMuon(List<ChiTietMuonSach> dsMuon) {
		this.dsMuon = dsMuon;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maSach);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sach other = (Sach) obj;
		return Objects.equals(maSach, other.maSach);
	}

	@Override
	public String toString() {
		return "Sach [maSach=" + maSach + ", tuaSach=" + tuaSach + ", tacGia=" + tacGia + ", namXB=" + namXB
				+ ", giaBia=" + giaBia + "]";
	}

}
