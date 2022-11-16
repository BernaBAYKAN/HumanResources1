package org.team3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import org.team3.service.UserService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EmailController {

    private final JavaMailSender emailSender;
    private final UserService userService;

    @CrossOrigin(originPatterns = "*")
    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestParam @RequestBody String email) throws MessagingException, UnsupportedEncodingException {

        String response = userService.forgotPassword(email);

        if (!response.startsWith("Invalid")) {
            response = "https://localhost:8081/api/reset-password?token=" + response  ;
            //response="https://www.google.com.tr/";
            sendEmail(email, response);
        }
        return response;
    }

    public void sendEmail(String recipientEmail, String link) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("team3-hrproject@outlook.com", "Spring team Support");
        helper.setTo(recipientEmail);

        String subject = "Here's the link to reset your password";

        String content = "<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + link + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        emailSender.send(message);
    }
    @CrossOrigin(originPatterns = "*")
    @PutMapping("/reset-password")
    public String resetPassword(@RequestParam String token,
                                @RequestParam String password) {

        return userService.resetPassword(token, password);
    }
}