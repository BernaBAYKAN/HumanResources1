package org.team3.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.team3.dto.request.AdminProfileRequestDto;
import org.team3.dto.request.EditProfileRequestDto;
import org.team3.exception.AdminManagerException;
import org.team3.exception.ErrorType;
import org.team3.repository.entity.Admin;
import org.team3.service.AdminService;
import org.team3.utility.JwtTokenManager;

import javax.validation.Valid;
import java.util.Optional;

import static org.team3.constants.ApiUrls.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(BASE_URL+ADMIN)
public class AdminController {
    private final AdminService adminService;
    private final JwtTokenManager jwtTokenManager;

    /* Şirket listesi için düzenlenecek
    @GetMapping("/getAll")
    public List<Admin> getAll(){
        return this.adminService.getAll();
    }*/
   /* @PostMapping("/save")
    public ResponseEntity<Void> save(@RequestBody @Valid AdminProfileRequestDto dto){
        adminService.save(dto);
        return ResponseEntity.ok().build();
    }*/
    @PostMapping(SAVE)
    public ResponseEntity<Admin> save(Admin admin){
        return ResponseEntity.ok(adminService.save(admin));
    }
   /* @PostMapping("/update")
    public ResponseEntity<Boolean> update(AdminProfileUpdateRequestDto dto){
        return ResponseEntity.ok(adminService.update(dto));
    }*/
   @PostMapping(UPDATE)
   public ResponseEntity<Boolean> updateProfile(@RequestBody @Valid EditProfileRequestDto dto){
       if(dto.getToken()==null)
           throw new AdminManagerException(ErrorType.INVALID_TOKEN);
       try{
           Optional<Long> adminid = jwtTokenManager.getUserId(dto.getToken());
           if(adminid.isEmpty()) throw new AdminManagerException(ErrorType.INVALID_TOKEN);
           return ResponseEntity.ok(adminService.updateAdmin(dto,adminid.get()));
       }catch (Exception exception){
           throw new AdminManagerException(ErrorType.INVALID_TOKEN);
       }
   }
}
