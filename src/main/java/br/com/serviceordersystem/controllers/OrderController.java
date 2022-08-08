package br.com.serviceordersystem.controllers;

import br.com.serviceordersystem.dtos.OrderDto;
import br.com.serviceordersystem.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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
    public ResponseEntity<OrderDto> findById(@PathVariable Integer id) {
        OrderDto ofServiceDto = new OrderDto(orderService.findById(id));
        return ResponseEntity.ok().body(ofServiceDto);
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> findAll() {
        List<OrderDto> ofServiceDto = orderService.findAll()
                .stream().map(newOfServiceDto -> new OrderDto(newOfServiceDto))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(ofServiceDto);
    }

    @PostMapping
    public ResponseEntity<OrderDto> create(@Valid @RequestBody OrderDto orderDto) {
        orderDto = new OrderDto(orderService.create(orderDto));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(orderDto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping
    public ResponseEntity<OrderDto> update(@Valid @RequestBody OrderDto orderDto) {
        orderDto = new OrderDto(orderService.update(orderDto));
        return ResponseEntity.ok().body(orderDto);
    }
}
