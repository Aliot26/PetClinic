package volha.spring.petclinic.services;

import volha.spring.petclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

    }
