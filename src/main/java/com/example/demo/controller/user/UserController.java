package com.example.demo.controller.user;

import com.example.demo.model.repositories.user.User;
import com.example.demo.model.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping({"/", ""})
    public ResponseEntity<List<User>> Index () {
        try {
            List<User> users = userService.findAll();

            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping({"/{userId}/", "/{userId}"})
    public ResponseEntity<User> FindById (
            @PathVariable Long userId
    ) {
        try {
            User user = userService.findById(userId);

            if (user == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(user);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @PostMapping({"/", ""})
    public ResponseEntity<User> Create (
            @RequestBody User user
    ) {
        try {
            if (user.id != null) {
                User foundUser = userService.findById(user.id);

                if (foundUser != null) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).build();
                }
            }

            User newUser = userService.create(user);

            URI location = URI.create("/users/" + user);

            return ResponseEntity.created(location).body(newUser);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @PatchMapping({"/{userId}","/{userId}"})
    public ResponseEntity<User> Update (
            @PathVariable Long userId,
            @RequestBody User user
    ) {
        try {
            User foundUser = userService.findById(userId);

            if (foundUser == null) {
                return ResponseEntity.notFound().build();
            }

            user.id = userId;

            User updatedUser = userService.update(user);

            return ResponseEntity.ok(updatedUser);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @DeleteMapping({"/{userId}","/{userId}"})
    public ResponseEntity<User> Delete (
            @PathVariable Long userId
    ) {
        try {
            boolean result = userService.delete(userId);

            if (!result) {
                throw new Exception();
            }

            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }  catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }

    }
}
