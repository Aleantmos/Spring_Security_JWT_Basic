package com.oauth2_openId.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "token_settings")
public class ClientTokenSettings {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "access_token_ttl")
    private int accessTokenTTL;

    private String type;

    @OneToOne
    private Client client;
}
