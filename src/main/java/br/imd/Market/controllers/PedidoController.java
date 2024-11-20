package br.imd.Market.controllers;

import br.imd.Market.DTO.PedidoDTO;
import br.imd.Market.model.PedidoEntity;
import br.imd.Market.service.PedidoService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
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


    //Metodo para deletar fisicamente um pedido
    @DeleteMapping("DeletePedido/{id}")
    public ResponseEntity<PedidoEntity> delete (@PathVariable Long id){
        PedidoEntity pedido = pedidoService.delete(id);
        return ResponseEntity.ok(pedido);
    }

    //Metodo para deletar logicamente um pedido
    @DeleteMapping("DeleteLogic/{id}")
    public ResponseEntity<PedidoEntity> deleteLogic(@PathVariable Long id){
        PedidoEntity pedido = pedidoService.deleteLogic(id);
        return ResponseEntity.ok(pedido);
    }

    //Metodo para adicionar um produto no pedido
    @PostMapping("AdicionarProduto/{Id}/{Id2}")
    public ResponseEntity<PedidoEntity> AdicionarProduto(@PathVariable Long Id, @PathVariable Long Id2){
        PedidoEntity pedido = pedidoService.addProduto(Id,Id2);
        return ResponseEntity.ok(pedido);
    }

    //Metodo para remover um produto de algum pedido
    @DeleteMapping("RemoverProduto/{Id}/{Id2}")
    public ResponseEntity<PedidoEntity> RemoverProduto(@PathVariable Long Id, @PathVariable Long Id2){
        PedidoEntity pedido = pedidoService.removerProduto(Id,Id2);
        return ResponseEntity.ok(pedido);
    }
}
