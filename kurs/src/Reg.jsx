import React, { useState } from 'react';
import './RegPage.css'; // Импортируем CSS файл для стилей
import { Link } from 'react-router-dom'; // Импортируем Link

function RegPage() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [email, setEmail] = useState('');
  const [message, setMessage] = useState('');
  const [error, setError] = useState(null);

  const handleSubmit = async (event) => {
    event.preventDefault();

    const userData = {
      username,
      password,
      email,
    };

    try {
      const response = await fetch('http://localhost:8080/api/users', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(userData),
      });

      if (response.ok) {
        setMessage('Регистрация прошла успешно!');
        setError(null);
        // Очистка полей после успешной регистрации
        setUsername('');
        setPassword('');
        setEmail('');
      } else {
        const errorData = await response.json();
        console.error('Ошибка сервера:', errorData); // Логируем ответ сервера
        setError(errorData.message || 'Ошибка при регистрации.');
        setMessage('');
      }
    } catch (error) {
      setError('Произошла ошибка. Попробуйте позже.');
      setMessage('');
    }
  };

  return (
    <div className="reg-page">
      <div>
        <Link to="/">Назад</Link>
      </div>

      <h1>Форма регистрации</h1>
      <form onSubmit={handleSubmit} className="form">
        <div className="form-group">
          <label htmlFor="username">Имя пользователя:</label>
          <input
            type="text"
            id="username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
            className="form-input"
          />
        </div>
        <div className="form-group">
          <label htmlFor="password">Пароль:</label>
          <input
            type="password"
            id="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
            className="form-input"
          />
        </div>
        <div className="form-group">
          <label htmlFor="email">Email:</label>
          <input
            type="email"
            id="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
            className="form-input"
          />
        </div>

        <div>
            <Link to="/EnterPage">
                <button type="submit" className="submit-button">Зарегистрироваться</button>
            </Link>
            
        </div>
      </form>
      {error && <p className="error-message">{error}</p>}
      {message && <p className="success-message">{message}</p>}
    </div>
  );
}

export default RegPage;