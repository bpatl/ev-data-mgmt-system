package com.usgov.ev.dms.services.vehicles;

import java.util.List;
import com.usgov.ev.dms.dto.VehicleDTO;
import com.usgov.ev.dms.dto.VehicleUpdateRequestDTO;

public interface VehicleService {
    VehicleDTO createVehicle(VehicleDTO vehicleDTO);
    VehicleDTO getVehicleById(Long id);
    List<VehicleDTO> getAllVehicles();
    VehicleDTO updateVehicle(Long id, VehicleDTO vehicleDTO);
    void deleteVehicle(Long id);
    // let us use this method to update MSRP for a given car such as Tesla Model Y
    int updateBaseMsrpOfVehicle(VehicleUpdateRequestDTO updateRequest);

    }