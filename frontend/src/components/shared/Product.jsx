import { useContext, useState, useEffect } from "react"
import { ShoppingContext } from "../../contexts/ShoppingProvider";
import { ShoppingCart } from 'lucide-react';

export function Product({image, title, description, price, product}){

    const {addToCart} = useContext(ShoppingContext); 

    const [clicked, setClicked] = useState(false);
    const [buttonState, setButtonState] = useState(false);
    const [showCart, setShowCart] = useState(false);

    const currentClicked = () => {
        setClicked(!clicked);
        setButtonState(clicked ? false : true);
    }

    const handleClick = () => {
        setClicked(!clicked);
        setButtonState(clicked ? false : true);
    };

    const handleAddToCartClick = () => {
        addToCart(product);
        setShowCart(true);
        setTimeout(() => {
          setShowCart(false);
        }, 2000);
      };
    
      useEffect(() => {
        if (showCart) {
        }
      }, [showCart]);

    return (
        <div className="w-80 h-[500px] bg-gray-700 text-white aspect-square p-2.5 flex flex-col items-center gap-3 ring-1 ring-white rounded">
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
                        <button className={`w-9 text-black font-bold rounded p-2 cursor-pointer text-center hover:bg-orange-500 transition-all ${clicked ? 'bg-orange-600 hover:bg-orange-600' : 'bg-white'}`} onClick={() => {setClicked(!clicked, "P"); handleClick();}}>S</button>
                        <button className={`w-9 text-black font-bold rounded p-2 cursor-pointer text-center hover:bg-orange-500 transition-all ${clicked ? 'bg-orange-600 hover:bg-orange-600' : 'bg-white'}`} onClick={() => {setClicked(!clicked, "M"); handleClick();}}>M</button>
                        <button className={`w-9 text-black font-bold rounded p-2 cursor-pointer text-center hover:bg-orange-500 transition-all ${clicked ? 'bg-orange-600 hover:bg-orange-600' : 'bg-white'}`} onClick={() => {setClicked(!clicked), "G"; handleClick();}}>L</button>
                    </div>
                    <button className="text-black w-full w-[240px] font-bold bg-white/100 rounded p-2 cursor-pointer text-center hover:bg-orange-600 transition-all">Comprar</button>
                    <button className="text-black w-full font-bold bg-white/100 rounded p-2 cursor-pointer text-center hover:bg-orange-600 transition-all" onClick={handleAddToCartClick}>Adicionar ao carrinho</button>
                </div>
                    {showCart && (
                        <div className="absolute bottom-0 h-full flex justify-center w-full">
                        <div className="animate-cart-up">
                            <ShoppingCart className="color-black"/>
                        </div>
                        </div>
                    )}
            </div>
    );
}