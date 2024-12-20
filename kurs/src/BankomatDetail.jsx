// BankomatDetail.jsx

import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import './BankomatDetail.css'; // Создайте файл стилей для этого компонента

function BankomatDetail() {
  const { id } = useParams();
  const [atm, setAtm] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    const storedAtms = JSON.parse(localStorage.getItem('atms')) || [];
    if (storedAtms[id]) {
      setAtm(storedAtms[id]);
    } else {
      navigate('/'); // Перенаправляем на главную, если банкомата нет
    }
  }, [id, navigate]);

  if (!atm) {
    return <p>Загрузка...</p>; // Или любое другое сообщение о загрузке
  }

  return (
    <div className="container">
      <h1>{atm.name}</h1>
      <p><strong>Номер:</strong> {atm.number}</p>
      <p><strong>Адрес:</strong> {atm.address}</p>
      <p><strong>Тип:</strong> {atm.type}</p>
      <p><strong>Дополнительная информация:</strong> {atm.additionalInfo}</p>
      <button onClick={() => navigate(`/edit/${id}`)}>Редактировать</button>
    </div>
  );
}

export default BankomatDetail;