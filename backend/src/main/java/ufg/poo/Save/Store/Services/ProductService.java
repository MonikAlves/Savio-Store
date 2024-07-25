package ufg.poo.Save.Store.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ufg.poo.Save.Store.Entities.Product;
import ufg.poo.Save.Store.Exception.BadRequestException;
import ufg.poo.Save.Store.Exception.ClientNotFound;
import ufg.poo.Save.Store.Exception.ProductNotFound;
import ufg.poo.Save.Store.Repositories.ProductRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    /**
     * @deprecated
     * @return
     */
    public String getProduto(){
        return this.productRepository.toString();
    }

    /**
     * Register a new product
     * @param newProduct New product to be registered
     */
    public void register(Product newProduct) throws BadRequestException {
        this.verifyInformationEmpty(newProduct);
        this.productRepository.save(newProduct);
    }


    /**
     * Verify if product exists by id
     * @param id Product id
     * @throws ProductNotFound
     */
    public void productExist(long id) throws ProductNotFound {
        boolean exist = this.productRepository.existsById(id);
        if(!exist) throw new ProductNotFound();
    }

    /**
     * Generate id
     * @param max Max value of id
     * @return Generated id
     */
    public int idGen(int max){
        Random rand  = new Random();
        int numero = rand.nextInt(max);

        while(numero == 0){
            numero = rand.nextInt(max);
        };

        return numero;
    }

    /**
     * Get three random products
     * @param products List with all available products
     * @return List with three random products
     */
    public List<Product> getRandom(List<Product> products){
        List<Product> escolhidos = new ArrayList<>();
        List<Long> ids = new ArrayList<>();
        for(Product p : products){
            ids.add(p.getId());
        }
        Random rand = new Random();

        for(int i = 0; i < 3; i++){
            int id = rand.nextInt(ids.size());
            escolhidos.add(productRepository.getProductById(ids.get(id)));
            ids.remove(id);
        }

        return escolhidos;

    }

    /**
     * Get a list with all available products
     * @return A list with all available products
     */
    public List<Product> getAll(){
        List<Product> products = this.productRepository.findAll();
        List<Product> productsValid = new ArrayList<>();
        for(Product p : products){
            if(!p.getStock().equals("0-0-0")){
                productsValid.add(p);
            }
        }
        return productsValid;
    }

    /**
     * Get product by id
     * @param id Product id
     * @return Product by id
     * @throws ProductNotFound
     */
    public Product getProduct(long id) throws ProductNotFound {
        this.productExist(id);
        return this.productRepository.getProductById(id);
    };

    /**
     * Verify if an information is empty
     * @param produto Product whose information will be verified
     * @throws BadRequestException
     */
    public void verifyInformationEmpty(Product produto) throws BadRequestException {
        if(produto.getName() == null) throw new BadRequestException("Nome não informado");
        if(produto.getDescription() == null) throw new BadRequestException("Descrição não informada");
        if(produto.getSize() == null) throw new BadRequestException("Tamanho não informado");
        if(produto.getPrice() == null) throw new BadRequestException("Preço não informado");
        if(produto.getImage() == null) throw new BadRequestException("Imagem não informada");
    }

    /**
     * Delete a product with validations
     * @param id Product id
     * @throws ProductNotFound
     */
    public void delete(long id) throws ProductNotFound {
        this.productExist(id);
        this.productRepository.deleteById(id);
    }
}
