import React from "react";

export function InfoProduct ({image, title, description, price, product}) {

    const handleAddToCartClick = () => {
        addToCart(product);

        if (audioRef.current) { 
            audioRef.current.volume = 0.1;
            audioRef.current.play().catch(error => {
              console.error("Erro ao tentar reproduzir o áudio: ", error);
            });
        }

        setShowCart(true);
        setTimeout(() => {
          setShowCart(false);
        }, 1000);
      };

    return (
        <div className="p-5 pt-16 flex flex-row items-center h-full w-full">
            <div className="flex w-[400px] h-full">
                <figure className="flex border-[2px] gap-5 rounded h-[400px] bg-amazon-apricot p-2.5 flex-col items-center">
                    <img src="/public/luiz.png" alt={description} className="h-[300px] w-[250px]border-[2px] border-black rounded"/>
                    <span className="flex font-bold text-xl">
                        dadmadkamlmlmkmklmd slakdmalkdmlakmds
                        {title}
                    </span>
                </figure>
            </div>
            
            <div className="flex flex-col h-full">
                <h2 className="flex p-3.5 h-full w-[500px] font-semibold">
                A Savio´Store é uma loja virtual que visa trazer um maior conforto aos seus clientes,
            com nossos produtos de alta qualidade e preços imbatíveis no mercado.
            Seja você também um de nossos clientes de sorte e aproveite a experiência promovida
            por nossa marca, que está há mais de 50 anos tornando a vida das pessoas mais confortável.
                    {description}
                </h2>
            </div>

            <div className="flex flex-col items-center h-full">
                <figure className="flex gap-3 flex-col bg-amazon-apricot p-5 h-[400px] w-[300px] aspect-square rounded border-[2px] b-black">
                    <h2 className="flex items-left">R${price}</h2>
                    <div className="flex justify-end flex-col h-full gap-3">
                        <button className="text-black w-full justify-end font-bold bg-white/100 rounded p-2 cursor-pointer text-center hover:bg-orange-600 transition-all">Comprar</button>
                        <button className="text-black w-full font-bold bg-white/100 rounded p-2 cursor-pointer text-center hover:bg-orange-600 transition-all" onClick={handleAddToCartClick}>Adicionar ao carrinho</button>
                    </div>
                </figure>
            </div>
            
        </div>

    )
}