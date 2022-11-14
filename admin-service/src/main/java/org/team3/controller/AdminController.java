package org.team3.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.team3.dto.request.EditProfileRequestDto;
import org.team3.dto.response.DetailInformationResponseDto;
import org.team3.exception.AdminManagerException;
import org.team3.exception.ErrorType;
import org.team3.repository.entity.Admin;
import org.team3.repository.enums.Role;
import org.team3.service.AdminService;

import javax.validation.Valid;

import static org.team3.constants.ApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(BASE_URL+ADMIN)
public class AdminController {
    private final AdminService adminService;


    @CrossOrigin(originPatterns = "*")
    @PostMapping(PROFILE_DETAIL)
    @PreAuthorize("hasAuthority(Role.ADMIN)")
    public ResponseEntity<DetailInformationResponseDto> profileDetail(){

        DetailInformationResponseDto dto = adminService.profileDetail();

        return ResponseEntity.ok(dto)   ;
    }@PreAuthorize("hasAuthority('ADMIN')")
   @CrossOrigin(originPatterns = "*")
   @PostMapping(UPDATE)
   public ResponseEntity<Boolean> updateProfile(@RequestBody @Valid EditProfileRequestDto dto){
       Admin admin = adminService.findByRole(Role.ADMIN);
       try{
           return ResponseEntity.ok(adminService.updateAdmin(dto,admin.getId()));
       }catch (Exception exception){
           throw new AdminManagerException(ErrorType.INVALID_TOKEN);
       }
   }


}
