package com.spideo.auction.controller;

import com.spideo.auction.dto.UserDTO;
import com.spideo.auction.entities.User;
import com.spideo.auction.error.CustomErrorType;
import com.spideo.auction.exceptions.UserNotFoundException;
import com.spideo.auction.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * The type User controller.
 */
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * Create a user.
     *
     * @param userDTO the user to be created
     * @return the user created
     */
    @PostMapping("")
    public ResponseEntity createUser(@RequestBody final UserDTO userDTO) {
        if (userDTO == null) {
            return ResponseEntity.badRequest().body("Cannot create user with empty fields");
        }

        Optional<User> userStored = userRepository.findByName(userDTO.getUserName());
        if (userStored.isPresent()) {
            return new ResponseEntity(new CustomErrorType("Unable to create. A user with name "
                    + userDTO.getUserName() + " already exist."), HttpStatus.CONFLICT);
        }
        User user = new User();
        user.setUserName(userDTO.getUserName());

        User createdUser = userRepository.save(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    /**
     * Retrieve all users.
     *
     * @return the user list
     */
    @GetMapping("")
    public ResponseEntity getAllUsers() {
        ResponseEntity responseEntity = null;
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            responseEntity = ResponseEntity.noContent().build();
        } else {
            responseEntity = ResponseEntity.ok(users);
        }
        return responseEntity;
    }

    /**
     * Delete a user.
     *
     * @param id the auction id
     * @return the http response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable(value = "id") final Long id) {
        User user =
                userRepository
                        .findById(id).orElseThrow(() -> new UserNotFoundException(id));
        userRepository.delete(user);
        return ResponseEntity.ok("auction " + id + " successfully deleted");
    }
}
