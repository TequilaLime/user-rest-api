package com.homework.controller;

import com.homework.entity.User;
import com.homework.exception.BadRequestException;
import com.homework.exception.NotFoundException;
import com.homework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    /**
     * Note: Additional filtering can be added in future by providing query params, for instance, /users?fistName=SomeFirstName
     */
    @RequestMapping(method = RequestMethod.GET, value = {"/users", "/users/{userId}"})
    public ResponseEntity<?> getUsers(@PathVariable Optional<Long> userId) {
        if (!userId.isPresent()) {
            return getResponse(HttpStatus.OK, userService.getAllRecords());
        }
        Optional<User> rate = Optional.ofNullable(userService.getById(userId.get()));
        if (!rate.isPresent()) {
            throw new NotFoundException("The record of user id is not found: " + userId.get());
        }

        return getResponse(HttpStatus.OK, rate.get());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
        user = userService.register(user);
        return getResponse(HttpStatus.OK, user);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/users/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId,
                                                   @Valid @RequestBody User user) {
        if (user.getId() == null) {
            throw new BadRequestException("The body is missing id");
        }
        if (!userId.equals(user.getId())) {
            throw new BadRequestException("The id in request path and in body not the same");
        }
        user = userService.update(user);

        return getResponse(HttpStatus.OK, user);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{userId}")
    public ResponseEntity<?> removeUser(@PathVariable Long userId) {
        Optional<User> record = Optional.ofNullable(userService.getById(userId));
        if (!record.isPresent()) {
            throw new NotFoundException("The record of user id is not found: " + userId);
        }
        userService.delete(record.get());
        return getResponse(HttpStatus.OK, "{\"status\":\"Deleted\"}");
    }

    private ResponseEntity getResponse(HttpStatus status, Object body) {
        return ResponseEntity.status(status).body(body);
    }
}
