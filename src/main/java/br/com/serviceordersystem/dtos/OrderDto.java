package br.com.serviceordersystem.dtos;

import br.com.serviceordersystem.models.Order;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

public class OrderDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime openingDate;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime closingDate;
    private Integer priority;
    @NotBlank(message = "The comments field is required")
    private String comments;
    private Integer status;
    private Integer technician;
    private Integer client;

    public OrderDto() {
        super();
    }

    public OrderDto(Order orderOfService) {
        super();
        this.id = orderOfService.getId();
        this.openingDate = orderOfService.getOpeningDate();
        this.closingDate = orderOfService.getClosingDate();
        this.priority = orderOfService.getPriority().getCod();
        this.comments = orderOfService.getComments();
        this.status = orderOfService.getStatus().getCod();
        this.technician = orderOfService.getTechnician().getId();
        this.client = orderOfService.getClient().getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDateTime openingDate) {
        this.openingDate = openingDate;
    }

    public LocalDateTime getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(LocalDateTime closingDate) {
        this.closingDate = closingDate;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTechnician() {
        return technician;
    }

    public void setTechnician(Integer technician) {
        this.technician = technician;
    }

    public Integer getClient() {
        return client;
    }

    public void setClient(Integer client) {
        this.client = client;
    }
}
