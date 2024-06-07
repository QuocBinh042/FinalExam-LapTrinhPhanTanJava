package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dao.CandidateDAO;
import dao.impl.CandidateImpl;
import entity.Candidate;

class testCandidateDAO {
	private static CandidateDAO candidateDAO;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		candidateDAO = new CandidateImpl();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		candidateDAO = null;
	}

	@Test
	void testListCadidatesByCompanies() {
		Map<Candidate, Long> result = candidateDAO.listCadidatesByCompanies();	
		assertEquals(7, result.size());
	}

}
