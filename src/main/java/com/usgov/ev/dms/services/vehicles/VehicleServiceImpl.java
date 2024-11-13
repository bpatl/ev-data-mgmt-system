package com.usgov.ev.dms.services.vehicles;

import com.usgov.ev.dms.dto.VehicleDTO;
import com.usgov.ev.dms.dto.VehicleUpdateRequestDTO;
import com.usgov.ev.dms.entity.Vehicle;
import com.usgov.ev.dms.exceptions.ResourceNotFoundException;
import com.usgov.ev.dms.repository.VehicleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final ModelMapper modelMapper;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, ModelMapper modelMapper) {
        this.vehicleRepository = vehicleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public VehicleDTO createVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = modelMapper.map(vehicleDTO, Vehicle.class);
        vehicle = vehicleRepository.save(vehicle);
        return modelMapper.map(vehicle, VehicleDTO.class);
    }

    @Override
    public VehicleDTO getVehicleById(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with this vehicle id " + id));
        return modelMapper.map(vehicle, VehicleDTO.class);
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        return vehicleRepository.findAll().stream()
                .map(vehicle -> modelMapper.map(vehicle, VehicleDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public VehicleDTO updateVehicle(Long id, VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found for id " + id));

        modelMapper.map(vehicleDTO, vehicle);
        vehicleRepository.save(vehicle);
        return modelMapper.map(vehicle, VehicleDTO.class);
    }

    @Override
    public void deleteVehicle(Long id) {
        if (!vehicleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Vehicle not found for id " + id);
        }
        vehicleRepository.deleteById(id);
    }

    @Override
    public int updateBaseMsrpOfVehicle(VehicleUpdateRequestDTO updateRequest) {
        // update any vehicle by criteria fields ,
        // In our requirements project.pdf, we want to update by make and model
        return vehicleRepository.updateFieldsByCriteria(
                updateRequest.getMake(),
                updateRequest.getModel(),
                updateRequest.getBaseMsrp()
        );
    }

}



