package com.example.demo.user.util;

import com.example.demo.user.domain.User;
import com.example.demo.user.service.dto.UserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface TestHelperUtil {
  static <E> String toJsonString(E e) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.writeValueAsString(e);
  }

  static UserDTO userDTO(Long id) {
    UserDTO userDTO = new UserDTO();

    if (id > 0) userDTO.setId(1L);

    userDTO.setUserName("test");
    userDTO.setActive(1);

    return userDTO;
  }

  static User user(Long id) {
    User user = new User();

    if (id > 0) user.setId(1L);

    user.setUserName("test");
    user.setActive(1);

    return user;
  }
}
