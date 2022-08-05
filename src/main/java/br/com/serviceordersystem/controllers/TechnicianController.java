package br.com.serviceordersystem.controllers;

import br.com.serviceordersystem.dtos.TechnicianDto;
import br.com.serviceordersystem.models.Technician;
import br.com.serviceordersystem.services.TechnicianService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/technician")
public class TechnicianController {

    private final TechnicianService technicianService;

    public TechnicianController(TechnicianService technicianService) {
        this.technicianService = technicianService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TechnicianDto> findById(@PathVariable Integer id) {
        TechnicianDto technicianDto = new TechnicianDto(technicianService.findById(id));
        return ResponseEntity.ok().body(technicianDto);
    }

    @GetMapping
    public ResponseEntity<List<TechnicianDto>> findAll() {
        List<TechnicianDto> technicianDtos = technicianService.findAll()
                .stream().map(obj -> new TechnicianDto(obj)).collect(Collectors.toList());

        return ResponseEntity.ok().body(technicianDtos);
    }

    @PostMapping
    public ResponseEntity<TechnicianDto> create(@Valid @RequestBody TechnicianDto technicianDto) {
        Technician technician = technicianService.create(technicianDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(technician.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TechnicianDto> update(@PathVariable Integer id, @Valid @RequestBody TechnicianDto
                                                technicianDto) {

        TechnicianDto newTechnicianDto = new TechnicianDto(technicianService.update(id, technicianDto));

        return ResponseEntity.ok().body(newTechnicianDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        technicianService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
