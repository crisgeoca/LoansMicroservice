package de.smava.homework.customermicroservice.model;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class LoanDTO {
    private String id;
    @NotNull
    private Double amount;
    @NotNull
    private Integer duration;
    private LoanStatus status;
}