package uo.ri.cws.application.service.invoice.create.command;

import java.util.List;

import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.invoice.InvoiceDto;
import uo.ri.cws.application.util.command.Command;

public class CreateInvoiceFor implements Command<InvoiceDto> {

	private List<String> workOrderIds;

	public CreateInvoiceFor(List<String> workOrderIds) {
		this.workOrderIds = workOrderIds;
	}

	List<String> _getWorkOrderIds() {
		return workOrderIds;
	}

	@Override
	public InvoiceDto execute() throws BusinessException {
		//TODO: No has terminado las prácticas del laboratorio: CreateInvoiceFor
		throw new RuntimeException("Not yet implemented.");
	}

}
