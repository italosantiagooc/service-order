package br.com.serviceordersystem.services;

import br.com.serviceordersystem.dtos.OrderDto;
import br.com.serviceordersystem.models.Client;
import br.com.serviceordersystem.models.Order;
import br.com.serviceordersystem.models.Technician;
import br.com.serviceordersystem.models.enums.Priority;
import br.com.serviceordersystem.models.enums.Status;
import br.com.serviceordersystem.repositories.OrderServiceRepository;
import br.com.serviceordersystem.services.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderServiceRepository serviceRepository;

    private final TechnicianService technicianService;

    private final ClientService clientService;

    public OrderService(OrderServiceRepository serviceRepository, TechnicianService technicianService, ClientService clientService) {
        this.serviceRepository = serviceRepository;
        this.technicianService = technicianService;
        this.clientService = clientService;
    }

    public Order findById(Integer id) {
        Optional<Order> orderService = serviceRepository.findById(id);
        return orderService.orElseThrow(() -> new ObjectNotFoundException("object not found! Id: " + id +
                ", Type: " + Technician.class.getName()));
    }

    public List<Order> findAll() {
        return serviceRepository.findAll();
    }


    public Order create(@Valid OrderDto orderDto) {
        return fromDto(orderDto);
    }

    public Order update(@Valid OrderDto orderDto) {
        findById(orderDto.getId());
        return fromDto(orderDto);
    }

    private Order fromDto(OrderDto orderDto) {
        Order newOrder = new Order();
        newOrder.setId(orderDto.getId());
        newOrder.setComments(orderDto.getComments());
        newOrder.setPriority(Priority.toEnum(orderDto.getPriority()));
        newOrder.setStatus(Status.toEnum(orderDto.getStatus()));

        Technician technician = technicianService.findById(orderDto.getTechnician());
        Client client = clientService.findById(orderDto.getClient());

        newOrder.setTechnician(technician);
        newOrder.setClient(client);

        if (newOrder.getStatus().getCod().equals(2)) {
            newOrder.setClosingDate(LocalDateTime.now());
        }
        return serviceRepository.save(newOrder);
    }


}
