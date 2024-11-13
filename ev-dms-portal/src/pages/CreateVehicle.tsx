import React, { useState } from 'react';
import api from '../api/axios';

interface Vehicle {
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

const CreateVehicle: React.FC = () => {
  const [vehicle, setVehicle] = useState<Vehicle>({
    vin: '',
    modelYear: 1886,
    make: '',
    model: '',
  });
  const [error, setError] = useState('');

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setVehicle({ ...vehicle, [name]: value });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    // validation
    if (!vehicle.vin || !vehicle.make || !vehicle.model || !vehicle.modelYear) {
      setError('Please fill out all mandatory fields.');
      return;
    }

    try {
      await api.post('/vehicles', vehicle);
      setError('Vehicle created successfully!');
    } catch (err) {
      setError('Error creating vehicle.');
    }
  };

  return (
    <div>
      <h2>Create Electric Vehicle</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>VIN*</label>
          <input type="text" name="vin" value={vehicle.vin} onChange={handleChange} required />
        </div>
        <div>
          <label>Model Year*</label>
          <input type="number" name="modelYear" value={vehicle.modelYear} onChange={handleChange} required />
        </div>
        <div>
          <label>Make*</label>
          <input type="text" name="make" value={vehicle.make} onChange={handleChange} required />
        </div>
        <div>
          <label>Model*</label>
          <input type="text" name="model" value={vehicle.model} onChange={handleChange} required />
        </div>
        <button type="submit">Create Electric Vehicle</button>
      </form>
      {error && <p style={{ color: error === 'Vehicle created successfully!' ? 'green' : 'red' }}>{error}</p>}
    </div>
  );
};

export default CreateVehicle;
