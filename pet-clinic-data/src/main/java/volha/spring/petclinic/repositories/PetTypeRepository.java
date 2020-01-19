package volha.spring.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import volha.spring.petclinic.model.PetType;

/*
 *Created by olga on 19.01.20
 */
public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
