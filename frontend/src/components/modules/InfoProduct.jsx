import React, { useEffect, useState, useContext, useRef } from 'react'
import { useUser } from "../../contexts/UserProvider";
import { ProductsApi } from "../../lib/axios";
import { Product } from "../shared/Product";
import { productConsumer } from "../FetchAPI/productCosumer";
import { useParams } from 'react-router-dom';
import { ChevronsLeft, ChevronsRight, ShoppingCart } from 'lucide-react';
import { NavLink } from "react-router-dom";
import { ShoppingContext } from "../../contexts/ShoppingProvider";
import Message from '../shared/Message';
import Warning from '../shared/Warning';

export function InfoProduct (props) {

    const {user} = useUser();
    const [produto, setProduto] = useState();
    const {productId} = useParams();
    const consumer = new productConsumer(import.meta.env.VITE_API_BASE_URL);
    const [selectedButton, setSelectedButton] = useState(null);
    const [buttonState, setButtonState] = useState(false);
    const [ message, setMessage ] = useState("")
    const audioRef = useRef(null);
    const [showCart, setShowCart] = useState(false);
    const cartConsumer = new productConsumer(import.meta.env.VITE_PRODUCT_API_URL);
    const [warning, setWarning] = useState(false)
    const idsConsumer = new productConsumer(import.meta.env.VITE_API_BASE_URL) 
    const [dataIds, setDataIds] = useState()
    

    async function fetchProduct(){
        try{
            const dataProduct = await consumer.GetPurchase(productId)
            setProduto(dataProduct)
        }catch(error){
            console.log(error)
        }
    }

    async function fetchIds(){
        try{
            const dataIds = await idsConsumer.
        }catch(){

        }

    }

    const handleWarning = (message) => {
        setWarning(true)
        setMessage(message)
        setTimeout(() => {
            setWarning(false);
          }, 1000);
    }

    const handleButtonClick = (buttonId) => {
        console.log(buttonId)
        if(selectedButton === buttonId) {
            setSelectedButton(null);
        }
        else {
            setSelectedButton(buttonId);
        }
    };

    const getButtonClasses = (buttonId) => {
        return `w-9 text-black font-bold rounded p-2 cursor-pointer text-center transition-all ${selectedButton === buttonId ? 'bg-orange-600' : 'bg-white hover:bg-orange-500'}`;
    };

    useEffect( () => {
            fetchProduct();
            const response = await ids.
            console.log(ids)
        }, [] // o [] serve para a montagem só ocorrer uma vez
    )

    const handleAddToCartClick = async () => {
                
        const parametro = {idClient: user.id, idProduto: produto.id, Tamanho: selectedButton};

        if(selectedButton) {
            
            try {
                const response = await cartConsumer.addCart(parametro)
                const erroBack = response.error;
                console.log(erroBack)

                if(erroBack == "Deu certo"){
                    setShowCart(true);
                    setTimeout(() => {
                      setShowCart(false);
                    }, 1000);
    
            //addToCart(product);
    
                    if (audioRef.current) { 
                        audioRef.current.volume = 0.1;
                        audioRef.current.currentTime = 0;
                        audioRef.current.play().catch(error => {
                          console.error("Erro ao tentar reproduzir o áudio: ", error);
                        });
                    }
                }else{
                    handleWarning(erroBack)
                }


            } catch (error) {
                console.log(error)
                handleWarning(error.message)            
            }
        } else {
            handleWarning("Nenhum tamanho selecionado")
        }

        setSelectedButton("") 
             
    };

    if(!produto)
        return null

    return (
        <div className='flex justify-center items-center'>

            {/*message && type && (
                <Message message ={message} type= {type}/>
            )*/}

            
            <audio ref={audioRef} src="./../../public/cart_sound.mp3"/>

        <div className='flex mt-10 flex-row p-2.5 items-center h-[750px] w-3/4 bg-gray-700 rounded p-2 border border-white-500 relative'>
            
            {warning ? <Warning message={message}/> : ""}

            <div className='flex flex-col h-full w-full items-center'>

                <div className='flex h-full items-center justify-center gap-10'>
                    <div className='flex w-1/2 gap-5 h-[450px] bg-amazon-gunmetal flex-col justify-center items-center border border-white m-2 rounded'>
                            <img src={"https://exbxwvxqlnbphyieygiz.supabase.co/storage/v1/object/public/Roupas/" + produto.image}
                            alt=""
                            className='flex max-h-[350px]' />
                        
                            <div className="flex w-full border-t border-b gap-3 p-2 flex-wrap flex-col items-center justify-end">
                                <div className="flex gap-8 space-x-3">
                                    <button className={getButtonClasses('P')} onClick={() => handleButtonClick('P')}>P</button>
                                    <button className={getButtonClasses('M')} onClick={() => handleButtonClick('M')}>M</button>
                                    <button className={getButtonClasses('G')} onClick={() => handleButtonClick('G')}>G</button>
                                </div>

                            </div>
                    </div>

                    <div className='flex w-1/2 text-white h-full flex-col p-2.5 items-center justify-center gap-8' >
                        <p className='flex justify-center items-center text-4xl'>{produto.name}</p>
                        <p className='flex justify-center items-center'> {produto.description} </p>
                        <p className='flex justify-center items-center text-3xl'>${produto.price}</p>
                    </div>

                </div>

                    <div className='flex w-full justify-end items-end'>
                    <div className='flex w-full h-1/7 flex-row justify-evenly items-center border border-white-500 rounded bg-white p-2 m-0'>
                        <button className='pl-1 w-10 h-10 justify-center items-center rounded hover:bg-orange-500'><ChevronsLeft /></button>
                        {/* <button className='h-10 p-1 justify-center  items-center rounded hover:bg-orange-500'>Comprar</button> */}
                        <button className='flex h-10 w-10 justify-center items-center rounded hover:bg-orange-500' onClick={handleAddToCartClick}> <ShoppingCart />+
                        
                        
                    {showCart && (
                        <div className="absolute h-full flex justify-center w-full">
                        <div className="animate-cart-up">
                            <ShoppingCart className="h-[50px] w-[50px] text-orange-500"/>
                        </div>
                        </div>
                    )}
            

                        </button>
                        <button className='pl-1 w-10 h-10 w-10 justify-center items-center rounded hover:bg-orange-500'><ChevronsRight /></button>
                    </div>
                    </div>
                    
                    
                </div>

                
                
            </div>
        </div>
        
    )
    /*

    antes--

    <div className='flex h-45 w-90 justify-center items-center min-h-screen'>
        
        <div className='flex flex-col h-2/3 w-2/3 bg-gray-700 rounded p-2 border border-white-500'>

        
            <div className='flex flex-col justify-evenly items-center border border-white-500 rounded bg-white'>
                    <div className=''>
                        <button className='w-10 h-10 justify-center items-center'><ChevronsLeft /></button>
                    </div>
                    <div className=''>
                        <button className='w-10 h-10 justify-center items-center'>Comprar</button>
                    </div>
                    <div className='inline-flex flex flex-col '>
                        <button className='flex h-10 w-10 h-8 justify-center items-center'> <ShoppingCart />+</button>
                    </div>
                    <div className=''>
                        <button className='w-10 h-10 w-10 h-8 justify-center items-center'><ChevronsRight /></button>
                    </div>
                </div>

                <div className='flex flex-row h-full w-full justify-center items-center'>
                    <div className='flex flex-col justify-center items-center border border-white m-2 rounded'>

                        <img src={"https://exbxwvxqlnbphyieygiz.supabase.co/storage/v1/object/public/Roupas/" + produto.image}
                        alt=""
                        className='h-85 w-85' />
                    
                        <div className="p-5 flex gap-3 flex-wrap flex-col h-full items-center justify-end">
                            <div className="flex gap-2 space-x-3">
                                <button className={getButtonClasses('P')} onClick={() => handleButtonClick('P')}>P</button>
                                <button className={getButtonClasses('M')} onClick={() => handleButtonClick('M')}>M</button>
                                <button className={getButtonClasses('G')} onClick={() => handleButtonClick('G')}>G</button>
                            </div>

                        </div>
                    </div>
                    <div className='flex flex-col h-full m-2 border border-white-500 justify-between items-center rounded bg-white' >
                        <p className='text-4xl h-1/10'>{produto.name}</p>
                        <p className=' h-8/10'> {produto.description} </p>
                        <p className='text-3xl h-1/10'>${produto.price}</p>
                    </div>
                </div>
                
                
            </div>
                        
        </div>

        ----------------------

    <div className='flex flex-row justify-between items-center w-full'>
                    <div className=''>
                        <button className='w-10 h-8 justify-center items-center'><ChevronsLeft /></button>
                    </div>
                    <div className=''>
                        <button className='h-8'>Comprar</button>
                    </div>
                    <div className='inline-flex flex flex-col '>
                        <button className='flex h-8'> <ShoppingCart />+</button>
                    </div>
                    <div className=''>
                        <button className='w-10 h-8'><ChevronsRight /></button>
                    </div>
                </div>


    return (
        <div className='flex flex-row p-2 pt-6 w-full h-full'>
        <div className='flex mx-10 h-full w-full gap-8 items-center'>
        <figure className='flex flex-col gap-3 bg-amazon-apricot items-center justify-center h-[480px] w-[450px] border-[2px] rounded'>
        <img src="/public/luiz.png" alt="" className='flex mx-4 border-[2px] rounded h-[300px] w-[300px]'/>
        <h1 className='font-bold'>Nome do Produto</h1>
        </figure>
        
        <div className='flex h-full w-2/3'>
        <h2 className='w-full h-full justify-init items-init'>
        Descrição do produto aqui! Descrição do produto aqui! Descrição do produto aqui! 
        </h2>
        </div>
        
        
        <div className='flex flex-col items-center justify-init bg-amazon-apricot h-[400px] w-[350px] border-[2px] rounded aspect-square b-white'>
        <h2 className='flex p-2'>Preço do Produto</h2>
        </div>
        </div>
        </div>
        );
        */
    };

