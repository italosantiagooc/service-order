package br.com.serviceordersystem.controllers;

import br.com.serviceordersystem.dtos.OrderOfServiceDto;
import br.com.serviceordersystem.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping(value = "/order-service")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderOfServiceDto> findById(@PathVariable Integer id) {
        OrderOfServiceDto ofServiceDto = new OrderOfServiceDto(orderService.findById(id));
        return ResponseEntity.ok().body(ofServiceDto);
    }

    @GetMapping
    public ResponseEntity<List<OrderOfServiceDto>> findAll() {
        List<OrderOfServiceDto> ofServiceDto = orderService.findAll()
                .stream().map(newOfServiceDto -> new OrderOfServiceDto(newOfServiceDto))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(ofServiceDto);
    }
}
