package org.team3.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DoLoginRequestDto {
    @NotNull(message = "Boş bırakılamaz")
    @Size(min = 3, max = 20, message = "Kullanıcı adı en az 3 karakter ve en fazla 20 karakter olabilir")
    @Email(message = "Email formatı uygun değil")
    String mail;
    @NotNull(message = "Boş bırakılamaz")
    String password;
}
