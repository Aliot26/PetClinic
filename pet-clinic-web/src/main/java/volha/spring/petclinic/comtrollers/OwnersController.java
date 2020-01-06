package volha.spring.petclinic.comtrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OwnersController {
    @RequestMapping({"/owners", "/owners/index", "/owners/index.html"})
    public String listVets(){
        return "owners/index";
    }
}
