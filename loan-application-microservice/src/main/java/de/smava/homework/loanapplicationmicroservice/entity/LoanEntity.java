package de.smava.homework.loanapplicationmicroservice.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import de.smava.homework.loanapplicationmicroservice.model.LoanStatus;
import lombok.Data;

@Data
@Entity
@Table(name = "loan")
public class LoanEntity {
    @Id
    private String id;
    private Double amount;
    private Integer duration;
    private LoanStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
