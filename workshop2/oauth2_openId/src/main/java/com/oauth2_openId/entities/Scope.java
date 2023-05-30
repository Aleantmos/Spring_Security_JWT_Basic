package com.oauth2_openId.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "scopes")
public class Scope {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String scope;

    @ManyToOne
    private Client client;
}
