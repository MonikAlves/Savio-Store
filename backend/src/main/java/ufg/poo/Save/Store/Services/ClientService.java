package ufg.poo.Save.Store.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ufg.poo.Save.Store.Entities.Client;
import ufg.poo.Save.Store.Exception.*;
import ufg.poo.Save.Store.Repositories.ClientRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ClientService {

    private final ClientRepository clientRepository;

    public void clientExist(long id) throws ClientNotFound {
        boolean exist = this.clientRepository.existsById(id);
        if(!exist) throw new ClientNotFound();
    }


    public void loginExists(String email) throws ClientNotFound {
        Optional<Client> exist = this.clientRepository.findByEmail(email);
        if(exist.isEmpty())throw new ClientNotFound();
    }

    public void verifyClientExist(String email) throws ClientAlreadyExist {
        Optional<Client> isClientRegistered = this.clientRepository.findByEmail(email);
        if(isClientRegistered.isPresent()) throw new ClientAlreadyExist();
    }

    public void validateEmail(String email) throws EmailNotValid {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9-.]+.[.]com$");
        Matcher matcher = pattern.matcher(email);

        if (!matcher.matches()) {
            throw new EmailNotValid();
        }
    }

    public void validateLegalData(String legalData) throws LegalDataNotValid {
        List<Integer> digits = new ArrayList<>();
        boolean equals = true;

        for (int index = 0; index < legalData.length(); index++) {
            char digit = legalData.charAt(index);

            if (!Character.isDigit(digit)) {
                throw new LegalDataNotValid();
            }

            if (digit != legalData.charAt(0)) {
                equals = false;
            }

            digits.add((Integer)(digit - '0'));
        }

        if (equals || digits.size() != 11) {
            throw new LegalDataNotValid();
        }

        int first = 0;
        int second = 0;

        for (int index = 0, weight = 10; index < 9; index++, weight--) {
            first += (weight * digits.get(index));
        }

        first = ((10 * first) % 11) % 10;
        second += (2 * first);

        for (int index = 0, weight = 11; index < 9; index++, weight--) {
            second += (weight * digits.get(index));
        }

        second = ((10 * second) % 11) % 10;

        if (first != digits.get(9) && second != digits.get(10)) {
            throw new LegalDataNotValid();
        }
    }

    public void validatePhone(String phone) throws PhoneNotValid {
        List<Integer> digits = new ArrayList<>();

        for (int index = 0; index < phone.length(); index++) {
            char digit = phone.charAt(index);

            if (!Character.isDigit(digit)) {
                throw new PhoneNotValid();
            }

            digits.add((Integer)(digit - '0'));
        }

        if (digits.size() != 11) {
            throw new PhoneNotValid();
        }
    }

    public Client addClient(Client client) throws ClientAlreadyExist, EmailNotValid, LegalDataNotValid, PhoneNotValid {
        String email = client.getEmail();
        String legalData = client.getLegalData();
        String phone = client.getPhone();

        this.verifyClientExist(email);

        this.validateEmail(email);
        this.validateLegalData(legalData);
        this.validatePhone(phone);

        this.clientRepository.save(client);

        return this.clientRepository.getReferenceByEmail(email);
    }

    public Client verifyLogin(Client client) throws Unauthorized,ClientNotFound {
        String email = client.getEmail();
        String password = client.getPassword();

        this.loginExists(client.getEmail());

        Client cliente = clientRepository.getReferenceByEmail(email);

        if (!cliente.getPassword().equals(password)) throw new Unauthorized();
        return cliente;
    }

    public void verifyInformationEmpty(Client client) throws BadRequestException{
        if(client.getName() == null) throw new BadRequestException("Nome não informado");
        if(client.getEmail() == null) throw new BadRequestException("Email não informada");
        if(client.getPassword() == null) throw new BadRequestException("Senha não informada");
        if(client.getPhone() == null) throw new BadRequestException("Telefone não informado");
        if(client.getLegalData() == null) throw new BadRequestException("CPF ou CNPJ não informado");
    }

    public void delete(long id) throws ClientNotFound{
        this.clientExist(id);
        this.clientRepository.deleteById(id);
    }

}
