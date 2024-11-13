package com.usgov.ev.dms.controllers;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.usgov.ev.dms.dto.VehicleDTO;
import com.usgov.ev.dms.dto.VehicleUpdateRequestDTO;
import com.usgov.ev.dms.services.vehicles.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {
    private static final Logger logger = LoggerFactory.getLogger(VehicleController.class);

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService ) {
        this.vehicleService = vehicleService;

    }

    @PostMapping
    public ResponseEntity<VehicleDTO> createVehicle(@Valid @RequestBody VehicleDTO vehicleDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleService.createVehicle(vehicleDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDTO> getVehicleById(@PathVariable Long id) {
        return ResponseEntity.ok(vehicleService.getVehicleById(id));
    }

    @GetMapping
    public ResponseEntity<List<VehicleDTO>> getAllVehicles() {
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleDTO> updateVehicle(@PathVariable Long id, @Valid @RequestBody VehicleDTO vehicleDTO) {
        return ResponseEntity.ok(vehicleService.updateVehicle(id, vehicleDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateBaseMsrpForVehicle(@RequestBody VehicleUpdateRequestDTO updateRequest) {
        logger.info("updateVehicle () called ...");
        int updatedRows = vehicleService.updateBaseMsrpOfVehicle(updateRequest);

        if (updatedRows > 0) {
            logger.info("{} vehicles updated successfully.", updatedRows);
            return ResponseEntity.ok("Vehicle(s) updated successfully.");
        } else {
            logger.info("No vehicles were updated.");
            return ResponseEntity.notFound().build();
        }
    }

    // more api endpoints to be added
}
