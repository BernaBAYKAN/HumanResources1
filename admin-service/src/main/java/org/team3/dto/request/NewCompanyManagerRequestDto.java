package org.team3.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.team3.repository.entity.Company;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NewCompanyManagerRequestDto {
    String photo;
    String name;
    String lastName;
    String secondName;
    String secondLastname;
    String birthdate;
    String address;
    String phone;
    String mail;
    String password;
    String workStartDate;
    Company company;
}
