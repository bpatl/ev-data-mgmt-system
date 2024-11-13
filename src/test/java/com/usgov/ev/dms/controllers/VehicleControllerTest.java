package com.usgov.ev.dms.controllers;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import com.usgov.ev.dms.dto.VehicleDTO;
import com.usgov.ev.dms.dto.VehicleUpdateRequestDTO;
import com.usgov.ev.dms.services.vehicles.VehicleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

// Tests for controller using mockito
class VehicleControllerTest {

    @Mock
    private VehicleService vehicleService;
    @InjectMocks
    private VehicleController vehicleController;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void createVehicleReturnsACreatedVehicle() {
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setMake("Tesla");
        vehicleDTO.setModel("Model Y");
        when(vehicleService.createVehicle(vehicleDTO)).thenReturn(vehicleDTO);
        ResponseEntity<VehicleDTO> response = vehicleController.createVehicle(vehicleDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(vehicleDTO, response.getBody());
        verify(vehicleService, times(1)).createVehicle(vehicleDTO);
    }

    @Test
    void getVehicleByIdGetsAVehicleIfFound() {
        Long vehicleId = 1L;
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setId(vehicleId);
        when(vehicleService.getVehicleById(vehicleId)).thenReturn(vehicleDTO);
        ResponseEntity<VehicleDTO> response = vehicleController.getVehicleById(vehicleId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(vehicleDTO, response.getBody());
        verify(vehicleService, times(1)).getVehicleById(vehicleId);
    }

    @Test
    void getAllVehiclesAListOfVehicles() {
        VehicleDTO vehicle1 = new VehicleDTO();
        VehicleDTO vehicle2 = new VehicleDTO();
        List<VehicleDTO> vehicles = Arrays.asList(vehicle1, vehicle2);
        when(vehicleService.getAllVehicles()).thenReturn(vehicles);
        ResponseEntity<List<VehicleDTO>> response = vehicleController.getAllVehicles();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(vehicles, response.getBody());
        verify(vehicleService, times(1)).getAllVehicles();
    }

    @Test
    void updateVehicleAUpdatedVehicleResponded() {
        Long vehicleId = 1L;
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setId(vehicleId);
        when(vehicleService.updateVehicle(vehicleId, vehicleDTO)).thenReturn(vehicleDTO);
        ResponseEntity<VehicleDTO> response = vehicleController.updateVehicle(vehicleId, vehicleDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(vehicleDTO, response.getBody());
        verify(vehicleService, times(1)).updateVehicle(vehicleId, vehicleDTO);
    }

    @Test
    void deleteVehicle() {
        Long vehicleId = 1L;
        doNothing().when(vehicleService).deleteVehicle(vehicleId);
        ResponseEntity<Void> response = vehicleController.deleteVehicle(vehicleId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(vehicleService, times(1)).deleteVehicle(vehicleId);
    }

    @Test
    void updateBaseMsrpOfAVehicle () {
        VehicleUpdateRequestDTO updateRequest = new VehicleUpdateRequestDTO();
        updateRequest.setMake("Tesla");
        updateRequest.setModel("Model Y");
        updateRequest.setBaseMsrp(BigDecimal.valueOf (60000.00));
        int updatedRows = 1;
        when(vehicleService.updateBaseMsrpOfVehicle(updateRequest)).thenReturn(updatedRows);
        ResponseEntity<String> response = vehicleController.updateBaseMsrpForVehicle(updateRequest);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Vehicle(s) updated successfully.", response.getBody());
        verify(vehicleService, times(1)).updateBaseMsrpOfVehicle(updateRequest);
    }

    @Test
    void updateBaseMsrpForVehicleOnANoVehicleMatched() {
        VehicleUpdateRequestDTO updateRequest = new VehicleUpdateRequestDTO();
        updateRequest.setMake("Tesla");
        updateRequest.setModel("Model Y");
        updateRequest.setBaseMsrp(BigDecimal.valueOf (60000.00));
        int updatedRows = 0;
        when(vehicleService.updateBaseMsrpOfVehicle(updateRequest)).thenReturn(updatedRows);
        ResponseEntity<String> response = vehicleController.updateBaseMsrpForVehicle(updateRequest);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(vehicleService, times(1)).updateBaseMsrpOfVehicle(updateRequest);
    }
}
