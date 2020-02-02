package volha.spring.petclinic.comtrollers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import volha.spring.petclinic.model.Vet;
import volha.spring.petclinic.services.VetService;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VetsControllerTest {

    @Mock
    VetService vetService;

    @InjectMocks
    VetsController controller;

    Set<Vet> vets;
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        vets = new HashSet<>();
        vets.add(Vet.builder().id(1L).build());
        vets.add(Vet.builder().id(2L).build());
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void listVets() throws Exception{
        when(vetService.findAll()).thenReturn(vets);
        mockMvc.perform(get("/vets/"))
                .andExpect(status().isOk())
                .andExpect(view().name("vets/index"))
                .andExpect(model().attribute("vets", hasSize(2)));
    }

    @Test
    void listVetsByIndex() throws Exception{
        when(vetService.findAll()).thenReturn(vets);
        mockMvc.perform(get("/vets/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("vets/index"))
                .andExpect(model().attribute("vets", hasSize(2)));
    }
}
