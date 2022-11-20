package org.team3.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.team3.repository.entity.CompanyManager;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NewCompanyRequestDto {

    @NotNull(message = "Boş bırakılamaz")
    @Size(min = 3, max = 20, message = "En az 3 karakter ve en fazla 20 karakter olabilir")
    String companyName;
    @NotNull(message = "Boş bırakılamaz")
    String title;
    @NotNull(message = "Boş bırakılamaz")
    @Size(min = 3, max = 20, message = "Sadece rakam giriniz")
    String taxNo;
    @NotNull(message = "Boş bırakılamaz")
    @Size(min = 3, max = 20, message = "Sadece rakam giriniz")
    String commercialRegistryNo;
    @NotNull(message = "Boş bırakılamaz")
    @Size(min = 3, max = 20, message = "Sadece rakam giriniz")
    String employeeNumber;
    @NotNull(message = "Boş bırakılamaz")
    @Size(min = 3, max = 50, message = "En az 3 karakter ve en fazla 50 karakter olabilir")
    String address;
    @NotNull(message = "Telefon numarası gereklidir")
    @Size(min = 10, max = 10)
    String phone;

    @Email(message = "Email formatı uygun değil")
    @NotNull(message = "Boş bırakılamaz")
    @Size(min = 3, max = 30, message = "Kullanıcı adı en az 3 karakter ve en fazla 20 karakter olabilir")
    String mail;

    String logo;

    @NotNull(message = "Boş bırakılamaz")
    @Size(min = 3, max = 20)
    String foundingDate;
   
}
