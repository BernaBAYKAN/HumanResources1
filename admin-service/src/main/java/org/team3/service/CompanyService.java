package org.team3.service;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.team3.dto.request.NewCompanyRequestDto;
import org.team3.dto.response.AllCompanyDtoResponse;
import org.team3.mapper.ICompanyMapper;
import org.team3.repository.ICompanyRepository;
import org.team3.repository.entity.Company;
import org.team3.utility.ServiceManager;

import java.util.List;

@Service
public class CompanyService extends ServiceManager<Company,Long> {

    private final ICompanyRepository companyRepository;

    public CompanyService(ICompanyRepository companyRepository) {
        super(companyRepository);
        this.companyRepository = companyRepository;
    }
    public List<Company> findAll() {


        List<Company> companyList = companyRepository.findAll();

        return companyList;
    }

    public void saveNewCompany(NewCompanyRequestDto dto) {
        Company company = ICompanyMapper.INSTANCE.toCompany(dto);
        //todo domain i burada belirteceğiz
        save(company);
    }
}
