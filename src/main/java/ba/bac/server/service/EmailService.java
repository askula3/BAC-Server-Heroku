package ba.bac.server.service;

import ba.bac.server.shared.dto.UserDto;
import org.springframework.mail.javamail.JavaMailSender;

public interface EmailService {
    void sendMail(String to, String subject, String body);
    JavaMailSender javaMailSender();
    void sendHTMLMail(String emailTo, String subject, String body);
    void verifyEmail(UserDto userDto);
}
