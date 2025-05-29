package bookalyze.api.demo.contacts.repository;

import bookalyze.api.demo.contacts.repository.entity.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactsRepository extends JpaRepository<Contacts, Long> {
}
