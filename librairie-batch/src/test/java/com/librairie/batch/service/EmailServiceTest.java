package com.librairie.batch.service;

import com.librairie.batch.config.AppProperties;
import com.librairie.batch.model.Livre;
import com.librairie.batch.model.Reservation;
import com.librairie.batch.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmailServiceTest {

    @InjectMocks
    private EmailService emailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Mock
    private IMailService mailService;

    @Mock
    private AppProperties appProperties;

    @Mock
    private ResponseEntity<List<Reservation>> responseEntity;

    @BeforeEach
    public void init() {
        emailService = new EmailService(javaMailSender, mailService, appProperties);
    }

    @AfterEach
    public void end() {
        emailService = null;
    }


    @Test
    void sendEmail() {
        when(mailService.getReservations()).thenReturn(responseEntity);
        when(responseEntity.getBody()).thenReturn(mockReservations());
        when(mailService.getUser(anyLong())).thenReturn(mockOptionalUser());
        assertDoesNotThrow(() -> emailService.sendEmail());
    }


    @Test
    void getList() {
        when(mailService.getLivreById(anyLong())).thenReturn(mockLivre());
        assertEquals("<li>NOM --- AUTEUR</li><li>NOM --- AUTEUR</li><li>NOM --- AUTEUR</li>", emailService.getList("1,2,3"));
    }

    private Livre mockLivre() {
        Livre livre = new Livre();
        livre.setAuteur("AUTEUR");
        livre.setNom("NOM");
        return livre;
    }

    private Optional<User> mockOptionalUser() {
        return Optional.of(mockUser());
    }

    private User mockUser() {
        User user = new User();
        user.setUsername("aze");
        user.setEmail("aze@aze");
        return user;
    }

    private List<Reservation> mockReservations() {
        List<Reservation> reservations = new ArrayList<>();
        Reservation       reservation  = new Reservation();
        reservation.setId(1);
        reservation.setUserId(1);
        reservation.setFinished(true);
        reservation.setExtended(true);
        reservation.setDateLimite(LocalDate.now());
        reservation.setDateReservation(LocalDate.now().minusDays(2));
        reservation.setLivreId("1");
        reservations.add(reservation);
        return reservations;
    }
}