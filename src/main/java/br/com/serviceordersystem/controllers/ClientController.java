package br.com.serviceordersystem.controllers;

import br.com.serviceordersystem.dtos.ClientDto;
import br.com.serviceordersystem.dtos.TechnicianDto;
import br.com.serviceordersystem.models.Client;
import br.com.serviceordersystem.models.Technician;
import br.com.serviceordersystem.services.ClientService;
import br.com.serviceordersystem.services.TechnicianService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDto> findById(@PathVariable Integer id) {
        ClientDto clientDto = new ClientDto(clientService.findById(id));
        return ResponseEntity.ok().body(clientDto);
    }

    @GetMapping
    public ResponseEntity<List<ClientDto>> findAll() {
        List<ClientDto> clientDto = clientService.findAll()
                .stream().map(obj -> new ClientDto(obj)).collect(Collectors.toList());

        return ResponseEntity.ok().body(clientDto);
    }

    @PostMapping
    public ResponseEntity<ClientDto> create(@Valid @RequestBody ClientDto clientDto) {
        Client client = clientService.create(clientDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(client.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientDto> update(@PathVariable Integer id, @Valid @RequestBody ClientDto
                                                clientDto) {

        ClientDto newClientDto = new ClientDto(clientService.update(id, clientDto));

        return ResponseEntity.ok().body(newClientDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
