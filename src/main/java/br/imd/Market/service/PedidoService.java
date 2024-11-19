package br.imd.Market.service;

import br.imd.Market.DTO.ClienteDTO;
import br.imd.Market.DTO.PedidoDTO;
import br.imd.Market.model.ClienteEntity;
import br.imd.Market.model.PedidoEntity;
import br.imd.Market.model.ProdutoEntity;
import br.imd.Market.repository.ClienteRepository;
import br.imd.Market.repository.PedidoRepository;
import br.imd.Market.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    //Metodo post para criação do pedido
    public PedidoEntity postPedido(PedidoDTO pedidoDTO){
        PedidoEntity pedido = new PedidoEntity();
        pedido.setCodigo(pedidoDTO.getCodigo());
        List<ProdutoEntity> produtos = produtoRepository.findAllById(pedidoDTO.getProdutosId());
        pedido.setProdutos(produtos);
        ClienteEntity cliente = clienteRepository.findById(pedidoDTO.getClienteId()).orElseThrow(()-> new RuntimeException("Cliente não encontrado"));
        pedido.setCliente(cliente);
        return pedidoRepository.save(pedido);
    }

    //Metodo getAll para retornar todos os pedidos ativos
    public List<PedidoEntity> getAll(){
        List<PedidoEntity> pedidos = pedidoRepository.findAllByAtivoTrue();
        return pedidos;
    }

    //Metodo getById para retornar apenas um pedido ativo
    public PedidoEntity getById(long id){
        PedidoEntity pedido = pedidoRepository.findByIdAndAtivoTrue(id).orElseThrow(() -> new RuntimeException("Id informado é invalido"));
        return pedido;
    }

    //Metodo put para atualizar algum campo do pedido
    public PedidoEntity putPedido(long id, PedidoDTO pedidoAtt){
        PedidoEntity pedido = pedidoRepository.findByIdAndAtivoTrue(id).orElseThrow(()-> new RuntimeException("Pedido com id informado não foi encontrado"));
        if (pedidoAtt.getCodigo() != null){
            pedido.setCodigo(pedidoAtt.getCodigo());
        }
        if (pedidoAtt.getProdutosId() != null && !pedidoAtt.getProdutosId().isEmpty()){
            List<ProdutoEntity> produtos = produtoRepository.findAllById(pedidoAtt.getProdutosId());
            pedido.setProdutos(produtos);
        }
        if (pedidoAtt.getClienteId() != null){
            ClienteEntity cliente = clienteRepository.findByIdAndAtivoTrue(pedidoAtt.getClienteId()).orElseThrow(()-> new RuntimeException("Cliente com id informado não foi encontrado"));
            pedido.setCliente(cliente);
        }

        return pedidoRepository.save(pedido);
    }

    //Metodo delete para deletar fisicamente um pedido
    public PedidoEntity delete (Long id){
        Optional<PedidoEntity> pedido = pedidoRepository.findByIdAndAtivoTrue(id);
        PedidoEntity pedidoDelet = pedido.get();
        pedidoRepository.delete(pedidoDelet);
        return pedidoDelet;
    }

    //Metodo deleteLogic para deletar logicamente um pedido
    public PedidoEntity deleteLogic(Long Id){
        PedidoEntity pedido = pedidoRepository.findByIdAndAtivoTrue(Id).orElseThrow(()-> new RuntimeException("Pedido não encontrado com o id informado"));
        pedidoRepository.delete(pedido);
        return pedido;
    }
    //Metodo para adicionar um novo produto no pedido
    public PedidoEntity addProduto(Long Id, Long Id2){
        PedidoEntity pedido = pedidoRepository.findByIdAndAtivoTrue(Id).orElseThrow(() -> new RuntimeException("Id pedido invalido"));
        ProdutoEntity produto = produtoRepository.findByIdAndAtivoTrue(Id2).orElseThrow(()-> new RuntimeException("Id produto invalido"));
        pedido.getProdutos().add(produto);
        pedidoRepository.save(pedido);
        return pedido;
    }

}
