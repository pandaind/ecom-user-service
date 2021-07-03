package com.example.demo.user.web.rest;

import com.example.demo.user.service.UserService;
import com.example.demo.user.service.dto.UserDTO;
import com.example.demo.user.web.rest.errors.BadRequestAlertException;
import com.example.demo.user.web.rest.util.HeaderUtil;
import java.net.URI;
import java.net.URISyntaxException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class RegistrationResource {
  private final UserService service;

  private static final String ENTITY_NAME = "User";

  @Value("${spring.application.name}")
  private String applicationName;

  @Autowired
  public RegistrationResource(UserService service) {
    this.service = service;
  }

  /**
   * {@code POST /users} : Create a new user.
   *
   * @param user the userDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new
   *     userDTO, or with status {@code 400 (Bad Request)} if the person has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/user/registration")
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
