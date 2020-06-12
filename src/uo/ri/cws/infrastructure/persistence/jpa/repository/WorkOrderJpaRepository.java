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
	// TODO Auto-generated method stub
	return null;
    }

}
