package com.udacity.jdnd.course3.critter.user;

import javax.persistence.*;
import java.util.List;

@Entity(name="Employee")
public class EmployeeEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "employee")
    private List<SkillEntity> skills;

    @OneToMany(mappedBy = "employee")
    private List<DayAvailableEntity> daysAvailable;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "book_id"
//    private Set<Book> books = new HashSet<>();

}
