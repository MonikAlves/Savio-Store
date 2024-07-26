package ufg.poo.Save.Store.Services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ufg.poo.Save.Store.Entities.Product;
import ufg.poo.Save.Store.Repositories.ProductRepository;
import ufg.poo.Save.Store.Exception.BadRequestException;

@ExtendWith(SpringExtension.class)
public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @DisplayName("Testar geração de id aleatório")
    @Test
    public void idGenTest() {
        for (int max = 1; max < 100000; max++) {
            for (int rep = 0; rep < 100; rep++) {
                int id = this.productService.idGen(max);

                assertTrue(1 <= id && id <= max);
            }
        }
    }

    @DisplayName("Testar verificação de informação vazia")
    @Test
    public void verifyInformationEmptyTest() {
        Product product = new Product();

        product.setName("Camiseta");
        assertThrows(BadRequestException.class, () -> {
            this.productService.verifyInformationEmpty(product);
        });

        product.setDescription("Camiseta Polo");
        assertThrows(BadRequestException.class, () -> {
            this.productService.verifyInformationEmpty(product);
        });
        
        product.setSize("P-M-G");
        assertThrows(BadRequestException.class, () -> {
            this.productService.verifyInformationEmpty(product);
        });

        product.setPrice(120.0);
        assertThrows(BadRequestException.class, () -> {
            this.productService.verifyInformationEmpty(product);
        });

        product.setImage("camiseta.png");
        assertDoesNotThrow(() -> {
            this.productService.verifyInformationEmpty(product);
        });
    }
}
