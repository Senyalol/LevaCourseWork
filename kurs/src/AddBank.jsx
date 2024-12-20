import React, { useState } from 'react';
import './AddBank.css'; // Импортируем CSS файл для стилей

function AddBank() {
  const [bank, setBank] = useState({
    name: '',
    address: '',
    contactNumber: '',
  });
  const [message, setMessage] = useState('');
  const [error, setError] = useState(null);

  const handleChange = (e) => {
    setBank({ ...bank, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const bankData = {
      
      name: bank.name,
      address: bank.address,
      contactNumber: bank.contactNumber,
    };

    // Логируем данные, которые отправляем
    console.log('Отправляемые данные:', bankData);

    try {
      const response = await fetch('http://localhost:8080/api/banks', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(bankData),
      });

      if (response.ok) {
        setMessage('Банк успешно добавлен!');
        setError(null);
        // Очистка полей после успешного добавления
        setBank({ name: '', address: '', contactNumber: '' });
      } else {
        const errorData = await response.json();
        console.error('Ошибка при добавлении банка:', errorData); // Логируем ошибку
        setError(`Ошибка: ${errorData.error || 'Непредвиденная ошибка'}`);
        setMessage('');
      }
    } catch (error) {
      console.error('Ошибка сети:', error);
      setError('Произошла ошибка. Попробуйте позже.');
      setMessage('');
    }
  };

  return (
    <div className="add-bank-container">
      <h1>Добавить банк</h1>
      <form onSubmit={handleSubmit} className="add-bank-form">
        <div>
          <label>Имя банка:</label>
          <input
            type="text"
            name="name"
            value={bank.name}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Адрес банка:</label>
          <input
            type="text"
            name="address"
            value={bank.address}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Контактный номер:</label>
          <input
            type="text"
            name="contactNumber"
            value={bank.contactNumber}
            onChange={handleChange}
            required
          />
        </div>
        <button type="submit">Добавить</button>
      </form>
      {error && <p className="error-message">{error}</p>}
      {message && <p className="success-message">{message}</p>}
    </div>
  );
}

export default AddBank;