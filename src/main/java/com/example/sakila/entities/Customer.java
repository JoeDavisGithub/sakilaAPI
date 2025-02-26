package com.example.sakila.entities;
import java.sql.Timestamp;
import jakarta.persistence.*;
import jakarta.websocket.server.ServerEndpoint;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="customer")
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id")
    @Setter(AccessLevel.NONE)
    private Short id;

    @Column(name="store_id")
    private Byte storeId;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Formula("concat(first_name,' ',last_name)")
    @Setter(AccessLevel.NONE)
    private String fullName;

    @Column(name="email")
    private String email;

    @Column(name="address_id")
    private Short addressId;

    @Column(name="active")
    private Byte active;

    //ASK ABOUT THIS
    /*
    @Column(name="create_date")
    private Timestamp createDate;
     */
}
