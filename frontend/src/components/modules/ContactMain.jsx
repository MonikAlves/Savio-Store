import { useState } from "react"
import { useNavigate } from "react-router-dom"
export function ContactMain() {
    const [ name, setName ] = useState("")
    const [ email, setEmail ] = useState("")
    const [ mensagem, setMensagem ] = useState("")
    const navigate = useNavigate();
    
    const handleSubmit  = (event) => {
        event.preventDefault()

        const FormDetail = {
            name, 
            email,
            mensagem
        }
        
        
        navigate("/")
    }

    return (
        <main className="flex-1 p-5 flex flex-col gap-8 justify-center items-center">
            <h1 className='text-4xl font-semibold text-white w-full text-center p-2.5'>Contate-nos</h1>
            <form className="w-full max-w-96 h-full flex flex-col gap-5 items-center justify-center" onSubmit={(event) => handleSubmit(event)}>
                <label className="w-full flex flex-col gap-2.5">
                    <span className="text-white">
                        Nome:
                    </span>
                    <input 
                        className="w-full p-2.5 rounded ring-[1px] ring-black/40 focus:pl-4 transition-all" 
                        type="text" 
                        name="" 
                        placeholder="Digite seu nome" 
                        onChange={(e) => setName(e.target.value)}
                    />
                </label>
                <label className="w-full flex flex-col gap-2.5">
                    <span className="text-white">
                        E-mail:
                    </span>
                    <input 
                        className="w-full p-2.5 rounded ring-[1px] ring-black/40 focus:pl-4 transition-all" 
                        type="text" 
                        name=""
                        placeholder="Digite seu e-mail" 
                        onChange={(e) => setEmail(e.target.value)}
                    />
                </label>
                <label className="w-full flex flex-col gap-2.5">
                    <span className="text-white">
                        Mensagem:
                    </span>
                    <textarea 
                        className="w-full p-2.5 rounded ring-[1px] ring-black/40 focus:pl-4 transition-all" 
                        type="text" 
                        name="" 
                        placeholder="Digite sua mensagem" 
                        onChange={(e) => setMensagem(e.target.value)}    
                    />
                </label>
                <button className="cursor-pointer bg-orange-500 w-full rounded p-2.5 font-bold text-black hover:bg-orange-700 hover:text-white transition-all">
                    Enviar
                </button>
            </form>
        </main>
    )
}
