package ca.wonderfish.filmstreamingapi.domains;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Email(message="please fill in an email")
    @NotBlank(message = "please fill in an email")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "please fill in your full name")
    private String fullName;

    @NotBlank(message = "password is required")
    private String password;

    @Transient
    private String confirmPassword;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date created_at;

    private String role;

    public User() {
    }

    @PrePersist
    protected void onCreate(){
        this.created_at = new Date();
        this.role="user";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
