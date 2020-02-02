package volha.spring.petclinic.services.datajpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import volha.spring.petclinic.model.Pet;
import volha.spring.petclinic.repositories.PetRepository;
import volha.spring.petclinic.repositories.PetTypeRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetJPAServiceTest {

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    Pet returnPet;

    @InjectMocks
    PetJPAService petJPAService;

    @BeforeEach
    void setUp() {
        returnPet = Pet.builder().id(1L).build();
    }

    @Test
    void findAll() {
        Set<Pet> returnPetSet = new HashSet<>();
        returnPetSet.add(returnPet);
        returnPetSet.add(Pet.builder().id(2L).build());

        when(petRepository.findAll()).thenReturn(returnPetSet);

        Set<Pet> pets = petJPAService.findAll();

        assertNotNull(pets);
        assertEquals(2, pets.size());
    }

    @Test
    void findById() {
        when(petRepository.findById(anyLong())).thenReturn(Optional.of(returnPet));
        Pet pet = petJPAService.findById(1L);
        assertNotNull(pet);
    }

    @Test
    void findByIdNotFound() {
        when(petRepository.findById(anyLong())).thenReturn(Optional.empty());
        Pet pet = petJPAService.findById(1L);
        assertNull(pet);
    }

    @Test
    void save() {
        Pet petToSave = Pet.builder().id(1L).build();

        when(petRepository.save(any())).thenReturn(returnPet);
        Pet savedPet = petJPAService.save(petToSave);
        assertNotNull(savedPet);

        Mockito.verify(petRepository).save(any());
    }

    @Test
    void delete() {
        petJPAService.delete(returnPet);
        verify(petRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        petJPAService.deleteById(1L);
        verify(petRepository, times(1)).deleteById(anyLong());
    }
}
