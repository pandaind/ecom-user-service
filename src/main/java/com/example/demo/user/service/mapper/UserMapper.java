package com.example.demo.user.service.mapper;

import com.example.demo.user.domain.User;
import com.example.demo.user.service.dto.UserDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/** Mapper for the entity {@link User} and its DTO {@link UserDTO}. */
@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDTO, User> {
  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  UserDTO toDtoId(User user);
}
