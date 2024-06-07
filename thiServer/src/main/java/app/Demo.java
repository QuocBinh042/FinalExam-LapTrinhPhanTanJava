package app;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import dao.DoctorDao;
import dao.PaitentDao;
import entity.Doctor;
import entity.Patient;
import entity.Treatment;

public class Demo {
  public static void main(String[] args) {
    PaitentDao paitentDao = new PaitentDao();
    DoctorDao doctorDao = new DoctorDao();
    Patient patient = new Patient("HCM", 20, "long", "nam", "PT-8", "nguyen", "0366231860");
    List<Treatment> treatments = Arrays.asList(new Treatment("kuga", new Date(), new Date(), new Doctor("DT100")),
        new Treatment("kuga2", new Date(), new Date(), new Doctor("DT100")));
    patient.setTreatments(treatments);
    System.out.println(paitentDao.addPaintent(patient));
    // doctorDao.getDoctorsBySpecialty("logist").forEach(System.out::println);
    doctorDao.getNumOfDoctorsByDepartments().forEach((k, v) -> System.out.println(k + " - soluong: " + v));
  }
}
