export function PurchaseProduct({ image, title, description, quantity, price, itemPrice, size, date }) {

    return (
        <div className="w-full flex items-center gap-5 p-2.5 border-b-[1px] border-b-black/30">
            <img src={image} alt={description} className="w-32" />
            <div className=" text-white w-full flex flex-col gap-2.5">
                <div className="flex flex-wrap justify-between">
                    <h2 className="text-xl font-bold">
                        {title}
                    </h2>
                    <h3 className="text-xl font-bold"> Data de compra: {date}</h3>
                </div>
                <div className="flex justify-between">
                    <div>


                <p> {description} </p>
                <p> Preço do item: {`R$ ${itemPrice.toFixed(2)}`}</p>
                <p> Tamanho Escolhido: {size}</p>
                <p> Quantidade escolhida: {quantity} </p>
                    </div>
                <p className="flex items-end font-bold text-lg"> Total: {`R$ ${price.toFixed(2)}`}</p>
                </div>
            </div>
        </div>
    )
}
