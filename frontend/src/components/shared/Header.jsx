import React, { useContext, useState } from 'react'
import { NavLink } from 'react-router-dom'
import { ShoppingContext } from '../../contexts/ShoppingProvider';
import { FilePen, LogIn, Pencil, ShoppingCart, User } from 'lucide-react';
import logo from '/S-logo.png';
import { useUser } from "../../contexts/UserProvider";

export function Header() {
    const { logout } = useUser();
    const { user } = useUser();
    const { isLoggedIn } = useUser();
    const { state } = useContext(ShoppingContext);

    const LOGOUT = () => {
        console.log("desloguei")
        logout();
    }

    const navItems = [
        { to: '/', label: 'Home' },
        { to: '/products', label: 'Products' },
        { to: '/about', label: 'About' },
        { to: '/contact', label: 'Contact' },
    ];

    const [userActions, setUserActions] = useState([
        { to: '/login', label: 'Login', icon: <LogIn /> },
        { to: '/register', label: 'Register', icon: <User /> },
    ]);

    const addCart = () => {
        const newAction = { to: '/cart', label: `Carrinho (${state.reduce((acc, item) => acc + item.quantity, 0)})`, icon: <ShoppingCart /> };
        setUserActions((prevActions) => [...prevActions, newAction]);
    };

    const cartIcon = [
        { to: '/cart', label: `Carrinho (${state.reduce((acc, item) => acc + item.quantity, 0)})`, icon: <ShoppingCart /> },
    ];


    return (
        <header className='flex items-center flex-col w-full'>
            <div className='flex w-full p-2 justify-between bg-amazon-eerie'>
                <NavLink to="/" className="text-3xl justify-start text-white font-bold flex gap-2.5 items-center">
                    <img src={logo} alt="Savio´Store" className="w-12 aspect-square" />
                    <span className='hover:text-amazon-orange transition-all'>
                        Savio´Store
                    </span>
                </NavLink>
                    
                    {!isLoggedIn && (
                    <div className='flex gap-2.5 justify-end'>
                    {
                        userActions.map((item, index) => (
                            <NavLink key={index} to={item.to} className="transition-all text-white ring-black rounded py-1 px-2 text-sm hover:text-amazon-orange flex items-center gap-2">
                                {item.icon && item.icon}
                                {item.label}
                            </NavLink>
                        ))
                    }
                    </div>
                    )}

                    {isLoggedIn && (
                    <div className='flex gap-2.5 justify-end'>
                        <NavLink to={"/about"} className="transition-all text-white ring-black rounded py-1 px-2 text-sm hover:text-amazon-orange flex items-center gap-2">
                            <User />
                            {user.email}
                        </NavLink>
                        <NavLink to={"/cart"} className="transition-all text-white ring-black rounded py-1 px-2 text-sm hover:text-amazon-orange flex items-center gap-2">
                            <ShoppingCart/>
                            Carrinho
                        </NavLink>
                        <button onClick={LOGOUT} className="transition-all text-white ring-black rounded py-1 px-2 text-sm hover:text-amazon-orange flex items-center gap-2">
                            <LogIn />
                            Logout
                        </button>

                    </div>
                    )}

            </div>
            
            <div className="flex w-full flex-row p-1 items-center border-b-[1px] border-amazon-eerie bg-amazon-gunmetal justify-center">
                <nav>
                    <ul className='flex flex-row w-full gap-20 text-lg'>
                        {navItems.map((item, index) => (
                            <li key={index}>
                                <NavLink to={item.to} className="hover:text-amazon-orange text-white transition-all hover:ring-black-rounded" >
                                    {item.label}
                                </NavLink>
                            </li>
                        ))}
                    </ul>
                </nav>
                
            </div>
        </header>
    )
}
