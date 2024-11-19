package br.imd.Market.controllers;

import br.imd.Market.DTO.PedidoDTO;
import br.imd.Market.model.PedidoEntity;
import br.imd.Market.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pedidos")
public class PedidoController {
    @Autowired
    PedidoService pedidoService;

    //Metodo para inserir um novo pedido
    @PostMapping("postPedido")
    public ResponseEntity<PedidoEntity> postPedido(@Valid  @RequestBody PedidoDTO pedidoDTO){
        PedidoEntity pedido = pedidoService.postPedido(pedidoDTO);
        return ResponseEntity.ok(pedido);
    }

    //Metodo para alterar algum dado de um pedido especifico
    @PutMapping("putPedido/{id}")
    public PedidoEntity putPedido (@PathVariable long id, @RequestBody PedidoDTO pedidoDTO){
        return pedidoService.putPedido(id, pedidoDTO);
    }

    //Metodo para retornar todos os pedidos ativos
    @GetMapping("getAll")
    public ResponseEntity<List<PedidoEntity>> getAll(){
        List<PedidoEntity> pedidos = pedidoService.getAll();
        return ResponseEntity.ok(pedidos);
    }

    //Metodo para retornar apenas um pedido ativo pelo id
    @GetMapping("getById/{id}")
    public ResponseEntity<PedidoEntity> getById(@PathVariable long id){
        PedidoEntity pedido = pedidoService.getById(id);
        return ResponseEntity.ok(pedido);
    }



}
