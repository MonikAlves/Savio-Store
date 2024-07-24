import { Outlet } from 'react-router-dom'
import { Header } from './components/shared/Header'
import { ShoppingProvider } from './contexts/ShoppingProvider'
import { UserProvider } from './contexts/UserProvider'
import { BrowserRouter, Routes, Route } from 'react-router-dom'

function App() {

  return (
    <ShoppingProvider>
    <UserProvider>
        <div className='min-h-screen bg-black/80'>
            <Header />
            <Outlet />
        </div>
    </UserProvider>
    </ShoppingProvider>
  )
}

export default App
