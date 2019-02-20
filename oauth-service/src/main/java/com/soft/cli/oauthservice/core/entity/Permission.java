package com.soft.cli.oauthservice.core.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "permissions")
@Getter
@Setter
@NoArgsConstructor
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "permissionsid")
    private Integer id;

    @Column(name = "useridfrom")
    private Integer userIdFrom;

    @Column(name = "useridto")
    private Integer userIdTo;

    @Column(name = "createddate")
    private Date createdDate;

    @Column(name = "deadline")
    private Date deadline;

    @Column(name = "current")
    private Boolean current;

}
