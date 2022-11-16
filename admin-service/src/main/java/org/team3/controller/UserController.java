package org.team3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.team3.dto.request.DetailInformationRequestDto;
import org.team3.dto.request.DoLoginRequestDto;
import org.team3.dto.request.EditProfileRequestDto;
import org.team3.dto.request.UserProfileRequestDto;
import org.team3.dto.response.DetailInformationResponseDto;
import org.team3.dto.response.DoLoginResponseDto;
import org.team3.exception.UserManagerException;
import org.team3.exception.ErrorType;
import org.team3.repository.entity.User;
import org.team3.config.security.JwtTokenManager;
import org.team3.service.UserService;

import javax.validation.Valid;
import java.util.Optional;

import static org.team3.constants.ApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(BASE_URL + USER)
public class UserController {
    private final UserService userService;
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
    @PostMapping(UPDATE)
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
    public ResponseEntity<Void> newCompanyManager(@RequestBody @Valid UserProfileRequestDto dto){
        userService.saveNewCompanyManager(dto);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(originPatterns = "*")
    @PostMapping(PROFILE_DETAIL)
    //@PreAuthorize("hasAuthority(Role.ADMIN)")
    public ResponseEntity<DetailInformationResponseDto> profileDetail(DetailInformationRequestDto detailInformationRequestDto){

        DetailInformationResponseDto dto = userService.profileDetail(detailInformationRequestDto);

        return ResponseEntity.ok(dto);
    }


}
