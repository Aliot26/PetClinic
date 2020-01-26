package volha.spring.petclinic.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * created 05.01.2020
 */
@Setter
@Getter
@MappedSuperclass
public class Person extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

}
