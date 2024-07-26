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
    public void validateEmailTest() {
        List<String> valid_emails = Arrays.asList(
            "matheus@gmail.com",
            "monikinha@hotmail.com",
            "gabriel123@yahoo.com",
            "emmanuel.castro@outlook.com",
            "luizGCD@gmail.com",
            "luis_cesar@outlook.com",
            "savio@savio.com",
            "rabudinha69@hotmail.com",
            "theusincomedordeshereka@yahoo.com",
            "espanca_xota@outlook.com"
        );

        List<String> invalid_emails = Arrays.asList(
            "@gmail.com",
            "monikinhahotmail.com",
            "gabriel123@.com",
            "emmanuel.castro@outlookcom",
            "luizGCD@gmail.",
            "luis_cesar@outlook.com lakslkas",
            "ajakjs savio@savio.com",
            "@.com",
            "theusincomedordesherekayahoo",
            "1090hA)(*Ygd08Ŝ)sŜds0~0776¨%S"
        );

        for (String valid_email: valid_emails) {
            assertDoesNotThrow(() -> {
                this.clientService.validateEmail(valid_email);
            });
        }

        for (String invalid_email: invalid_emails) {
            assertThrows(EmailNotValid.class, () -> {
                this.clientService.validateEmail(invalid_email);
            });
        }
    }

    @Test
    public void validateCpfTest() {
        List<String> valid_cpfs = Arrays.asList(
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

        List<String> invalid_cpfs = Arrays.asList(
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

        for (String valid_cpf: valid_cpfs) {
            assertDoesNotThrow(() -> {
                this.clientService.validateCpf(valid_cpf);
            });
        }

        for (String invalid_cpf: invalid_cpfs) {
            assertThrows(LegalDataNotValid.class, () -> {
                this.clientService.validateCpf(invalid_cpf);
            });
        }
    }

    @Test
    public void validateCnpjTest() {
        List<String> valid_cnpjs = Arrays.asList(
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

        List<String> invalid_cnpjs = Arrays.asList(
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

        for (String valid_cnpj: valid_cnpjs) {
            assertDoesNotThrow(() -> {
                this.clientService.validateCnpj(valid_cnpj);
            });
        }

        for (String invalid_cnpj: invalid_cnpjs) {
            assertThrows(LegalDataNotValid.class, () -> {
                this.clientService.validateCnpj(invalid_cnpj);
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
