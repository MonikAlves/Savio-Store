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
    const [idsChave, setIdsChave] = useState()
    const [idAtual, setIdAtual] = useState()
    const [keyAtual, setKeyAtual] = useState()
    
    const atualizarIdEKey = (id, key) => {
        const novaKey = key;
        const novoId = id;

        setIdAtual(prevState => novoId)
        setKeyAtual(prevState => novaKey)
    }

    //se der problema olha aqui

    async function fetchProduct(){
        try{
            const dataProduct = await consumer.GetPurchase(idAtual)
            setProduto(dataProduct)
        }catch(error){
            console.log(error)
        }
    }

    async function fetchIds(){
        try{
            const dataIdsFromBackend = await idsConsumer.validIds();
            setDataIds(dataIdsFromBackend)
            let vetorDePares = []
            for(let c = 0; c < dataIdsFromBackend.length; c++){
                vetorDePares.push([dataIdsFromBackend[c], c])
            }
            setIdsChave(vetorDePares)
            setIdAtual(productId)

            console.log(dataIdsFromBackend)
            //console.log(productId)
            
            let c = -1, d = vetorDePares.length;
            for(; d - c > 1; ){
                let half = ((d + c) / 2) >> 0;
                //console.log(`C: ${c}\nD:${d}\nHalf: ${half}\n`)
                if(productId < dataIdsFromBackend[half]){
                    d = half;
                }else{
                    c = half;
                }    
            }

            setIdAtual(productId)
            
            if(dataIdsFromBackend[d] == productId){
                setKeyAtual(d)
            }
            if(dataIdsFromBackend[c] == productId){
                setKeyAtual(c)
            }

        }catch(error){
            console.log(error)
        }
    }

    useEffect(() => {
        console.log("idAtual: " + idAtual);
        console.log("keyAtual: " +keyAtual);
      }, [idAtual, keyAtual])

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
            fetchIds()
        }, [] // o [] serve para a montagem só ocorrer uma vez
    )

    useEffect( () => {
        fetchProduct()
    }, [idAtual] // o [] serve para a montagem só ocorrer uma vez
)

    const handleLeftArrowButton = async () => {    
        const key = (keyAtual - 1) < 0 ? idsChave.length - 1 : (keyAtual - 1);
        setKeyAtual(key)
        setIdAtual(idsChave[key][0])
        //console.log(idsChave[key][0])
    }

    const handleRightArrowButton = async () => {    
        const key = (keyAtual + 1) % idsChave.length;
        //key = (key >= idsChave.length ? 0 : key);

        setKeyAtual(key)
        setIdAtual(idsChave[key][0])
        //console.log(idsChave[key][0])
    }

    const handleAddToCartClick = async () => {
                
        const parametro = {idClient: user.id, idProduto: idAtual, Tamanho: selectedButton};

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
                        <p className='flex justify-center items-center text-3xl'>R${produto.price}</p>
                    </div>

                </div>

                    <div className='flex w-full justify-end items-end'>
                    <div className='flex w-full h-1/7 flex-row justify-evenly items-center border border-white-500 rounded bg-white p-2 m-0'>
                        <button className='pl-1 w-10 h-10 justify-center items-center rounded hover:bg-orange-500' onClick={handleLeftArrowButton}><ChevronsLeft /></button>
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
                        <button className='pl-1 w-10 h-10 justify-center items-center rounded hover:bg-orange-500' onClick={handleRightArrowButton}><ChevronsRight /></button>
                    </div>
                    </div>
                    
                    
                </div>

                
                
            </div>
        </div>
        
    )};