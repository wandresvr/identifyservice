package com.flowlite.identifyservice.infrastructure.persistence.mappers;

import com.flowlite.identifyservice.domain.entities.*;
import com.flowlite.identifyservice.domain.valueobjects.Username;
import com.flowlite.identifyservice.domain.valueobjects.Email;
import com.flowlite.identifyservice.domain.valueobjects.Password;
import com.flowlite.identifyservice.infrastructure.persistence.entities.UserEntity;

public class UserMapper {

    public static UserEntity toEntity(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .username(user.getUsername().getValue())
                .email(user.getEmail().getValue())
                .password(user.getPassword().getValue())
                .role(user.getRole())
                .active(user.isActive())
                .build();
    }

    public static User toDomain(UserEntity entity) {
        return User.builder()
                .id(entity.getId())
                .username(new Username(entity.getUsername()))
                .email(new Email(entity.getEmail()))
                .password(new Password(entity.getPassword()))
                .role(entity.getRole())
                .active(entity.isActive())
                .build();
    }
}
