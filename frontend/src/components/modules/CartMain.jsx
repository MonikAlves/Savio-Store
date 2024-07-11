import { useContext } from "react"
import { ShoppingContext } from "../../contexts/ShoppingProvider";
import { CartProduct } from "../shared/CartProduct";
import { NavLink } from "react-router-dom";
import { ArrowLeftIcon } from "lucide-react";

export function CartMain() {

    const { state } = useContext(ShoppingContext);

    const summaries = [
        { label: 'Quantidade de produtos:', value: state.reduce((acc, item) => acc + item.quantity, 0) },
        { label: 'Data da compra:', value: new Date().toLocaleDateString() },
        { label: 'Status do pedido:', value: 'Comprando' },
        { label: 'Valor total:', value: `$${state.reduce((acc, item) => acc + item.price * item.quantity, 0).toFixed(2)}`, isBold: true }
    ]

    return (
        <main className="flex-1 p-5 flex flex-col gap-10 pt-32 justify-center items-center">
            <h1 className='text-4xl font-semibold text-black/80 w-full text-center p-2.5 border-b-[1px] border-b-black/30'>Carrinho</h1>
            <div className="flex gap-5">
                {
                    state.length == 0 ?
                        <div className="bg-slate-100 w-full p-5 rounded flex flex-col gap-5 justify-center items-center">
                            <h2 className="text-center text-5xl font-thin">
                                Seu carrinho está vazio
                            </h2>
                            <NavLink className="flex gap-2.5 p-2.5 ring-[1px] ring-black rounded hover:bg-black hover:text-white transition-all" to={"/products"}>
                                <ArrowLeftIcon/>
                                Continuar comprado
                            </NavLink>
                        </div>
                        :
                        <>
                            <div className="flex flex-col ring-[1px] ring-black/30 rounded p-2.5 gap-5">
                                <h2 className="bg-slate-100 p-2.5 rounded text-xl font-semibold">Lista de Produtos</h2>
                                {
                                    state.map((product, index) => (
                                        <CartProduct
                                            key={index}
                                            image={product.image}
                                            title={product.title}
                                            description={product.description}
                                            quantity={product.quantity}
                                            price={product.price}
                                        />
                                    ))}

                            </div>
                            <div className="flex flex-col h-fit ring-[1px] ring-black/30 rounded p-2.5 min-w-80">
                                <h2 className="bg-slate-100 p-2.5 rounded text-xl font-semibold">Resumo do pedido</h2>
                                {
                                    summaries.map((summary, index) => (
                                        <div key={index} className={`flex justify-between p-2.5 ${summary.isBold && "font-bold"}`}>
                                            <span>
                                                {summary.label}
                                            </span>
                                            <span>
                                                {summary.value}
                                            </span>
                                        </div>
                                    ))
                                }
                                <button className="bg-black/85 text-white p-2.5 rounded font-bold hover:bg-black transition-all">
                                    Finalizar compra
                                </button>
                            </div>
                        </>
                }
            </div>
        </main>
    )
}
