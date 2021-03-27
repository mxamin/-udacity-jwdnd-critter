package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.CustomerEntity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name="Pet")
public class PetEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private PetType type;

    private String name;

    private LocalDate birthDate;

    private String notes;

    @ManyToOne
    private CustomerEntity customer;
}
