package com.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String password;

    private int fullName;

    @OneToMany(mappedBy = "account")
    private List<AccountRole> accountRoles;
}
