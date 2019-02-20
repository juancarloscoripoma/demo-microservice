package com.soft.cli.clientbe.entity;

/**
 * Created by jcarlos on 07/02/2019.
 */

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/*
@NoArgsConstructor
*/

@Entity
@Getter
@Setter
@ToString(exclude = {"id"})
@Table(name = "clients")
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nit")
    private Integer nit;

    @Column(name = "reasonsocial")
    private String reasonsocial;
}
