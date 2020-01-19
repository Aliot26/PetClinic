package volha.spring.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import volha.spring.petclinic.model.Pet;

/*
 *Created by olga on 19.01.20
 */
public interface PetRepository extends CrudRepository<Pet, Long> {
}
