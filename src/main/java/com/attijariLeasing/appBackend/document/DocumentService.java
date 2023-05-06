package com.attijariLeasing.appBackend.document;

import com.attijariLeasing.appBackend.ApiResponse.Response;
import com.attijariLeasing.appBackend.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public Response saveFile(MultipartFile file, String folder) throws IOException {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

            if (file.isEmpty() || folder.length() == 0) {
                throw new ApiRequestException("Le fichier n'est pas valide, vérifiez que le fichier est vide ou que le nom du dossier est manquant");
            }
            else {
                Document document = new Document();
                document.setTitle(fileName);
                document.setType(file.getContentType());
                document.setData(file.getBytes());
                document.setFolder(folder);

                documentRepository.save(document);
                return new Response("Operation ajouter est terminer avec succès", true);
            }
    }

    public Document downloadFile(Long id) throws Exception {
        Optional<Document> result = documentRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new ApiRequestException("Document non trouvé");
    }

    //get all documents custom method in repo with folder name
    public List<Document> getDocuments() {
        return documentRepository.findAll();
    }

    @Transactional
    public Response updateDocument(Long id, ModifyObject newData) {
        Document document = documentRepository.findById(id).
                orElseThrow(() -> new ApiRequestException("Document non trouvé"));

        if (newData.getFolder() != null && newData.getFolder().length() > 0 && !Objects.equals(document.getFolder(), newData.getFolder())) {
            document.setFolder(newData.getFolder());
        }
        return new Response("Operation modifier est terminer avec succès", true);
    }

    public Response deleteDocument(Long id) {
        Optional<Document> document = documentRepository.findById(id);
        if (document.isPresent()) {
            documentRepository.deleteById(id);
            return new Response("Operation supprimer est terminer avec succès", true);
        } else {
            throw new ApiRequestException("Document non trouvé");
        }
    }

}






