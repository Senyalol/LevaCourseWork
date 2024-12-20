import React, { useState } from 'react';
import './EnterPage.css'; // Импортируем CSS файл для стилей
import { Link, useNavigate } from 'react-router-dom'; // Импортируем Link и useNavigate

function EnterPage() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState(null);
  const [message, setMessage] = useState('');
  const navigate = useNavigate(); // Инициализация useNavigate

  const handleSubmit = async (event) => {
    event.preventDefault();
    
    try {
      const response = await fetch('http://localhost:8080/api/users', {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
        },
      });

      const data = await response.json();
      const user = data.find(user => user.username === username && user.password === password);

      if (user) {
        setMessage(`Добро пожаловать, ${user.username}!`);
        setError(null);
        // Перенаправляем на страницу с ID пользователя
        navigate(`/start/${user.user_id}`);
      } else {
        setMessage('');
        setError('Неверный логин или пароль.');
      }
    } catch (error) {
      setError('Произошла ошибка. Попробуйте позже.');
      setMessage('');
    }
  };

  return (
    <div className="enter-page">
      <h1>Вход</h1>
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
        <div>
          <Link to="/">
            <button type="button" className="submit-button">Назад</button>
          </Link>
        </div>
        
        <button type="submit" className="submit-button">Войти</button>
      </form>
      {error && <p className="error-message">{error}</p>}
      {message && <p className="success-message">{message}</p>}
    </div>
  );
}

export default EnterPage;