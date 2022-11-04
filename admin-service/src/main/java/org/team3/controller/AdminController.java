package org.team3.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.team3.dto.request.AdminProfileRequestDto;
import org.team3.dto.request.EditProfileRequestDto;
import org.team3.service.AdminService;
import org.team3.utility.JwtTokenManager;

import javax.validation.Valid;
import java.util.Optional;
@Controller
@RequiredArgsConstructor
public class AdminController {
    private AdminService adminService;
    private JwtTokenManager jwtTokenManager;

    /* Şirket listesi için düzenlenecek
    @GetMapping("/getAll")
    public List<Admin> getAll(){
        return this.adminService.getAll();
    }*/
    @PostMapping("/save")
    public ResponseEntity<Void> save(@RequestBody @Valid AdminProfileRequestDto dto){
        adminService.save(dto);
        return ResponseEntity.ok().build();
    }
   /* @PostMapping("/update")
    public ResponseEntity<Boolean> update(AdminProfileUpdateRequestDto dto){
        return ResponseEntity.ok(adminService.update(dto));
    }*/
   @PostMapping("/update")
   public ResponseEntity<Boolean> updateProfile(@RequestBody @Valid EditProfileRequestDto dto){
       if(dto.getToken()==null)
           throw new UserManagerException(ErrorType.INVALID_TOKEN);
       try{
           Optional<Long> adminid = jwtTokenManager.getUserId(dto.getToken());
           if(adminid.isEmpty()) throw new UserManagerException(ErrorType.INVALID_TOKEN);
           return ResponseEntity.ok(adminService.updateAdmin(dto,adminid.get()));
       }catch (Exception exception){
           throw new UserManagerException(ErrorType.INVALID_TOKEN);
       }
   }
}
