package com.attijariLeasing.appBackend.document;

import com.attijariLeasing.appBackend.ApiResponse.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "api/documents")
public class DocumentController {

    private final DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    //upload file with folder name passing to param then save it to database
    @PostMapping("/upload/{folder}")
    public Response uploadFile(@PathVariable String folder, @RequestParam("file") MultipartFile file) throws Exception {

        return documentService.saveFile(file,folder);
    }

    //get document with id if the url with id then it will download file auto and directly
    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("id") Long id , HttpServletResponse response) throws Exception {
        Document document = documentService.downloadFile(id);

        return  ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(document.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + document.getTitle()
                                + "\"")
                .body(new ByteArrayResource(document.getData()));
    }


    //get all documents custom method in repo with folder name
    @GetMapping(path = "/get")
    public List<Document> getDocuments(){
        return documentService.getDocuments();
    }


    @PutMapping(path = "/update/{id}")
    public Response updateDocument(
            @PathVariable("id") Long id, @RequestBody ModifyObject newData){
        return documentService.updateDocument(id,newData);
    }

    @DeleteMapping(path = "/delete/{id}")
    public Response deleteDocument(@PathVariable("id") Long id){
        return documentService.deleteDocument(id);
    }


}
