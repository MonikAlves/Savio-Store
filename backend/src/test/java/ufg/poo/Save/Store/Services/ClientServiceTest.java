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
    public void clientExistTest() {}
    @Test
    public void loginExistsTest() {}
    @Test
    public void verifyClientExistTest() {}
    @Test
    public void validateEmailTest() {}
    @Test
    public void validateCpfTest() {}
    @Test
    public void validateCnpjTest() {}
    @Test
    public void validateLegalDataTest() {}
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
