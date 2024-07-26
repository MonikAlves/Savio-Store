import React, { useEffect,useState } from "react";
import { UserRoundPlus,ArrowLeftIcon } from 'lucide-react';
import { UserContext, UserProvider, useUser } from "../../contexts/UserProvider";
import { PurchaseProduct } from "../shared/PurchaseProduct";
import { NavLink } from "react-router-dom";
import { productConsumer } from "../FetchAPI/productCosumer";

export function AccountMain() {

    const {user} = useUser();
    const [ cart, setCart ] = useState([]);
    const consumer = new productConsumer(import.meta.env.VITE_PURCHASE_API_URL);

    useEffect (() => {
        
        async function bla () {
            if(user){
                try {
                    const data =  await consumer.GetPurchase(user.id);
                    setCart(data);
                } catch(error) {
                    console.log(error)
                }
            }
        }

        bla();

    }, [])

    useEffect(()=> console.log(cart), [cart])

    return (
    <div className="flex flex-row w-full max-h-[800px] justify-center gap-7 p-5 pt-6">
            <div className="flex bg-amazon-gunmetal justify-center flex-col gap-3 w-2/3 items-center p-5 border-[2px] rounded ">
                {
                    cart.length == 0 ?
                    <div className="text-white w-full p-5 rounded flex flex-col gap-5 justify-center items-center">
                            <h2 className="text-center text-5xl font-thin">
                                Você ainda não realizou nenhuma compra
                            </h2>
                            <NavLink className="flex bg-white text-black gap-2.5 p-2.5 ring-[1px] ring-white rounded hover:bg-orange-600 transition-all" to={"/products"}>
                                <ArrowLeftIcon/>
                                Continuar comprado
                            </NavLink>
                        </div>
                        :
                        <>
                            <div className="flex flex-col  text-white rounded p-2.5 gap-5">
                                <h2 className="bg-white p-2.5 text-black rounded text-xl font-bold ">Lista de Compras</h2>
                                <div className="max-h-[550px] overflow-y-auto">

                                {
                                    cart.map((item, index) => (
                                        <PurchaseProduct
                                        key={index}
                                        image={"https://exbxwvxqlnbphyieygiz.supabase.co/storage/v1/object/public/Roupas/" + item.product.image}
                                        title={item.product.name}
                                        description={item.product.description}
                                        quantity={item.quantity}
                                        price={item.total}
                                        itemPrice={item.product.price}
                                        size={item.size}
                                        date={item.date}
                                        />
                                    ))
                                }
                                </div>

                            </div>
                        </>
                }
            </div>
            <ul className="flex bg-amazon-gunmetal justify-center flex-col gap-3 w-1/3 items-center p-5 border-[2px] rounded aspect-square ">

                <li className="flex max-h-[200px] items-center justify-center bg-amazon-apricot h-1/4 w-1/4 border-[2px] rounded">
                 <UserRoundPlus className="flex h-1/2 w-1/2"/>
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
                        <h2>{user && user.legalData}</h2>
                    </li>
                </div>
                

            </ul>
        </div>
    );
}