import React, { useEffect, useState } from 'react';
import API from '../services/api';

const Portfolio = () => {
  const [data, setData] = useState({ balance: 0, portfolio: {} });

  useEffect(() => {
    API.get('/portfolio').then(res => setData(res.data));
  }, []);

  return (
    <div>
      <h2>Portfolio</h2>
      <p>Balance: ₹{data.balance}</p>
      <ul>
        {Object.entries(data.portfolio).map(([symbol, qty]) => (
          <li key={symbol}>{symbol}: {qty} shares</li>
        ))}
      </ul>
    </div>
  );
};

export default Portfolio;
