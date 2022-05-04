package com.example.demo.model.repositories.user;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@Table(
        name = "users",
        schema = "public"
)
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    public Long id;

    @NotEmpty
    @Size(max = 50)
    @Column(length=50, nullable=false)
    public String name;

    @NotEmpty
    @Size(max = 50)
    @Column(length=50, nullable=false)
    public String lastname;

    @NotEmpty
    @Email
    @Column(length=50, nullable=false)
    public String email;

    @NotEmpty
    @Size(max = 50)
    @Column(length=50, nullable=false)
    public String phone;

    @Transient
    @Positive
    public Integer age;

    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    public Date birthdate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public Gender gender;

    public User(
            long id,
            String name,
            String lastname,
            String email,
            String phone,
            Gender gender,
            Date birthdate
    ) {
    }

    public User(
            long id
    ) {
    }

    public User(){

    }
}
