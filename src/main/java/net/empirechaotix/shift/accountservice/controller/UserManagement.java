package net.empirechaotix.shift.accountservice.controller;

import net.empirechaotix.shift.accountservice.dao.model.User;
import net.empirechaotix.shift.accountservice.dao.repositories.UserRepository;
import net.empirechaotix.shift.accountservice.model.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/v1/usermanagement/")
public class UserManagement {

    private UserRepository userRepository;

    @Autowired
    public UserManagement(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("")
    Resource<Wrapper> createUser(@RequestBody User user) {

        Wrapper response = new Wrapper();

        user = userRepository.save(user);

        response.getUsers().add(user);

        return new Resource<>(response);
    }

    @DeleteMapping("/{id}")
    Resource<Wrapper> deleteStock(@PathVariable String id) {

        Wrapper response = new Wrapper();

        List<User> userList = userRepository.findAllById(Long.parseLong(id));

        if(userList.size() > 1) {
            //should not delete more than one item, this should never happen though, since id's should be unique
            return null;
        }

        //should only have one item
        for(User user : userList) {
            response.getUsers().add(user);
            userRepository.delete(user);
        }

        return new Resource<>(response);
    }


    @GetMapping("")
    Resource<Wrapper> getAll() {
        Wrapper response = new Wrapper();

        List<User> userList = userRepository.findAll();

        //should only have one item
        for(User user : userList) {
            response.getUsers().add(user);
        }

        return new Resource<>(response);
    }

    @GetMapping("/{id}")
    Resource<Wrapper> getById(@PathVariable String id) {
        Wrapper response = new Wrapper();

        List<User> userList = userRepository.findAllById(Long.parseLong(id));

        //should only have one item
        for(User user : userList) {
            response.getUsers().add(user);
        }

        return new Resource<>(response);
    }

}
