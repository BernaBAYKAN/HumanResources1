package org.team3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.team3.dto.request.DoLoginRequestDto;
import org.team3.dto.response.DoLoginResponseDto;
import org.team3.repository.entity.Admin;
import org.team3.repository.entity.CompanyManager;
import org.team3.service.AdminService;
import org.team3.service.CompanyManagerService;
import org.team3.config.security.JwtTokenManager;

import javax.validation.Valid;
import java.util.Optional;

import static org.team3.constants.ApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(BASE_URL)
public class AuthController {
    private final AdminService adminService;
    private final CompanyManagerService companyManagerService;
    private final JwtTokenManager jwtTokenManager;


    @CrossOrigin(originPatterns = "*")
    @PostMapping(LOGIN)
    public ResponseEntity<DoLoginResponseDto> doLogin(@RequestBody @Valid DoLoginRequestDto dto) {
        Optional<Admin> admin = adminService.doLogin(dto);
        Optional<CompanyManager> manager = companyManagerService.doLogin(dto);
        if (admin.isPresent()) {
            String token = jwtTokenManager.createToken(admin.get().getId()).get();
           return ResponseEntity.ok(DoLoginResponseDto.builder()
                    .token(token)
                    .message("Admin login successful")
                    .responsecode(200L)
                    .build());
        } else if (manager.isPresent()) {
            String token = jwtTokenManager.createToken(admin.get().getId()).get();
            return ResponseEntity.ok(DoLoginResponseDto.builder()
                    .token(token)
                    .message("Manager login successful")
                    .responsecode(200L)
                    .build());
        } else {
            return ResponseEntity.ok(DoLoginResponseDto.builder()
                    .message("EMail or password incorrect")
                    .responsecode(400L)
                    .build());
        }
    }


}
