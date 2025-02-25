package com.shelved.shelved.users;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User> getUsers() {
      return userService.getAllUsers();
    }

    @GetMapping
    public User getUser(
          @RequestParam(required = false) Integer id,
          @RequestParam(required = false) String username
      ) {
      if (id != null) {
        return userService.findById(id);
      } else if (username != null) {
        return userService.findByUsername(username);
      } else {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
      }
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
      User newUser = userService.addUser(user);
      return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
      User resultUser = userService.updateUser(user);
      if (resultUser != null) {
        return new ResponseEntity<>(resultUser, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
      User user = userService.findById(id);
      if (user != null) {
        userService.deleteUser(id);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
      }
      return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }
}
