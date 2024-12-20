package com.Bankomat.Bankomat.Entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "bank")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bank_id_gen")
    @SequenceGenerator(name = "bank_id_gen", sequenceName = "bank_bank_id_seq", allocationSize = 1)
    @Column(name = "bank_id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Size(max = 255)
    @Column(name = "address")
    private String address;

    @Size(max = 15)
    @Column(name = "contact_number", length = 15)
    private String contactNumber;

}