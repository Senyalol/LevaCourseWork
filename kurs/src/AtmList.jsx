import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import './AtmList.css';

function AtmList() {
  const [atms, setAtms] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchAtms = async () => {
      try {
        const response = await fetch('http://localhost:8080/api/atms');
        if (!response.ok) {
          throw new Error('Ошибка при загрузке данных о банкоматах');
        }
        const data = await response.json();
        setAtms(data); // Предполагаем, что данные — это массив объектов банкоматов
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchAtms();
  }, []);

  if (loading) {
    return <p>Загрузка...</p>;
  }

  if (error) {
    return <p>Ошибка: {error}</p>;
  }

  return (
    <div className="container">
      <h1>Список банкоматов</h1>
      {atms.length === 0 ? (
        <p>Нет добавленных банкоматов.</p>
      ) : (
        <ul className="atm-list">
          {atms.map((atm) => (
            <li key={atm.atm_id} className="atm-item">
              <Link to={`/bankomat/${atm.atm_id}`}>{atm.location} - {atm.status}</Link>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}

export default AtmList;