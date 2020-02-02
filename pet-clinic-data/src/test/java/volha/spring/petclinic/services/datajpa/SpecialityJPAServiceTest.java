package volha.spring.petclinic.services.datajpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import volha.spring.petclinic.model.Speciality;
import volha.spring.petclinic.repositories.SpecialityRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialityJPAServiceTest {

    @Mock
    SpecialityRepository specialityRepository;

    @InjectMocks
    SpecialityJPAService specialityJPAService;

    Speciality returnSpeciality;

    @BeforeEach
    void setUp() {
        returnSpeciality = Speciality.builder().id(1L).build();
    }

    @Test
    void findAll() {
        Set<Speciality> specialitySet = new HashSet<>();
        specialitySet.add(returnSpeciality);
        specialitySet.add(Speciality.builder().id(2L).build());

        when(specialityRepository.findAll()).thenReturn(specialitySet);
        Set<Speciality> specialitySetReturn = specialityJPAService.findAll();

        assertNotNull(specialitySetReturn);
        assertEquals(2,specialitySetReturn.size());
    }

    @Test
    void findById() {
        when(specialityRepository.findById(anyLong())).thenReturn(Optional.of(returnSpeciality));
        Speciality speciality = specialityJPAService.findById(1L);
        assertNotNull(speciality);
    }

    @Test
    void findByIdNotFound() {
        when(specialityRepository.findById(anyLong())).thenReturn(Optional.empty());
        Speciality speciality = specialityJPAService.findById(1L);
        assertNull(speciality);
    }
    @Test
    void save() {
        Speciality specialityToSave = Speciality.builder().id(1L).build();
        when(specialityRepository.save(any())).thenReturn(returnSpeciality);
        Speciality savedSpeciality = specialityJPAService.save(specialityToSave);
        assertNotNull(savedSpeciality);

        verify(specialityRepository).save(any());
    }

    @Test
    void delete() {
        specialityJPAService.delete(returnSpeciality);
        verify(specialityRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        specialityJPAService.deleteById(anyLong());
        verify(specialityRepository, times(1)).deleteById(anyLong());
    }
}
