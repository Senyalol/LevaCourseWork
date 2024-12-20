import React, { useEffect, useState } from 'react';
import './BankInfo.css'; // Импортируем CSS файл для стилей

function BankInfo() {
  const [banks, setBanks] = useState([]);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchBanks = async () => {
      try {
        const response = await fetch('http://localhost:8080/api/banks');
        if (!response.ok) {
          throw new Error('Ошибка при загрузке данных');
        }
        const data = await response.json();
        setBanks(data); // Предполагаем, что ответ — это массив банков
      } catch (error) {
        setError(error.message);
      }
    };

    fetchBanks();
  }, []);

  return (

    <div className="bank-info">
        
      <h1>Информация о банках</h1>
      {error && <p className="error-message">{error}</p>}
      {banks.length > 0 ? (
        <ul className="bank-list">
          {banks.map(bank => (
            <li key={bank.bank_id} className="bank-item">
              <h2>{bank.name}</h2>
              <p>Адрес: {bank.address}</p>
              <p>Контактный номер: {bank.contactNumber}</p>
            </li>
          ))}
        </ul>
      ) : (
        <p>Загрузка банков...</p>
      )}
    </div>
  );
}

export default BankInfo;