package org.team3.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.team3.repository.enums.Gender;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class    NewCompanyManagerDto {

    Long companyId;
    String photo;
    String name;
    String lastName;
    String secondLastname;
    String secondName;
    String birthdate;
    String workStartDate;
    String address;
    String phoneNumber;
    Gender gender;

}
