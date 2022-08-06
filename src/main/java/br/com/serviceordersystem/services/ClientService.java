package br.com.serviceordersystem.services;

import br.com.serviceordersystem.dtos.ClientDto;
import br.com.serviceordersystem.models.Client;
import br.com.serviceordersystem.models.Person;
import br.com.serviceordersystem.models.Technician;
import br.com.serviceordersystem.repositories.ClientRepository;
import br.com.serviceordersystem.repositories.PersonRepository;
import br.com.serviceordersystem.services.exceptions.DataIntegrityViolationException;
import br.com.serviceordersystem.services.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    private final PersonRepository personRepository;

    public ClientService(ClientRepository clientRepository, PersonRepository personRepository) {
        this.clientRepository = clientRepository;
        this.personRepository = personRepository;
    }

    public Client findById(Integer id) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        return clientOptional.orElseThrow(() -> new ObjectNotFoundException("object not found! Id: " + id +
                ", Type: " + Technician.class.getName()));
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client create(ClientDto clientDto) {
        if (findByCpf(clientDto) != null){
            throw new DataIntegrityViolationException("CPF already registered in the database");
        }

        return clientRepository.save(new Client(null, clientDto.getName(), clientDto.getCpf(),
                clientDto.getPhone()));
    }

    public Client update(Integer id, @Valid ClientDto clientDto) {
        Client client = findById(id);

        if (findByCpf(clientDto) != null && findByCpf(clientDto).getId() != id) {
            throw new DataIntegrityViolationException("CPF already registered in the database");
        }

        client.setName(clientDto.getName());
        client.setCpf(clientDto.getCpf());
        client.setPhone(clientDto.getPhone());

        return clientRepository.save(client);
    }

    public void delete(Integer id) {
        Client client = findById(id);

        if (client.getOrderService().size() > 0) {
            throw new DataIntegrityViolationException("Technician has a service order, cannot be deleted");
        }

        clientRepository.deleteById(id);
    }

    private Person findByCpf(ClientDto clientDto) {
        Person person = personRepository.findByCpf(clientDto.getCpf());
        if (person != null) {
            return person;
        }
        return null;
    }
}
