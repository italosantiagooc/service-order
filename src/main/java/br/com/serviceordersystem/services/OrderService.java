package br.com.serviceordersystem.services;

import br.com.serviceordersystem.models.OrderOfService;
import br.com.serviceordersystem.models.Technician;
import br.com.serviceordersystem.repositories.OrderServiceRepository;
import br.com.serviceordersystem.services.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderServiceRepository serviceRepository;

    public OrderService(OrderServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public OrderOfService findById(Integer id) {
        Optional<OrderOfService> orderService = serviceRepository.findById(id);
        return orderService.orElseThrow(() -> new ObjectNotFoundException("object not found! Id: " + id +
                ", Type: " + Technician.class.getName()));
    }

    public List<OrderOfService> findAll() {
        return serviceRepository.findAll();
    }
}
