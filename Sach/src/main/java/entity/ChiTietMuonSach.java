package entity;

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
@Table (name = "ChiTietMuonSach")
public class ChiTietMuonSach implements Serializable{
	@Id
	@Column (name = "NgayMuon", nullable = false)
	private Date ngayMuon;
	@Column (name = "NgayTra", nullable = true)
	private Date ngayTra;
	@Column (name = "TienCoc", columnDefinition = "bigint", nullable = true)
	private long tienCoc;
	@Id
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "MaDG")
	private DocGia docGia;
	@Id
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "MaSach")
	private Sach sach;
	
	
}
