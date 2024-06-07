package entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table (name = "MonHoc")
public class MonHoc implements Serializable{
	@Id
	private String msmh;
	private int hocKyGD;
	private int soTinChi;
	private String tenMonHoc;
	
	@OneToMany (mappedBy = "monHoc")
	private List<BangDiem> dsdk;
}
