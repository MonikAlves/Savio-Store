export function PurchaseProduct({ image, title, description, quantity, price, itemPrice, size, date }) {

    return (
        <div className="w-full flex items-center gap-5 p-2.5 border-b-[1px] border-b-black/30">
            <img src={image} alt={description} className="w-32" />
            <div className=" w-full flex flex-col gap-2.5">
                <h2 className="text-xl font-bold">
                    {title}
                </h2>
                <h3 className="text-xl font-bold"> Data de compra: {date}</h3>
                <p> {description} </p>
                <p> Preço do item: {`R$ ${itemPrice.toFixed(2)}`}</p>
                <p> Tamanho Escolhido: {size}</p>
                <p> Quantidade escolhida: {quantity} </p>
                <p> Total: {`R$ ${price.toFixed(2)}`}</p>
            </div>
        </div>
    )
}
