package com.example.ProjectWiserCat;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class Controller {
    @Autowired
    private PetService petService;
    @Autowired
    private AccountService accountService;


    @GetMapping("/api/getpetlist")
    public List<Pet> getAllPets(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        return petService.getAllPetsByToken(UUID.fromString(token));
    }


    @PostMapping("/api/addpet")
    public Pet createPet(@RequestBody Pet pet, @RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        pet.setUserId(accountService.findUserByToken(UUID.fromString(token)));
        return petService.savePet(pet);
    }


    @GetMapping("/api/pet/{id}")
    public Optional<Pet> getPetById(@PathVariable int id) {
        return petService.findPetByID(id);
    }

    @PutMapping("/api/pet/{id}")
    public Pet updatePet(@PathVariable Long id, @RequestBody Pet pet, HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        String userid = accountService.findUserByToken(UUID.fromString(token));
        pet.setPetId(id);
        pet.setUserId(userid);
        return petService.updatePet(pet);
    }

    @PostMapping("/api/login")
    public ResponseEntity<Object> login(@RequestBody Account appuser) {
        boolean canLogIn = accountService.isAccount(appuser);
        if (canLogIn) {
            UUID uniqueString = UUID.randomUUID();
            appuser.setToken(uniqueString);
            accountService.updateToken(appuser.getUsername(), uniqueString);
            LoginResponse response = new LoginResponse();
            response.setStatus("success");
            response.setMessage("User logged in successfully");
            response.setUsername(appuser.getUsername());
            response.setUserToken(uniqueString.toString()); // tagastame ka unikaalse tokeni
            return ResponseEntity.ok(response);
        } else {
            LoginResponse response = new LoginResponse();
            response.setStatus("error");
            response.setMessage("Invalid credentials");
            response.setUsername(appuser.getUsername());
            return ResponseEntity.ok(response);
        }
    }
}
