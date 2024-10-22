package br.imd.Market.service;

import br.imd.Market.DTO.ProdutoDTO;
import br.imd.Market.model.ProdutoEntity;
import br.imd.Market.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    //Metodo para converter o DTO e salvar o produto
    public ProdutoEntity postProduto(ProdutoDTO produtoDTO) {
        ProdutoEntity produto = new ProdutoEntity();
        produto.setNomeProduto(produtoDTO.getNomeProduto());
        produto.setMarca(produtoDTO.getMarca());
        produto.setDataFabricacao(produtoDTO.getDataFabricacao());
        produto.setDataValidade(produtoDTO.getDataValidade());
        produto.setGenero(ProdutoEntity.GeneroEnum.valueOf(produtoDTO.getGenero()));
        produto.setLote(produtoDTO.getLote());
        return produtoRepository.save(produto);
    }

    //Metodo para alterar caracteristicas de um elemento pelo id
    public ProdutoEntity putProduto(Long id, ProdutoDTO produtoAtt) {
        Optional<ProdutoEntity> produtoOptional = produtoRepository.findById(id);
        if (produtoOptional.isPresent()) {
            ProdutoEntity produto = produtoOptional.get();
            produto.setNomeProduto(produtoAtt.getNomeProduto());
            produto.setMarca(produtoAtt.getMarca());
            produto.setDataFabricacao(produtoAtt.getDataFabricacao());
            produto.setDataValidade(produtoAtt.getDataValidade());
            produto.setGenero(ProdutoEntity.GeneroEnum.valueOf(produtoAtt.getGenero()));
            produto.setLote(produtoAtt.getLote());
            return produtoRepository.save(produto);
        } else {
            throw new RuntimeException("Produto nao existe no banco de dados da loja");
        }
    }

    //Metodo para retornar todos os produtos do banco de dados
    public List<ProdutoEntity> getAll() {
        List<ProdutoEntity> produtos = produtoRepository.findAll();
        return produtos;
    }

    //Metodo para retornar produto pelo id
    public ProdutoEntity getById(Long id) {
        Optional<ProdutoEntity> produto = produtoRepository.findById(id);
        return produto.orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    //Metodo para deletar um produto pelo id
    public ProdutoEntity deleteById(Long id) {
        Optional<ProdutoEntity> produto = produtoRepository.findById(id);
        ProdutoEntity produtoAtt = produto.get();
        produtoRepository.delete(produtoAtt);
        return produtoAtt;
    }



}
