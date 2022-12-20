package org.team3.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.team3.repository.enums.Gender;

import javax.validation.constraints.Email;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserProfileRequestDto {

 String photo;
 String name;
 String lastName;
 String secondLastName;
 String secondName;
 String birthdate;
 String workStartDate;
 String address;
 String phoneNumber;


}
