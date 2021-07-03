package com.example.demo.user.web.rest;

import static com.example.demo.user.util.TestHelperUtil.toJsonString;
import static com.example.demo.user.util.TestHelperUtil.userDTO;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.user.service.UserService;
import com.example.demo.user.service.dto.UserDTO;
import com.example.demo.user.util.TestHelperUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class RegistrationResourceTest {
  @Autowired private RegistrationResource resource;

  @MockBean private UserService service;

  @Autowired private MockMvc mockMvc;

  @Test
  void testAddUser_validRequest() throws Exception {
    when(service.save(any(UserDTO.class))).thenReturn(userDTO(1L));

    this.mockMvc
        .perform(
            post("/api/users/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestHelperUtil.toJsonString(userDTO(0L))))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value(1L));
  }

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
}
