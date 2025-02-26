package com.example.sakila.entities;

import jakarta.persistence.*;
import jakarta.websocket.server.ServerEndpoint;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="staff")
@Getter
@Setter
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="staff_id")
    @Setter(AccessLevel.NONE)
    private Byte id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="address_id")
    private Short addressId;

    /* -> blob in sql
    @Column(name="picture")
    private String picture;
    */

    @Column(name="email")
    private String email;

    @Column(name="store_id")
    private Byte storeId;

    @Column(name="active")
    private Byte active;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;


}
