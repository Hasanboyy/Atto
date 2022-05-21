package com.hasanboy.Atto.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter

@Entity
@Table(name = "terminal")
public class Terminal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 8,unique = true)
    private String terminal_number;

    private BigDecimal balance;

    private String adress;

    private Boolean status;

    private LocalDate date;
}
