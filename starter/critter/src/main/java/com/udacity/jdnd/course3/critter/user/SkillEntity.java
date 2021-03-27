package com.udacity.jdnd.course3.critter.user;

import javax.persistence.*;

@Entity(name="Skill")
public class SkillEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private EmployeeSkill name;

    @ManyToOne
    private EmployeeEntity employee;
}
