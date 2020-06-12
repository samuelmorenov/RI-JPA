package uo.ri.cws.application.service.invoice.create.command;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.InvoiceRepository;
import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.invoice.InvoiceDto;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Invoice;
import uo.ri.cws.domain.WorkOrder;

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

	InvoiceRepository ir = Factory.repository.forInvoice();
	long numberInvoice = ir.getNextInvoiceNumber();
	List<WorkOrder> workOrders = new ArrayList<WorkOrder>();

	for (String workOrderID : workOrderIds) {
	    WorkOrderRepository wor = Factory.repository.forWorkOrder();
	    Optional<WorkOrder> owo = wor.findById(workOrderID);
	    BusinessCheck.exists(owo);
	    workOrders.add(owo.get());
	}

	Invoice invoice = new Invoice(numberInvoice, new Date(), workOrders);

	return DtoAssembler.toDto(invoice);

    }

}
