import { NavLink } from "react-router-dom";

export function LoginMain() {
    return (
        <main className="p-5 flex flex-col gap-8 justify-center items-center">
            <h1 className='text-4xl font-semibold text-white w-full text-center p-2.5'>Login</h1>
            <form className="w-full max-w-96 h-full flex flex-col gap-10 items-center justify-center">
                <div className="flex w-full flex-col gap-2.5">
                    <label className="w-full flex flex-col gap-2.5">
                        <span className="text-white">
                            E-mail:
                        </span>
                        <input className="w-full p-2.5 rounded ring-[1px] ring-black/30 focus:pl-4 transition-all" type="text" name="" placeholder="Digite seu e-mail" />
                    </label>
                    <label className="w-full flex flex-col gap-2.5">
                        <span className="text-white">
                            Senha:
                        </span>
                        <input className="w-full p-2.5 rounded ring-[1px] ring-black/30 focus:pl-4 transition-all" type="password" name="" placeholder="Digite sua senha" />
                    </label>
                </div>
                <button className="gap-5 cursor-pointer bg-orange-500 w-full rounded p-2.5 font-bold text-black hover:bg-orange-700 hover:text-white transition-all">
                    Login
                </button>
                <span className="text-white">
                    Novo por aqui? <NavLink className="text-blue-600 underline font-semibold" to={"/register"}>Cadastrar</NavLink>
                </span>
            </form>
        </main>
    )
}
