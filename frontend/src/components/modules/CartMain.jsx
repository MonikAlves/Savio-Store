import { useContext, useEffect, useState } from "react"
import { ShoppingContext, ShoppingProvider } from "../../contexts/ShoppingProvider";
import { CartProduct } from "../shared/CartProduct";
import { NavLink } from "react-router-dom";
import { ArrowLeftIcon} from "lucide-react";
import { productConsumer} from "../FetchAPI/productCosumer";
import { UserProvider, UserContext, useUser } from "../../contexts/UserProvider";
import { BallTriangle } from 'react-loader-spinner';
import Warning from "../shared/Warning";


export function CartMain() {
    
    const {user} = useUser();
    const [warning, setWarning] = useState(false)
    const [message, setMessage] = useState(""); 
    const [ cart, setCart ] = useState([]);
    const consumer = new productConsumer(import.meta.env.VITE_PRODUCT_API_URL);
    const [isLoading,setLoading] = useState(false);

    async function encapBuyAll(idClient){

        try{
            const errorBack = await consumer.buyAll(idClient)
            fetchCart();
            handleWarning(errorBack.error)
            
        }catch(error){
            handleWarning(error.message)
        }
    }

    const handleWarning = (message) => {
        setWarning(true)
        setMessage(message)
        setTimeout(() => {
            setWarning(false);
          }, 1000);
    }

    async function fetchCart() {
        if(user){
            try {
                const data =  await consumer.GetCart(user.id);
                setCart(data);
                setLoading(true)
            } catch(error) {
                handleWarning(error.message)
            }
        }
    }
    
    useEffect (() => {
        fetchCart();
    }, [user])
    
    const summaries = [
        { label: 'Quantidade de produtos:', value: cart.reduce((acc, item) => acc + item.quantity, 0) },
        { label: 'Data da compra:', value: new Date().toLocaleDateString() },
        { label: 'Status do pedido:', value: 'Comprando' },
        { label: 'Valor total:', value: `R$ ${cart.reduce((acc, item) => acc + item.total, 0).toFixed(2)}`, isBold: true }
    ]

    return (
        <>
            {
                !isLoading ? (
                    <div className="flex justify-center items-center">
                <BallTriangle  width="200" color="#EB0A0A"/>
                </div>
            ):(
        <main className="flex-1 p-5 flex flex-col gap-10 justify-center items-center">
            <h1 className='text-4xl font-semibold text-white w-full text-center p-2.5'>Carrinho</h1>

            { warning ? <Warning message = {message} /> : ""}

            <div className="flex gap-5">
                {
                    cart.length == 0 ?
                        <div className="text-white ring ring-white w-full p-5 rounded flex flex-col gap-5 justify-center items-center">
                            <h2 className="text-center text-5xl font-thin">
                                Seu carrinho está vazio
                            </h2>
                            <NavLink className="flex bg-white text-black gap-2.5 p-2.5 ring-[1px] ring-white rounded hover:bg-orange-600 transition-all" to={"/products"}>
                                <ArrowLeftIcon/>
                                Continuar comprado
                            </NavLink>
                        </div>
                     : 

                             <>
                                <div className="flex flex-col ring-[1px] ring-white text-white rounded p-2.5 gap-5">
                                    <h2 className="bg-white p-2.5 text-black rounded text-xl font-bold">Lista de Produtos</h2>
                                        <div className="max-h-[550px] overflow-y-auto">

                                        {
                                            cart.slice().reverse().map((item, index) => (
                                                <CartProduct
                                                key={index}
                                                image={"https://exbxwvxqlnbphyieygiz.supabase.co/storage/v1/object/public/Roupas/" + item.product.image}
                                                title={item.product.name}
                                                description={item.product.description}
                                                quantity={item.quantity}
                                                price={item.total}
                                                itemPrice={item.product.price}
                                                size={item.size}
                                                available={item.available}
                                                idCart={item.id}
                                                deleted={fetchCart}
                                                />
                                            ))
                                        }
                                    </div>
                                </div>

                            <div className="flex flex-col h-fit ring-[1px] ring-white rounded p-2.5 min-w-80">
                                <h2 className="bg-white p-2.5 rounded text-xl font-bold">Resumo do pedido</h2>
                                {
                                    summaries.map((summary, index) => (
                                        <div key={index} className={`flex justify-between text-white p-2.5 ${summary.isBold && "font-bold"}`}>
                                            <span>
                                                {summary.label}
                                            </span>
                                            <span>
                                                {summary.value}
                                            </span>
                                        </div>
                                    ))
                                }
                                <button className="bg-white text-black p-2.5 rounded font-bold hover:bg-orange-600 transition-all" onClick = {() => encapBuyAll(user.id)}>
                                    Finalizar compra
                                </button>
                            </div>
                        </>
                
                }
            </div>
        </main>
        )}
        </>
    )
}

