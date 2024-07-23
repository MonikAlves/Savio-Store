export class userConsumer {
    constructor(baseURL) {
        this.BASEURL = baseURL;
    }

    async VerifyLogin  (Client) {
        try {
            console.log(this.BASEURL)
            const response = await fetch("http://localhost:8080/SavioStore/Client/login", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(Client),
            });

            if (!response.ok) {
                const errorResponse = await response.json(); 
                throw new Error(errorResponse.error || "Erro desconhecido"); 
            }

            return await response.json();

        } catch (error) {
            console.error("Erro na requisição:", error);
            throw error; 
        }
    }

    async CadUser (Client) {
        try {
            console.log(this.BASEURL)
            const response = await fetch("http://localhost:8080/SavioStore/Client/create", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(Client),
            });

            if (!response.ok) {
                const errorResponse = await response.json(); 
                throw new Error(errorResponse.error || "Erro desconhecido"); 
            }

            return await response.json();

        } catch (error) {
            console.error("Erro na requisição:", error);
            throw error; 
        }
    }
}