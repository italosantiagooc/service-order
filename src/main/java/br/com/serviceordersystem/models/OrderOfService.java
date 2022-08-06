package br.com.serviceordersystem.models;

import br.com.serviceordersystem.models.enums.Priority;
import br.com.serviceordersystem.models.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class OrderOfService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime openingDate;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime closingDate;
    private Integer priority;
    private String comments;
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "technician_id")
    private Technician technician;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public OrderOfService() {
        super();
        this.setOpeningDate(LocalDateTime.now());
        this.setPriority(Priority.LOW);
        this.setStatus(Status.OPEN);
    }

    public OrderOfService(Integer id, Priority priority, String comments, Status status, Technician technician,
                          Client client) {
        super();
        this.id = id;
        this.setOpeningDate(LocalDateTime.now());
        this.priority = (priority == null) ? 0 : priority.getCod();
        this.comments = comments;
        this.status = (status == null) ? 0 : status.getCod();
        this.technician = technician;
        this.client = client;
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

    public Priority getPriority() {
        return Priority.toEnum(this.priority);
    }

    public void setPriority(Priority priority) {
        this.priority = priority.getCod();
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Status getStatus() {
        return Status.toEnum(this.status);
    }

    public void setStatus(Status status) {
        this.status = status.getCod();
    }

    public Technician getTechnician() {
        return technician;
    }

    public void setTechnician(Technician technician) {
        this.technician = technician;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderOfService that = (OrderOfService) o;
        return Objects.equals(id, that.id) && Objects.equals(openingDate, that.openingDate) && Objects.equals(closingDate, that.closingDate) && Objects.equals(priority, that.priority) && Objects.equals(comments, that.comments) && Objects.equals(status, that.status) && Objects.equals(technician, that.technician) && Objects.equals(client, that.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, openingDate, closingDate, priority, comments, status, technician, client);
    }
}
