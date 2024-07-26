package ufg.poo.Save.Store.Services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ufg.poo.Save.Store.Entities.Client;
import ufg.poo.Save.Store.Exception.*;
import ufg.poo.Save.Store.Repositories.ClientRepository;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
