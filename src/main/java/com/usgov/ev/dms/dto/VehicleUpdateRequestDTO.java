package com.usgov.ev.dms.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class VehicleUpdateRequestDTO {
    private String make;
    private String model;
    private BigDecimal baseMsrp;
    // lets see if we need more DTOs for every api call
}
