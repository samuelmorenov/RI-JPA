package uo.ri.cws.domain;

public class Associations {

	public static class Own {

		public static void link(Client client, Vehicle vehicle) {
			vehicle._setClient(client);
			client._getVehicles().add(vehicle);
		}

		public static void unlink(Client client, Vehicle vehicle) {
			client._getVehicles().remove(vehicle);
			vehicle._setClient(null);
		}

	}

	public static class Classify {

		public static void link(VehicleType vehicleType, Vehicle vehicle) {
			vehicle._setVehicleType(vehicleType);
			vehicleType._getVehicles().add(vehicle);
		}

		public static void unlink(VehicleType vehicleType, Vehicle vehicle) {
			vehicleType._getVehicles().remove(vehicle);
			vehicle._setVehicleType(null);
		}

	}

	public static class Pay {

		public static void link(PaymentMean p, Client client) {
			p._setClient(client);
			client._getPaymentMeans().add(p);
		}
		
		public static void unlink(PaymentMean p, Client client) {

			client._getPaymentMeans().remove(p);
			p._setClient(null);
		}

	}

	public static class Order {

		public static void link(Vehicle vehicle, WorkOrder workOrder) {
			workOrder._setVehicle(vehicle);
			vehicle._getWorkOrders().add(workOrder);
		}

		public static void unlink(Vehicle vehicle, WorkOrder workOrder) {
			vehicle._getWorkOrders().remove(workOrder);
			workOrder._setVehicle(null);
		}

	}

	public static class ToInvoice {
		// TODO
	}

	public static class Charges {

	}

	public static class Assign {

		public static void link(Mechanic mechanic, WorkOrder workOrder) {
			workOrder._setMechanic(mechanic);
			mechanic._getWorkOrders().add(workOrder);
		}

		public static void unlink(Mechanic mechanic, WorkOrder workOrder) {

			mechanic._getWorkOrders().remove(workOrder);
			workOrder._setMechanic(null);
		}

	}

	public static class Intervene {

		public static void link(WorkOrder workOrder, Intervention intervention, Mechanic mechanic) {
			intervention._setMechanic(mechanic);
			intervention._setWorkOrder(workOrder);

			workOrder._getInterventions().add(intervention);
			mechanic._getInterventions().add(intervention);
		}

		public static void unlink(Intervention intervention) {
			
			Mechanic mechanic = intervention.getMechanic();
			WorkOrder workOrder = intervention.getWorkOrder();
			
			workOrder._getInterventions().remove(intervention);
			mechanic._getInterventions().remove(intervention);

			intervention._setMechanic(null);
			intervention._setWorkOrder(null);
		}

	}

	public static class Sustitute {

		public static void link(SparePart sparePart, Substitution substitution, Intervention intervention) {
			substitution._setIntervention(intervention);
			substitution._setSparePart(sparePart);
			
			intervention._getSubstitutions().add(substitution);
			sparePart._getSubstitutions().add(substitution);
			
		}
		
		public static void unlink(Substitution substitution) {

			Intervention intervention = substitution.getIntervention();
			SparePart sparePart = substitution.getSparePart();
			
			intervention._getSubstitutions().add(substitution);
			sparePart._getSubstitutions().add(substitution);
			
			substitution._setIntervention(null);
			substitution._setSparePart(null);
			
		}

	}

}
