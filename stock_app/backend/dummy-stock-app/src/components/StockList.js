import React, { useEffect, useState } from 'react';
import API from '../services/api';

const StockList = () => {
  const [stocks, setStocks] = useState([]);

  useEffect(() => {
    API.get('/stocks').then((res) => setStocks(res.data));
  }, []);

  const buyStock = (symbol) => {
    const qty = prompt('Enter quantity to buy:');
    API.post(`/orders/buy?symbol=${symbol}&quantity=${qty}`).then(() => alert('Bought!'));
  };

  const sellStock = (symbol) => {
    const qty = prompt('Enter quantity to sell:');
    API.post(`/orders/sell?symbol=${symbol}&quantity=${qty}`).then(() => alert('Sold!'));
  };

  return (
    <div>
      <h2>Stocks</h2>
      <table>
        <thead><tr><th>Symbol</th><th>Name</th><th>Price</th><th>Actions</th></tr></thead>
        <tbody>
          {stocks.map(s => (
            <tr key={s.symbol}>
              <td>{s.symbol}</td>
              <td>{s.name}</td>
              <td>{s.price}</td>
              <td>
                <button onClick={() => buyStock(s.symbol)}>Buy</button>
                <button onClick={() => sellStock(s.symbol)}>Sell</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default StockList;
