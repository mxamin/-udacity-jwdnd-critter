package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        return convertPet2PetDTO(petService.savePet(convertPetDTO2Pet(petDTO)));
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return convertPet2PetDTO(petService.findPetById(petId));
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<Pet> pets = petService.findAllPets();

        List<PetDTO> petDTOS = new ArrayList<>();
        for (Pet pet: pets)
            petDTOS.add(convertPet2PetDTO(pet));

        return petDTOS;
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
         List<Pet> pets = customerService.findCustomerById(ownerId).getPets();

         List<PetDTO> petDTOS = new ArrayList<>();
         for (Pet pet: pets)
             petDTOS.add(convertPet2PetDTO(pet));

         return petDTOS;
    }

    private Pet convertPetDTO2Pet(PetDTO petDTO) {
        Pet pet = new Pet();
        pet.setId(petDTO.getId());
        pet.setType(petDTO.getType());
        pet.setName(petDTO.getName());
        pet.setBirthDate(petDTO.getBirthDate());
        pet.setNotes(petDTO.getNotes());
        if (petDTO.getOwnerId() != 0) {
            Customer customer = customerService.findCustomerById(petDTO.getOwnerId());
            pet.setCustomer(customer);
        }

        return pet;
    }

    private PetDTO convertPet2PetDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        petDTO.setId(pet.getId());
        petDTO.setType(pet.getType());
        petDTO.setName(pet.getName());
        petDTO.setBirthDate(pet.getBirthDate());
        petDTO.setNotes(pet.getNotes());
        if (pet.getCustomer() != null)
            petDTO.setOwnerId(pet.getCustomer().getId());

        return petDTO;
    }
}
