package com.oauth2_openId.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "client_id")
    private String clientId;

    private String secret;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<GrantType> grantTypes;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<RedirectUrl> redirectUrls;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<Scope> scopes;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<AuthenticationMethod> authenticationMethods;

    @OneToOne(mappedBy = "client")
    private ClientTokenSettings clientTokenSettings;
}
