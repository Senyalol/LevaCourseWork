import React, { useState, useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import './BankomatInfo.css';

function BankomatInfo() {
  const [atms, setAtms] = useState([]);
  const {id} = useParams();

  useEffect(() => {
    const storedAtms = JSON.parse(localStorage.getItem('atms')) || [];
    setAtms(storedAtms);
  }, []);

  return (
    <div className="container">
      <h1>Информация о банкоматах</h1>
      <div className="button-group">
        <Link to={`/atms/${id}`}> 
          <button>Банкоматы</button>
        </Link>
        <Link to={`/add/${id}`}>
          <button>Добавить банкомат</button>
        </Link>
        <Link to={`/transactions/${id}`}>
          <button>Транзакции</button>
        </Link>
        <Link to={`/BankInfo/${id}`}>
          <button>Информация о банках</button> 
        </Link>
        <Link to={`/AddBank/${id}`}>
          <button>Добавить банк</button>
        </Link>
      </div>
    </div>
  );
}

export default BankomatInfo;