package com.udacity.jdnd.course3.critter.entity;

import javax.persistence.*;
import java.util.List;

@Entity(name="Employee")
public class Employee {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "employee")
    private List<Skill> skills;

    @OneToMany(mappedBy = "employee")
    private List<DayAvailable> daysAvailable;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "book_id"
//    private Set<Book> books = new HashSet<>();

}
