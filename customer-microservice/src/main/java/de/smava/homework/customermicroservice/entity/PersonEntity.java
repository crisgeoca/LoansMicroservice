package de.smava.homework.customermicroservice.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "person")
public class PersonEntity {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String city;
    private String postalCode;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private UserEntity user;
}
