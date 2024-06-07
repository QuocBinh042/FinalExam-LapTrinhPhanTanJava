package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import dao.PositionDAO;
import entity.Position;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class PositionImpl implements PositionDAO {
	private EntityManager em;

	public PositionImpl() {
		em = Persistence.createEntityManagerFactory("jpa-mssql").createEntityManager();
	}

	@Override
	public List<Position> listPositions(String name, double salaryFrom, double salaryTo) {
		String jpql = "SELECT p FROM Position p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')) AND p.salary BETWEEN :salaryFrom AND :salaryTo ORDER BY p.name";
		return em.createQuery(jpql, Position.class).setParameter("name", name).setParameter("salaryFrom", salaryFrom)
				.setParameter("salaryTo", salaryTo).getResultList();
	}

	
	@Override
	public Map<Position, Integer> listYearsOfExperienceByPosition(String candidateID) {
		String sql = "SELECT candidate_id, position_id, DATEDIFF(day, from_date, to_date) AS days "
				+ "FROM experiences AS temp " + "WHERE DATEDIFF(day, from_date, to_date) >= ALL "
				+ "(SELECT DATEDIFF(day, from_date, to_date) " + "FROM experiences AS temp_b "
				+ "WHERE temp_b.candidate_id = temp.candidate_id)";

		Query query = em.createNativeQuery(sql);
		List<Object[]> results = query.getResultList();

		return results.stream().collect(Collectors.toMap(row -> {
			Position position = new Position();
			position.setId((String) row[1]);
			return position;
		}, row -> ((Number) row[2]).intValue()));

	}

	public static void main(String[] args) {
		PositionImpl pDAO = new PositionImpl();
//		Map<Position, Integer> list = pDAO.listYearsOfExperienceByPosition("C101");
//		list.forEach((position, integer) -> {
//			System.out.println(position + ": " + integer);
//		});
		
		List<Position> list2 = pDAO.listPositions("P110,", 120, 150000);
		list2.forEach(System.out::println);
	}
}
