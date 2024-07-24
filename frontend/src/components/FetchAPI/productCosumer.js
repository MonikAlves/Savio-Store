export class productConsumer {
    constructor (baseURL) {
        this.BASEURL = baseURL;
    }

    async GetCart(id) {
        try {
            fetch(this.BASEURL + "/" + id, {
                method: "GET",
            })
            .then(data => {
                return data.json()
            })
            .catch(error => {
                console.log(error)
            })

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
}