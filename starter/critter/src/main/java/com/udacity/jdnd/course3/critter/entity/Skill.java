package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;

@Entity(name="Skill")
public class Skill {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private EmployeeSkill name;

    @ManyToOne
    private Employee employee;
}
