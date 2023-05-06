package com.attijariLeasing.appBackend.user;

import com.attijariLeasing.appBackend.ApiResponse.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/get")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping(path = "/get/{id}")
    public User getUser(@PathVariable("id") Long id){
        return userService.getUser(id);
    }

    @PostMapping(path = "/save")
    public Response addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @PostMapping(path = "/login")
    public LoginResponse loginUser(@RequestBody LoginObject loginObject){
        LoginResponse loginResponse= userService.loginUser(loginObject);
        return loginResponse;
    }

    @PutMapping(path = "/update/{id}")
    public Response updateUser(
            @PathVariable("id") Long id, @RequestBody User user){
            return userService.updateUser(id, user);
    }

    @DeleteMapping(path = "/delete/{id}")
    public Response deleteUser(@PathVariable("id") Long id){
        return userService.deleteUser(id);
    }
}
