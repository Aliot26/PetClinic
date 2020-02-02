package volha.spring.petclinic.services.datajpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import volha.spring.petclinic.model.Vet;
import volha.spring.petclinic.repositories.VetRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VetJPAServiceTest {

    @Mock
    VetRepository vetRepository;

    @InjectMocks
    VetJPAService vetService;

    Vet returnVet;

    @BeforeEach
    void setUp() {
        returnVet = Vet.builder().id(1L).build();
    }

    @Test
    void findAll() {
        Set<Vet> vetSet = new HashSet<>();
        vetSet.add(returnVet);
        vetSet.add(Vet.builder().id(2L).build());
        when(vetRepository.findAll()).thenReturn(vetSet);
        Set<Vet> vets = vetService.findAll();

        assertNotNull(vets);
        assertEquals(2, vets.size());
    }

    @Test
    void findById() {
        when(vetRepository.findById(anyLong())).thenReturn(Optional.of(returnVet));
        Vet vet = vetService.findById(1L);
        assertNotNull(vet);
    }

    @Test
    void findByIdNotFound() {
        when(vetRepository.findById(anyLong())).thenReturn(Optional.empty());
        Vet vet = vetService.findById(1L);
        assertNull(vet);
    }

    @Test
    void save() {
        Vet vetToSave = Vet.builder().id(1L).build();
        when(vetRepository.save(any())).thenReturn(returnVet);
        Vet savedVet = vetService.save(vetToSave);
        assertNotNull(savedVet);

        verify(vetRepository).save(any());
    }

    @Test
    void delete() {
        vetService.delete(returnVet);
        verify(vetRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        vetService.deleteById(1L);
        verify(vetRepository, times(1)).deleteById(anyLong());
    }
}
