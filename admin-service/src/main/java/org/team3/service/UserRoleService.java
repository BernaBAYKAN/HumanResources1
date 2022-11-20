package org.team3.service;

import org.springframework.stereotype.Service;
import org.team3.repository.ICompanyRepository;
import org.team3.repository.IUserRoleRepository;
import org.team3.repository.entity.Company;
import org.team3.repository.entity.UserRole;
import org.team3.utility.ServiceManager;
@Service
public class UserRoleService extends ServiceManager<UserRole,Long> {
    private final IUserRoleRepository userRoleRepository;

    public UserRoleService(IUserRoleRepository userRoleRepository) {
        super(userRoleRepository);
        this.userRoleRepository = userRoleRepository;
    }
}
