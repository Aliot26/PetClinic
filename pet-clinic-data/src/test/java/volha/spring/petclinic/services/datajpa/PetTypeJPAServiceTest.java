package volha.spring.petclinic.services.datajpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import volha.spring.petclinic.model.PetType;
import volha.spring.petclinic.repositories.PetTypeRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetTypeJPAServiceTest {

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    PetTypeJPAService petTypeJPAService;
    PetType returnPetType;

    @BeforeEach
    void setUp() {
        returnPetType = PetType.builder().id(1l).build();
    }

    @Test
    void findAll() {
        Set<PetType> returnPetTypeSet = new HashSet<>();
        returnPetTypeSet.add(returnPetType);
        returnPetTypeSet.add(PetType.builder().id(2L).build());

        when(petTypeRepository.findAll()).thenReturn(returnPetTypeSet);

        Set<PetType> petTypes = petTypeJPAService.findAll();

        assertNotNull(petTypes);
        assertEquals(2, petTypes.size());
    }

    @Test
    void findById() {
        when(petTypeRepository.findById(anyLong())).thenReturn(Optional.of(returnPetType));
        PetType petType = petTypeJPAService.findById(1L);
        assertNotNull(petType);
    }

    @Test
    void findByIdNotFound() {
        when(petTypeRepository.findById(anyLong())).thenReturn(Optional.empty());
        PetType petType = petTypeJPAService.findById(1L);
        assertNull(petType);
    }

    @Test
    void save() {
        PetType petTypeToSave = PetType.builder().id(1L).build();
        when(petTypeRepository.save(any())).thenReturn(returnPetType);
        PetType petTypeSaved = petTypeJPAService.save(petTypeToSave);
        assertNotNull(petTypeSaved);

        verify(petTypeRepository).save(any());
    }

    @Test
    void delete() {
        petTypeJPAService.delete(returnPetType);

        verify(petTypeRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        petTypeJPAService.deleteById(1l);
        verify(petTypeRepository, times(1)).deleteById(anyLong());
    }
}
