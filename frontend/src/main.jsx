import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.jsx'
import './index.css'
import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import { ProductsMain } from './components/modules/ProductsMain.jsx';
import { AboutMain } from './components/modules/AboutMain.jsx';
import { ContactMain } from './components/modules/ContactMain.jsx';
import { LoginMain } from './components/modules/LoginMain.jsx';
import { RegisterMain } from './components/modules/RegisterMain.jsx';
import { CartMain } from './components/modules/CartMain.jsx';
<<<<<<< HEAD
import { InfoProduct } from './components/modules/InfoProduct.jsx';
=======
>>>>>>> e19ed83de4b40e80f57e07f67e5b12ef1563c95a

const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
    children: [
      {
        path: "/",
        element: <ProductsMain />,
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
    ]
  },
]);


ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>,
)
