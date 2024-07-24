import { useContext, useRef, useState, useEffect } from "react"
import { ShoppingContext } from "../../contexts/ShoppingProvider";
import { ShoppingCart } from 'lucide-react';
import { useUser } from "../../contexts/UserProvider";
import { productConsumer } from "../FetchAPI/productCosumer";
import Message from "./Message";


export function Product({image, title, description, price, product}){
    const { user } = useUser()
    const {addToCart} = useContext(ShoppingContext); 
    const consumer = new productConsumer(import.meta.env.VITE_PRODUCT_API_URL)
    const audioRef = useRef(null);
    const [selectedButton, setSelectedButton] = useState(null);
    const [buttonState, setButtonState] = useState(false);
    const [showCart, setShowCart] = useState(false);
    const [ message, setMessage ] = useState("")
    const [ type, setType ] = useState("")

    const handleButtonClick = (buttonId) => {
        console.log(buttonId)
        if(selectedButton === buttonId) {
            setSelectedButton(null);
        }
        else {
            setSelectedButton(buttonId);
        }
    };

    const getButtonClasses = (buttonId) => {
        return `w-9 text-black font-bold rounded p-2 cursor-pointer text-center transition-all ${selectedButton === buttonId ? 'bg-orange-600' : 'bg-white hover:bg-orange-500'}`;
    };


    const handleAddToCartClick = async () => {
        setShowCart(true);
        
        setTimeout(() => {
          setShowCart(false);
        }, 1000);

        addToCart(product);

        if (audioRef.current) { 
            audioRef.current.volume = 0.1;
            //audioRef.current.currentTime = 0; // Reinicia o áudio se ele já estiver tocando
            audioRef.current.play().catch(error => {
              console.error("Erro ao tentar reproduzir o áudio: ", error);
            });
        }
        /*
            vc tem que pegar e exibir esse carrinho na tela
        */
        if(user){
            console.log(user.id)
            try {
                const blabla =  await consumer.GetCart(user.id);
                console.log(blabla)
            } catch(error) {
                console.log(error)
            }
        }

        const parametro = {idClient: user.id, idProduto: product.id, Tamanho: selectedButton};
        if(setSelectedButton) {
            try {
                const response = await consumer.addCart(parametro)
                console.log(response)
                setMessage(response.error)
                setType("sucess")

            } catch (error) {
                console.log(error)
                setMessage(error)
                setType("error")
            }
        } else {
            setMessage("Nenhum tamanho selecionado")
            setType("error")
        }

        setSelectedButton("") 
             
      };

    return (
        <div className="w-80 h-[500px] bg-gray-700 text-white aspect-square p-2.5 flex flex-col items-center gap-3 ring-1 ring-white rounded">
            {message && type && (
                <Message message ={message} type= {type}/>
            )}
            
            <audio ref={audioRef} src="public/cart_sound.mp3"/>
            <figure className="flex bg-gray-500 flex-col ring-1 ring-white w-[170px] h-[180px]">
                <img src={image} alt={description} className="h-[160px]"/>
            </figure>
            <div className="flex flex-col gap-1 items-center">
                <h2 className="text-xl flex font-bold text-center">
                    {title.length > 22 ? title.slice(0, 22) + '...' : title}
                </h2>
                <p className="text-center text-sm flex">
                    {description.length > 80 ? description.slice(0, 80) + '...' : description}
                </p>
            </div>
                <p className="text-center flex flex-col font-thin text-2xl w-full">
                    $ {price}
                </p>
                <div className="p-5 flex gap-3 flex-wrap flex-col h-full items-center justify-end">
                    <div className="flex gap-2 space-x-3">
                        <button className={getButtonClasses('P')} onClick={() => handleButtonClick('P')}>P</button>
                        <button className={getButtonClasses('M')} onClick={() => handleButtonClick('M')}>M</button>
                        <button className={getButtonClasses('G')} onClick={() => handleButtonClick('G')}>G</button>
                    </div>
                    <button className="text-black w-full w-[240px] font-bold bg-white/100 rounded p-2 cursor-pointer text-center hover:bg-orange-600 transition-all">Comprar</button>
                    <button className="text-black w-full font-bold bg-white/100 rounded p-2 cursor-pointer text-center hover:bg-orange-600 transition-all" onClick={handleAddToCartClick}>Adicionar ao carrinho</button>
                </div>
                    {showCart && (
                        <div className="absolute h-full flex justify-center w-full">
                        <div className="animate-cart-up">
                            <ShoppingCart className="h-[50px] w-[50px] text-orange-500"/>
                        </div>
                        </div>
                    )}
            </div>
    );
}