package volha.spring.petclinic.services.datajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import volha.spring.petclinic.model.PetType;
import volha.spring.petclinic.repositories.PetTypeRepository;
import volha.spring.petclinic.services.PetTypeService;

import java.util.HashSet;
import java.util.Set;

/*
 *Created by olga on 21.01.20
 */
@Service
@Profile("datajpa")
public class PetTypeJPAService implements PetTypeService {

    private final PetTypeRepository petTypeRepository;

    public PetTypeJPAService(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }


    @Override
    public Set<PetType> findAll() {
        Set<PetType> petTypes = new HashSet<>();
        petTypeRepository.findAll().forEach(petTypes::add);
        return petTypes;
    }

    @Override
    public PetType findById(Long aLong) {
        return petTypeRepository.findById(aLong).orElse(null);
    }

    @Override
    public PetType save(PetType object) {
        return petTypeRepository.save(object);
    }

    @Override
    public void delete(PetType object) {
        petTypeRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        petTypeRepository.deleteById(aLong);
    }
}
