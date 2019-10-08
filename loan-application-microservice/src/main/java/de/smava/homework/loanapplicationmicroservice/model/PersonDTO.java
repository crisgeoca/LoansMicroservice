package de.smava.homework.loanapplicationmicroservice.model;

import lombok.Data;

@Data
public class PersonDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String city;
    private String postalCode;
}
