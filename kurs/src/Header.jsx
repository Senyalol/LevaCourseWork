import React from 'react';
import { Link } from 'react-router-dom'; // Импортируем Link
import logo from './logo.png';
import './Header.css';

function Header() {
  return (
    <header>
      <div className="header-container">
        <div className="logo-container">
          <img src={logo} alt="Логотип ATM's Union" />
        </div>
        <div className="title">ATM's Union</div>
        <div className="buttons">
          <Link to="/EnterPage" className="button">
            <button>Вход</button> 
          </Link>
          <Link to="/RegPage" className="button">
            <button>Регистрация</button>
          </Link>
        </div>
      </div>
    </header>
  );
}

export default Header;