import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './Header';
import BankomatInfo from './BankomatInfo';
import BankomatAdd from './BankomatAdd';
import BankomatRed from './BankomatRed';
import AtmList from './AtmList'; 
import BankomatDetail from './BankomatDetail';
import RegPage from './Reg';
import EnterPage from './EnterPage';
import TransactionalList from './transactions';
import BankInfo from './BankInfo';
import AddBank from './AddBank';
import './App.css'; 

function App() {
  return (
    <Router>
      <div className="App">
        <Routes>
          <Route path="/AddBank/:id" element={<AddBank/>}/>
          <Route path="/BankInfo/:id" element={<BankInfo/>}/>
          <Route path="/start/:id" element={<BankomatInfo />} />
          <Route path="/add/:id" element={<BankomatAdd />} />
          <Route path="/edit/:id" element={<BankomatRed />} />
          <Route path="/atms/:id" element={<AtmList />} />
          <Route path="/bankomat/:id" element={<BankomatDetail />} /> 
          <Route path="/transactions/:id" element={<TransactionalList/>}/>
          
          <Route path="/" element={<Navbar/>} />
          <Route path="/RegPage" element={<RegPage/>} />
          <Route path="/EnterPage" element={<EnterPage/>}/>
        </Routes>
      </div>
    </Router>
  );
}

export default App;