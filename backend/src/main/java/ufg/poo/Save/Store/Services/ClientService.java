package ufg.poo.Save.Store.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ufg.poo.Save.Store.Entities.Client;
import ufg.poo.Save.Store.Exception.ClientAlreadyExist;
import ufg.poo.Save.Store.Exception.ClientNotFound;
import ufg.poo.Save.Store.Repositories.ClientRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ClientService {
    @Autowired
    private final ClientRepository clientRepository;

    public void clientExist(long id){
        boolean exist = this.clientRepository.existsById(id);
        if(!exist) throw new ClientNotFound("Client not found");
    }

    public void verifyClientExist(String email) {
        Optional<Client> isClientRegistered= this.clientRepository.findByEmail(email);
        if(isClientRegistered.isPresent()) throw new ClientAlreadyExist("Client already exist");
    }

    public String addClient(Client client){
            this.verifyClientExist(client.getEmail());
            clientRepository.save(client);
            return "Client added successfully";

    }

    public Client getClient(long id){
        return this.clientRepository.findById(id).orElseThrow(() -> new ClientNotFound("Client not found"));
    }


}
