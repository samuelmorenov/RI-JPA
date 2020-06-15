package uo.ri.cws.infrastructure.persistence.jpa.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.domain.WorkOrder;
import uo.ri.cws.infrastructure.persistence.jpa.util.BaseJpaRepository;
import uo.ri.cws.infrastructure.persistence.jpa.util.Jpa;

public class WorkOrderJpaRepository extends BaseJpaRepository<WorkOrder>
	implements WorkOrderRepository {

    @Override
    public List<WorkOrder> findByIds(List<String> idsAveria) {
	return Jpa.getManager()
		  .createNamedQuery("WorkOrder.findByIds", WorkOrder.class)
		  .setParameter(1, idsAveria)
		  .getResultList();
    }

    @Override
    public Optional<WorkOrder> SearchWorkOrder(String vehicleId, Date date) {
	System.out.println("Test SearchWorkOrder");//TODO testear
	return Jpa.getManager()
		  .createNamedQuery("WorkOrder.SearchWorkOrder",
			  WorkOrder.class)
		  .setParameter(1, vehicleId)
		  .setParameter(2, date)
		  .getResultStream()
		  .findFirst();
    }

    @Override
    public List<WorkOrder> findByPlateNumber(String plate) {
	System.out.println("Test findByPlateNumber");//TODO testear
	return Jpa.getManager()
		  .createNamedQuery("WorkOrder.findByPlateNumber",
			  WorkOrder.class)
		  .setParameter(1, plate)
		  .getResultList();
    }

    @Override
    public List<WorkOrder> findByVehicleId(String id) {
	System.out.println("Test findByVehicleId");//TODO testear
	return Jpa.getManager()
		  .createNamedQuery("WorkOrder.findByVehicleId",
			  WorkOrder.class)
		  .setParameter(1, id)
		  .getResultList();
    }

    @Override
    public List<WorkOrder> findUnfinishedWorkOrders() {
	System.out.println("Test findUnfinishedWorkOrders");//TODO testear
	return Jpa.getManager().createNamedQuery("WorkOrder.findUnfinished", 
		WorkOrder.class)
		.getResultList();
    }

}
