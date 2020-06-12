package uo.ri.cws.application.service.mechanic.crud.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Mechanic;

public class DeleteMechanic implements Command<Void> {

    private String mechanicId;
    private MechanicRepository mr = Factory.repository.forMechanic();

    public DeleteMechanic(String idMecanico) {
	this.mechanicId = idMecanico;
    }

    /**
     * @param idMecanico
     * 
     * @throws BusinessException if <br>
     *                           the mechanic does not exist
     */
    public Void execute() throws BusinessException {

	Optional<Mechanic> om = mr.findById(mechanicId);

	BusinessCheck.isTrue(om.isPresent(), "This mechanic does not exist");

	Mechanic m = om.get();
	BusinessCheck.isTrue(m.getInterventions().size() == 0,
		"This mechanics has interventions");

	mr.remove(m);

	return null;
    }

}
