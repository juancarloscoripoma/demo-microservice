package com.soft.cli.oauthservice.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "application_client")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"oauthClientDetails"})
public class ApplicationClient {
    @NotNull
    @Size(min = 2, max = 256)
    @Id
    @Column(name = "clientId",length = 256)
    private String client;

    @Size(min = 2,max = 150)
    @Column(name = "name",length = 150)
    private String name;

    @Size(min = 2,max = 100)
    @Column(name = "clientSecret",length = 100)
    private String clientSecret;

    private Boolean ui;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "clientId", referencedColumnName = "clientId")
    private OauthClientDetails oauthClientDetails;

}
