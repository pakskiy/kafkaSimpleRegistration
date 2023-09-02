package com.pakskiy.kafkasimpleregistration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequestDto {
    @NotBlank
    @Size(min = 3, message = "Wrong first name length")
    @JsonProperty("first_name")
    private String firstName;

    @NotBlank
    @Size(min = 3, message = "Wrong last name length")
    @JsonProperty("last_name")
    private String lastName;

    @Email(message = "Wrong email format")
    @Size(min = 3, message = "Wrong email length")
    @JsonProperty("email")
    private String email;
}