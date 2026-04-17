import React, {useState} from 'react'
import './App.css'
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import Home from "./components/home/Home";
import Products from "./components/products/Products";
import Navbar from './components/shared/Navbar'

function App() {
    const [count, setCount] = useState(0);
  return (
      <Router>
          <Navbar />
          <Routes>
              <Route path='/' element={ <Home />}/>
              <Route path='/products' element={ <Products />}/>
          </Routes>
      </Router>
  )
}

export default App
