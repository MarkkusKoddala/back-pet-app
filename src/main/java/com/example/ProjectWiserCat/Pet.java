package com.example.ProjectWiserCat;


import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

@Entity
@SequenceGenerator(name = "pet_seq", sequenceName = "pet_seq", allocationSize = 1, initialValue = 3)
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pet_seq")
    private Long petId;
    private String petName;

    private String petCode;

    private String petType;

    private String petFurColor;

    private String petCountry;
    private String userId;


    public Pet(Long petId, String petName, String petCode, String petType, String petFurColor, String petCountry, String userId) {
        this.petId = petId;
        this.petName = petName;
        this.petCode = petCode;
        this.petType = petType;
        this.petFurColor = petFurColor;
        this.petCountry = petCountry;
        this.userId = userId;
    }

    public Pet() {
    }

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetCode() {
        return petCode;
    }

    public void setPetCode(String petCode) {
        this.petCode = petCode;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public String getPetFurColor() {
        return petFurColor;
    }

    public void setPetFurColor(String petFurColor) {
        this.petFurColor = petFurColor;
    }

    public String getPetCountry() {
        return petCountry;
    }

    public void setPetCountry(String petCountry) {
        this.petCountry = petCountry;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}