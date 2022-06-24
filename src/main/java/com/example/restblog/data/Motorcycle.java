package com.example.restblog.data;

import lombok.ToString;

import javax.persistence.*;

@ToString
@Entity
@Table(name="motorcycles")
public class Motorcycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String model;
    @Column(nullable = false)
    private String year;
    @Column(nullable = false)
    private String cc;
}
