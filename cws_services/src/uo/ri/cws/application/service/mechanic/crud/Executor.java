package uo.ri.cws.application.service.mechanic.crud;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.command.Command;

public class Executor {

	public <T> T execute(Command<T> cmd) throws BusinessException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("carworkshop");
		EntityManager em = emf.createEntityManager();
		try {
			EntityTransaction et = em.getTransaction();

			et.begin();

			try {
				T res = cmd.execute();

				et.commit();

				return res;
			} catch (BusinessException e) {
				if (et.isActive())
					et.rollback();
				throw e;
			}
		} finally {
			if (em.isOpen())
				em.close();
		}
	}

}
