package com.example.demo.user.web.rest;

import static com.example.demo.user.util.TestHelperUtil.*;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.demo.user.service.UserService;
import com.example.demo.user.service.dto.UserDTO;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UserResourceTest {

  @Autowired private UserResource resource;

  @MockBean private UserService service;

  @Autowired private MockMvc mockMvc;

  @Test
  void testAddUser_badRequest() throws Exception {
    when(service.save(any(UserDTO.class))).thenReturn(userDTO(1L));

    this.mockMvc
        .perform(
            post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJsonString(userDTO(1L))))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  void testAddUser_validRequest() throws Exception {
    when(service.save(any(UserDTO.class))).thenReturn(userDTO(1L));

    this.mockMvc
        .perform(
            post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJsonString(userDTO(0L))))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value(1L));
  }

  @Test
  void testGetUserById() throws Exception {
    when(service.findOne(any(Long.TYPE))).thenReturn(Optional.of(userDTO(1L)));

    this.mockMvc
        .perform(get("/api/users/1"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.userName").value("test"));
  }

  @Test
  void testGetUserByUserName() throws Exception {
    when(service.findOneByName(any(String.class))).thenReturn(Optional.of(userDTO(1L)));

    this.mockMvc
        .perform(get("/api/users").param("name", "test"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.userName").value("test"))
        .andExpect(content().json(toJsonString(userDTO(1L))));
  }

  @Test
  void testGetUsers() throws Exception {
    when(service.findAll()).thenReturn(List.of(userDTO(1L), userDTO(2L)));

    this.mockMvc
        .perform(get("/api/users"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()", is(2)))
        .andExpect(jsonPath("$.[0].id").value(1L));
  }
}
