package ludopia.controller;

import ludopia.objects.associations.Association;
import ludopia.objects.associations.service.AssociationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
    private AssociationService assoService;
    public ApiController(AssociationService assoService){
        this.assoService = assoService;
    }
    @GetMapping("/assoList")
    public Iterable<Association> getAllAsso(){
        return this.assoService.getAll();
    }
}
