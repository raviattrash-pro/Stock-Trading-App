import React, { useState } from 'react';
import axios from 'axios';

const Login = ({ setToken }) => {
  const [form, setForm] = useState({ username: '', password: '' });

  const handleLogin = async () => {
    const res = await axios.post('http://localhost:8080/api/auth/login', form);
    localStorage.setItem('token', res.data.token);
    setToken(res.data.token);
  };

  return (
    <div>
      <h2>Login</h2>
      <input placeholder="Username" onChange={e => setForm({ ...form, username: e.target.value })} />
      <input placeholder="Password" type="password" onChange={e => setForm({ ...form, password: e.target.value })} />
      <button onClick={handleLogin}>Login</button>
    </div>
  );
};

export default Login;
