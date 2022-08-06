package br.com.serviceordersystem.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Technician extends Person implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @OneToMany(mappedBy = "technician")
    private List<OrderOfService> orderServices = new ArrayList<>();

    public Technician() {
        super();
    }

    public Technician(Integer id, String name, String cpf, String phone) {
        super(id, name, cpf, phone);
    }

}
