package com.oauth2_openId.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

@Getter
@Setter

@Entity
@Table(name = "authentication_methods")
public class AuthenticationMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "authentication_method")
    private String authenticationMethod;

    @ManyToOne
    private Client client;


}
