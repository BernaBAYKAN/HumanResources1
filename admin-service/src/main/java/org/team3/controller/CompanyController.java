package org.team3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.team3.dto.request.NewCompanyRequestDto;
import org.team3.dto.response.AllCompanyDtoResponse;
import org.team3.mapper.IAdminMapper;
import org.team3.mapper.ICompanyMapper;
import org.team3.repository.entity.Company;
import org.team3.service.CompanyService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.team3.constants.ApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(BASE_URL+COMPANY)
public class CompanyController {


    private final CompanyService companyService;

    @CrossOrigin(originPatterns = "*")
    @PostMapping(NEWCOMPANY)
    public ResponseEntity<Void> newCompany(@RequestBody @Valid NewCompanyRequestDto dto){
        companyService.saveNewCompany(dto);
        return ResponseEntity.ok().build();
    }


    /**
     *Bütün şirketleri listelemek için kullanacağımız endpoint
     */
    @CrossOrigin(originPatterns = "*")
    @PostMapping(FINDALL)
    public ResponseEntity<List<AllCompanyDtoResponse>> findAllCompanyList(){

        List<Company> companyList = companyService.findAll();
        List<AllCompanyDtoResponse> responseList= new ArrayList<>();
        companyList.forEach(company -> {
            responseList.add(ICompanyMapper.INSTANCE.toCompanyResponse(company));
        });
        return ResponseEntity.ok(responseList);
    }


}
