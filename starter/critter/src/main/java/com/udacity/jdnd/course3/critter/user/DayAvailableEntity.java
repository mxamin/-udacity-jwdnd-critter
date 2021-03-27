package com.udacity.jdnd.course3.critter.user;

import javax.persistence.*;
import java.time.DayOfWeek;

@Entity(name = "DayAvailable")
public class DayAvailableEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private DayOfWeek day;

    @ManyToOne
    private EmployeeEntity employee;
}
