package com.attijariLeasing.appBackend.actuality;

import com.attijariLeasing.appBackend.ApiResponse.Response;
import com.attijariLeasing.appBackend.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ActualityService {

    private final ActualityRepository actualityRepository;

    @Autowired
    public ActualityService(ActualityRepository actualityRepository) {
        this.actualityRepository = actualityRepository;
    }

    public Response addActuality(Actuality actuality){
        actualityRepository.save(actuality);
        return new Response("Operation ajouter est terminer avec succès", true);
    }
    public List<Actuality> getActualities(){
        return actualityRepository.findAll();
    }

    public Actuality getActuality(Long id){
        Optional<Actuality> actuality = actualityRepository.findById(id);
        if (actuality.isPresent()) {
            return actuality.get();
        }
        throw new ApiRequestException("Actualité non trouvé");
    }

    @Transactional
    public Response updateActuality(Long id, Actuality newActuality) {
        Actuality oldActuality = actualityRepository.findById(id).
                orElseThrow(()-> new ApiRequestException("Actualité non trouvé"));

        if (newActuality.getTitle() != null && newActuality.getTitle().length() > 0 &&
                !Objects.equals(oldActuality.getTitle(), newActuality.getTitle())){
            oldActuality.setTitle(newActuality.getTitle());
        }

        if (newActuality.getLink() != null && newActuality.getLink().length() > 0 &&
                !Objects.equals(oldActuality.getLink(), newActuality.getLink())) {
            oldActuality.setLink(newActuality.getLink());
        }

        if (newActuality.getBody() != null && newActuality.getBody().length() > 0 &&
                !Objects.equals(oldActuality.getBody(), newActuality.getBody())){
            oldActuality.setBody(newActuality.getBody());
        }
        return new Response("Operation modifier est terminer avec succès", true);

    }

    public Response deleteActuality(Long id){
        Optional<Actuality> actuality = actualityRepository.findById(id);
        if (actuality.isPresent()) {
            actualityRepository.deleteById(id);
            return new Response("Operation supprimer est terminer avec succès", true);
        }
        else {
            throw new ApiRequestException("Actualité non trouvé");
        }
    }

}
