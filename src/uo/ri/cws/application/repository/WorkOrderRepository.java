package uo.ri.cws.application.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.domain.WorkOrder;

public interface WorkOrderRepository extends Repository<WorkOrder> {

    /**
     * @param idsAveria, lista de los id de avería a recuperar
     * @return lista con averias cuyo id aparece en idsAveria, o lista vacía si no
     *         hay ninguna
     */
    List<WorkOrder> findByIds(List<String> workOrderIds);

    Optional<WorkOrder> SearchWorkOrder(String vehicleId, Date date);

    List<WorkOrder> findAll();

    List<WorkOrder> findByPlateNumber(String plate);

    List<WorkOrder> findByVehicleId(String id);

    List<WorkOrder> findUnfinishedWorkOrders();

}