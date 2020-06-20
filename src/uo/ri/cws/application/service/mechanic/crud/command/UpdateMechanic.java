package uo.ri.cws.application.service.mechanic.crud.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicDto;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Mechanic;

public class UpdateMechanic implements Command<Void> {

    private MechanicDto dto;
    private MechanicRepository mr = Factory.repository.forMechanic();

    public UpdateMechanic(MechanicDto dto) {
	this.dto = dto;
    }

    /**
     * Updates values for the mechanic specified by the id field, just name and
     * surname will be updated
     * 
     * @param mechanic dto, the id field, name and surname cannot be null
     * @throws BusinessException if the mechanic does not exist
     */
    public Void execute() throws BusinessException {
	
	BusinessCheck.isNotNull(dto.dni);
	BusinessCheck.isNotNull(dto.name);
	BusinessCheck.isNotNull(dto.surname);

	Optional<Mechanic> om = mr.findById(dto.id);
	BusinessCheck.exists(om, "There is no such mechanic");

	Mechanic m = om.get();
	BusinessCheck.hasVersion(m, dto.version);
	// DONE Hacer en todos los updates

	m.setName(dto.name);
	m.setSurname(dto.surname);

	return null;
    }

}
