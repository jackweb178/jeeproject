package cours.jee.m1gl.api.controlleur;

import cours.jee.m1gl.api.dao.IService;
import cours.jee.m1gl.api.model.Service;
import cours.jee.m1gl.api.service.IServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    private IServiceService iServiceService;

    /*@Autowired
    private IService iService;*/

    @PreAuthorize("hasAuthority('ROLE_MEDECIN')")
    @PostMapping("/add")
    public @ResponseBody Service add(@RequestBody Service service )
    {
        return iServiceService.save(service);
    }

    @PreAuthorize("hasAuthority('ROLE_MEDECIN') or hasAuthority('ROLE_SECRETAIRE')")
    @GetMapping("/all")
    public @ResponseBody List<Service> findAll(){
        return  iServiceService.findAll();
    }
}
