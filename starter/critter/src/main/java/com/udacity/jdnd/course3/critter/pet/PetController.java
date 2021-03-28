package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    PetService petService;

    @Autowired
    CustomerService customerService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = petService.savePet(convertPetDTO2Pet(petDTO));

        if (petDTO.getOwnerId() != 0) {
            Customer customer = customerService.findCustomerById(petDTO.getOwnerId());
            customer.getPets().add(pet);
            customerService.saveCustomer(customer);
        }

        return convertPet2PetDTO(pet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return convertPet2PetDTO(petService.findPetById(petId));
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<Pet> pets = petService.findAllPets();
        return pets.stream().map(this::convertPet2PetDTO).collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
         List<Pet> pets = customerService.findCustomerById(ownerId).getPets();
         return pets.stream().map(this::convertPet2PetDTO).collect(Collectors.toList());
    }

    private Pet convertPetDTO2Pet(PetDTO petDTO) {
        Pet pet = modelMapper.map(petDTO, Pet.class);
        if (petDTO.getOwnerId() != 0) {
            Customer customer = customerService.findCustomerById(petDTO.getOwnerId());
            pet.setCustomer(customer);
        }

        return pet;
    }

    private PetDTO convertPet2PetDTO(Pet pet) {
        PetDTO petDTO = modelMapper.map(pet, PetDTO.class);
        if (pet.getCustomer() != null)
            petDTO.setOwnerId(pet.getCustomer().getId());

        return petDTO;
    }
}
