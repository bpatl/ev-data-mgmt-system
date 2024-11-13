import React, { useState } from 'react';
import api from '../api/axios';
import { useNavigate } from 'react-router-dom';

const Login: React.FC = () => {
  const [username, setUsername] = useState('usgov');
  const [password, setPassword] = useState('nsa123@pwd');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleLogin = async (e: React.FormEvent) => {
    e.preventDefault();
    const token = btoa(`${username}:${password}`);

    try {
      // Test login by making a simple authenticated request
      await api.get('/vehicles', {
        headers: {
          Authorization: `Basic ${token}`,
        },
      });

      // If successful, store token in localStorage
      localStorage.setItem('authToken', token);
      navigate('/'); // Redirect to home page
    } catch (err) {
      setError('Invalid credentials or server error');
    }
  };

  return (
    <div>
      <h2>Login</h2>
      <form onSubmit={handleLogin}>
        <div>
          <label>Username</label>
          <input
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
        </div>
        <div>
          <label>Password</label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
        <button type="submit">Login</button>
      </form>
      {error && <p style={{ color: 'red' }}>{error}</p>}
    </div>
  );
};

export default Login;
