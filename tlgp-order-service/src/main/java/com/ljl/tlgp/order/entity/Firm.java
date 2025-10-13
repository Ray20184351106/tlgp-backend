package com.ljl.tlgp.order.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;



@Entity
@Table(name = "firm_table")
@RequiredArgsConstructor
@Getter
@Setter
public class Firm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long firmid;

    @Column(name = "firmname",nullable = false)
    private String firmname;

    @Column(name = "marketvalue")
    private String marketvalue;

    @Column(name = "markettime")
    private String markettime;

    @Column(name = "firmintro")
    private String firmintro;


}
