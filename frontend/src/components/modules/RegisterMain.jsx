import { NavLink } from "react-router-dom";
import { useState } from "react";
import { userConsumer } from "../FetchAPI/userConsumer";

export function RegisterMain() {
    const  [ NomeCompleto, setNomeCompleto ] = useState("")
    const  [ email, setEmail ] = useState("")
    const  [ password, setPassword ] = useState("")
    const  [ telefone, setTelefone ] = useState("")
    const  [ cpf, setCpf ] = useState("")
    const  [ errorMessage, setErrorMessage] = useState("")
    
    const consumer = new userConsumer()


    const handleSubmit = async (event) => {
        event.preventDefault()

        const client = {
            name: NomeCompleto,
            email: email,
            password: password,
            telefone: telefone,
        }

        try {
            await consumer.CadUser(client)
            setErrorMessage("cadastrado com sucesso")

        } catch (error) {
            setErrorMessage(error.message)
        }

    }

    const inputs = [
        { label: 'Nome completo', type: 'text', onchange: e => setNomeCompleto(e.target.value), name: 'name', placeholder: 'Digite seu nome' },
        { label: 'E-mail', type: 'text', name: 'email', onchange: e => setEmail(e.target.value), placeholder: 'Digite seu e-mail' },
        { label: 'Senha', type: 'password', name: 'password', onchange: e => setPassword(e.target.value), placeholder: 'Digite sua senha' },
        { label: 'Telefone', type: 'phone', name: 'phone', onchange: e => setTelefone(e.target.value), placeholder: '(XX) 9 XXXX-XXXX' },
        { label: 'CPF ou CNPJ', type: 'text', name: 'cpf', onchange: e => setCpf(e.target.value), placeholder: 'Digite seu CPF ou CNPJ' },
    ];

    return (
        <main className="p-5 flex gap-7 flex-col justify-center items-center">
            <h1 className='text-4xl flex-col font-semibold text-white w-full text-center p-2.5'>Cadastrar</h1>
            {errorMessage && ( // Exibe a mensagem de erro se existir
                    <div className="text-red-500 text-center">
                        {errorMessage}
                    </div>
            )}
            <form className="w-full max-w-96 h-full flex flex-col gap-3.5 items-center justify-center" onSubmit={(event) => handleSubmit(event)}>
                {
                    inputs.map((input, index) => (
                        <label key={index} className="w-full flex flex-col gap-2.5">
                            <span className="text-white">
                                {input.label}:
                            </span>
                            <input className="w-full p-2.5 rounded ring-[1px] ring-black/30 focus:pl-4 transition-all" type={input.type} name={input.name} placeholder={input.placeholder} />
                        </label>
                    ))
                }
                <div className="pt-2"/>
                <button className="cursor-pointer w-full bg-orange-500 rounded p-2.5 font-bold text-black hover:text-white hover:bg-orange-700 transition-all">
                    Cadastrar
                </button>
            </form>
                
        </main>
    )
}
