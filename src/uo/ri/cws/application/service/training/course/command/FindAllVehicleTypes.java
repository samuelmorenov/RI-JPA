package uo.ri.cws.application.service.training.course.command;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.VehicleTypeRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.vehicletype.VehicleTypeDto;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.VehicleType;

public class FindAllVehicleTypes implements Command<List<VehicleTypeDto>> {

    private VehicleTypeRepository vtr = Factory.repository.forVehicleType();

    /**
     * @return a list of VehicleTypeDto.
     * @see VehicleTypeDto class for details.
     * 
     *      DOES NOT @throws BusinessException
     */
    @Override
    public List<VehicleTypeDto> execute() throws BusinessException {
	List<VehicleType> ms = vtr.findAll();
	return DtoAssembler.toVehicleTypeDtoList(ms);
    }

}
