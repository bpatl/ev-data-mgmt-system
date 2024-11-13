package com.usgov.ev.dms.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import jakarta.validation.constraints.*;
@Getter
@Setter

public class VehicleDTO {
    private Long id;

    @NotBlank(message = "VIN is required")
    private String vin;

    private String county;
    private String city;
    private String state;
    private String postalCode;

    @Min(value = 2000)
    private Integer modelYear;

    @NotBlank()
    private String make;

    @NotBlank()
    private String model;

    private String electricVehicleType;
    private String cafvEligibility;
    private Integer electricRange;
    private BigDecimal baseMsrp;
    private Integer legislativeDistrict;
    private Long dolVehicleId;
    private String vehicleLocation;
    private String electricUtility;
    private Long censusTract;

}
