package com.carros.domain;

import com.carros.entity.UserEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDomain {

    private String login;
    private String nome;
    private String email;


    // token jwt
    private String token;

    public static UserDomain create(UserEntity user, String token) {
        UserDomain userDomain = new UserDomain();
        userDomain.setLogin(user.getLogin());
        userDomain.setNome(user.getNome());
        userDomain.setEmail(user.getEmail());
        userDomain.token = token;
        return userDomain;
    }

    public String toJson() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(this);
    }


}
