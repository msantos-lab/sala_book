package com.salabook.server.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.salabook.server.entities.User;
import com.salabook.server.entities.enums.NivelUser;
import com.salabook.server.repositories.UserRepository;

@Configuration
@Profile("test")
public class TesteConfig implements CommandLineRunner{

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception{

        User u1 = new User(null, "Milu Santos", "milu@gmail.com", "123456", NivelUser.ADMIN);
        User u2 = new User(null, "Flor Santos", "flor@gmail.com", "987123", NivelUser.FUNCIONARIO);
       
        userRepository.saveAll(Arrays.asList(u1, u2));

    }

}
