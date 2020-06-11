package uo.ri.cws.application.service.mechanic.crud.command;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.service.mechanic.MechanicDto;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Mechanic;

public class FindAllMechanics implements Command<List<MechanicDto>> {

    private MechanicRepository mr = Factory.repository.forMechanic();

    public List<MechanicDto> execute() {
	List<Mechanic> ms = mr.findAll();
	return DtoAssembler.toMechanicDtoList(ms);
    }

}
