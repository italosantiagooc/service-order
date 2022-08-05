package br.com.serviceordersystem.dtos;

import br.com.serviceordersystem.models.Technician;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class TechnicianDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    @NotBlank(message = "The name field is required")
    private String name;
    @CPF
    @NotBlank(message = "The CPF field is required")
    private String cpf;
    @NotBlank(message = "The phone field is required")
    private String phone;

    public TechnicianDto() {
        super();
    }

    public TechnicianDto(Technician technician) {
        this.id = technician.getId();
        this.name = technician.getName();
        this.cpf = technician.getCpf();
        this.phone = technician.getPhone();
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
