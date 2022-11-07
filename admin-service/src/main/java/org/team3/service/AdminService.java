package org.team3.service;

import org.springframework.stereotype.Service;
import org.team3.dto.request.AdminProfileRequestDto;
import org.team3.dto.request.EditProfileRequestDto;
import org.team3.mapper.IAdminMapper;
import org.team3.repository.IAdminRepository;
import org.team3.repository.entity.Admin;
import org.team3.repository.enums.Role;
import org.team3.utility.ServiceManager;

import java.util.Optional;

@Service
public class AdminService extends ServiceManager<Admin,Long> {
    private final IAdminRepository repository;

    public AdminService(IAdminRepository repository) {
        super(repository);
        this.repository = repository;
    }


    public void save(AdminProfileRequestDto dto){
        repository.save(IAdminMapper.INSTANCE.toAdminProfile(dto));
    }

    public Boolean updateAdmin(EditProfileRequestDto dto, Long adminid){

        Admin admin = IAdminMapper.INSTANCE.toAdmin(dto);
        Optional<Admin> optionalAdmin = repository.findOptionalById(adminid);
        if(optionalAdmin.isEmpty()) return false;
        try{
            admin.setId(optionalAdmin.get().getId());
            update(admin);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public Admin findByRole(Role role){
        Admin admin = repository.findByRole(role);

        return admin;
    }
}
