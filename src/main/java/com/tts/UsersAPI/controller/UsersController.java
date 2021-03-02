package com.tts.UsersAPI.controller;

import com.tts.UsersAPI.model.User;
import com.tts.UsersAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class UsersController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getUsers(@RequestParam(value = "state", required = false) String state){
        if(state != null) {
            return (List<User>) userRepository.findByState(state);
        }
        return (List<User>) userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value="id")Long id){
        Optional <User> user = userRepository.findById(id);
        if (!user.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user.get(), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<Void> createUser(@RequestBody @Valid User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public void createUser(@PathVariable(value="id") Long id, @RequestBody User user){
        userRepository.save(user);
    }

    @DeleteMapping("/users/{id}")
    public void createUser(@PathVariable(value = "id") Long id){
        userRepository.deleteById(id);
    }
}
