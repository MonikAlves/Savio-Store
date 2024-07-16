import { useContext, useState } from "react"
import { ShoppingContext } from "../../contexts/ShoppingProvider";

export function Product({image, title, description, price}){

    const {addToCart} = useContext(ShoppingContext); 

    const [clicked, setClicked] = useState(false);
    const [buttonState, setButtonState] = useState(false);

    const currentClicked = () => {
        setClicked(!clicked);
        setButtonState(clicked ? false : true);
    }

    const handleClick = () => {
        setClicked(!clicked);
        setButtonState(clicked ? false : true);
    };

    return (
        <div className="w-80 h-[500px] bg-gray-700 text-white aspect-square flex flex-col items-center p-2.5 ring-1 ring-white rounded">
            <figure className="flex flex-col">
                    <img src={image} alt={description} className="h-[150px]" />
                </figure>
            <div className="flex flex-col items-center h-full">
                <h2 className="text-xl flex font-bold text-center">
                    {title.length > 22 ? title.slice(0, 22) + '...' : title}
                </h2>
                <p className="text-center text-sm flex">
                    {description.length > 80 ? description.slice(0, 80) + '...' : description}
                </p>
                <p className="text-center flex h-full justify-center flex-col font-thin text-2xl w-full">
                    $ {price}
                </p>
            </div>
            <div className="flex h-full justify-end">
                <div className="p-5 flex gap-4 flex-wrap flex-col items-center justify-end">
                    <div className="flex gap-2 space-x-3">
                        <button className={`w-9 text-black font-bold rounded p-2 cursor-pointer text-center hover:bg-orange-500 transition-all ${clicked ? 'bg-orange-600 hover:bg-orange-600' : 'bg-white'}`} onClick={() => {setClicked(!clicked, "P"); handleClick();}}>S</button>
                        <button className={`w-9 text-black font-bold rounded p-2 cursor-pointer text-center hover:bg-orange-500 transition-all ${clicked ? 'bg-orange-600 hover:bg-orange-600' : 'bg-white'}`} onClick={() => {setClicked(!clicked, "M"); handleClick();}}>M</button>
                        <button className={`w-9 text-black font-bold rounded p-2 cursor-pointer text-center hover:bg-orange-500 transition-all ${clicked ? 'bg-orange-600 hover:bg-orange-600' : 'bg-white'}`} onClick={() => {setClicked(!clicked), "G"; handleClick();}}>L</button>
                    </div>
                    <button className="text-black w-full max-w-96 font-bold bg-white/100 rounded p-2 cursor-pointer text-center hover:bg-orange-600 transition-all">Comprar</button>
                    <button className="text-black w-full font-bold bg-white/100 rounded p-2 cursor-pointer text-center hover:bg-orange-600 transition-all" /*onClick={() => addToCart(product)}*/> Adicionar ao carrinho </button>
                </div>
            </div>
            
        </div>
    )
}

//`w-9 text-black font-bold bg-white/100 rounded p-2 cursor-pointer text-center hover:bg-red-700 hover:text-white transition-all"
