package volha.spring.model;

import java.time.LocalDate;

/**
 * created 05.01.2020
 */
public class Pet extends BaseEntity{
    PetType petType;
    Owner owner;
    LocalDate birthData;

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public LocalDate getBirthData() {
        return birthData;
    }

    public void setBirthData(LocalDate birthData) {
        this.birthData = birthData;
    }
}
