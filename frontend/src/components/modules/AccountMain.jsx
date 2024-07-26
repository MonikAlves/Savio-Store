import React, { useEffect,useState } from "react";
import { UserRoundPlus,ArrowLeftIcon } from 'lucide-react';
import { UserContext, UserProvider, useUser } from "../../contexts/UserProvider";
import { PurchaseProduct } from "../shared/PurchaseProduct";
import { NavLink } from "react-router-dom";
import { productConsumer } from "../FetchAPI/productCosumer";
import{ BallTriangle } from 'react-loader-spinner';

export function AccountMain() {

    const {user} = useUser();
    const [ cart, setCart ] = useState([]);
    const [filteredCart, setFilteredCart] = useState([]);
    const [uniqueDates, setUniqueDates] = useState([]);
    const [isLoading,setLoading] = useState(false);
    const consumer = new productConsumer(import.meta.env.VITE_PURCHASE_API_URL);

    useEffect (() => {
        
        async function fetch () {
            if(user){
                try {
                    const data =  await consumer.GetPurchase(user.id);
                    setCart(data);
                    setFilteredCart(data); 
                    const dates = [...new Set(data.map(item => item.date))].sort((a, b) => new Date(b) - new Date(a));
                    setUniqueDates(dates)
                    setLoading(true)
                } catch(error) {
                    console.log(error)
                }
            }
        }

        fetch();

    }, [])

    useEffect(()=> console.log(cart), [cart])

    function formatDate(dateString) {
        const date = new Date(dateString);
        date.setMinutes(date.getMinutes() + date.getTimezoneOffset());
        return date.toLocaleDateString('pt-BR', {
            day: '2-digit',
            month: '2-digit',
            year: 'numeric'
        });
    }

    function filterByDate(selectedDate) {
        if (selectedDate) {
            const filtered = cart.filter(item => item.date === selectedDate);
            setFilteredCart(filtered);
        } else {
            setFilteredCart(cart);
        }
    }

    return (
        <>
            {
                !isLoading ? (
                    <div className="flex justify-center items-center">
                <BallTriangle  width="200" color="#EB0A0A"/>
                </div>
            ):(
                <div className="flex flex-row w-full max-h-[800px] justify-center gap-7 p-5 pt-6">
                    <div className="flex bg-amazon-gunmetal justify-center flex-col gap-3 w-2/3 items-center p-5 border-[2px] rounded">
                <div className="flex w-full justify-between items-center">
                    <h2 className="flex-1 bg-white p-2.5 mr-4 text-black rounded text-xl font-bold">Lista de Compras</h2>
                    <select
                        onChange={(e) => filterByDate(e.target.value)}
                        className="p-2.5 rounded text-black bg-white hover:bg-gray-200 transition-all"
                    >
                        <option value="">Todas as datas</option>
                        {uniqueDates.map(date => (
                            <option key={date} value={date}>
                                {formatDate(date)}
                            </option>
                        ))}
                    </select>
                </div>
                <div className="max-h-[550px] overflow-y-auto w-full">
                    {filteredCart.length === 0 ?
                        <div className="text-white w-full p-5 rounded flex flex-col gap-5 justify-center items-center">
                            <h2 className="text-center text-5xl font-thin">
                                Nenhuma compra encontrada
                            </h2>
                            <NavLink className="flex bg-white text-black gap-2.5 p-2.5 ring-[1px] ring-white rounded hover:bg-orange-600 transition-all" to={"/products"}>
                                <ArrowLeftIcon />
                                Continuar comprando
                            </NavLink>
                        </div>
                        :
                        filteredCart.slice().reverse().map((item, index) => (
                            <PurchaseProduct
                            key={index}
                            image={"https://exbxwvxqlnbphyieygiz.supabase.co/storage/v1/object/public/Roupas/" + item.product.image}
                            title={item.product.name}
                            description={item.product.description}
                            quantity={item.quantity}
                            price={item.total}
                            itemPrice={item.product.price}
                            size={item.size}
                            date={formatDate(item.date)}
                            />
                        ))
                    }
                    </div>
                </div>
                    <ul className="flex bg-amazon-gunmetal justify-center flex-col gap-3 w-1/3 items-center p-5 border-[2px] rounded aspect-square ">
                    <li className="flex max-h-[200px] items-center justify-center bg-amazon-apricot h-1/4 w-1/4 border-[2px] rounded">
                    <UserRoundPlus className="flex h-1/2 w-1/2" />
                    </li>
                        <div className="flex w-full flex-col">
                            <p className="flex p-1 text-white">User :</p>
                            <li className="flex p-2.5 text-white items-center bg-amazon-eerie h-[40px] w-full border-[2px] rounded">
                                <h2>{user && user.name}</h2>
                            </li>
                        </div>
                        <div className="flex w-full flex-col">
                            <p className="flex p-1 text-white">Email :</p>
                            <li className="flex p-2.5 text-white items-center bg-amazon-eerie h-[40px] w-full border-[2px] rounded">
                                <h2>{user && user.email}</h2>
                            </li>
                        </div>
                        <div className="flex w-full flex-col">
                            <p className="flex p-1 text-white">Phone :</p>
                            <li className="flex p-2.5 text-white items-center bg-amazon-eerie h-[40px] w-full border-[2px] rounded">
                                <h2>{user && user.phone}</h2>
                            </li>
                        </div>
                        <div className="flex w-full flex-col">
                        <p className="flex p-1 text-white">LegalData :</p>
                            <li className="flex p-2.5 text-white items-center bg-amazon-eerie h-[40px] w-full border-[2px] rounded">
                            <   h2>{user && user.legalData}</h2>
                            </li>
                        </div>
                    </ul>
                </div>
            )
        }
        </>
    );
}