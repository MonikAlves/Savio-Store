import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.jsx'
import './index.css'
import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import { HomeMain } from './components/modules/HomeMain.jsx';
import { AboutMain } from './components/modules/AboutMain.jsx';
import { ContactMain } from './components/modules/ContactMain.jsx';
import { LoginMain } from './components/modules/LoginMain.jsx';
import { RegisterMain } from './components/modules/RegisterMain.jsx';
import { CartMain } from './components/modules/CartMain.jsx';
import { InfoProduct } from './components/modules/InfoProduct.jsx';
import { ProductsMain } from './components/modules/ProductsMain.jsx';
import { AccountMain } from './components/modules/AccountMain.jsx';

const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
    children: [
      {
        path: "/",
        element: <HomeMain />,
      },
      {
        path: "/products",
        element: <ProductsMain />,
      },
      {
        path: "/about",
        element: <AboutMain />,
      },
      {
        path: "/contact",
        element: <ContactMain />,
      },
      {
        path: "/login",
        element: <LoginMain />,
      },
      {
        path: "/register",
        element: <RegisterMain />,
      },
      {
        path: "/cart",
        element: <CartMain />,
      },
      {
        path: "/infoproduct",
        element: <InfoProduct />,
      },
      {
        path: "/account",
        element: <AccountMain />,
      },
    ]
  },
]);


ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>,
)
