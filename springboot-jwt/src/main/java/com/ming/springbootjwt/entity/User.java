package com.ming.springbootjwt.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by echisan on 2018/6/23
 */
@Entity
@Table(name = "jd_user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

}

