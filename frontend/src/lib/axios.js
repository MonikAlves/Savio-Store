import axios from 'axios';

export class ProductsApi {

    constructor() {
        this.axios = axios.create({
            baseURL: import.meta.env.VITE_API_BASE_URL
        });
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
}
