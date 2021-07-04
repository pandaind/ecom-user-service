package com.example.demo.user.service.impl;

import static com.example.demo.user.util.TestHelperUtil.user;
import static com.example.demo.user.util.TestHelperUtil.userDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

import com.example.demo.user.domain.User;
import com.example.demo.user.repository.UserRepository;
import com.example.demo.user.service.UserService;
import com.example.demo.user.service.dto.UserDTO;
import com.example.demo.user.service.mapper.UserMapper;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UserServiceImplTest {

  @MockBean private UserRepository repository;

  @Autowired private UserMapper mapper;

  @Autowired private UserService service;

  @Test
  void findAll() {
    when(repository.findAll()).thenReturn(List.of(user(1L), user(2L)));
    List<UserDTO> userDTOS = service.findAll();
    assertEquals(2, userDTOS.size());
    assertEquals(1L, userDTOS.get(0).getId());
  }

  @Test
  void findOne() {
    when(repository.findById(isA(Long.class))).thenReturn(Optional.of(user(1L)));
    Optional<UserDTO> userDTO = service.findOne(1L);
    assertEquals(1L, userDTO.orElseThrow().getId());
  }

  @Test
  void findOneByName() {
    when(repository.findByUserName(isA(String.class))).thenReturn(Optional.of(user(1L)));
    Optional<UserDTO> userDTO = service.findOneByName("test");
    assertEquals(user(1L).getUserName(), userDTO.orElseThrow().getUserName());
  }

  @Test
  void save() {
    when(repository.save(isA(User.class))).thenReturn(user(1L));
    UserDTO userDTO = service.save(userDTO(1L));
    assertEquals(user(1L).getId(), userDTO.getId());
  }
}
