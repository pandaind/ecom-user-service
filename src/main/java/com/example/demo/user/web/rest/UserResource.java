package com.example.demo.user.web.rest;

import com.example.demo.user.service.UserService;
import com.example.demo.user.service.dto.UserDTO;
import com.example.demo.user.web.rest.errors.BadRequestAlertException;
import com.example.demo.user.web.rest.util.HeaderUtil;
import com.example.demo.user.web.rest.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserResource {

  private final UserService service;
  private static final String ENTITY_NAME = "User";

  @Value("${spring.application.name}")
  private String applicationName;

  @Autowired
  public UserResource(UserService service) {
    this.service = service;
  }

  /**
   * {@code GET /users}: get all the users
   *
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of users in body.
   */
  @GetMapping("/users")
  public ResponseEntity<List<UserDTO>> getAllUsers() {
    log.debug("REST request to get all users");
    var users = service.findAll();
    return ResponseEntity.ok().body(users);
  }

  /**
   * {@code GET /users?name=:name}: get the "name" user.
   *
   * @param name the name of the userDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userDTO, or
   *     with status {@code 404 (not found)}.
   */
  @GetMapping(
      value = "/users",
      params = {"name"})
  public ResponseEntity<UserDTO> getUserByName(@RequestParam("name") String name) {
    log.debug("REST request to get user : {}", name);
    Optional<UserDTO> user = service.findOneByName(name);
    return ResponseUtil.wrapNotFound(user);
  }

  /**
   * {@code GET /users/:id}: get the "id" user.
   *
   * @param id the id of the userDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userDTO, or
   *     with status {@code 404 (not found)}.
   */
  @GetMapping(value = "/users/{id}")
  public ResponseEntity<UserDTO> getUserById(@PathParam("id") Long id) {
    log.debug("REST request to get user : {}", id);
    Optional<UserDTO> user = service.findOne(id);
    return ResponseUtil.wrapNotFound(user);
  }

  /**
   * {@code POST /users} : Create a new user.
   *
   * @param user the userDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new
   *     userDTO, or with status {@code 400 (Bad Request)} if the person has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/users")
  public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO user) throws URISyntaxException {
    log.debug("REST request to save User : {}", user);
    if (user.getId() != null) {
      throw new BadRequestAlertException(
          "A new person cannot already have an ID", ENTITY_NAME, "idexists");
    }
    UserDTO result = service.save(user);
    return ResponseEntity.created(new URI("/api/users/" + result.getId()))
        .headers(
            HeaderUtil.createEntityCreationAlert(
                applicationName, false, ENTITY_NAME, result.getId().toString()))
        .body(result);
  }
}
