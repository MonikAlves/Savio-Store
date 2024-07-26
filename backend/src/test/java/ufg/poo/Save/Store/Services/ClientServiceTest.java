package ufg.poo.Save.Store.Services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ufg.poo.Save.Store.Exception.*;
import ufg.poo.Save.Store.Repositories.ClientRepository;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
public class ClientServiceTest {
    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @Test
    public void clientExistTest() {}
    @Test
    public void loginExistsTest() {}
    @Test
    public void verifyClientExistTest() {}
    @Test
    public void validateEmailTest() {}

    @Test
    public void validateCpfTest() {
        List<String> cpfs_validos = Arrays.asList(
            "79554775105",
            "05124657626",
            "97882662151",
            "82684645850",
            "37126055790",
            "51767642539",
            "02676905252",
            "14537886978",
            "94562255773",
            "17162053360"
        );

        List<String> cpfs_invalidos = Arrays.asList(
            "192819281",
            "182937263726",
            "ahausiaoosj",
            "1h&h)9a*9s0",
            "           ",
            "11111111111",
            "vai tomando",
            "79554775104",
            "05124657616",
            "97882662115",
            "82684645805",
            "37126055757",
            "51767642552",
            "02676905219",
            "14537886972",
            "94562255753",
            "17162053361",
            "795.547.751-05",
            "051.246.576-26"
        );

        for (String cpf_valido: cpfs_validos) {
            assertDoesNotThrow(() -> {
                this.clientService.validateCpf(cpf_valido);
            });
        }

        for (String cpf_invalido: cpfs_invalidos) {
            assertThrows(LegalDataNotValid.class, () -> {
                this.clientService.validateCpf(cpf_invalido);
            });
        }
    }

    @Test
    public void validateCnpjTest() {
        List<String> cnpjs_validos = Arrays.asList(
            "34975768000100",
            "74685191000109",
            "67532302000181",
            "65323502000107",
            "78100723000150",
            "86152224000134",
            "59785751000103",
            "10656815000187",
            "75463853000169",
            "69355778000110"
        );

        List<String> cnpjs_invalidos = Arrays.asList(
            "3497576800010",
            "746851910001090",
            "ahausiaoosjaks",
            "1h&h)9a*9s0+^]",
            "              ",
            "11111111111111",
            "vai tomando ze",
            "34975768000101",
            "74685191000119",
            "67532302000118",
            "65323502000118",
            "78100723000134",
            "86152224000150",
            "59785751190103",
            "10656815037187",
            "75463853092169",
            "69355778111110",
            "34.975.768/0001-00",
            "74.685.191/0001-09"
        );

        for (String cnpj_valido: cnpjs_validos) {
            assertDoesNotThrow(() -> {
                this.clientService.validateCnpj(cnpj_valido);
            });
        }

        for (String cnpj_invalido: cnpjs_invalidos) {
            assertThrows(LegalDataNotValid.class, () -> {
                this.clientService.validateCnpj(cnpj_invalido);
            });
        }
    }

    @Test
    public void validatePhoneTest() {}
    @Test
    public void addClientTest() {}
    @Test
    public void verifyLoginTest() {}
    @Test
    public void verifyInformationEmptyTest() {}
    @Test
    public void deleteTest() {}
}
