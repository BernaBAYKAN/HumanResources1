package org.team3.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.team3.repository.enums.Gender;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AdminProfileRequestDto {
 Long id;
 String photo;
 String name;
 String lastName;
 String secondLastName;
 String secondName;
 Gender gender;
 String department;
 String birthdate;
 String workStartDate;
 String address;
 String phoneNumber;
 String mail;

}
