package br.com.serviceordersystem.dtos;

import br.com.serviceordersystem.models.Client;
import br.com.serviceordersystem.models.Technician;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class ClientDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    @NotBlank(message = "The name field is required")
    private String name;
    @CPF
    @NotBlank(message = "The CPF field is required")
    private String cpf;
    @NotBlank(message = "The phone field is required")
    private String phone;

    public ClientDto() {
        super();
    }

    public ClientDto(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.cpf = client.getCpf();
        this.phone = client.getPhone();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
