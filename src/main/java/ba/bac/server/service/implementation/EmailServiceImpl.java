package ba.bac.server.service.implementation;

import ba.bac.server.service.EmailService;
import ba.bac.server.shared.dto.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.util.Calendar;
import java.util.Properties;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service("emailService")
public class EmailServiceImpl implements EmailService {
    @Value("${email.verification.url}")
    private String VERIFICATION_URL;

    @Value("${email.host}")
    private String EMAIL_HOST;

    @Value("${email.port}")
    private int EMAIL_PORT;

    @Value("${email.debug}")
    private String EMAIL_DEBUG;

    @Value("${email.ba.username}")
    private String BA_EMAIL_USERNAME;

    @Value("${email.ba.password}")
    private String BA_EMAIL_PASSWORD;

    public JavaMailSender javaMailSender()
    {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(EMAIL_HOST);
        mailSender.setPort(EMAIL_PORT);

        mailSender.setUsername(BA_EMAIL_USERNAME);
        mailSender.setPassword(BA_EMAIL_PASSWORD);


        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.enable", "false");
        props.put("mail.debug", EMAIL_DEBUG);

        return mailSender;
    }

    public void sendMail(String to, String subject, String body) {

        new Thread(() -> {
            try {
                JavaMailSender mailSender = javaMailSender();
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(to);
                message.setSubject(subject);
                message.setText(body);
                mailSender.send(message);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }).start();
    }

    public void sendHTMLMail(String emailTo, String subject, String body) {
        new Thread(() -> {
            try {
                JavaMailSender mailSender = javaMailSender();
                MimeMessage message = mailSender.createMimeMessage();
                message.setFrom(new InternetAddress("bac.ba"));
                message.setRecipients(
                        Message.RecipientType.TO, InternetAddress.parse(emailTo));
                message.setSubject(subject);

                MimeBodyPart mimeBodyPart = new MimeBodyPart();
                mimeBodyPart.setContent(body, "text/html; charset=UTF-8");

                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(mimeBodyPart);

                message.setContent(multipart);

                mailSender.send(message);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }).start();
    }

    public void verifyEmail(UserDto userDto) {

        Integer year = Calendar.getInstance().get(Calendar.YEAR);
        String subject = "Email verification";
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("html/verify_email_email_ba.html");
        String fullUrl = VERIFICATION_URL + userDto.getEmailVerificationToken();

        // Read resource
        try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
            String body = FileCopyUtils.copyToString(reader);
            body = body.replaceAll("urlLinkHere", fullUrl);
            body = body.replaceAll("yearHere", year.toString());
            sendHTMLMail(userDto.getEmail(), subject, body);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        System.out.println("Email sent!");

    }
}
