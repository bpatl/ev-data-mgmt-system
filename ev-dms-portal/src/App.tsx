import React from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import Header from './components/Header';
import Footer from './components/Footer';
import Navbar from './components/Navbar';
import Home from './pages/Home';
import CreateVehicle from './pages/CreateVehicle';
import VehicleList from './pages/VehicleList';
import UpdateVehicle from './pages/UpdateVehicle';
import UpdateVehicleBaseMsrp from './pages/UpdateVehicleBaseMsrp';
import ContactUs from './pages/ContactUs';
import Login from './pages/Login';

const isAuthenticated = () => {
  return !!localStorage.getItem('authToken');
};

const PrivateRoute = ({ element }: { element: JSX.Element }) => {
  return isAuthenticated() ? element : <Navigate to="/login" replace />;
};

const App: React.FC = () => {
  return (
    <Router>
      <Header />
      <Navbar />
      <Routes>
        <Route path="/" element={<PrivateRoute element={<Home />} />} />
        <Route path="/create-vehicle" element={<PrivateRoute element={<CreateVehicle />} />} />
        <Route path="/vehicles" element={<PrivateRoute element={<VehicleList />} />} />
        <Route path="/update-vehicle" element={<PrivateRoute element={<UpdateVehicle />} />} />
        <Route path="/update-vehicle-base-msrp" element={<PrivateRoute element={<UpdateVehicleBaseMsrp />} />} />
        <Route path="/contact-us" element={<ContactUs />} />
        <Route path="/login" element={<Login />} />
      </Routes>
      <Footer />
    </Router>
  );
};

export default App;
