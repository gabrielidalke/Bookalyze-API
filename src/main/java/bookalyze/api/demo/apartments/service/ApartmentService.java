package bookalyze.api.demo.apartments.service;

import bookalyze.api.demo.apartments.repository.ApartmentRepository;
import bookalyze.api.demo.apartments.repository.entity.Apartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApartmentService {

    @Autowired
    private ApartmentRepository apartmentRepository;

    public List<Apartment> findAll() {
        return apartmentRepository.findAll();
    }

    public Optional<Apartment> findById(Long id) {
        return apartmentRepository.findById(id);
    }

    public Apartment save(Apartment apartment) {
        return apartmentRepository.save(apartment);
    }

    public void delete(Long id) {
        apartmentRepository.deleteById(id);
    }

    public Apartment update(Long id, Apartment updatedApartment) {
        return apartmentRepository.findById(id).map(apartment -> {
            apartment.setTitle(updatedApartment.getTitle());
            apartment.setCity(updatedApartment.getCity());
            apartment.setState(updatedApartment.getState());
            apartment.setMaxGuests(updatedApartment.getMaxGuests());
            apartment.setDailyRate(updatedApartment.getDailyRate());
            return apartmentRepository.save(apartment);
        }).orElseThrow(() -> new RuntimeException("Apartment not found with id " + id));
    }
}
