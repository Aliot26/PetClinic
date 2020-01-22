package volha.spring.petclinic.services.datajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import volha.spring.petclinic.model.Speciality;
import volha.spring.petclinic.repositories.SpecialityRepository;
import volha.spring.petclinic.services.SpecialityService;

import java.util.HashSet;
import java.util.Set;

/*
 *Created by olga on 22.01.20
 */
@Service
@Profile("datajpa")
public class SpecialityJPAService implements SpecialityService {

    private final SpecialityRepository specialityRepository;

    public SpecialityJPAService(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }

    @Override
    public Set<Speciality> findAll() {
        Set<Speciality> specialities = new HashSet<>();
        specialityRepository.findAll().forEach(specialities::add);
        return specialities;
    }

    @Override
    public Speciality findById(Long aLong) {
        return specialityRepository.findById(aLong).orElse(null);
    }

    @Override
    public Speciality save(Speciality object) {
        return specialityRepository.save(object);
    }

    @Override
    public void delete(Speciality object) {
        specialityRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        specialityRepository.deleteById(aLong);
    }
}
