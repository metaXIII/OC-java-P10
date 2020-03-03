package com.librairie.batch.service;

import com.librairie.batch.config.AppProperties;
import com.librairie.batch.model.Livre;
import com.librairie.batch.model.Reservation;
import com.librairie.batch.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;

    private final IMailService mailService;

    private final String defaultSender;

    public EmailService(JavaMailSender mailSender, IMailService mailService, AppProperties appProperties) {
        this.mailSender = mailSender;
        this.mailService = mailService;
        this.defaultSender = appProperties.getDefaultEmailSender();
    }

    public void sendEmail() {
        List<Reservation> reservations = mailService.getReservations().getBody();
        reservations.forEach(reservation -> {
            Optional<User> user = mailService.getUser(reservation.getUserId());
            user.ifPresent(value -> {
                try {
                    sendEmail(reservation, value);
                } catch (MessagingException e) {
                    log.error("Une erreur est survenue ");
                    log.error(e.getMessage());
                }
            });
        });
    }

    private void sendEmail(Reservation reservation, User user) throws MessagingException {
        System.setProperty("mail.mime.charset", "utf8");
        MimeMessage       message = this.mailSender.createMimeMessage();
        MimeMessageHelper helper  = new MimeMessageHelper(message, true, "ISO-8859-1");
        helper.setFrom(this.defaultSender);
        helper.setTo(user.getEmail());
//        helper.setFrom(MyConstants.MY_EMAIL);
//        helper.setTo(MyConstants.FRIEND_EMAIL);
        helper.setText("<h3>Bonjour " + user.getUsername() + "</h6>" +
                "    <p> Merci de régulariser la réservation " + reservation.getId() + " dans les meilleurs " +
                "délais. Pour rappel : </p>" +
                "<ul>" + getList(reservation.getLivreId()) +
                "</ul><p>Merci de votre compréhension</p><p>Cordialement</p><p>Le service Librairie</p>", true);
        helper.setSubject("Urgent : Régularisation de votre réservation !");
        this.mailSender.send(message);
    }

    private String getList(String listId) {
        String        start         = "<li>";
        String        end           = "</li>";
        StringBuilder stringBuilder = new StringBuilder();
        String[]      liste         = listId.split(",");
        for (String id : liste) {
            Livre livre = mailService.getLivreById(Long.parseLong(id));
            stringBuilder.append(start);
            stringBuilder.append(livre.getNom());
            stringBuilder.append(" --- ");
            stringBuilder.append(livre.getAuteur());
            stringBuilder.append(end);
        }
        return stringBuilder.toString();
    }
}

