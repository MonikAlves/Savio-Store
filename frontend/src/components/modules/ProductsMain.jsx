import { useEffect, useState } from "react"
import { ProductsApi } from "../../lib/axios";
import { Product } from "../shared/Product";
import { useLocation } from "react-router-dom";
import { useUser } from "../../contexts/UserProvider";
import { productConsumer } from "../FetchAPI/productCosumer";

export function ProductsMain() {
    const consumer = new productConsumer(import.meta.env.VITE_API_BASE_URL);
    const location = useLocation();
    const {user} = useUser();
    const  [products, setProducts] = useState();
    
    useEffect(() => {

        const productsApi = new ProductsApi()

        async function fetchProducts() {
            const data = await productsApi.getProducts();
            setProducts (data);
        }

        fetchProducts();
    }, [])

    return (
        <main className="p-5 flex flex-col gap-10">
            <section className="flex flex-col gap-5">
                <h1 className="w-full p-1 text-center text-white text-5xl">
                    Produtos Disponíveis
                </h1>
                <div className="flex p-5 gap-2.5 flex-wrap items-start justify-center" >
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
