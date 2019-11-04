package uo.ri.cws.application.service.mechanic.crud.command;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.domain.Mechanic;

public class DeleteMechanic {

	private String mechanicId;

	public DeleteMechanic(String idMecanico) {
		this.mechanicId = idMecanico;
	}

	public Void execute() throws BusinessException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("carworkshop");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();

		Mechanic m = em.find(Mechanic.class, mechanicId);
		
		BusinessCheck.isTrue(m!=null,  "This mechanic does not exist");
		BusinessCheck.isTrue(m.getInterventions().size() == 0, "This mechanics has interventions");
		
		em.remove(m);

		et.commit();
		em.close();
		return null;
	}

}
