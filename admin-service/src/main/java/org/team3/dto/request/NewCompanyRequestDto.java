package org.team3.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NewCompanyRequestDto {

    String companyName;
    String title;
    String taxNo;
    String commercialRegistryNo;
    String employeeNumber;
    String address;
    String phone;
    String mail;
    String logo;
    String foundingDate;
}
