package com.orness.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity {

    @Id
    private UUID id;

    //unique = true
    @Column(nullable = false, length = 300)
    private String mail;

    @Column(nullable = false, length = 200)
    private String firstname;

    @Column(nullable = false, length = 200)
    private String lastname;

    @Column(nullable = false)
    private int age;
}