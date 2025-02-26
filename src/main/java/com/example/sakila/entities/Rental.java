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
@Table(name="rental")
@Getter
@Setter
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="rental_id")
    @Setter(AccessLevel.NONE)
    private Integer id;

    @Column(name="rental_date")
    private Timestamp rentalDate;

    @Column(name="inventory_id")
    private Integer inventoryId;

    @Column(name="customer_id")
    private Short customerId;

    @Column(name="return_date")
    private Timestamp returnDate;

    @Column(name="staff_id")
    private Byte staffId;


}
