package bookalyze.api.demo.apartments.repository;

import bookalyze.api.demo.apartments.repository.entity.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
}
