package com.carros.security;

import com.carros.entity.UserEntity;
import com.carros.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service(value = "userDetailService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRep;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userLogado = userRep.findByLogin(username);

        if(Objects.isNull(userLogado)){
            throw new UsernameNotFoundException("User Not Found.");
        }
        return userLogado;
//        return User.withUsername(userLogado.getLogin()).password(userLogado.getSenha()).roles("USER").build();
//        return User.withUsername("admin").password(encoder.encode("admin")).roles("ADMIN","USER").build();
    }
}
