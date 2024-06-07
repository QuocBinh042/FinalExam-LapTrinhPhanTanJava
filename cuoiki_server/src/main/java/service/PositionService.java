package service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import dao.PositionDAO;
import entities.Position;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class PositionService implements PositionDAO {
	private EntityManager entityManager;
	
	
	public PositionService(EntityManager entityManager) {
		
		this.entityManager = entityManager;
	}

	@Override
	public List<Position> listPositions(String name, double salaryFrom, double salaryTo) {
		String query="SELECT p FROM Position p "
				+ "WHERE p.name LIKE :name AND p.salary >= :salaryFrom AND p.salary <= :salaryTo "
				+ "ORDER BY p.name";
		List<Position> results=entityManager.createQuery(query).setParameter("name","%"+ name+"%").setParameter("salaryFrom", salaryFrom).setParameter("salaryTo", salaryTo).getResultList();	
		return results;
	}

	@Override
	public Map<Position, Integer> listYearsOfExperienceByPosition(String candidateID) {
		// TODO Auto-generated method stub
		Map<Position, Integer> map = new LinkedHashMap<Position, Integer>();
		String query = "SELECT e.positions.id,  SUM(YEAR(e.toDate)- YEAR(e.fromDate)) AS total_years "
				+ "FROM Experience e  where e.candidates.id= :candidateID "
				+ "GROUP BY e.positions.id";
		List<?> results= entityManager.createQuery(query).setParameter("candidateID", candidateID).getResultList();
		results.stream().map(obj -> (Object[]) obj).forEach(a -> {
			String positionID = (String) a[0];
			Long year = (Long) a[1];
			 Position position = entityManager.find(Position.class, positionID);
			
			map.put(position, year.intValue());
		});

		return map;
	}

}
