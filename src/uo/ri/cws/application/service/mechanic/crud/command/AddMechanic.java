package uo.ri.cws.application.service.mechanic.crud.command;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicDto;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Mechanic;

public class AddMechanic implements Command<MechanicDto> {

    private MechanicDto dto;
    private MechanicRepository mr = Factory.repository.forMechanic();

    public AddMechanic(MechanicDto mecanico) {
	this.dto = mecanico;
    }

    public MechanicDto execute() throws BusinessException {

	Mechanic m = new Mechanic(dto.dni, dto.name, dto.surname);

	mr.add(m);

	dto.id = m.getId();

	return dto;
    }

}
