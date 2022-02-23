package ca.wonderfish.filmstreamingapi.domains;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Charge {

    @Id
    @GeneratedValue
    private Long id;

    private String currency;
    private String description;
    private String source;
    private Double amount;
    private String customerName;


    public Charge() {
    }

    public Charge(Long id, String currency, String description, String source, Double amount, String customerName) {
        this.id = id;
        this.currency = currency;
        this.description = description;
        this.source = source;
        this.amount = amount;
        this.customerName = customerName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
