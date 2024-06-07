package enties;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ChiTietMuonSach")
public class ChiTietMuonSach implements Serializable {
	@Column(name = "NgayTra", columnDefinition = "datetime")
	private Date ngayTra;
	@Id
	@Column(name = "NgayMuon", columnDefinition = "datetime")
	private Date ngayMuon;
	@Column(name = "TienCoc", columnDefinition = "bigint")
	private long tienCoc;

	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MaDG")
	private DocGia docGia;

	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MaSach")
	private Sach sach;

	public ChiTietMuonSach() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChiTietMuonSach(Date ngayTra, Date ngayMuon, long tienCoc) {
		super();
		this.ngayTra = ngayTra;
		this.ngayMuon = ngayMuon;
		this.tienCoc = tienCoc;
	}

	public Date getNgayTra() {
		return ngayTra;
	}

	public void setNgayTra(Date ngayTra) {
		this.ngayTra = ngayTra;
	}

	public Date getNgayMuon() {
		return ngayMuon;
	}

	public void setNgayMuon(Date ngayMuon) {
		this.ngayMuon = ngayMuon;
	}

	public long getTienCoc() {
		return tienCoc;
	}

	public void setTienCoc(long tienCoc) {
		this.tienCoc = tienCoc;
	}

	public DocGia getDocGia() {
		return docGia;
	}

	public void setDocGia(DocGia docGia) {
		this.docGia = docGia;
	}

	public Sach getSach() {
		return sach;
	}

	public void setSach(Sach sach) {
		this.sach = sach;
	}

	@Override
	public String toString() {
		return "ChiTietMuonSach [ngayTra=" + ngayTra + ", ngayMuon=" + ngayMuon + ", tienCoc=" + tienCoc + "]";
	}

}
