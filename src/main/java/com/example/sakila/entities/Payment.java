package com.example.sakila.entities;

import jakarta.persistence.*;
import jakarta.websocket.server.ServerEndpoint;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="payment")
@Getter
@Setter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="payment_id")
    @Setter(AccessLevel.NONE)
    private Short id;

    @Column(name="customer_id")
    private Short customerId;

    @Column(name="staff_id")
    private Byte staffId;

    @Column(name="rental_id")
    private Byte rentalId;

    @Column(name="amount")
    private Float amount;

    @Column(name="payment_date")
    private Timestamp paymentDate;



}
