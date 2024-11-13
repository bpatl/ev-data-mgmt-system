package com.usgov.ev.dms.services;

import com.usgov.ev.dms.dto.VehicleDTO;
import com.usgov.ev.dms.entity.Vehicle;
import com.usgov.ev.dms.repository.VehicleRepository;
import com.usgov.ev.dms.services.vehicles.VehicleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VehicleServiceTest {

    @Mock
    private VehicleRepository vehicleRepository;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private VehicleServiceImpl vehicleService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createVehicleRespondWithVehicleDTO() {
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setMake("Tesla");
        vehicleDTO.setModel("Model Y");
        Vehicle vehicle = new Vehicle();
        when(modelMapper.map(vehicleDTO, Vehicle.class)).thenReturn(vehicle);
        when(vehicleRepository.save(vehicle)).thenReturn(vehicle);
        when(modelMapper.map(vehicle, VehicleDTO.class)).thenReturn(vehicleDTO);
        VehicleDTO result = vehicleService.createVehicle(vehicleDTO);
        assertEquals(vehicleDTO, result);
        verify(vehicleRepository, times(1)).save(vehicle);
    }

    @Test
    void getVehicleById () {
        Long vehicleId = 1L;
        Vehicle vehicle = new Vehicle();
        vehicle.setId(vehicleId);
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setId(vehicleId);
        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(vehicle));
        when(modelMapper.map(vehicle, VehicleDTO.class)).thenReturn(vehicleDTO);
        VehicleDTO result = vehicleService.getVehicleById(vehicleId);
        assertEquals(vehicleDTO, result);
        verify(vehicleRepository, times(1)).findById(vehicleId);
    }
}
