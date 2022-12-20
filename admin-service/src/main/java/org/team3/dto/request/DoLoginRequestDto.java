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
    @Size(min = 3)
    @Email(message = "Email formatı uygun değil")
    String mail;
    @NotNull(message = "Boş bırakılamaz")
    String password;
}
