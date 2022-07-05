package com.epam.spring.homework1.pet;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Pet {
    private List<Animal> pets;

    public Pet(List<Animal> animals) {
        this.pets = animals;
    }

    public void printPets() {
        pets.forEach(pet -> System.out.println(pet.getClass().getSimpleName()));
    }
}
