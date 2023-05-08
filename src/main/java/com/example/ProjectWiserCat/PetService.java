package com.example.ProjectWiserCat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PetService {
@Autowired
    private PetRepository petRepository;
@Autowired
    private AccountService accountService;

    public Pet savePet(Pet pet){
        return petRepository.save(pet);
    }
    public Optional<Pet> findPetByID(int id) {
        return petRepository.findById((long) id);
    }
    public Pet updatePet(Pet pet) {
        return petRepository.save(pet);
    }

    public List<Pet> getAllPetsByToken(UUID token) {
        String userid = accountService.findUserByToken(token);
        return petRepository.findAllByUserIdIs(userid);
    }
}
