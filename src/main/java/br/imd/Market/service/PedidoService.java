package br.imd.Market.service;

import br.imd.Market.DTO.PedidoDTO;
import br.imd.Market.model.ClienteEntity;
import br.imd.Market.model.PedidoEntity;
import br.imd.Market.model.ProdutoEntity;
import br.imd.Market.repository.ClienteRepository;
import br.imd.Market.repository.PedidoRepository;
import br.imd.Market.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    //Post Pedido
    public PedidoEntity postPedido(PedidoDTO pedidoDTO){
        PedidoEntity pedido = new PedidoEntity();
        pedido.setCodigo(pedidoDTO.getCodigo());
        List<ProdutoEntity> produtos = produtoRepository.findAllById(pedidoDTO.getProdutosId());
        pedido.setProdutos(produtos);
        ClienteEntity cliente = clienteRepository.findById(pedidoDTO.getClienteId()).orElseThrow(()-> new RuntimeException("Cliente não encontrado"));
        pedido.setCliente(cliente);
        return pedidoRepository.save(pedido);
    }

    //GET ALL
    public List<PedidoEntity> getAll(){
        List<PedidoEntity> pedidos = pedidoRepository.findAllByAtivoTrue();
        return pedidos;
    }

    //GET BY ID
    public PedidoEntity getById(long id){
        PedidoEntity pedido = pedidoRepository.findByIdAndAtivoTrue(id).orElseThrow(() -> new RuntimeException("Id informado é invalido"));
        return pedido;
    }

}
