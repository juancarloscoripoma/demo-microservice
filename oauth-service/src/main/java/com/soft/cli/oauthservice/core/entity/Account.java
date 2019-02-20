package com.soft.cli.oauthservice.core.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"authorities"})
public class Account implements Serializable {

    @NotNull
    @Size(min = 0, max = 50)
    @Id
    @Column(name = "username",length = 50)
    private String userName;

    @NotNull
    @Size(min = 0,max = 150)
    @Column(name = "password",length = 150,nullable = false)
    @JsonIgnore
    private String password;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "id")
    private Integer id;

    @ManyToMany
    @JoinTable(name = "authorities",
            joinColumns = @JoinColumn(name="username", referencedColumnName="userName"),
            inverseJoinColumns = @JoinColumn(name="authority", referencedColumnName="name"))
    private List<Authority> authorities = new ArrayList<>();

    public Account(String userName, String password,
                   Boolean enabled, Boolean active, Integer id) {
        this.userName = userName;
        this.password = password;
        this.enabled = enabled;
        this.id = id;
    }

}
