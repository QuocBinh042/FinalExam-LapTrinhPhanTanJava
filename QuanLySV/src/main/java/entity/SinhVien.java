package entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table (name = "SinhVien")
public class SinhVien implements Serializable{
	@Id
	private String mssv;
	private String ho;
	private String ten;
	
	@ElementCollection
	@CollectionTable(
			name = "DienThoai",
			joinColumns = @JoinColumn (name = "mssv")
	)
	@Column (name = "DienThoai", nullable = false)
	private Set<String> dsDienThoai;
	private LocalDate ngaySinh;
	private String gioiTinh;
	
	@Embedded
	private DiaChi diaChi;
	
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "mslop")
	private LopHoc lopHoc;
	
	@OneToMany (mappedBy = "sinhVien")
	private List<BangDiem> dsdk;
}
