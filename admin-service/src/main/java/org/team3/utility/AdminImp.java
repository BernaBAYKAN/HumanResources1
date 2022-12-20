package org.team3.utility;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.team3.repository.entity.User;
import org.team3.repository.entity.UserRole;
import org.team3.repository.enums.Gender;
import org.team3.repository.enums.Role;
import org.team3.service.UserService;

import javax.annotation.PostConstruct;


@Component
@RequiredArgsConstructor
public class AdminImp {
    private final UserService userService;

    @PostConstruct
    public void init() {

    User user =    User.builder()
                    .photo("photo")
                    .name("Berna")
                    .secondName("QueenB")
                    .lastName("Baykan")
                    .secondLastname("DERE")
                    .gender(Gender.FEMALE)
                    .department("Software Deparment")
                    .birthdate("14.05.1997")
                    .workStartDate("01.12.2022")
                    .address("Angara")
                    .phoneNumber("0534 327 2928")
                    .mail("berna@gmail.com")
                    .password("237320d509717dc3f0d6bdcd5e8dc8f88f8fd94b06c728c1aaf94118ed34af38")
            .build();
        UserRole role= UserRole.builder()
                .role(Role.ADMIN)
                .userId(user)
                .build();
        user.setUserRole(role);
        userService.save(user);
    }

}
