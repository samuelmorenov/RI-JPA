package uo.ri.cws.infrastructure.persistence.jpa.executor;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.application.util.command.CommandExecutor;
import uo.ri.cws.infrastructure.persistence.jpa.util.Jpa;

public class JpaCommandExecutor implements CommandExecutor {

    /**
     * Registers a new work order out of the data received. Only this fields will be
     * taken into account: - the vehicle id, and - the description of the work to be
     * done The rest of fields will assigned by the service, thus any provided value
     * will be ignored.
     *
     * @param dto. Just vehicle id and description.
     *
     * @return another dto with the provided values and service-assigned fields
     *         filled: id, date and status
     *
     * @throws BusinessException if: - there is another work order for the same
     *                           vehicle at the same date and time (timestamp), or -
     *                           the vehicle does not exist
     */
    @Override
    public <T> T execute(Command<T> cmd) throws BusinessException {
	EntityManager mapper = Jpa.createEntityManager();
	try {
	    EntityTransaction trx = mapper.getTransaction();
	    trx.begin();

	    try {
		T res = cmd.execute();
		trx.commit();

		return res;

	    } catch (BusinessException | PersistenceException ex) {
		if (trx.isActive()) {
		    trx.rollback();
		}
		throw ex;
	    }
	} finally {
	    if (mapper.isOpen()) {
		mapper.close();
	    }
	}
    }
}
