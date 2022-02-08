package ca.wonderfish.filmstreamingapi.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="app_orders")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "customer email can't be blank")
    private String customerEmail;

    private Double total;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "order",orphanRemoval = true)
    @JsonIgnoreProperties({"order"})
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date created_at;

    public Order() {
    }

    @PrePersist
    protected void onCreate(){
        this.created_at = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
