package com.Makushev.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Asset { // // активы пользователей

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double quantity;

    private double buyPrice;

    @ManyToOne
    private Coin coin;

    @ManyToOne
    private User user;
}
