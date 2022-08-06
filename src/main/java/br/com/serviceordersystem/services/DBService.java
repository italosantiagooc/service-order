package br.com.serviceordersystem.services;

import br.com.serviceordersystem.models.Client;
import br.com.serviceordersystem.models.OrderOfService;
import br.com.serviceordersystem.models.Technician;
import br.com.serviceordersystem.models.enums.Priority;
import br.com.serviceordersystem.models.enums.Status;
import br.com.serviceordersystem.repositories.ClientRepository;
import br.com.serviceordersystem.repositories.OrderServiceRepository;
import br.com.serviceordersystem.repositories.TechnicianRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    private final TechnicianRepository technicianRepository;
    private final ClientRepository clientRepository;
    private final OrderServiceRepository orderServiceRepository;

    public DBService(TechnicianRepository technicianRepository, ClientRepository clientRepository, OrderServiceRepository orderServiceRepository) {
        this.technicianRepository = technicianRepository;
        this.clientRepository = clientRepository;
        this.orderServiceRepository = orderServiceRepository;
    }

    public void instanceDB() {
        Technician technician = new Technician(null, "Italo Santiago", "041.475.543-00", "(88)99220-4003");
        Client client = new Client(null, "Maria Ivonete", "769.380.693-87", "(88)99732-4141");
        OrderOfService orderService = new OrderOfService(null, Priority.HIGH, "Teste create OS", Status.PROGRESS, technician, client);

        technicianRepository.saveAll(Arrays.asList(technician));
        clientRepository.saveAll((Arrays.asList(client)));
        orderServiceRepository.saveAll(Arrays.asList(orderService));
    }
}
