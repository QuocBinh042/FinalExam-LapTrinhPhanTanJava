package entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table (name = "Sach")
public class Sach implements Serializable{
	@Id
	@Column (name = "MaSach",  columnDefinition = "varchar(13)", nullable = false)
	private String maSach;
	@Column (name = "TuaSach", columnDefinition = "nvarchar(100)", nullable = true)
	private String tuaSach;
	@Column (name = "TacGia", columnDefinition = "nvarchar(100)", nullable = true)
	private String tacGia;
	@Column (name = "NamXB", columnDefinition = "int", nullable = true)
	private int namXB;
	@Column (name = "GiaBan", columnDefinition = "bigint", nullable = true)
	private long giaBia;
	
	@OneToMany (mappedBy = "sach")
	private List<ChiTietMuonSach> dsMuon;
	
	
}
