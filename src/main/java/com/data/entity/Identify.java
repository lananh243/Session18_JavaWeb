package com.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Identify {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String idenNumber;

    private Date createdAt;

    @OneToOne
    @JoinColumn(name = "person_id", unique = true)
    private Person person;
}
