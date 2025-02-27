package com.shelved.shelved.users;

import com.shelved.shelved.collections.Collection;
import com.shelved.shelved.items.Item;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true)
  private Integer id;

  @Column(name = "username", unique = true)
  private String username;

  @Column(name = "password")
  private String password;

  @Column(name = "imageUrl")
  private String imageUrl;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Collection> collections;

  public User() {
  }

  public User(Integer id, String username, String password, String imageUrl, List<Collection> collections) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.imageUrl = imageUrl;
    this.collections = collections;
  }

  public Integer getId() { return id; }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public List<Collection> getCollections() { return collections; }

  public void setCollections(List<Collection> collections) { this.collections = collections; }

}
