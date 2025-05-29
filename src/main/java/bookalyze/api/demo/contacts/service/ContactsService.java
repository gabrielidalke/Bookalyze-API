package bookalyze.api.demo.contacts.service;

import bookalyze.api.demo.contacts.repository.ContactsRepository;
import bookalyze.api.demo.contacts.repository.entity.Contacts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactsService {

    @Autowired
    private ContactsRepository contactsRepository;

    public List<Contacts> findAll() {
        return contactsRepository.findAll();
    }

    public Optional<Contacts> findById(Long id) {
        return contactsRepository.findById(id);
    }

    public Contacts save(Contacts contact) {
        return contactsRepository.save(contact);
    }

    public void delete(Long id) {
        contactsRepository.deleteById(id);
    }

    public Contacts update(Long id, Contacts updatedContact) {
        return contactsRepository.findById(id)
                .map(contact -> {
                    contact.setName(updatedContact.getName());
                    contact.setEmail(updatedContact.getEmail());
                    contact.setPhone(updatedContact.getPhone());
                    contact.setType(updatedContact.getType());
                    contact.setDocument(updatedContact.getDocument());
                    return contactsRepository.save(contact);
                })
                .orElseThrow(() -> new RuntimeException("Contato não encontrado com ID " + id));
    }
}
