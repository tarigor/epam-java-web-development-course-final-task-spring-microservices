package com.epam.mailingservice.service;

import com.epam.mailingservice.model.EmailToAdmin;
import com.epam.mailingservice.model.EmailToClient;
import com.epam.mailingservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl {
    public static final String ADMIN = "hotelgrodnoinn@yandex.by";
    private final String REQUEST_TABLE = "" +
            "|Request ID     - %d \n" +
            "|Client Name    - %s %s\n" +
            "|Persons        - %d\n" +
            "|Room Class     - %s\n" +
            "|Check In Date  - %s\n" +
            "|Check Out Date - %s\n\n";

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private Environment env;

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(ADMIN);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    public void sendEmailToAdmin(EmailToAdmin emailToAdmin) {
        String emailBody = prepareEmailBodyForAdmin(
                emailToAdmin.getUser(),
                emailToAdmin.getRequestID(),
                emailToAdmin.getPersons(),
                emailToAdmin.getRoomClass(),
                emailToAdmin.getDateFrom(),
                emailToAdmin.getDateTo());
        String emailSubject = prepareEmailSubjectForAdmin(emailToAdmin.getRequestID());
        sendSimpleMessage(ADMIN, emailSubject, emailBody);
    }

    public void sendEmailToClient(EmailToClient emailToClient) {
        String emailBody = prepareEmailBodyForClient(
                emailToClient.getUser(),
                emailToClient.getRequestID(),
                emailToClient.getOrderAssigned());
        String emailSubject = prepareEmailSubjectForClient(emailToClient.getRequestID());
        sendSimpleMessage(emailToClient.getUser().getEmail(), emailSubject, emailBody);
    }

    private String prepareEmailSubjectForClient(int requestID) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(env.getProperty("email.subject.send.invoice.row1.1"))
                .append(requestID)
                .append(env.getProperty("email.subject.send.invoice.row1.2"));
        return stringBuilder.toString();
    }

    private String prepareEmailBodyForClient(User user, int requestID, int orderAssigned) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(env.getProperty("email.body.send.invoice.row1"))
                .append(user.getFirstName()).append(" ")
                .append(user.getLastName()).append(",\n\n");
        stringBuilder
                .append(env.getProperty("email.body.send.invoice.row2.1"))
                .append(requestID)
                .append(env.getProperty("email.body.send.invoice.row2.2"))
                .append(orderAssigned)
                .append(env.getProperty("email.body.send.invoice.row2.3"))
                .append(env.getProperty("email.body.send.invoice.row3"))
                .append(env.getProperty("email.body.new.request.row2"));
        return stringBuilder.toString();
    }

    private String prepareEmailBodyForAdmin(User user, int requestID, int persons, String roomClass, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(env.getProperty("email.body.new.request.row1"));
        stringBuilder.append(String.format(REQUEST_TABLE, requestID, user.getFirstName(), user.getLastName(), persons, roomClass, dateFrom, dateTo));
        stringBuilder.append(env.getProperty("email.body.new.request.row2"));
        return stringBuilder.toString();
    }

    private String prepareEmailSubjectForAdmin(int requestID) {
        return env.getProperty("email.subject.new.request") + requestID;
    }

}
