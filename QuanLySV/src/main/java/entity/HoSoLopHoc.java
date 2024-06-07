package entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "HoSoLopHoc")
public class HoSoLopHoc implements Serializable{
	@Id
	private String maSoHoSo;
	private String ghiChu;
	private LocalDate ngayLap;
	
	@OneToOne 
	@JoinColumn (name = "msLop")
	private LopHoc lopHoc;
}
