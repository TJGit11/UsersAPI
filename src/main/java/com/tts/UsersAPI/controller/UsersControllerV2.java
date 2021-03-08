package com.tts.UsersAPI.controller;

import com.tts.UsersAPI.model.UserV1;
import com.tts.UsersAPI.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping("/v2")
@RestController
@Api (description = "This is a description")
public class UsersControllerV2 {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    @ApiOperation(value = "value1" , response = UserV1.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved users"),
            @ApiResponse(code = 400, message = "You are not authorized to view this menu item")})
    public ResponseEntity<List<UserV1>> getUsers(@RequestParam(value = "state", required = true) String state){
        List<UserV1> usersFound;
        if(state != null) {
            usersFound = userRepository.findByState(state);
            if(!usersFound.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(usersFound, HttpStatus.OK);
        }
        usersFound = (List<UserV1>) userRepository.findAll();
        if(usersFound.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usersFound, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    @ApiOperation(value = "value2" , response = UserV1.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved user by id"),
            @ApiResponse(code = 401, message = "You are not authorized to view this menu item")})
    public ResponseEntity<UserV1> getUserById(@PathVariable(value="id")Long id){
        Optional <UserV1> user = userRepository.findById(id);
        if (!user.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<UserV1>(user.get(), HttpStatus.OK);
    }

    @PostMapping("/users")
    @ApiOperation(value = "value3" , response = UserV1.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created user"),
            @ApiResponse(code = 401, message = "You are not authorized to view this menu item")})
    public ResponseEntity<Void> createUser(@RequestBody @Valid UserV1 user, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    @ApiOperation(value = "value4" , response = UserV1.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created user"),
            @ApiResponse(code = 401, message = "You are not authorized to view this menu item")})
    public void createUser(@PathVariable(value="id") Long id, @RequestBody UserV1 user){
        userRepository.save(user);
    }

    @DeleteMapping("/users/{id}")
    @ApiOperation(value = "value5" , response = UserV1.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created user"),
            @ApiResponse(code = 401, message = "You are not authorized to view this menu item")})
    public void createUser(@PathVariable(value = "id") Long id){
        userRepository.deleteById(id);
    }
}
