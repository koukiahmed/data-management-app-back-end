package com.attijariLeasing.appBackend.folder;

import com.attijariLeasing.appBackend.ApiResponse.Response;
import com.attijariLeasing.appBackend.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FolderService {

    private final FolderRepository folderRepository;

    @Autowired
    public FolderService(FolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

    public Response addFolder(Folder folder){
        folderRepository.save(folder);
        return new Response("Operation ajouter est terminer avec succès", true);
    }
    public List<Folder> getFolders(){
        return folderRepository.findAll();
    }

    public Folder getFolder(Long id){
        Optional<Folder> folder = folderRepository.findById(id);
        if (folder.isPresent()) {
            return folder.get();
        }
        throw new ApiRequestException("Dossier non trouvé");
    }

    @Transactional
    public Response updateFolder(Long id, Folder newFolder) {
        Folder oldFolder = folderRepository.findById(id).
                orElseThrow(()-> new ApiRequestException("Dossier non trouvé"));

        if (newFolder.getTitle() != null && newFolder.getTitle().length() > 0 &&
                !Objects.equals(oldFolder.getTitle(), newFolder.getTitle())){
            oldFolder.setTitle(newFolder.getTitle());
        }

        if (newFolder.getUserName() != null && newFolder.getUserName().length() > 0 &&
                !Objects.equals(oldFolder.getUserName(), newFolder.getUserName())) {
            oldFolder.setUserName(newFolder.getUserName());
        }
        return new Response("Operation modifier est terminer avec succès", true);
    }

    public Response deleteFolder(Long id){
        Optional<Folder> folder = folderRepository.findById(id);
        if (folder.isPresent()) {
            folderRepository.deleteById(id);
            return new Response("Operation supprimer est terminer avec succès", true);
        }
        else {
            throw new ApiRequestException("Dossier non trouvé");
        }
    }


}
