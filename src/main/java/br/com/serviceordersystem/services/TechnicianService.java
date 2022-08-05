package br.com.serviceordersystem.services;

import br.com.serviceordersystem.dtos.TechnicianDto;
import br.com.serviceordersystem.models.Person;
import br.com.serviceordersystem.models.Technician;
import br.com.serviceordersystem.repositories.PersonRepository;
import br.com.serviceordersystem.repositories.TechnicianRepository;
import br.com.serviceordersystem.services.exceptions.DataIntegrityViolationException;
import br.com.serviceordersystem.services.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class TechnicianService {

    private final TechnicianRepository technicianRepository;

    private final PersonRepository personRepository;

    public TechnicianService(TechnicianRepository technicianRepository, PersonRepository personRepository) {
        this.technicianRepository = technicianRepository;
        this.personRepository = personRepository;
    }

    public Technician findById(Integer id) {
        Optional<Technician> technician = technicianRepository.findById(id);
        return technician.orElseThrow(() -> new ObjectNotFoundException("object not found! Id: " + id +
                ", Tipo: " + Technician.class.getName()));

    }

    public List<Technician> findAll() {
        return technicianRepository.findAll();
    }

    public Technician create(TechnicianDto technicianDto) {
        if (findByCpf(technicianDto) != null){
            throw new DataIntegrityViolationException("CPF already registered in the database");
        }

        return technicianRepository.save(new Technician(null, technicianDto.getName(), technicianDto.getCpf(),
                technicianDto.getPhone()));
    }

    public Technician update(Integer id, @Valid TechnicianDto technicianDto) {
        Technician technician = findById(id);

        if (findByCpf(technicianDto) != null && findByCpf(technicianDto).getId() != id){
            throw new DataIntegrityViolationException("CPF already registered in the database");
        }

        technician.setName(technicianDto.getName());
        technician.setCpf(technicianDto.getCpf());
        technician.setPhone(technicianDto.getPhone());

        return technicianRepository.save(technician);
    }

    public void delete(Integer id) {
        Technician technician = findById(id);

        if (technician.getOrderServices().size() > 0) {
            throw new DataIntegrityViolationException("Technician has a service order, cannot be deleted");
        }

        technicianRepository.deleteById(id);
    }

    private Person findByCpf(TechnicianDto technicianDto) {
        Person person = personRepository.findByCpf(technicianDto.getCpf());
        if (person != null) {
            return person;
        }
        return null;
    }


}
