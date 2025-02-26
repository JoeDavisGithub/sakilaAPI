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
@Table(name="address")
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="address_id")
    @Setter(AccessLevel.NONE)
    private Short id;

    @Column(name="address")
    private String address;

    @Column(name="address2")
    private String address2;

    @Column(name="district")
    private String district;

    @Column(name="city_id")
    private Short cityId;

    @Column(name="postal_code")
    private String postalCode;

    @Column(name="phone")
    private String phone;

    /*
    @Column(name="location")
    private ;
     */
}
