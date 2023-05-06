package com.attijariLeasing.appBackend.actuality;

import com.attijariLeasing.appBackend.ApiResponse.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "api/actualities")
public class ActualityController {

    private final ActualityService actualityService;

    @Autowired
    public ActualityController(ActualityService actualityService) {
        this.actualityService = actualityService;
    }

    @PostMapping(path = "/add")
    public Response addActuality(@RequestBody Actuality actuality){
        return actualityService.addActuality(actuality);
    }

    @GetMapping(path = "/get")
    public List<Actuality> getActualities(){
        return actualityService.getActualities();
    }

    @GetMapping(path = "/get/{id}")
    public Actuality getActuality(@PathVariable("id") Long id){
        return actualityService.getActuality(id);
    }

    @PutMapping(path = "/update/{id}")
    public Response updateActuality(
            @PathVariable("id") Long id, @RequestBody Actuality actuality){
        return actualityService.updateActuality(id, actuality);
    }

    @DeleteMapping(path = "/delete/{id}")
    public Response deleteActuality(@PathVariable("id") Long id){
        return actualityService.deleteActuality(id);
    }
}
