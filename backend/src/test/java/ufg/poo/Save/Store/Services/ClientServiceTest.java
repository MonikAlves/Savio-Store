package ufg.poo.Save.Store.Services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ufg.poo.Save.Store.Entities.Client;
import ufg.poo.Save.Store.Exception.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ExtendWith(SpringExtension.class)
public class ClientServiceTest {
    @Mock
    private ClientService clientService;

    @Test
    public void clientExistTest(long id) {}
    @Test
    public void loginExistsTest(String email) {}
    @Test
    public void verifyClientExistTest(String email) {}
    @Test
    public void validateEmailTest(String email) {}
    @Test
    public void validateCpfTest(String cpf) {}
    @Test
    public void validateCnpjTest(String cnpj) {}
    @Test
    public void validateLegalDataTest(String legalData) {}
    @Test
    public void validatePhoneTest(String phone) {}
    @Test
    public void addClientTest(Client client) {}
    @Test
    public void verifyLoginTest(Client client) {}
    @Test
    public void verifyInformationEmptyTest(Client client) {}
    @Test
    public void deleteTest(long id) {}
}