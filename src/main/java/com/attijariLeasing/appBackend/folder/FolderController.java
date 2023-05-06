package com.attijariLeasing.appBackend.folder;

import com.attijariLeasing.appBackend.ApiResponse.Response;
import com.attijariLeasing.appBackend.link.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "api/folders")
public class FolderController {

    private final FolderService folderService;

    @Autowired
    public FolderController(FolderService folderService) {
        this.folderService = folderService;
    }

    @PostMapping(path = "/add")
    public Response addFolder(@RequestBody Folder folder){
        return folderService.addFolder(folder);
    }

    @GetMapping(path = "/get")
    public List<Folder> getFolders(){
        return folderService.getFolders();
    }

    @GetMapping(path = "/get/{id}")
    public Folder getLibrary(@PathVariable("id") Long id){
        return folderService.getFolder(id);
    }

    @PutMapping(path = "/update/{id}")
    public Response updateLink(
            @PathVariable("id") Long id, @RequestBody Folder folder){
        return folderService.updateFolder(id, folder);
    }

    @DeleteMapping(path = "/delete/{id}")
    public Response deleteLink(@PathVariable("id") Long id){
        return folderService.deleteFolder(id);
    }

}
