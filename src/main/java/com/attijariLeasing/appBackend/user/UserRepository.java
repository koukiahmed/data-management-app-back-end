package com.attijariLeasing.appBackend.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

    //add another custom method to repo for find user with his name
    Optional<User> findUserByName(String name);

    //add another custom method to repo for find user with his email
    Optional<User> findUserByEmail(String email);

    //add another custom method to repo for login object
    Optional<User> findUserByEmailAndPassword(String name,String password);
}
