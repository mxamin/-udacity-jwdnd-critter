package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.entity.Employee;

import javax.persistence.*;
import java.time.DayOfWeek;

@Entity(name = "DayAvailable")
public class DayAvailable {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private DayOfWeek day;

    @ManyToOne
    private Employee employee;
}
