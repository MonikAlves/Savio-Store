import axios from 'axios';

export class ProductsApi {
    constructor() {
        this.axios = axios.create({
            baseURL: import.meta.env.VITE_API_BASE_URL
        });
        console.log("Base URL no constructor:", import.meta.env.VITE_API_BASE_URL); // Verificação
    }

    async getProducts() {
        try {
            const response = await this.axios.get('/all');
            return response.data;
        } catch (error) {
            console.error(error);
            return [];
        }
    }

    async getProductById(id) {
        try {
            const response = await this.axios.get('/listarProdutoById/' + id);
            return response.data;
        } catch (error) {
            console.error(error);
            return [];
        }
    }
   
}
