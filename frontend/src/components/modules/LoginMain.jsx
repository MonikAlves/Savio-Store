import { NavLink } from "react-router-dom";
import { useState } from "react";
import { userConsumer } from "../FetchAPI/userConsumer";
import { useUser } from "../../contexts/UserProvider";
import { useNavigate } from "react-router-dom";
import Message from "../shared/Message";

export function LoginMain() {
    const [email, setEmail] = useState("");
    const [senha, setSenha] = useState("");
    const [message, setMessage] = useState(""); 
    const [ type, setType ] = useState("")
    const { user, login, logout} = useUser()
    const navigate = useNavigate(); 

    const validations = (Client) => {
        if(Client.email.trim().length <= 0 || Client.email === null) {
            return false;
        }
        else if (Client.password.trim().length <= 0 || Client.senha === null) {
            return false;
        }
        return true;
    }


    const handleSubmit = async (event) => {
        const consumer = new userConsumer(import.meta.env.VITE_USER_API_URL);
        event.preventDefault();

        const Client = {
            password: senha,
            email: email
        };

        try {
            if(validations(Client)){
                const data = await consumer.VerifyLogin(Client); 
                setMessage(""); 
                login({
                    email: data.email}
                )
                setMessage("Usuario logado com sucesso")
                setType("sucess")
                navigate("/"); 
            } else {
                setMessage("Campo senha ou email estão vazios")
                setType("error")
            }
            //fica pro luiz implementar o local storage para guardar a session
            
        } catch (error) {
            setMessage(error.message); 
            setType("error")
        }
    }

    return (
        <main className="p-5 flex flex-col gap-8 justify-center items-center">
            <h1 className='text-4xl font-semibold text-white w-full text-center p-2.5'>Login</h1>
            <form 
                className="w-full max-w-96 h-full flex flex-col gap-10 items-center justify-center"
                onSubmit={(event) => handleSubmit(event)}
            >
                <div className="flex w-full flex-col gap-2.5">
                    <label className="w-full flex flex-col gap-2.5">
                        <span className="text-white">E-mail:</span>
                        <input 
                            onChange={(event) => setEmail(event.target.value)}
                            className="w-full p-2.5 rounded ring-[1px] ring-black/30 focus:pl-4 transition-all"
                            type="text"
                            placeholder="Digite seu e-mail"
                        />
                    </label>
                    <label className="w-full flex flex-col gap-2.5">
                        <span className="text-white">Senha:</span>
                        <input 
                            onChange={(event) => setSenha(event.target.value)}
                            className="w-full p-2.5 rounded ring-[1px] ring-black/30 focus:pl-4 transition-all"
                            type="password"
                            placeholder="Digite sua senha"
                        />
                    </label>
                </div>
                {message && type && ( // Exibe a mensagem de erro se existir
                    <Message
                        message = {message}
                        type = {type}
                    />
                )}
                <button 
                    className="gap-5 cursor-pointer bg-orange-500 w-full rounded p-2.5 font-bold text-black hover:bg-orange-700 hover:text-white transition-all"
                >
                    Login
                </button>
                <span className="text-white">
                    Novo por aqui? <NavLink className="text-blue-600 underline font-semibold" to={"/register"}>Cadastrar</NavLink>
                </span>
            </form>
        </main>
    );
}
