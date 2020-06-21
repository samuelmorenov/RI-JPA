package uo.ri.cws.application.repository;

public interface RepositoryFactory {

    MechanicRepository forMechanic();

    WorkOrderRepository forWorkOrder();

    PaymentMeanRepository forPaymentMean();

    InvoiceRepository forInvoice();

    ClientRepository forClient();

    SparePartRepository forSparePart();

    InterventionRepository forIntervention();

    VehicleRepository forVehicle();

    VehicleTypeRepository forVehicleType();

    CertificateRepository forCertificate(); // Added in the extension

    CourseRepository forCourse(); // Added in the second extension

    DedicationRepository forDedication();

}
