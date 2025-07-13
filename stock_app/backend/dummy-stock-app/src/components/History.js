import React, { useEffect, useState } from 'react';
import API from '../services/api';

const History = () => {
  const [transactions, setTransactions] = useState([]);

  useEffect(() => {
    API.get('/history').then(res => setTransactions(res.data));
  }, []);

  return (
    <div>
      <h2>Transaction History</h2>
      <ul>
        {transactions.map(t => (
          <li key={t.id}>{t.type} {t.quantity} x {t.symbol} @ ₹{t.price} on {new Date(t.timestamp).toLocaleString()}</li>
        ))}
      </ul>
    </div>
  );
};

export default History;
