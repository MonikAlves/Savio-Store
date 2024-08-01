
import { useContext, useEffect, useState } from "react"
import { productConsumer } from "../FetchAPI/productCosumer";
import {Trash2} from "lucide-react";
import Warning from "./Warning";

export function CartProduct({ image, title, description, quantity, price, itemPrice, size, available,idCart,deleted}) {
    const consumer = new productConsumer(import.meta.env.VITE_PRODUCT_API_URL)
    const [warning, setWarning] = useState(false)
    const [message, setMessage] = useState(""); 


    const handleWarning = (message,callback) => {
        setWarning(true)
        setMessage(message)
        if (callback) {
            callback();
        }
    }

    async function encapDeleteCart(idCart){
        try {
            const errorBack = await consumer.deleteCart(idCart); 
            handleWarning("Produto retirado do carrinho", () => {
                setTimeout(() => {
                    deleted()
                }, 1500);
            });
            
        } catch(error) {
            console.log(error)
        }    
    }
    
    async function buy(idCart){
            try {
                const errorBack = await consumer.buy(idCart); 
                handleWarning(errorBack.error)
                setTimeout(() => {
                    deleted()
                }, 1500);
            } catch(error) {
                console.log(error)
            }

    }

    function availableText(available) {
        return (
            available ? 
                <p className="text-green-500">
                    Produto disponível
                </p> 
            : 
            <p className="text-red-500">
                    Produto não disponível
            </p> 
        )
      }
    return (
        <div className="w-full flex items-center gap-5 p-2.5 border-b-[1px] border-b-black/30">
            
            { warning ? <Warning message = {message} /> : ""}

            <img src={image} alt={description} className="w-32" />
            <div className=" w-full flex flex-col gap-2.5">
                <div className="flex justify-between">

                <h2 className="text-xl font-bold">
                    {title}
                </h2>
                <buttom  className="cursor-pointer" onClick={() => encapDeleteCart(idCart)}>
                    <Trash2 className="w-8 h-8" color="#ff5252"/>
                </buttom>
                </div>
                <p>  {availableText(available)} </p>
                <p> {description} </p>
                <div className="flex felx-wrap justify-between">
                    <div>
                    <p> Preço do item: {`R$ ${itemPrice.toFixed(2)}`}</p>
                    <p> Tamanho Escolhido: {size}</p>
                    <p> Quantidade escolhida: {quantity} </p>
                    <p> Total: {`R$ ${price.toFixed(2)}`}</p>
                    </div>    
                        <button className="text-black w-60 h-10 font-bold bg-white/100 rounded p-2 cursor-pointer self-end hover:bg-orange-600 transition-all" onClick={() => buy(idCart)} >Comprar</button>
                </div>
            </div>
        </div>
    )
}
