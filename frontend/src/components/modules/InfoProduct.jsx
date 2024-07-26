import React from 'react'

export function InfoProduct () {



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
};