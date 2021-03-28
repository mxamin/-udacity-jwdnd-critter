package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ScheduleService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ScheduleRepository scheduleRepository;

    public Schedule saveSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> findAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> findAllSchedulesByPetId(Long petId) {
        return scheduleRepository.findAllByPetsId(petId);
    }

    public List<Schedule> findAllSchedulesByEmployeeId(Long employeeId) {
        return scheduleRepository.findAllByEmployeesId(employeeId);
    }

    public List<Schedule> findAllSchedulesByCustomerId(Long customerId) {
        Set<Schedule> schedules = new HashSet<>();

        Customer customer = customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
        for (Pet pet: customer.getPets()) {
            schedules.addAll(findAllSchedulesByPetId(pet.getId()));
        }

        return new ArrayList<>(schedules);
    }


}
