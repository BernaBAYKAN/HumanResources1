package org.team3.service;

import org.springframework.stereotype.Service;
import org.team3.config.security.JwtTokenManager;
import org.team3.dto.request.AdminProfileRequestDto;
import org.team3.dto.request.DoLoginRequestDto;
import org.team3.dto.request.EditProfileRequestDto;
import org.team3.dto.response.DetailInformationResponseDto;
import org.team3.mapper.IAdminMapper;
import org.team3.repository.IAdminRepository;
import org.team3.repository.entity.Admin;
import org.team3.repository.enums.Role;
import org.team3.utility.ServiceManager;

import java.util.Optional;

@Service
public class AdminService extends ServiceManager<Admin,Long> {
    private final IAdminRepository repository;
    private final JwtTokenManager jwtTokenManager;

    public AdminService(IAdminRepository repository, JwtTokenManager jwtTokenManager) {
        super(repository);
        this.repository = repository;
        this.jwtTokenManager = jwtTokenManager;
    }


    public void save(AdminProfileRequestDto dto){
        repository.save(IAdminMapper.INSTANCE.toAdminProfile(dto));
    }

    public Boolean updateAdmin(EditProfileRequestDto dto, Long adminid){

        Optional<Admin> optionalAdmin = repository.findOptionalById(adminid);
        if(optionalAdmin.isEmpty()) return false;
        else {
            try {
                Admin admin = Admin.builder()
                        .id(optionalAdmin.get().getId())
                        .photo(dto.getPhoto())
                        .name(optionalAdmin.get().getName())
                        .lastName(optionalAdmin.get().getLastName())
                        .secondName(optionalAdmin.get().getSecondName())
                        .secondLastname(optionalAdmin.get().getSecondLastname())
                        .gender(optionalAdmin.get().getGender())
                        .department(optionalAdmin.get().getDepartment())
                        .birthdate(optionalAdmin.get().getBirthdate())
                        .workStartDate(optionalAdmin.get().getWorkStartDate())
                        .phoneNumber(dto.getPhoneNumber())
                        .address(dto.getAddress())
                        .mail(optionalAdmin.get().getMail())
                        .role(optionalAdmin.get().getRole())
                        .build();
                save(admin);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }
    public Admin findByRole(Role role){
        Admin admin = repository.findByRole(role);

        return admin;
    }

    public DetailInformationResponseDto profileDetail() {
        Admin admin = findByRole(Role.ADMIN);
        DetailInformationResponseDto dto = IAdminMapper.INSTANCE.toDetailInformationResponseDto(admin);
        return dto;
    }

    public Optional<Admin> doLogin(DoLoginRequestDto dto){
        String encodedPassword = jwtTokenManager.encryptPassword(dto.getPassword());
        return repository.findOptionalByUsernameIgnoreCaseAndPassword(dto.getMail(),
                encodedPassword);
    }
}
