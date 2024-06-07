package dao.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import dao.CandidateDAO;
import entity.Candidate;
import entity.Certificate;
import entity.Position;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class CandidateImpl implements CandidateDAO {
	private EntityManager em;

	public CandidateImpl() {
		em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
	}

	@Override
	public Map<Candidate, Long> listCadidatesByCompanies() {
		String jpql = "SELECT c, COUNT(DISTINCT e.companyName) FROM Candidate c JOIN c.experiences e GROUP BY c";
		TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
		List<Object[]> results = query.getResultList();
		return results.stream().collect(Collectors.toMap(row -> (Candidate) row[0], row -> (Long) row[1]));
	}

	@Override
	public Map<Candidate, Position> listCandidatesWithLongestWorking() {
        String jpql = "SELECT c, p, SUM(DATEDIFF(IFNULL(e.toDate, CURRENT_DATE), e.fromDate)) as duration " +
                      "FROM Experience e " +
                      "JOIN e.candidate c " +
                      "JOIN e.position p " +
                      "GROUP BY c, p " +
                      "ORDER BY duration DESC";
        
        TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
        List<Object[]> results = query.getResultList();

        Map<Position, Object[]> longestWorkingByPosition = results.stream()
                .collect(Collectors.toMap(
                        row -> (Position) row[1], 
                        row -> row,           
                        (row1, row2) -> (long) row1[2] > (long) row2[2] ? row1 : row2 
                ));

        return longestWorkingByPosition.values().stream()
                .collect(Collectors.toMap(
                        row -> (Candidate) row[0], 
                        row -> (Position) row[1] 
                ));
    }
	

	@Override
	public boolean addCandidate(Candidate candidate) {
		if (candidate.getId().matches("^C\\d{3,}$")) {
            try {
                em.persist(candidate); 
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            System.out.println("Mã số ứng viên không hợp lệ.");
            return false;
        }
	}

	@Override
	public Map<Candidate, Set<Certificate>> listCadidatesAndCertificates() {
	    String jpql = "SELECT c, cert FROM Candidate c LEFT JOIN FETCH c.certificates cert";
	    TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
	    List<Object[]> results = query.getResultList();

	    Map<Candidate, Set<Certificate>> candidateCertificatesMap = new HashMap<>();

	    for (Object[] result : results) {
	        Candidate candidate = (Candidate) result[0];
	        Certificate certificate = (Certificate) result[1];

	        if (!candidateCertificatesMap.containsKey(candidate)) {
	            candidateCertificatesMap.put(candidate, new HashSet<>());
	        }
	        if (certificate != null) {
	            candidateCertificatesMap.get(candidate).add(certificate);
	        }
	    }

	    return candidateCertificatesMap;
	}
	
	public static void main(String[] args) {
		CandidateImpl c = new CandidateImpl();
		Map<Candidate, Set<Certificate>> candidateCertificatesMap = c.listCadidatesAndCertificates();

	    candidateCertificatesMap.forEach((candidate, certificates) -> {
	        System.out.println("Candidate: " + candidate);
	        certificates.forEach(certificate -> System.out.println("  Certificate: " + certificate));
	    });
	    
//	    Map<Candidate, Long> listCadidatesByCompanies = c.listCadidatesByCompanies();
//	    listCadidatesByCompanies.forEach((candidate, Long)->{
//	    	System.out.println(candidate + "  " + Long);
//	    });
		
		
		Map<Candidate, Position> listCandidatesWithLongestWorking = c.listCandidatesWithLongestWorking();
		listCandidatesWithLongestWorking.forEach((candidate, position)->{
			System.out.println(candidate + "  " + position);
		});
	}

}
