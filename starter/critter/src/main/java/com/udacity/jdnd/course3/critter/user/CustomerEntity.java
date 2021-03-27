package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.PetEntity;

import javax.persistence.*;
import java.util.List;

@Entity(name="Customer")
public class CustomerEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String phoneNumber;

    private String notes;

    @OneToMany(mappedBy = "customer")
    private List<PetEntity> pets;
}
