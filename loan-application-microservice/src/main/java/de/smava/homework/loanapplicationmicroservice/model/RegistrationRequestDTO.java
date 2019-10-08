package de.smava.homework.loanapplicationmicroservice.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class RegistrationRequestDTO {
    @Valid
    @NotNull
    private UserDTO user;
    @Valid
    @NotNull
    private PersonDTO person;
    @Valid
    @NotNull
    private LoanDTO loan;
}
