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
@Table(name="store")
@Getter
@Setter
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="store_id")
    @Setter(AccessLevel.NONE)
    private Byte id;

    @Column(name="manager_staff_id")
    private Byte managerStaffId;

    @Column(name="address_id")
    private Short addressId;



}
