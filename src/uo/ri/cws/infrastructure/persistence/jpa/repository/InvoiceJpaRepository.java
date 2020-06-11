package uo.ri.cws.infrastructure.persistence.jpa.repository;

import java.util.Optional;

import uo.ri.cws.application.repository.InvoiceRepository;
import uo.ri.cws.domain.Invoice;
import uo.ri.cws.infrastructure.persistence.jpa.util.BaseJpaRepository;

public class InvoiceJpaRepository extends BaseJpaRepository<Invoice>
	implements InvoiceRepository {

    @Override
    public Optional<Invoice> findByNumber(Long numero) {
	throw new RuntimeException("Not yet implemented.");
    }

    @Override
    public Long getNextInvoiceNumber() {
	throw new RuntimeException("Not yet implemented.");
    }

}
