package uo.ri.cws.infrastructure.persistence.jpa.repository;

import java.util.Optional;

import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.infrastructure.persistence.jpa.util.BaseJpaRepository;
import uo.ri.cws.infrastructure.persistence.jpa.util.Jpa;

public class MechanicJpaRepository extends BaseJpaRepository<Mechanic>
	implements MechanicRepository {

//	@Override
//	public void add(Mechanic t) {
//		Jpa.getManager().persist(t);
//
//	}
//
//	@Override
//	public void remove(Mechanic t) {
//		Jpa.getManager().remove(t);
//
//	}
//
//	@Override
//	public Optional<Mechanic> findById(String id) {
//		Mechanic m = Jpa.getManager().find(Mechanic.class, id);
//		return m != null ? Optional.of(m) : Optional.empty();
//	}
//
//	@Override
//	public List<Mechanic> findAll() {
//		// Auto-generated method stub
//		return null;
//	}

    @Override
    public Optional<Mechanic> findByDni(String dni) {
	return Jpa.getManager()
		  .createNamedQuery("Mechanic.findByDni", Mechanic.class)
		  .setParameter(1, dni)
		  .getResultStream()
		  .findFirst();
    }

}
