package org.team3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.team3.dto.request.NewCompanyManagerRequestDto;

import org.team3.service.CompanyManagerService;

import javax.validation.Valid;

import static org.team3.constants.ApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(BASE_URL+COMPANY_MANAGER)
public class CompanyManagerController {

    private final CompanyManagerService companyManagerService;



    public ResponseEntity<Void> newCompanyManager(@RequestBody @Valid NewCompanyManagerRequestDto dto){
        companyManagerService.saveNewCompanyManager(dto);
        return ResponseEntity.ok().build();
    }





}
