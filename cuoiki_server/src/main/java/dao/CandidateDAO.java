package dao;

import java.util.Map;
import java.util.Set;

import entities.Candidate;
import entities.Certificate;
import entities.Position;

public interface CandidateDAO {
	public Map<Candidate, Long> listCadidatesByCompanies();
	public Map<Candidate, Position> listCandidatesWithLongestWorking();
	public boolean addCandidate(Candidate candidate);
	public Map<Candidate,Set<Certificate>> listCadidatesAndCertificates();
	
}
