package com.pdp.backend.service.confirmation.email;

import com.pdp.backend.enums.MailType;
import com.pdp.backend.model.confirmation.ConfirmationEmail;
import lombok.SneakyThrows;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.NewsAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * @author Aliabbos Ashurov
 * Date: 14/April/2024  11:29
 **/
public class ConfirmationEmailServiceImpl implements ConfirmationEmailService {
    private static ConfirmationEmailServiceImpl instance;

    private ConfirmationEmailServiceImpl() {}
    public static ConfirmationEmailServiceImpl getInstance() {
        if (instance == null) {
            instance = new ConfirmationEmailServiceImpl();
        }
        return instance;
    }
    @Override
    public boolean add(ConfirmationEmail object) {
        return repository.add(object);
    }

    @Override
    public List<ConfirmationEmail> search(String query) {
        return null;
    }
    @Override
    public boolean emailSender(UUID userID, String email, MailType mailType) {
        ConfirmationEmail confirmationEmail = getOrCreate(userID, email,mailType);
        if (Objects.isNull(confirmationEmail)) return false;
        Properties properties = getProperties();
        Session session = getSession(properties);
        dispatchConfirmationCode(email, session, confirmationEmail.getCode());
        return true;
    }
    @Override
    public ConfirmationEmail getOrCreate(UUID userID, String email,MailType mailType) {
        List<ConfirmationEmail> sentList = repository.getAll();
        LocalDateTime currentTime = LocalDateTime.now();
        Optional<ConfirmationEmail> optional = sentList.stream()
                .filter(conf -> conf.getUserID().equals(userID) && conf.getEmail().equals(email)
                && conf.getMailType().equals(mailType))
                .findFirst();
        if (optional.isPresent()) {
            ConfirmationEmail confirmationEmail = optional.get();
            if (isConfirmationEmailExpired(confirmationEmail,currentTime)) {
                repository.remove(confirmationEmail.getId());
            }
            return null;
        }
        Integer confirmationCode = generateConfirmationCode();
        ConfirmationEmail confirmationEmail = new ConfirmationEmail(userID, email, confirmationCode,mailType);
        repository.add(confirmationEmail);
        return confirmationEmail;
    }
    private boolean isConfirmationEmailExpired(ConfirmationEmail email, LocalDateTime currentTime) {
        LocalDateTime dateFromFile = createDateFromFile(email.getDateTime());
        return ChronoUnit.MINUTES.between(dateFromFile, currentTime) >= 1;
    }
    private int generateConfirmationCode() {
        return new Random().nextInt(100000, 999999);
    }
    @Override
    public Integer getConfirmationCodeByUser(UUID userID,MailType mailType) {
        List<ConfirmationEmail> emails = repository.getAll();
        Optional<ConfirmationEmail> confirmationEmail = emails.stream()
                .filter(email -> email.getUserID().equals(userID) && email.getMailType().equals(mailType))
                .findFirst();
        if (confirmationEmail.isPresent()) return confirmationEmail.get().getCode();
        else return 0;
    }
    @SneakyThrows
    private LocalDateTime createDateFromFile(String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yy - HH:mm:ss");
        return LocalDateTime.parse(pattern,dateTimeFormatter);
    }

    @SneakyThrows
    private void dispatchConfirmationCode(String email, Session session, Integer confirmationCode) {
        Message message = new MimeMessage(session);
        message.setSubject("Email confirmation code");
        message.setText(String.valueOf(confirmationCode));
        message.setFrom(new NewsAddress(EmailConfiguration.USERNAME));
        message.setRecipient(Message.RecipientType.TO, new NewsAddress(email));
        Transport.send(message);
    }
    private Session getSession(Properties properties) {
        return Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EmailConfiguration.USERNAME, EmailConfiguration.PASSWORD);
            }
        });
    }

    private Properties getProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        return properties;
    }

    @Override
    public boolean update(ConfirmationEmail object) {
        return false;
    }

    @Override
    public boolean delete(UUID id) {
        return false;
    }

    @Override
    public List<ConfirmationEmail> getAll() {
        return repository.getAll();
    }

    @Override
    public ConfirmationEmail getById(UUID id) {
        return repository.findById(id);
    }
}
