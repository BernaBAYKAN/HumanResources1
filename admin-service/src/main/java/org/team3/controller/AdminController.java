package org.team3.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.team3.dto.request.EditProfileRequestDto;
import org.team3.exception.AdminManagerException;
import org.team3.exception.ErrorType;
import org.team3.repository.entity.Admin;
import org.team3.repository.enums.Role;
import org.team3.service.AdminService;

import javax.validation.Valid;

import static org.team3.constants.ApiUrls.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(BASE_URL+ADMIN)
public class AdminController {
    private final AdminService adminService;



//    @CrossOrigin(originPatterns = "*")
//    @PostMapping(SAVE)
//    public ResponseEntity<Admin> save(Admin admin){
//        return ResponseEntity.ok(adminService.save(admin));
//    }
   /* @PostMapping("/update")
    public ResponseEntity<Boolean> update(AdminProfileUpdateRequestDto dto){
        return ResponseEntity.ok(adminService.update(dto));
    }*/
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
