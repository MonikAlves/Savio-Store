package ufg.poo.Save.Store.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import ufg.poo.Save.Store.Entities.Client;
import ufg.poo.Save.Store.Entities.Product;
import ufg.poo.Save.Store.Exception.*;
import ufg.poo.Save.Store.Repositories.ClientRepository;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Transactional
public class ClientService {

    private final ClientRepository clientRepository;

    public void clientExist(long id){
        boolean exist = this.clientRepository.existsById(id);
        if(!exist) throw new ClientNotFound("Client not found");
    }

    public void loginExists(String email){
        Optional<Client> exist = this.clientRepository.findByEmail(email);
        if(exist.isEmpty()) throw new ClientNotFound("Client not registered");
    }


    public void verifyClientExist(String email) {
        Optional<Client> isClientRegistered = this.clientRepository.findByEmail(email);
        if(isClientRegistered.isPresent()) throw new ClientAlreadyExist("Client already exist");
    }

    public void validateEmail(String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9-.]+.[.]com$");
        Matcher matcher = pattern.matcher(email);

        if (!matcher.matches()) throw new EmailIsNotValid("Email is not valid");
        else {
            System.out.println("DEU ERRADO COM EMAIL: " + email);
        }
    }

    public String addClient(Client client) {
        String email = client.getEmail();

        this.verifyClientExist(email);
        this.clientRepository.save(client);

        this.validateEmail(email);

        return "Client added successfully";
    }

    public Client getClient(long id){
        return this.clientRepository.findById(id).orElseThrow(() -> new ClientNotFound("Client not found"));
    }

    public Client verifyLogin(Client client){
        String email = client.getEmail();
        String password = client.getPassword();

        this.loginExists(client.getEmail());
        Client cliente = this.clientRepository.getReferenceByEmail(email);
        if(!cliente.getPassword().equals(password)){
            throw new Unauthorized("Wrong password, KILL YOURSELF");
        }

        return cliente;
    }

    public void verifyInformationEmpty(Client client){
        if(client.getName() == null) throw new BadRequestException("Nome não informado");
        if(client.getEmail() == null) throw new BadRequestException("Email não informada");
        if(client.getPassword() == null) throw new BadRequestException("Senha não informada");
        if(client.getPhone() == null) throw new BadRequestException("Telefone não informado");
        if(client.getLegalData() == null) throw new BadRequestException("CPF ou CNPJ não informado");
    }

    public void delete(long id){
        this.clientExist(id);
        this.clientRepository.deleteById(id);
    }

}
