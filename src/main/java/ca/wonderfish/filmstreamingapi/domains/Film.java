package ca.wonderfish.filmstreamingapi.domains;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Film {

    @Id
    @GeneratedValue
    private Long id;

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

    @Lob
    @Column(columnDefinition = "text")
    private String large_picture;

    @Lob
    @Column(columnDefinition = "text")
    private String description;
    private Boolean featured;


    public Film() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getLarge_picture() {
        return large_picture;
    }

    public void setLarge_picture(String large_picture) {
        this.large_picture = large_picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getFeatured() {
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }
}
