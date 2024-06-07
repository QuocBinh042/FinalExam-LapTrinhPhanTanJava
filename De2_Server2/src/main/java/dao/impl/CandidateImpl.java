package dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import dao.CandidateDAO;
import entity.Candidate;
import entity.Certificate;
import entity.Position;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class CandidateImpl implements CandidateDAO {
	private EntityManager em;

	public CandidateImpl() {
		em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
	}

	@Override
	public Map<Candidate, Long> listCadidatesByCompanies() {
		String jpql = "SELECT c, COUNT(DISTINCT e.companyName) FROM Candidate c JOIN c.experiences e GROUP BY c";
		List<Object[]> l = em.createQuery(jpql, Object[].class).getResultList();
		return l.stream().collect(Collectors.toMap(row -> (Candidate) row[0], row -> (Long) row[1]));

	}

	@Override
	public Map<Candidate, Position> listCandidatesWithLongestWorking() {
		return null;
	}

	@Override
	public boolean addCandidate(Candidate candidate) {
		return false;
	}

	@Override
	public Map<Candidate, Set<Certificate>> listCadidatesAndCertificates() {
		String jpql = "SELECT c, cert FROM Candidate c LEFT JOIN FETCH c.certificates cert";
		List<Object[]> result = em.createQuery(jpql,Object[].class).getResultList();
		return result.stream().collect(Collectors.groupingBy(
	            row -> (Candidate) row[0],
	            Collectors.mapping(
	                row -> (Certificate) row[1], 
	                Collectors.toSet() 
	            )
	        ));
	}

	public static void main(String[] args) {
		CandidateImpl c = new CandidateImpl();
//		Map<Candidate, Long> listCadidatesByCompanies = c.listCadidatesByCompanies();
//		listCadidatesByCompanies.forEach((Candidate,Long)->{
//			System.out.println(Candidate + " " + Long);
//		});
		Map<Candidate, Set<Certificate>> result = c.listCadidatesAndCertificates();
		result.forEach((Candidate, certificates) -> {
			System.out.println("Candidate:  " + Candidate);
			certificates.forEach(certificate -> System.out.println("    " + certificate));

		});
	}
}
