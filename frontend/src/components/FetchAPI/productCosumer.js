export class productConsumer {
    constructor (baseURL) {
        this.BASEURL = baseURL;
    }

    async GetCart(id) {
        try {
            const response = fetch(this.BASEURL + "/" + id, {
                method: "GET",
            })

            if(!response) {
                const errorResponse = await response.json(); 
                throw new Error(errorResponse.error || "Erro desconhecido");
            }

            return (await response).json()
        } catch (e) {
            console.log(e)
            throw e
        }
    }

    async GetPurchase(id) {
        try {
            const response = fetch(this.BASEURL + "/" + id, {
                method: "GET",
            })

            if(!response) {
                const errorResponse = await response.json(); 
                throw new Error(errorResponse.error || "Erro desconhecido");
            }

            return (await response).json()
        } catch (e) {
            console.log(e)
            throw e
        }
    }

    async GetRandomProd() {
        try {
            const response = fetch(this.BASEURL + "/random", {
                method: "GET",
            })

            if(!response) {
                const errorResponse = await response.json(); 
                throw new Error(errorResponse.error || "Erro desconhecido");
            }

            return (await response).json()
        } catch (e) {
            console.log(e)
            throw e
        }
    }

    async addCart ({idClient, idProduto, Tamanho}) {
        try {
            const response = await fetch(this.BASEURL + "/add", {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    client: {
                        id: idClient,
                    },
                    product: {
                        id: idProduto,
                    },
                    quantity: 1,
                    size: Tamanho
                })
            })
            
            if(!response) {
                const errorResponse = await response.json(); 
                throw new Error(errorResponse.error || "Erro desconhecido");
            }

            return await response.json()
        } catch(e) {
            console.log(e)
            throw e
        }
    }

    async buy(idCart) {
        console.log('idCart:', idCart);
        try {
            const response = await fetch(this.BASEURL + "/buy", {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ 
                    id: idCart
                })
            })
            
            if(!response) {
                const errorResponse = await response.json(); 
                throw new Error(errorResponse.error || "Erro desconhecido");
            }

            return await response.json()
        } catch(e) {
            console.log(e)
            throw e
        }
    }

    async buyAll (idClient) {
        try {
            const response = await fetch(this.BASEURL + "/buyAll", {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    
                        id: idClient
                    
                })
            })
            
            if(!response) {
                const errorResponse = await response.json(); 
                throw new Error(errorResponse.error || "Erro desconhecido");
            }

            return await response.json()
        } catch(e) {
            console.log(e)
            throw e
        }
    }

}