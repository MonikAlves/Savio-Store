import { Outlet } from 'react-router-dom'
import { Header } from './components/shared/Header'
import { ShoppingProvider } from './contexts/ShoppingProvider'


function App() {

  return (
    <ShoppingProvider>
      <div className='min-h-screen bg-black/80'>
        <Header />
        <Outlet />
      </div>
    </ShoppingProvider>
  )
}

export default App
