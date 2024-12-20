import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import './BankomatRed.css'

function BankomatRed() {
  const { id } = useParams();
  const [atm, setAtm] = useState({});
  const navigate = useNavigate();

  useEffect(() => {
    const storedAtms = JSON.parse(localStorage.getItem('atms')) || [];
    if (storedAtms[id]) {
      setAtm(storedAtms[id]);
    } else {
      navigate('/'); // Перенаправляем на главную, если банкомата нет
    }
  }, [id, navigate]);

  const handleChange = (e) => {
    setAtm({ ...atm, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const storedAtms = JSON.parse(localStorage.getItem('atms')) || [];
    storedAtms[id] = atm;
    localStorage.setItem('atms', JSON.stringify(storedAtms));
    navigate('/');
  };

  const handleDelete = () => {
    const storedAtms = JSON.parse(localStorage.getItem('atms')) || [];
    storedAtms.splice(parseInt(id, 10), 1);
    localStorage.setItem('atms', JSON.stringify(storedAtms));
    navigate('/');
  };

  return (
    <div className="container">
      <h1>Редактировать банкомат</h1>
      {Object.keys(atm).length > 0 ? (
        <form onSubmit={handleSubmit}>
          <div>
            <label htmlFor="name">Имя банкомата:</label>
            <input
              type="text"
              id="name"
              name="name"
              value={atm.name || ''}
              onChange={handleChange}
              required
            />
          </div>
          <div>
            <label htmlFor="number">Номер банкомата:</label>
            <input
              type="text"
              id="number"
              name="number"
              value={atm.number || ''}
              onChange={handleChange}
              required
            />
          </div>
          <div>
            <label htmlFor="address">Адрес банкомата:</label>
            <input
              type="text"
              id="address"
              name="address"
              value={atm.address || ''}
              onChange={handleChange}
              required
            />
          </div>
          <div>
            <label htmlFor="type">Тип банкомата:</label>
            <input
              type="text"
              id="type"
              name="type"
              value={atm.type || ''}
              onChange={handleChange}
              required
            />
          </div>
          <div>
            <label htmlFor="additionalInfo">Дополнительная информация:</label>
            <textarea
              id="additionalInfo"
              name="additionalInfo"
              value={atm.additionalInfo || ''}
              onChange={handleChange}
            />
          </div>
          <button type="submit">Сохранить</button>
          <button type="button" onClick={handleDelete}>Удалить</button>
        </form>
      ) : (
        <p>Банкомат не найден</p>
      )}
    </div>
  );
}

export default BankomatRed;