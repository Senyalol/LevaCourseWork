package com.Bankomat.Bankomat.Entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "atm")
public class Atm {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "atm_id_gen")
    @SequenceGenerator(name = "atm_id_gen", sequenceName = "atm_atm_id_seq", allocationSize = 1)
    @Column(name = "atm_id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "location", nullable = false)
    private String location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    private com.Bankomat.Bankomat.Entites.Bank bank;

    @Size(max = 10)
    @NotNull
    @ColumnDefault("'active'")
    @Column(name = "status", nullable = false, length = 10)
    private String status;

    @Column(name = "last_maintenance")
    private Instant lastMaintenance;

}