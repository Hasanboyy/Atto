package com.hasanboy.Atto.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@Setter

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "terminal_number")
    private String terminalNumber;

    @Column(name = "card_number")
    private Integer cardNumber;

    private LocalDateTime date;

    private BigDecimal payment;
}
