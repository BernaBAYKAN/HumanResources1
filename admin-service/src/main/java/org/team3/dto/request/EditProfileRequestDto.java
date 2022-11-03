package org.team3.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EditProfileRequestDto {

    @NotNull
    String token;
    String phone;
    String photo;
    String address;
}
