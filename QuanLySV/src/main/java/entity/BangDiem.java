package entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "BangDiem")
public class BangDiem implements Serializable{
	private Double diemThi;
	@Id
	private int lanThi;
	@Id
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "mssv")
	private SinhVien sinhVien;
	
	@Id
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "msmh")
	private MonHoc monHoc;
	
}
