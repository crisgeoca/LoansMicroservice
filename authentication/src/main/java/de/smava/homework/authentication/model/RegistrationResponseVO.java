package de.smava.homework.authentication.model;

import lombok.Value;

@Value
public class RegistrationResponseVO {
    private String userId;
    private String firstName;
    private String lastName;
    private String loanId;
    private LoanStatus loanStatus;
}
