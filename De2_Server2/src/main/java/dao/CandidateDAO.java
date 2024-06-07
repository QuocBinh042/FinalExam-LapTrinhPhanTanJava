package dao;

import java.util.Map;
import java.util.Set;

import entity.Candidate;
import entity.Certificate;
import entity.Position;

public interface CandidateDAO {
	public Map<Candidate, Long> listCadidatesByCompanies();

	public Map<Candidate, Position> listCandidatesWithLongestWorking();

	public boolean addCandidate(Candidate candidate);

	public Map<Candidate, Set<Certificate>> listCadidatesAndCertificates();
}
