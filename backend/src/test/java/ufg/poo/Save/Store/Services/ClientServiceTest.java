package ufg.poo.Save.Store.Services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ufg.poo.Save.Store.Entities.*;
import ufg.poo.Save.Store.Exception.*;
import ufg.poo.Save.Store.Repositories.ClientRepository;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class ClientServiceTest {
    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @DisplayName("Testar validação de Email")
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

    @DisplayName("Testar validação de CPF")
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
            "192819",
            "182937263726281",
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

    @DisplayName("Testar validação de CNPJ")
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
            "3497576800",
            "7468519100010900100",
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

    @DisplayName("Testar validação de telefone")
    @Test
    public void validatePhoneTest() {
        List<String> valid_phones = Arrays.asList(
            "62984595071",
            "87648272864",
            "32764239784",
            "67587267492",
            "17281726187"
        );

        List<String> invalid_phones = Arrays.asList(
            "12781",
            "01928102981029128",
            "ad8s7d9a87d",
            "- -0 0 -- 0",
            "828@@8981@@"
        );

        for (String valid_phone: valid_phones) {
            assertDoesNotThrow(() -> {
                this.clientService.validatePhone(valid_phone);
            });
        }

        for (String invalid_phone: invalid_phones) {
            assertThrows(PhoneNotValid.class, () -> {
                this.clientService.validatePhone(invalid_phone);
            });
        }
    }

    @DisplayName("Testar a verificação de informação vazia")
    @Test
    public void verifyInformationEmptyTest() {
        Client client = new Client();

        client.setName("Matheus");
        assertThrows(BadRequestException.class, () -> {
            this.clientService.verifyInformationEmpty(client);
        });

        client.setEmail("matheus@gmail.com");
        assertThrows(BadRequestException.class, () -> {
            this.clientService.verifyInformationEmpty(client);
        });

        client.setPassword("12345");
        assertThrows(BadRequestException.class, () -> {
            this.clientService.verifyInformationEmpty(client);
        });
        
        client.setPhone("62984595071");
        assertThrows(BadRequestException.class, () -> {
            this.clientService.verifyInformationEmpty(client);
        });

        client.setLegalData("70833895192");
        assertDoesNotThrow(() -> {
            this.clientService.verifyInformationEmpty(client);
        });
    }
}
