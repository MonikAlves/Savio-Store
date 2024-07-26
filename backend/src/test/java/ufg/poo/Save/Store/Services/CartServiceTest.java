package ufg.poo.Save.Store.Services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ufg.poo.Save.Store.Exception.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
public class CartServiceTest {
    @InjectMocks
    private CartService cartService;

    @Mock
    private ProductService productService;

    @DisplayName("Testar cálculo de estoque pelo tamanho")
    @Test
    public void calculateStockBySizeTest() {
        List<List<String>> valid_entries = new ArrayList<>();
        List<String> valid_results = new ArrayList<>();

        valid_entries.add(Arrays.asList("P-M-G", "P", "19-37-84")); valid_results.add("19");
        valid_entries.add(Arrays.asList("P-M-G", "P", "73-45-17")); valid_results.add("73");
        valid_entries.add(Arrays.asList("P-M-G", "M", "64-29-63")); valid_results.add("29");
        valid_entries.add(Arrays.asList("P-M-G", "M", "18-62-39")); valid_results.add("62");
        valid_entries.add(Arrays.asList("P-M-G", "G", "43-20-39")); valid_results.add("39");
        valid_entries.add(Arrays.asList("P-M-G", "G", "48-39-94")); valid_results.add("94");

        valid_entries.add(Arrays.asList("20-21-22", "21", "19-37-84")); valid_results.add("37");
        valid_entries.add(Arrays.asList("32-33-34", "32", "73-45-17")); valid_results.add("73");
        valid_entries.add(Arrays.asList("40-41-42", "42", "64-29-63")); valid_results.add("63");
        valid_entries.add(Arrays.asList("36-37-38", "36", "18-62-39")); valid_results.add("18");
        valid_entries.add(Arrays.asList("28-29-30", "30", "43-20-39")); valid_results.add("39");
        valid_entries.add(Arrays.asList("24-25-26", "25", "48-39-94")); valid_results.add("39");

        for (int j = 0; j < valid_entries.size(); j++) {
            final int i = j;

            assertDoesNotThrow(() -> {
                String result = this.cartService.calculateStockBySize(
                    valid_entries.get(i).get(0),
                    valid_entries.get(i).get(1),
                    valid_entries.get(i).get(2)
                );

                String solution = valid_results.get(i);

                assertTrue(result.equals(solution));
            });
        }

        List<List<String>> invalid_entries = new ArrayList<>();

        invalid_entries.add(Arrays.asList("P-M-G", "A", "19-37-84"));
        invalid_entries.add(Arrays.asList("P-M-G", "0", "73-45-17"));
        invalid_entries.add(Arrays.asList("P-M-G", "#", "64-29-63"));
        invalid_entries.add(Arrays.asList("P-M-G", "PMG", "18-62-39"));
        invalid_entries.add(Arrays.asList("P-M-G", " ", "43-20-39"));
        invalid_entries.add(Arrays.asList("P-M-G", "M ", "48-39-94"));

        invalid_entries.add(Arrays.asList("20-21-22", "1", "19-37-84"));
        invalid_entries.add(Arrays.asList("32-33-34", "P", "73-45-17"));
        invalid_entries.add(Arrays.asList("40-41-42", "-", "64-29-63"));
        invalid_entries.add(Arrays.asList("36-37-38", "363738", "18-62-39"));
        invalid_entries.add(Arrays.asList("28-29-30", " ", "43-20-39"));
        invalid_entries.add(Arrays.asList("24-25-26", "24 ", "48-39-94"));

        for (int j = 0; j < invalid_entries.size(); j++) {
            final int i = j;

            assertThrows(SizeNotFound.class, () -> {
                this.cartService.calculateStockBySize(
                    invalid_entries.get(i).get(0),
                    invalid_entries.get(i).get(1),
                    invalid_entries.get(i).get(2)
                );
            });
        }
    }

    @DisplayName("Testar redução de estoque")
    @Test
    public void reduceStockTest() {
        // TODO
        // concluir teste unitário de redução de estoque
    }
}
