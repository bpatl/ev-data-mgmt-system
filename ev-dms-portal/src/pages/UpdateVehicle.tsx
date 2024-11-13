import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import api from '../api/axios';

// All cols in csv as fields
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

const UpdateVehicle: React.FC = () => {
  const navigate = useNavigate();
  const [vehicleId, setVehicleId] = useState<number | null>(null); // Holds the selected vehicle ID
  const [vehicle, setVehicle] = useState<Vehicle>({
    id: 0,
    vin: '',
    modelYear: 1886,
    make: '',
    model: '',
  });
  const [allVehicles, setAllVehicles] = useState<Vehicle[]>([]); // Holds the list of all vehicles
  const [error, setError] = useState<string | null>(null);
  const [successMessage, setSuccessMessage] = useState<string | null>(null);

  // Fetch all vehicles to populate the dropdown list
  useEffect(() => {
    const fetchAllVehicles = async () => {
      try {
        const response = await api.get<Vehicle[]>('/vehicles');
        setAllVehicles(response.data);
      } catch (err) {
        setError('Error fetching vehicle list.');
      }
    };
    fetchAllVehicles();
  }, []);

  // Fetch a specific vehicle data when a vehicle ID is selected
  useEffect(() => {
    if (vehicleId !== null) {
      const fetchVehicle = async () => {
        try {
          const response = await api.get<Vehicle>(`/vehicles/${vehicleId}`);
          setVehicle(response.data);
        } catch (err) {
          setError('Error fetching vehicle data.');
        }
      };
      fetchVehicle();
    }
  }, [vehicleId]);

  const handleDropdownChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    setVehicleId(Number(e.target.value)); // Update the vehicleId state
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setVehicle({ ...vehicle, [name]: value });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    // Validation for mandatory fields
    if (!vehicle.vin || !vehicle.make || !vehicle.model || !vehicle.modelYear) {
      setError('Please fill out all mandatory fields.');
      return;
    }

    try {
      // Use vehicleId for PUT request
      await api.put(`/vehicles/${vehicle.id}`, vehicle);
      setSuccessMessage('Vehicle updated successfully!');
      navigate('/vehicles'); // Navigate back to the vehicle list
    } catch (err) {
      setError('Error updating vehicle.');
    }
  };

  return (
    <div>
      <h2>Update Electric Vehicle</h2>
      {error && <p style={{ color: 'red' }}>{error}</p>}
      {successMessage && <p style={{ color: 'green' }}>{successMessage}</p>}

      {/* Dropdown to select vehicle by ID */}
      <div>
        <label>Select Vehicle</label>
        <select value={vehicleId ?? ''} onChange={handleDropdownChange}>
          <option value="">-- Select a Vehicle --</option>
          {allVehicles.map((vehicleOption) => (
            <option key={vehicleOption.id} value={vehicleOption.id}>
              {vehicleOption.id} - {vehicleOption.vin}
            </option>
          ))}
        </select>
      </div>

      {/* If a vehicle is selected, show the update form */}
      {vehicleId !== null && (
        <form onSubmit={handleSubmit}>
          <div>
            <label>VIN*</label>
            <input
              type="text"
              name="vin"
              value={vehicle.vin}
              onChange={handleChange}
              required
            />
          </div>
          <div>
            <label>Model Year*</label>
            <input
              type="number"
              name="modelYear"
              value={vehicle.modelYear}
              onChange={handleChange}
              required
            />
          </div>
          <div>
            <label>Make*</label>
            <input
              type="text"
              name="make"
              value={vehicle.make}
              onChange={handleChange}
              required
            />
          </div>
          <div>
            <label>Model*</label>
            <input
              type="text"
              name="model"
              value={vehicle.model}
              onChange={handleChange}
              required
            />
          </div>
          {/* Add other fields here as optional */}
          <button type="submit">Update Electric Vehicle</button>
        </form>
      )}
    </div>
  );
};

export default UpdateVehicle;
