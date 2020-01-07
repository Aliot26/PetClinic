package volha.spring.petclinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan(basePackages = {"volha.spring.petclinic.services"})
public class PetClinicApplication {


    public static void main(String[] args) {
        SpringApplication.run(PetClinicApplication.class, args);
    }

}

