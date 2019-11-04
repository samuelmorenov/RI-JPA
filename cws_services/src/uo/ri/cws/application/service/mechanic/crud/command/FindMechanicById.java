package uo.ri.cws.application.service.mechanic.crud.command;

import java.util.Optional;

import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicDto;
import uo.ri.cws.application.util.command.Command;

public class FindMechanicById implements Command<Optional<MechanicDto>> {

	private String id;

	public FindMechanicById(String id) {
		this.id = id;
	}

	public Optional<MechanicDto> execute() throws BusinessException {

		return Optional.empty();
	}

}
