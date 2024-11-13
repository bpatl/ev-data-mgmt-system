package com.usgov.ev.dms.entity;

import java.math.BigDecimal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String vin;
    private String county;
    private String city;
    private String state;
    private String postalCode;
    private Integer modelYear;
    private String make;
    private String model;
    @Column(name = "electric_vehicle_type")
    private String electricVehicleType;
    @Column(name = "cafv_eligibility")
    private String cafvEligibility;
    private Integer electricRange;
    @Column(name = "base_msrp")
    private BigDecimal baseMsrp;
    @Column(name = "legislative_district")
    private Integer legislativeDistrict;
    @Column(name = "dol_vehicle_id", unique = true)
    private Long dolVehicleId;
    private String vehicleLocation;
    private String electricUtility;
    private Long censusTract;
}

// we just need one entity , other comes in this folder