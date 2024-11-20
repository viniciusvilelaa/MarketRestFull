package br.imd.Market.controllers;

import br.imd.Market.DTO.ClienteDTO;
import br.imd.Market.model.ClienteEntity;
import br.imd.Market.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    //Metodo postCliente
    @PostMapping("postCliente")
    public ResponseEntity<ClienteEntity> postCliente(@Valid @RequestBody ClienteDTO clienteDTO){
        ClienteEntity cliente = clienteService.postCliente(clienteDTO);
        return ResponseEntity.ok(cliente);
    }

    //Metodo getAll
    @GetMapping("getAll")
    public ResponseEntity<List<ClienteEntity>> getAll (){
        List<ClienteEntity> clientes = clienteService.getAll();
        return ResponseEntity.ok(clientes);
    }

    //Metodo getById
    @GetMapping("getById/{id}")
    public ResponseEntity<ClienteEntity> getById(@PathVariable long id){
        ClienteEntity cliente = clienteService.getById(id);
        return ResponseEntity.ok(cliente);
    }

    //Metodo putCliente
    @PutMapping("putCliente/{id}")
    public ClienteEntity putCliente(@PathVariable long id, @RequestBody  ClienteDTO clienteAtt){
        return clienteService.putCliente(id, clienteAtt);
    }

    //Metodo delete por id
    @DeleteMapping("DeleteCliente/{id}")
    public ResponseEntity<ClienteEntity> deleteCliente(@PathVariable long id){
        ClienteEntity cliente = clienteService.deleteCliente(id);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("DeleteLogic/{id}")
    public ResponseEntity<ClienteEntity> DeleteLogic(@PathVariable long id){
        ClienteEntity cliente = clienteService.DeleteLogic(id);
        return ResponseEntity.ok(cliente);
    }

}
