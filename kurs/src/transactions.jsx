import React, { useEffect, useState } from 'react';
import './transactions.css';

function TransactionalList() {
  const [transactions, setTransactions] = useState([]);
  const [error, setError] = useState(null); // Для обработки ошибок

  useEffect(() => {
    const fetchTransactions = async () => {
      try {
        const response = await fetch('http://localhost:8080/api/transactions');
        if (!response.ok) {
          throw new Error('Ошибка при загрузке транзакций');
        }
        const data = await response.json();
        setTransactions(data); // Предполагаем, что ответ — это массив транзакций
      } catch (error) {
        setError(error.message);
      }
    };

    fetchTransactions();
  }, []);

  return (
    <div className="container">
      <h1>Список транзакций</h1>
      {error && <p className="error-message">{error}</p>}
      {transactions.length === 0 ? (
        <p>Нет добавленных транзакций.</p>
      ) : (
        <ul className="transaction-list">
          {transactions.map((transaction) => (
            <li key={transaction.transaction_id} className="transaction-item">
              <p>Тип транзакции: {transaction.transactionType}</p>
              <p>Сумма: {transaction.amount} USD</p>
              <p>Дата транзакции: {new Date(transaction.transactionDate).toLocaleString()}</p>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}

export default TransactionalList;