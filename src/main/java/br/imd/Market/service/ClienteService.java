package br.imd.Market.service;

import br.imd.Market.DTO.ClienteDTO;
import br.imd.Market.model.ClienteEntity;
import br.imd.Market.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;


    //Metodo para converter o DTO e salvar o cliente no banco de dados
    public ClienteEntity postCliente(ClienteDTO clienteDTO) {
        ClienteEntity cliente = new ClienteEntity();
        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setGenero(ClienteEntity.Genero.valueOf(clienteDTO.getGenero()));
        clienteRepository.save(cliente);
        return clienteRepository.save(cliente);
    }

    //Metodo para alterar caracteristica de um elemento pelo id
    public ClienteEntity putCliente(long id, ClienteDTO clienteAtt){
        Optional<ClienteEntity> cliente = clienteRepository.findById(id);
        if(cliente.isPresent()){
            ClienteEntity clienteEntity = cliente.get();
            if (clienteAtt.getNome() != null){
                clienteEntity.setNome(clienteAtt.getNome());
            }
            if (clienteAtt.getCpf() != null){
                clienteEntity.setCpf(clienteAtt.getCpf());
            }
            if (clienteAtt.getGenero() != null){
                clienteEntity.setGenero(ClienteEntity.Genero.valueOf(clienteAtt.getGenero()));
            }
            return clienteRepository.save(clienteEntity);
        } else {
            throw new RuntimeException("Cliente nao encontrado para realizar as alteracoes");
        }
    }

    //Metodo para retornar todos os cliesntes ativos no banco de dados
    public List<ClienteEntity> getAll() {
        List<ClienteEntity> clientes = clienteRepository.findByAllAtivoTrue();
        return clientes;
    }

    //Metodo para retornar cliente ativo pelo id
    public ClienteEntity getById(long id) {
        Optional<ClienteEntity> cliente = clienteRepository.findByIdAndAtivoTrue(id);
        return cliente.orElseThrow( () -> new RuntimeException("Cliente nao encontrado") );
    }

    //Metodo para deletar fisicamente clientes do banco de dados pelo id
    public ClienteEntity deleteCliente(long id) {
        Optional<ClienteEntity> cliente = clienteRepository.findById(id);
        ClienteEntity clienteEntity = cliente.get();
        clienteRepository.delete(clienteEntity);
        return clienteEntity;
    }

    //Metodo para deletar logicamente
    @Transactional
    public ClienteEntity DeleteLogic(long id) {
        Optional<ClienteEntity> cliente = clienteRepository.findByIdAndAtivoTrue(id);
        ClienteEntity clienteEntity = cliente.get();
        clienteRepository.delete(clienteEntity);
        return clienteEntity;
    }

}
