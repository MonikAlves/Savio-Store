import React from 'react'
import { useUser } from "../../contexts/UserProvider";


export function AboutMain() {
  const { user } = useUser();

  const categories = [
    { name: 'Roupas Masculinas', image: '/public/luiz.png' },
    { name: 'Roupas Femininas', image: 'https://images.pexels.com/photos/7679720/pexels-photo-7679720.jpeg?auto=compress&cs=tinysrgb&w=600' },
    { name: 'Jóias', image: 'https://images.pexels.com/photos/1927259/pexels-photo-1927259.jpeg?auto=compress&cs=tinysrgb&w=600' },
    { name: 'Eletrônicos', image: 'https://images.pexels.com/photos/356056/pexels-photo-356056.jpeg?auto=compress&cs=tinysrgb&w=600' },
  ]
  return (
    <main className="p-5 flex flex-col gap-7">
      <h1>current user {user ? user.email: "vazio"}</h1>
      <h1 className='text-4xl font-semibold text-white w-full text-center p-2.5'>Sobre nós</h1>
      <p className='text-center text-white text-xl font-thin'>
      A Savio´Store é uma loja virtual que visa trazer um maior conforto aos seus clientes,
        com nossos produtos de alta qualidade e preços imbatíveis no mercado.
        Seja você também um de nossos clientes de sorte e aproveite a experiência promovida
        por nossa marca, que está há mais de 50 anos tornando a vida das pessoas mais confortável.
      </p>

      <h2 className='text-3xl font-semibold w-full text-center text-white'>Nossos produtos</h2>
      <div className='flex gap-5 flex-wrap justify-center items-center'>
        {
          categories.map((category, index) => (
            <section key={index} className='w-72 aspect-video flex flex-col rounded ring-[1px] ring-white'>
              <img src={category.image} alt={category.name} className='w-full rounded-t' />
              <h3 className='text-xl font-semibold text-white text-center p-2.5'>{category.name}</h3>
            </section>
          ))
        }
      </div>
    </main>
  )
}
