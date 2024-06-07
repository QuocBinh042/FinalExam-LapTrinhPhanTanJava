package service;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import dao.CandidateDAO;
import entities.Candidate;
import entities.Certificate;
import entities.Position;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class CandidateService implements CandidateDAO{
	public EntityManager entityManager;
	
	public CandidateService(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	/**
	 *
	 */
	@Override
	public Map<Candidate, Long> listCadidatesByCompanies() {
		// TODO Auto-generated method stub
		Map<Candidate, Long> map=new LinkedHashMap<Candidate, Long>();
		String query="SELECT e.candidates.id,count(e.companyName) FROM Experience e GROUP BY e.candidates.id ";
		List<?> results=entityManager.createQuery(query).getResultList();
		results.stream().map(obj-> (Object[]) obj).forEach(a-> {
			String candidateid=(String)a[0];
			Long so = (Long) a[1];
			Candidate candidate=entityManager.find(Candidate.class,candidateid );
			map.put(candidate, so);
		});
		return map;
	}

	@Override
	public Map<Candidate, Position> listCandidatesWithLongestWorking() {
		// TODO Auto-generated method stub
		Map<Candidate, Position> map=new LinkedHashMap<Candidate,Position>();
		String query="SELECT p.id, e.candidates.id, MAX(DATEDIFF(e.toDate, e.fromDate) / 365) AS longest_years\r\n"
				+ "FROM Experience e JOIN Position p ON e.positions.id = p.id  \r\n"
				+ "GROUP BY p.id, p.name\r\n"
				+ "ORDER BY longest_years DESC";
		List<?> result=entityManager.createQuery(query).getResultList();
		result.stream().map(obj-> (Object[]) obj).forEach(a->{
			String candidate=(String)a[1];
			String position=(String)a[0];
			Candidate candidate2=entityManager.find(Candidate.class,candidate);
			Position position2=entityManager.find(Position.class, position);
			map.put(candidate2, position2);
		});
		return map;
	}

	@Override
	public boolean addCandidate(Candidate candidate) {
		// TODO Auto-generated method stub
		if (candidate.getId().matches("^C\\d{3,}$")) {
	        EntityTransaction transaction = entityManager.getTransaction();
	        try {
	            transaction.begin();
	            entityManager.persist(candidate);
	            transaction.commit();
	            return true;
	        } catch (Exception e) {
	            // Xử lý ngoại lệ và rollback giao dịch
	            if (transaction.isActive()) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	        }
	    } else {
	        // Nếu mã số không thỏa mãn điều kiện
	        System.out.println("Mã số ứng viên phải bắt đầu bằng 'C' và theo sau ít nhất 3 chữ số.");
	    }
	    return false;
	}

	@Override
	public Map<Candidate, Set<Certificate>> listCadidatesAndCertificates() {
		// TODO Auto-generated method stub
		Map<Candidate, Set<Certificate>> map=new LinkedHashMap<Candidate, Set<Certificate>>();
		List<Candidate> list=entityManager.createQuery("SELECT c from Candidate c ").getResultList();
		for (Candidate candidate : list) {
			String a=candidate.getId();
			List<Certificate> list1=entityManager.createQuery("SELECT c from Certificate c where c.candidate.id= :a").setParameter("a", a).getResultList();	
			Set<Certificate> set=new HashSet<>(list1);
			map.put(candidate, set);
		}
		return map;
	}
	
}
