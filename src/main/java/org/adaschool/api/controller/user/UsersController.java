package org.adaschool.api.controller.user;

import org.adaschool.api.exception.UserNotFoundException;
import org.adaschool.api.repository.user.User;
import org.adaschool.api.repository.user.UserDto;
import org.adaschool.api.service.user.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/users/")
public class UsersController {

    private final UsersService usersService;

    public UsersController(@Autowired UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    public ResponseEntity<User> createUser() {
        //TODO implement this method
        URI createdUserUri = URI.create("");
        return ResponseEntity.created(createdUserUri).body(null);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        //TODO implement this method
        return ResponseEntity.ok(usersService.all());
    }

    @GetMapping("{id}")
    public ResponseEntity<User> findById(@PathVariable("id") String id) {
        //TODO implement this method
        try {
            return new ResponseEntity<User>(usersService.findById(id).get(), HttpStatus.OK);
        }catch (Exception e){
            throw new UserNotFoundException(id);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@RequestBody UserDto userDto, @PathVariable("id") String id) {
        User user = new User(userDto);
        Optional<User> usuarioExistente = usersService.findById(id);
        if (!usuarioExistente.isEmpty()) {
            usersService.update(user, id);
            usersService.save(usuarioExistente.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        throw new UserNotFoundException(id);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser() {
        //TODO implement this method
        return ResponseEntity.ok().build();
    }
}
