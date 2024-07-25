import { useEffect, useState } from "react"
import { ProductsApi } from "../../lib/axios";
import { Product } from "../shared/Product";
import { useLocation } from "react-router-dom";
import { useUser } from "../../contexts/UserProvider";
import { productConsumer } from "../FetchAPI/productCosumer";

export function HomeMain() {
    const consumer = new productConsumer(import.meta.env.VITE_API_BASE_URL);
    const location = useLocation();
    const {user} = useUser();
    const  [products, setProducts] = useState();
   
    useEffect(() => {

        async function fetchProducts() {

            try {
                const data = await consumer.GetRandomProd();
                setProducts(data);
                console.log(data);
            } catch (error) {
                console.log(error);
            }
        }

        fetchProducts();

    }, [])

    return (
        <main className="p-5 flex flex-col gap-10">
            {
                location.pathname === '/' &&
                <section className="min-h-[80vh] bg-main-bg rounded text-white p-10 flex justify-center items-end">
                    <div className="flex flex-col gap-2.5 bg-black/40 rounded p-5 w-fit">
                        <h1 className="text-3xl font-bold">
                            Nossa Equipe de Desenvolvimento
                        </h1>
                        <p>
                            Aproveite a sua experiência na Savio´Store!
                        </p>
                        <p>
                            Venha descobrir nossas mais novas tendências!
                        </p>
                    </div>
                </section>
            }
            <section className="flex flex-col gap-7">
                <h1 className="w-full p-1 text-center text-white text-5xl">
                    Produtos Recomendados
                </h1>
                <div className="flex p-5 gap-2.5 flex-wrap p-2.5 items-start justify-center" >
                    {
                        products &&
                        products.map((product, index) => {
                            return (
                             <Product
                                key={index}
                                image = {"https://exbxwvxqlnbphyieygiz.supabase.co/storage/v1/object/public/Roupas/" +product.image}
                                title={product.name}
                                description ={product.description}
                                price = {product.price}
                                product={product}
                            />   
                            )
                        })
                    }
                    
                </div>
            </section>
        </main>
    )
}
