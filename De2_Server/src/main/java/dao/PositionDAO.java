package dao;

import java.util.List;
import java.util.Map;

import entity.Position;
public interface PositionDAO {
	List<Position> listPositions (String name, double salaryFrom, double salaryTo);
	Map<Position, Integer> listYearsOfExperienceByPosition(String candidateID);
}
