import { createContext, useState } from "react";
export const ShoppingContext = createContext({});


export const ShoppingProvider = ({ children }) => {
    const [state, setState] = useState([]);
    const [total, setTotal] = useState(0);
    
    
    const addToCart = (product) => {
        setState(prevState => {
            const exists = prevState.find((item) => item.id === product.id);
            if (exists) {
                exists.quantity += 1;
                return [...prevState];
            }
            return [...prevState, { ...product, quantity: 1 }];
        });
        setTotal(total + product.price);
    };

    const removeFromCart = (product) => {
        setState(state.filter((item) => item.id !== product.id));
        setTotal(total - product.price);
    };

    return (
        <ShoppingContext.Provider
            value={{
                state,
                total,
                addToCart,
                removeFromCart,
            }}
        >
            {children}
        </ShoppingContext.Provider>
    );
}
