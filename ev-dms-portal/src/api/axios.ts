import axios from 'axios';

const api = axios.create({
  baseURL: 'http://127.0.0.1:8080/api',
  headers: {
    'Content-Type': 'application/json',
  },
});

// basic auth
api.interceptors.request.use((config) => {
  const token = localStorage.getItem('authToken');
  if (token) {
    config.headers['Authorization'] = `Basic ${token}`;
  }
  return config;
});

export default api;
