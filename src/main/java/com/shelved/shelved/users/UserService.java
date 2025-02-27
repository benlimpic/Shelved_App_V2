package com.shelved.shelved.users;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

import com.shelved.shelved.collections.Collection;

import com.shelved.shelved.collections.CollectionRepository;
import org.antlr.v4.runtime.tree.pattern.ParseTreePattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserService {

  private final UserRepository userRepository;
  private final CollectionRepository collectionRepository;

  @Autowired
  public UserService(UserRepository userRepository, CollectionRepository collectionRepository) {

    this.userRepository = userRepository;
    this.collectionRepository = collectionRepository;
  }

  //Get All Users
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  //Get Users By ID
  public User findById(Integer id) {
    Optional<User> user = userRepository.findById(id);

    if (user.isPresent()) {
      return user.get();
    } else {
      return null;
    }
  }

  // Get User By Username
  public User findByUsername(String username) {
    Optional<User> optionalUser = userRepository.findAll().stream().filter(user -> user.getUsername().equals(username)).findFirst();
    if (optionalUser.isPresent()) {
      return optionalUser.get();
    } else {
      return null;
    }
  }

  // Add User
  public User addUser(User user) {
    Optional<User> userOptional = userRepository.findById(user.getId());
    if (userOptional.isPresent()) {
      return null;
    }
    return userRepository.save(user);
  }

  // Update User
  public User updateUser(User updatedUser) {
    Optional<User> existingUser = userRepository.findById(updatedUser.getId());

    if (existingUser.isPresent()) {
      User user = existingUser.get();
      user.setUsername(updatedUser.getUsername());
      user.setPassword(updatedUser.getPassword());
      user.setImageUrl(updatedUser.getImageUrl());
      return userRepository.save(user);
    } else {
      return null;
    }
  }

  // Delete User
  public void deleteUser(Integer id) {
  userRepository.deleteById(id);
  }
}
