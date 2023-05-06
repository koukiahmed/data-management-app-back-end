package com.attijariLeasing.appBackend.link;

import com.attijariLeasing.appBackend.ApiResponse.Response;
import com.attijariLeasing.appBackend.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LinkService {

    private final LinkRepository linkRepository;

    @Autowired
    public LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public Response addLink(Link link){
        linkRepository.save(link);
        return new Response("Operation ajouter est terminer avec succès", true);
    }

    public List<Link> getLinks(){
        return linkRepository.findAll();
    }

    public Link getLink(Long id){
        Optional<Link> link = linkRepository.findById(id);
        if (link.isPresent()) {
            return link.get();
        }
        throw new ApiRequestException("Lien non trouvé");
    }

    @Transactional
    public Response updateLink(Long id, Link newLink) {
        Link oldLink = linkRepository.findById(id).
                orElseThrow(()-> new ApiRequestException("Lien non trouvé"));

        if (newLink.getTitle() != null && newLink.getTitle().length() > 0 &&
                !Objects.equals(oldLink.getTitle(), newLink.getTitle())){
            oldLink.setTitle(newLink.getTitle());
        }

        if (newLink.getPath() != null && newLink.getPath().length() > 0 &&
                !Objects.equals(oldLink.getPath(), newLink.getPath())) {
            oldLink.setPath(newLink.getPath());
        }
        return new Response("Operation modifier est terminer avec succès", true);
    }

    public Response deleteLink(Long id){
        Optional<Link> link = linkRepository.findById(id);
        if (link.isPresent()) {
            linkRepository.deleteById(id);
            return new Response("Operation supprimer est terminer avec succès", true);
        }
        else {
            throw new ApiRequestException("Lien non trouvé");
        }
    }


}
