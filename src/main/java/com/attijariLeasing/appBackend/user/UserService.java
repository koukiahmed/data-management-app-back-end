package com.attijariLeasing.appBackend.user;

import com.attijariLeasing.appBackend.ApiResponse.Response;
import com.attijariLeasing.appBackend.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //get all users
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    //test if email or name already exist in database with other user
    public Response addUser(User user){

        Optional<User> userByName = userRepository.findUserByName(user.getName());
        Optional<User> userByEmail = userRepository.findUserByEmail(user.getEmail());

        if (userByName.isPresent()) {
            throw new ApiRequestException("Ce nom d'utilisateur est déjà existe ! essayez avec un autre nom");
        }
        else if(userByEmail.isPresent()){
            throw new ApiRequestException("Ce email est déjà existe ! essayez avec un autre email");
        }
        else {
            userRepository.save(user);
            return new Response("Operation ajouter est terminer avec succès", true);
        }
    }

    //test if user login data are valid and exists in database then back the response to front with his role
    public LoginResponse loginUser(LoginObject loginObject){

        Optional<User> userByEmailAndPassword = userRepository.findUserByEmailAndPassword(loginObject.getEmail(),loginObject.getPassword());

        if(userByEmailAndPassword.isPresent()){
            User user = userByEmailAndPassword.get();
            String role = user.getRole();
            String email = user.getEmail();
            return new LoginResponse("connexion réussie", true, email, role);
        }
        else {
            throw new ApiRequestException("échec de la connexion");
        }
    }
    //test if user exist in database then get it
    public User getUser(Long id){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        throw new ApiRequestException("Utilisateur non trouvé");
    }

    //test if name or email exists in database with other user then check other data from front
    @Transactional
    public Response updateUser(Long id, User newUser) {
        User oldUser = userRepository.findById(id).
                orElseThrow(()-> new ApiRequestException("Utilisateur non trouvé"));

        if (newUser.getName() != null && newUser.getName().length() > 0 &&
                !Objects.equals(oldUser.getName(), newUser.getName())){
            oldUser.setName(newUser.getName());
        }

        if (newUser.getEmail() != null && newUser.getEmail().length() > 0 &&
                !Objects.equals(oldUser.getEmail(), newUser.getEmail())) {
            oldUser.setEmail(newUser.getEmail());
        }

        if (newUser.getPassword() != null && newUser.getPassword().length() > 0 &&
                !Objects.equals(oldUser.getPassword(), newUser.getPassword())) {
            oldUser.setPassword(newUser.getPassword());
        }

        if (newUser.getRole() != null && newUser.getRole().length() > 0 &&
                !Objects.equals(oldUser.getRole(), newUser.getRole())){
            oldUser.setRole(newUser.getRole());
        }
        return new Response("Operation modifier est terminer avec succès", true);
    }

    //test if user exists in database then delete it
    public Response deleteUser(Long id){
        boolean exists = userRepository.existsById(id);
            if (!exists) {
                throw new ApiRequestException("User not found");
            }
            userRepository.deleteById(id);
        return new Response("Operation supprimer est terminer avec succès", true);
    }

}
