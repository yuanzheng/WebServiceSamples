import React, {useState} from 'react'
import './App.css'
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import Home from "./components/home/Home";
import Products from "./components/products/Products";
import Navbar from './components/shared/Navbar'
import About from "./components/About";
import Contact from "./components/Contact";
import {Toaster} from 'react-hot-toast'
import Cart from "./components/cart/Cart";
import PrivateRoute from './components/PrivateRoute'
import LogIn from "./components/auth/Login";

function App() {
    const [count, setCount] = useState(0);
  return (
      <React.Fragment>
          <Toaster position='top-center'/>
          <Router>
              <Navbar />
              <Routes>
                  <Route path='/' element={ <Home />}/>
                  <Route path='/products' element={ <Products />}/>
                  <Route path='/about' element={ <About />}/>
                  <Route path='/contact' element={ <Contact />}/>
                  <Route path='/cart' element={ <Cart />}/>
                  <Route path='/' element={<PrivateRoute publicPage />}>
                      <Route path='/login' element={ <LogIn />}/>
                  </Route>
              </Routes>
          </Router>
          <Toaster position='bottom-center'/>
      </React.Fragment>
  )
}

export default App
