import React from 'react'
import { useUser } from "../../contexts/UserProvider";


export function AboutMain() {
  const { user } = useUser();
  
  const categories = [
    { name: 'Roupas Masculinas', image: '/public/luiz.png' },
    { name: 'Roupas Femininas', image: '/public/monik.jpg' },
    { name: 'Tênis', image: 'https://images.pexels.com/photos/1927259/pexels-photo-1927259.jpeg?auto=compress&cs=tinysrgb&w=600' },
  ]
  return (
    <main className="p-5 flex flex-col gap-7">
      <div className='flex flex-col gap-5 mx-16'>
          <h1 className='text-4xl font-semibold text-white w-full text-center p-1'>Sobre nós</h1>
          <p className='text-center text-white text-xl font-thin'>
          A Savio´Store é uma loja virtual desenvolvida como projeto final da disciplina
            de programação orientada a objeto, e o nome "Savio´Store" faz referência
            ao nome do nosso professor. Nossos produtos fictícios são todos personalizados
            e todas as imagens dos modelos são autorais. Aproveite a sua experiência! 
          </p>
      </div>
      

      <h2 className='text-3xl font-semibold w-full text-center text-white'>Nossos produtos</h2>
      <div className='flex gap-5 flex-wrap justify-center items-center'>
        {
          categories.map((category, index) => (
            <section key={index} className='w-72 bg-amazon-eerie aspect-video flex flex-col rounded ring-[2px] ring-white'>
              <img src={category.image} alt={category.name} className='w-full rounded-t' />
              <h3 className='text-xl border-t-[1.5px] font-semibold text-white text-center p-2.5'>{category.name}</h3>
            </section>
          ))
        }
      </div>
    </main>
  )
}
