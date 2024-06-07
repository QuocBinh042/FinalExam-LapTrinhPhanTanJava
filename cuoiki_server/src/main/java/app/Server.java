package app;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dao.EntityManagerFactoryUtil;
import entities.Candidate;
import entities.Certificate;
import entities.Position;
import jakarta.persistence.EntityManager;
import service.CandidateService;
import service.PositionService;

public class Server {
	public static void main(String[] args) {
		try(ServerSocket server= new ServerSocket(6789);){
			ExecutorService executorService=Executors.newCachedThreadPool();
			System.out.println("Server listening on port 6789...");
			while(true) {
				
				Socket socket=server.accept();
				System.out.println("Client connected: " + socket.getInetAddress().getHostAddress());
				executorService.execute(new clientHandler(socket));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
}
class clientHandler implements Runnable {
	private Socket socket;
	private EntityManagerFactoryUtil entityManagerFactory;
	private EntityManager entityManager;
	private PositionService positionService;
	private CandidateService candidateService;
	public clientHandler(Socket socket) {
		this.socket = socket;
		this.entityManagerFactory =new  EntityManagerFactoryUtil();
		this.entityManager = entityManagerFactory.getEnManager();
		this.positionService = new PositionService(this.entityManager);
		this.candidateService=new CandidateService(this.entityManager);
	}

	@Override
	public void run() {
		try {
			ObjectInputStream in =new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream out=new ObjectOutputStream(socket.getOutputStream());
			
			int choice =0;
			
			while(true) {
				choice=in.readInt();
				
				switch (choice) {
				case 1:
					String candidateID= in.readUTF();
					System.out.println("Danh sach:");
					Map<Position, Integer> map = positionService.listYearsOfExperienceByPosition(candidateID);
					out.writeObject(map);
					out.flush();
					break;
				case 2:
					String name = in.readUTF();
					double salaryFrom=in.readDouble();
					double salaryTo=in.readDouble();
					List<Position> positions=positionService.listPositions(name, salaryFrom, salaryTo); 
					out.writeObject(positions);
					out.flush();
					break;
				case 3:
					Map<Candidate, Long> map1=candidateService.listCadidatesByCompanies();
					out.writeObject(map1);
					out.flush();
					break;
				case 4:
					Map<Candidate, Position> map2=candidateService.listCandidatesWithLongestWorking();
					out.writeObject(map2);
					out.flush();
					break;
				case 5:
					String candidateId=in.readUTF();
					String name1=in.readUTF();
					Candidate candidate=new Candidate(candidateId, name1, 0, "Male", null, null, null, null);
					 boolean a= candidateService.addCandidate(candidate);
					 out.writeBoolean(a);
				case 6:
					Map<Candidate, Set<Certificate>> map3=candidateService.listCadidatesAndCertificates();
					out.writeObject(map3);
					out.flush();
					break;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			this.entityManagerFactory.closeEnManager();
			this.entityManagerFactory.closeEnManagerFactory();
		}
	}
} 