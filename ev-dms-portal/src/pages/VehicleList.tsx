import React, { useEffect, useState } from 'react';
import api from '../api/axios';

// All fields in CSV
interface Vehicle {
  id: number;
  vin: string;
  county?: string;
  city?: string;
  state?: string;
  postalCode?: string;
  modelYear: number;
  make: string;
  model: string;
  electricVehicleType?: string;
  cafvEligibility?: string;
  electricRange?: number;
  baseMsrp?: number;
  legislativeDistrict?: number;
  dolVehicleId?: number;
  vehicleLocation?: string;
  electricUtility?: string;
  censusTract?: number;
}

const VehicleList: React.FC = () => {
  const [vehicles, setVehicles] = useState<Vehicle[]>([]);

  useEffect(() => {
    const fetchVehicles = async () => {
      try {
        const response = await api.get('/vehicles');
        setVehicles(response.data);
      } catch (err) {
        console.error('Error fetching vehicles:', err);
      }
    };
    fetchVehicles();
  }, []);

  return (
    <div>
      <h2>Electric  Vehicles List</h2>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>VIN</th>
            <th>County</th>
            <th>City</th>
            <th>State</th>
            <th>Postal Code</th>
            <th>Model Year</th>
            <th>Make</th>
            <th>Model</th>
            <th>Electric Vehicle Type</th>
            <th>CAFV Eligibility</th>
            <th>Electric Range</th>
            <th>Base MSRP</th>
            <th>Legislative District</th>
            <th>DOL Vehicle ID</th>
            <th>Vehicle Location</th>
            <th>Electric Utility</th>
            <th>Census Tract</th>
          </tr>
        </thead>
        <tbody>
          {vehicles.map((vehicle) => (
            <tr key={vehicle.id}>
              <td>{vehicle.id}</td>
              <td>{vehicle.vin}</td>
              <td>{vehicle.county}</td>
              <td>{vehicle.city}</td>
              <td>{vehicle.state}</td>
              <td>{vehicle.postalCode}</td>
              <td>{vehicle.modelYear}</td>
              <td>{vehicle.make}</td>
              <td>{vehicle.model}</td>
              <td>{vehicle.electricVehicleType}</td>
              <td>{vehicle.cafvEligibility}</td>
              <td>{vehicle.electricRange}</td>
              <td>{vehicle.baseMsrp}</td>
              <td>{vehicle.legislativeDistrict}</td>
              <td>{vehicle.dolVehicleId}</td>
              <td>{vehicle.vehicleLocation}</td>
              <td>{vehicle.electricUtility}</td>
              <td>{vehicle.censusTract}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default VehicleList;
