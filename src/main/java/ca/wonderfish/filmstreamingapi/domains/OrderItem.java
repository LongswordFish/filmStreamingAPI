package ca.wonderfish.filmstreamingapi.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "film id can not be blank")
    private Long film_id;

    @NotBlank(message = "movie title can not be blank")
    private String movie_title;

    @NotBlank(message = "movie type can not be blank")
    private String type;
    private String movie_type;

    @NotNull(message = "price to rent cannot be blank")
    private Double price_to_rent;

    @NotNull(message = "price to purchase cannot be blank")
    private Double price_to_purchase;

    @Lob
    @Column(columnDefinition = "text")
    private String small_picture;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="order_id",updatable = false,nullable = false)
    @JsonIgnore
    private Order order;

    public OrderItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFilm_id() {
        return film_id;
    }

    public void setFilm_id(Long film_id) {
        this.film_id = film_id;
    }

    public String getMovie_title() {
        return movie_title;
    }

    public void setMovie_title(String movie_title) {
        this.movie_title = movie_title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMovie_type() {
        return movie_type;
    }

    public void setMovie_type(String movie_type) {
        this.movie_type = movie_type;
    }

    public Double getPrice_to_rent() {
        return price_to_rent;
    }

    public void setPrice_to_rent(Double price_to_rent) {
        this.price_to_rent = price_to_rent;
    }

    public Double getPrice_to_purchase() {
        return price_to_purchase;
    }

    public void setPrice_to_purchase(Double price_to_purchase) {
        this.price_to_purchase = price_to_purchase;
    }

    public String getSmall_picture() {
        return small_picture;
    }

    public void setSmall_picture(String small_picture) {
        this.small_picture = small_picture;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
