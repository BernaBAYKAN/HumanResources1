package org.team3.service;


import org.springframework.stereotype.Service;
import org.team3.repository.ICompanyManagerRepository;
import org.team3.repository.entity.CompanyManager;
import org.team3.utility.ServiceManager;

@Service
public class CompanyManagerService extends ServiceManager<CompanyManager,Long> {

    private final ICompanyManagerRepository companyManagerRepository;

    public CompanyManagerService(ICompanyManagerRepository companyManagerRepository) {
        super(companyManagerRepository);
        this.companyManagerRepository = companyManagerRepository;
    }
}
