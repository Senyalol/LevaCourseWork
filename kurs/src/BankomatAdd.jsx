import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './BankomatAdd.css';

function BankomatAdd() {
  const [atm, setAtm] = useState({
    location: '',
    bank_id: '', // Изначально пустое значение для выбора банка
    status: '',
    lastMaintenance: new Date().toISOString(),
  });
  
  const [banks, setBanks] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchBanks = async () => {
      try {
        const response = await fetch('http://localhost:8080/api/banks');
        if (response.ok) {
          const data = await response.json();
          setBanks(data);
        } else {
          console.error('Ошибка при загрузке банков');
        }
      } catch (error) {
        console.error('Ошибка сети:', error);
      }
    };

    fetchBanks();
  }, []);

  const handleChange = (e) => {
    setAtm({ ...atm, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    const atmData = {
      location: atm.location,
      bank_id: Number(atm.bank_id), // Преобразуем bank_id в число
      status: atm.status,
      lastMaintenance: new Date(atm.lastMaintenance).toISOString(), // Форматируем дату в ISO 8601
    };

    console.log('Отправляемые данные:', atmData); // Логируем данные перед отправкой

    try {
      const response = await fetch('http://localhost:8080/api/atms', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(atmData),
      });

      if (response.ok) {
        // navigate('/');
        alert("Банкомат занесен в Базу данных");
      } else {
        const errorData = await response.json();
        console.error('Ошибка:', errorData);
        alert('Ошибка при добавлении банкомата.');
      }
    } catch (error) {
      console.error('Ошибка сети:', error);
      alert('Произошла ошибка. Попробуйте позже.');
    }
  };

  return (
    <div className="container">
      <h1>Добавить банкомат</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Локация банкомата:</label>
          <input
            type="text"
            name="location"
            value={atm.location}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Банк:</label>
          <select
            name="bank_id"
            value={atm.bank_id}
            onChange={handleChange}
            required
          >
            <option value="">Выберите банк</option>
            {banks.map((bank) => (
              <option key={bank.bank_id} value={bank.bank_id}>
                {bank.name}
              </option>
            ))}
          </select>
        </div>
        <div>
          <label>Статус банкомата:</label>
          <input
            type="text"
            name="status"
            value={atm.status}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Последнее обслуживание:</label>
          <input
            type="datetime-local"
            name="lastMaintenance"
            value={atm.lastMaintenance.slice(0, 16)}
            onChange={handleChange}
            required
          />
        </div>
        <button type="submit">Добавить</button>
      </form>
    </div>
  );
}

export default BankomatAdd;