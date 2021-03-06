package ba.bac.server.service;

import org.springframework.mail.javamail.JavaMailSender;

public interface EmailService {
    void sendMail(String to, String subject, String body);
    JavaMailSender javaMailSender();
    void sendHTMLMail(String emailTo, String subject, String body);
    void verifyEmail(String email, String token);
    boolean requestPasswordReset(String email, String token);
}
