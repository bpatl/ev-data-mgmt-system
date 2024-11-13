import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import api from '../api/axios';

interface VehicleUpdateRequest {
  make: string;
  model: string;
  baseMsrp: number;
}

const UpdateVehicleBaseMsrp: React.FC = () => {
  const navigate = useNavigate();
  const [vehicleUpdateRequest, setVehicleUpdateRequest] = useState<VehicleUpdateRequest>({
    make: '',
    model: '',
    baseMsrp: 0,
  });
  const [error, setError] = useState<string | null>(null);
  const [successMessage, setSuccessMessage] = useState<string | null>(null);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setVehicleUpdateRequest({ ...vehicleUpdateRequest, [name]: value });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    // Validation for mandatory fields
    if (!vehicleUpdateRequest.make || !vehicleUpdateRequest.model || !vehicleUpdateRequest.baseMsrp) {
      setError('Please fill out all mandatory fields.');
      return;
    }

    try {
      // Send PUT request to the /update endpoint
      const response = await api.put('/vehicles/update', vehicleUpdateRequest);
      setSuccessMessage(response.data); // The success message from the server
      navigate('/vehicles'); // Navigate back to the vehicle list
    } catch (err) {
      setError('Error updating vehicle base MSRP.');
    }
  };

  return (
    <div>
      <h2>Update Electric Vehicle Base MSRP</h2>
      {error && <p style={{ color: 'red' }}>{error}</p>}
      {successMessage && <p style={{ color: 'green' }}>{successMessage}</p>}

      <form onSubmit={handleSubmit}>
        <div>
          <label>Make*</label>
          <input
            type="text"
            name="make"
            value={vehicleUpdateRequest.make}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Model*</label>
          <input
            type="text"
            name="model"
            value={vehicleUpdateRequest.model}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Base MSRP*</label>
          <input
            type="number"
            name="baseMsrp"
            value={vehicleUpdateRequest.baseMsrp}
            onChange={handleChange}
            required
            step="0.01"
          />
        </div>
        <button type="submit">Update Base MSRP</button>
      </form>
    </div>
  );
};

export default UpdateVehicleBaseMsrp;
