import { useContext } from "react"
import { ShoppingContext } from "../../contexts/ShoppingProvider";

export function Product({image, title, description, price}){

    //const {addToCart} = useContext(ShoppingContext); 

    const changeColor = () => {
        setColor((prevColor) => (prevColor === 'bg-white/100' ? 'bg-red-700' : 'bg-white/100'));
      };

    return (
        <div className="w-80 h-120 bg-gray-700 text-white aspect-square flex flex-col items-center p-2.5 ring-1 ring-white rounded">
            <figure className="flex flex-col">
                    <img src={image} alt={description} className="h-44" />
                </figure>
            <div className="flex flex-col items-center justify-center">
                <h2 className="text-xl flex font-bold text-center">
                    {title > 20 ? title.slice(0, 20) + '...' : title}
                </h2>
                <p className="text-center flex">
                    {description > 100 ? description.slice(0, 100) + '...' : description}
                </p>
                <p className="text-center flex flex-col font-thin text-2xl w-full">
                    $ {price}
                </p>
            </div>
            <div className="p-5 flex gap-4 flex-wrap flex-col items-center">
                <div className="flex gap-2 space-x-3">
                    <button className="w-9 text-black font-bold bg-white/100 rounded p-2 cursor-pointer text-center hover:bg-red-700 hover:text-white transition-all" onClick={changeColor}>S</button>
                    <button className="w-9 text-black font-bold bg-white/100 rounded p-2 cursor-pointer text-center hover:bg-red-700 hover:text-white transition-all">M</button>
                    <button className="w-9 text-black font-bold bg-white/100 rounded p-2 cursor-pointer text-center hover:bg-red-700 hover:text-white transition-all">L</button>
                </div>
                <button className="text-black w-full max-w-96 font-bold bg-white/100 rounded p-2 cursor-pointer text-center hover:bg-red-700 hover:text-white transition-all">Comprar</button>
                <button className="text-black w-full font-bold bg-white/100 rounded p-2 cursor-pointer text-center hover:bg-red-700 hover:text-white transition-all" /*onClick={() => addToCart(product)}*/> Adicionar ao carrinho </button>
            </div>
        </div>
    )
}
