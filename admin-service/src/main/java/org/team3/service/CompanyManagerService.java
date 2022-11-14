package org.team3.service;

import org.springframework.stereotype.Service;
import org.team3.config.security.JwtTokenManager;
import org.team3.dto.request.DoLoginRequestDto;
import org.team3.dto.request.NewCompanyManagerRequestDto;
import org.team3.dto.request.NewCompanyRequestDto;
import org.team3.mapper.ICompanyManagerMapper;
import org.team3.mapper.ICompanyMapper;
import org.team3.repository.ICompanyManagerRepository;
import org.team3.repository.ICompanyRepository;
import org.team3.repository.entity.Admin;
import org.team3.repository.entity.Company;
import org.team3.repository.entity.CompanyManager;
import org.team3.utility.ServiceManager;

import java.util.Optional;

@Service
public class CompanyManagerService extends ServiceManager<CompanyManager,Long> {

    private final ICompanyManagerRepository companyManagerRepository;
    private final JwtTokenManager jwtTokenManager;

    public CompanyManagerService(ICompanyManagerRepository companyManagerRepository,JwtTokenManager jwtTokenManager) {
        super(companyManagerRepository);
        this.companyManagerRepository = companyManagerRepository;
        this.jwtTokenManager = jwtTokenManager;
    }

    public Optional<CompanyManager> doLogin(DoLoginRequestDto dto){
        String encodedPassword = jwtTokenManager.encryptPassword(dto.getPassword());
        return companyManagerRepository.findOptionalByUsernameIgnoreCaseAndPassword(dto.getMail(),
                encodedPassword);
    }

    public void saveNewCompanyManager(NewCompanyManagerRequestDto dto) {
        CompanyManager companyManager = ICompanyManagerMapper.INSTANCE.toManager(dto);
        save(companyManager);
    }
}
