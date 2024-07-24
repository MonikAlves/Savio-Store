export class productConsumer {
    constructor (baseURL) {
        this.BASEURL = baseURL;
    }

    async addCart ({idClient, idProduto, Tamanho}) {
        try {
            console.log("ENTREI PARA FAZER A REQ")
            console.log(idClient + " " + idProduto + "  " + Tamanho)
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
                    qunatity: 10000,
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