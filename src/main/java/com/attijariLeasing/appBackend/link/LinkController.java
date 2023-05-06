package com.attijariLeasing.appBackend.link;

import com.attijariLeasing.appBackend.ApiResponse.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "api/links")
public class LinkController {

    private final LinkService linkService;

    @Autowired
    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping(path = "/add")
    public Response addLink(@RequestBody Link link) {
        return linkService.addLink(link);
    }

    @GetMapping(path = "/get")
    public List<Link> getLinks(){
        return linkService.getLinks();
    }

    @GetMapping(path = "/get/{id}")
    public Link getLink (@PathVariable("id") Long id){
        return linkService.getLink(id);
    }

    @PutMapping(path = "/update/{id}")
    public Response updateLink(
            @PathVariable("id") Long id, @RequestBody Link link){
        return linkService.updateLink(id, link);
    }

    @DeleteMapping(path = "/delete/{id}")
    public Response deleteLink(@PathVariable("id") Long id){
        return linkService.deleteLink(id);
    }


}
