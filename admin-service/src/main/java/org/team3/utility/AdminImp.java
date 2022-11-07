package org.team3.utility;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.team3.repository.entity.Admin;
import org.team3.repository.enums.Gender;
import org.team3.service.AdminService;

import javax.annotation.PostConstruct;


@Component
@RequiredArgsConstructor
public class AdminImp {
    private final AdminService adminService;

    //@PostConstruct
    public void init() {

    adminService.save(Admin.builder()
                    .photo("photo")
                    .name("Berna")
                    .secondName("Mehmet")
                    .lastName("Baykan")
                    .secondLastname("Dere")
                    .gender(Gender.FEMALE)
                    .department("Software Deparment")
                    .birthdate("14.05.1997")
                    .workStartDate("01.12.2022")
                    .address("Angara")
                    .phoneNumber("0534 327 2928")
                    .mail("berna@gmail.com")
            .build());

    }

}
