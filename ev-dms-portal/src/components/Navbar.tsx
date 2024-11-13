import React from 'react';
import { Link, useNavigate } from 'react-router-dom';

const Navbar: React.FC = () => {
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem('authToken');
    navigate('/login');
  };

  return (
    <nav>
      <ul>
        <li><Link to="/">Home</Link></li>
        <li><Link to="/create-vehicle">Create Electric Vehicle</Link></li>
        <li><Link to="/vehicles">View Electric Vehicles</Link></li>
        <li><Link to="/update-vehicle">Update Electric Vehicle</Link></li>
        <li><Link to="/update-vehicle-base-msrp">Update Electric Vehicle Base MSRP</Link></li>
        <li><Link to="/contact-us">Contact Us</Link></li>
        {localStorage.getItem('authToken') && (
          <li><button onClick={handleLogout}>Logout</button></li>
        )}
      </ul>
    </nav>
  );
};

export default Navbar;
