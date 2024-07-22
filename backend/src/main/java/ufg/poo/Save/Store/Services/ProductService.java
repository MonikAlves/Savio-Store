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

    public String getProduto(){
        return this.productRepository.toString();
    }


    public boolean register(Product novoproduto){
        this.productRepository.save(novoproduto);
        return true;
    }

    public void productExist(long id){
        boolean exist = this.productRepository.existsById(id);
        if(!exist) throw new ProductNotFound("Product not found");
    }

    public int idGen(int max){
        Random rand  = new Random();
        int numero = rand.nextInt(max);

        while(numero == 0){
            numero = rand.nextInt(max);
        };

        return numero;
    }


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

    public List<Product> getAll(){
        return this.productRepository.findAll();
    }

    public void saveProduct(Product novoproduto){
        this.productRepository.save(novoproduto);
    }

    public void verifyInformationEmpty(Product produto){
        if(produto.getName() == null) throw new BadRequestException("Nome não informado");
        if(produto.getDescription() == null) throw new BadRequestException("Descrição não informada");
        if(produto.getSize() == null) throw new BadRequestException("Tamanho não informado");
        if(produto.getPrice() == null) throw new BadRequestException("Preço não informado");
        if(produto.getImage() == null) throw new BadRequestException("Imagem não informada");
    }

    public void delete(long id){
        this.productExist(id);
        this.productRepository.deleteById(id);
    }
}
