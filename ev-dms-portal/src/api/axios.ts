import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:58867/api',
  headers: {
    'Content-Type': 'application/json',
  },
});

// Add basic auth header if credentials are available
api.interceptors.request.use((config) => {
  const token = localStorage.getItem('authToken');
  if (token) {
    config.headers['Authorization'] = `Basic ${token}`;
  }
  return config;
});

export default api;
