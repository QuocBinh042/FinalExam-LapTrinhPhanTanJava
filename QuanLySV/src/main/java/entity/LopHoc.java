package entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table (name = "LopHoc")
public class LopHoc implements Serializable{
	@Id
	private String msLop;
	private int siSoDuKien;
	private String tenLop;
	
	@OneToMany (mappedBy = "lopHoc")
	private List<SinhVien> dssv;
}
