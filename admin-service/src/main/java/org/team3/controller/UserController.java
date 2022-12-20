package org.team3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import org.team3.dto.request.*;
import org.team3.dto.response.DetailInformationResponseDto;
import org.team3.dto.response.DoLoginResponseDto;
import org.team3.exception.UserManagerException;
import org.team3.exception.ErrorType;
import org.team3.repository.entity.User;
import org.team3.config.security.JwtTokenManager;
import org.team3.service.UserService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

import static org.team3.constants.ApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(BASE_URL + USER)
public class UserController {
    private final UserService userService;
    private final JavaMailSender emailSender;
    private final JwtTokenManager jwtTokenManager;



    @CrossOrigin(originPatterns = "*")
    @PostMapping(LOGIN)
    public ResponseEntity<DoLoginResponseDto> doLogin(@RequestBody @Valid DoLoginRequestDto dto) {
        Optional<User> user = userService.doLogin(dto);

        if (user.isPresent()) {
            String token = jwtTokenManager.createToken(user.get().getId()).get();
            return ResponseEntity.ok(DoLoginResponseDto.builder()
                    .token(token)
                    .message(user.get().getUserRole().getRole().name() + " Login successful")
                    .responsecode(200L)
                    .build());

        } else {
            return ResponseEntity.ok(DoLoginResponseDto.builder()
                    .message("EMail or password incorrect")
                    .responsecode(400L)
                    .build());
        }
    }

    @CrossOrigin(originPatterns = "*")
    @PutMapping(UPDATE)
    public ResponseEntity<Boolean> updateProfile(@RequestBody @Valid EditProfileRequestDto editProfileRequestDto) {

        if (editProfileRequestDto.getToken() == null)
            throw new UserManagerException(ErrorType.INVALID_TOKEN);
        try {
            Optional<Long> authid = jwtTokenManager.getUserId(editProfileRequestDto.getToken());
            if (authid.isEmpty()) throw new UserManagerException(ErrorType.INVALID_TOKEN);
            return ResponseEntity.ok(userService.updateProfile(editProfileRequestDto, authid.get()));
        } catch (Exception exception) {
            throw new UserManagerException(ErrorType.INVALID_TOKEN);
        }

    }
    @CrossOrigin(originPatterns = "*")
    @PostMapping("/newmanager")
    //@PreAuthorize("hasAuthority(Role.ADMIN)")
    public ResponseEntity<Void> newCompanyManager(@RequestBody @Valid NewCompanyManagerDto dto) throws MessagingException, UnsupportedEncodingException {
        User user = userService.saveNewCompanyManager(dto);
        forgotPassword(user.getMail());
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(originPatterns = "*")
    @PostMapping(PROFILE_DETAIL)
    public ResponseEntity<DetailInformationResponseDto> profileDetail(@RequestBody DetailInformationRequestDto detailInformationRequestDto){

        DetailInformationResponseDto dto = userService.profileDetail(detailInformationRequestDto);

        return ResponseEntity.ok(dto);
    }


    @CrossOrigin(originPatterns = "*")
    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestParam @RequestBody String email) throws MessagingException, UnsupportedEncodingException {

        String response = userService.forgotPassword(email);

        if (!response.startsWith("Invalid")) {
            response = "https://localhost:8081/v1/api/user/reset-password?token=" + response  ;
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
