package com.hasanboy.Atto.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter

@Entity
@Table
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String surname;

    @Column(length = 8,unique = true)
    private String card_number;

    private BigDecimal balance;

    private Boolean status;

    private LocalDate date;
}
