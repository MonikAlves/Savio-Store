export function CartProduct({ image, title, description, quantity, price, itemPrice, size, available }) {

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
            <img src={image} alt={description} className="w-32" />
            <div className="flex flex-col gap-2.5">
                <h2 className="text-xl font-bold">
                    {title}
                </h2>
                <p> Preço do item: {itemPrice}</p>
                <p>
                    {description}
                </p>
                <p> Tamanho Escolhido: {size}</p>
                <p>
                    Quantidade escolhida: {quantity} 
                </p>
                <p> Total: {price}</p>
                <p>  {availableText(available)} </p>
            </div>
        </div>
    )
}
