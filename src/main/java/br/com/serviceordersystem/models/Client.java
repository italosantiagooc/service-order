package br.com.serviceordersystem.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Client extends Person implements Serializable {
    private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "client")
    private List<OrderService> orderService = new ArrayList<>();

    public Client() {
        super();
    }

    public Client(Integer id, String name, String cpf, String phone) {
        super(id, name, cpf, phone);
    }



}
