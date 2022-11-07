package org.team3.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.team3.repository.enums.Gender;
import org.team3.repository.enums.Role;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DetailInformationResponseDto {

    String photo;
    String name;
    String lastName;
    String secondName;
    String secondLastname;
    Gender gender;
    String department;
    String birthdate;
    String workStartDate;
    String address;
    String phoneNumber;
    String mail;


}
