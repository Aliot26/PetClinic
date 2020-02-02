package volha.spring.petclinic.services.datajpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import volha.spring.petclinic.model.Visit;
import volha.spring.petclinic.repositories.PetRepository;
import volha.spring.petclinic.repositories.PetTypeRepository;
import volha.spring.petclinic.repositories.VisitRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VisitJPAServiceTest {

    @Mock
    VisitRepository visitRepository;

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    VisitJPAService visitJPAService;

    Visit returnVisit;

    @BeforeEach
    void setUp() {

        returnVisit = Visit.builder().id(1L).build();
    }

    @Test
    void findAll() {
        Set<Visit> returnVisitSet = new HashSet<>();
        returnVisitSet.add(returnVisit);
        returnVisitSet.add(Visit.builder().id(2L).build());
        when(visitRepository.findAll()).thenReturn(returnVisitSet);
        Set<Visit> visits = visitJPAService.findAll();
        assertNotNull(visits);
        assertEquals(2,visits.size());
    }

    @Test
    void findById() {
        when(visitRepository.findById(anyLong())).thenReturn(Optional.of(returnVisit));
        Visit visit = visitJPAService.findById(1L);
        assertNotNull(visit);
    }

    @Test
    void findByIdNotFound() {
        when(visitRepository.findById(anyLong())).thenReturn(Optional.empty());
        Visit visit = visitJPAService.findById(1L);
        assertNull(visit);
    }

    @Test
    void save() {
        Visit visitToSave = Visit.builder().id(1L).build();
        when(visitRepository.save(any())).thenReturn(returnVisit);
        Visit savedVisit = visitJPAService.save(visitToSave);
        assertNotNull(savedVisit);

        verify(visitRepository).save(any());
    }

    @Test
    void delete() {
        visitJPAService.delete(returnVisit);
        verify(visitRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        visitJPAService.deleteById(1L);
        verify(visitRepository, times(1)).deleteById(anyLong());
    }
}
