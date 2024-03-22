package com.salabook.server.config;

import java.util.Arrays;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.salabook.server.entities.Reserva;
import com.salabook.server.entities.ReservaItem;
import com.salabook.server.entities.Sala;
import com.salabook.server.entities.User;
import com.salabook.server.entities.enums.NivelUser;
import com.salabook.server.entities.enums.StatusSala;
import com.salabook.server.repositories.ReservaItemRepository;
import com.salabook.server.repositories.ReservaRepository;
import com.salabook.server.repositories.SalaRepository;
import com.salabook.server.repositories.UserRepository;

@Configuration
@Profile("test")
public class TesteConfig implements CommandLineRunner{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ReservaItemRepository reservaItemRepository;

    @Override
    public void run(String... args) throws Exception{

        User u1 = new User(null, "Milu Santos", "milu@gmail.com", "123456", NivelUser.ADMIN);
        User u2 = new User(null, "Flor Santos", "flor@gmail.com", "987123", NivelUser.FUNCIONARIO);       
        userRepository.saveAll(Arrays.asList(u1, u2));

        Sala s1 = new Sala(null, "Sala 01", 6, StatusSala.DISPONIVEL);
        Sala s2 = new Sala(null, "Sala 02", 12, StatusSala.OCUPADA);
        Sala s3 = new Sala(null, "Sala 02", 4, StatusSala.RESERVADA);
        salaRepository.saveAll(Arrays.asList(s1, s2, s3));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Reserva r1 = new Reserva(null, sdf.parse("1/02/2024 17:30"), u1, s1);
        Reserva r2 = new Reserva(null, sdf.parse("10/02/2024 9:00"), u1, s1);
        reservaRepository.saveAll(Arrays.asList(r1, r2));
        		
		ReservaItem ri1 = new ReservaItem(r1, s1, sdf.parse("10/02/2024 10:30"), sdf.parse("10/02/2024 13:30"));
        ReservaItem ri2 = new ReservaItem(r2, s1, sdf.parse("15/02/2024 14:00"), sdf.parse("15/02/2024 15:00"));
        
        r1.getItens().addAll(Arrays.asList(ri1));
        r2.getItens().addAll(Arrays.asList(ri2));

        s1.getItens().addAll(Arrays.asList(ri1, ri2));
       
        reservaItemRepository.saveAll(Arrays.asList(ri1, ri2));
    }

}
