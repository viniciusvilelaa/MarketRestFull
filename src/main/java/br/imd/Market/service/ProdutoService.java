package br.imd.Market.service;

import br.imd.Market.DTO.ProdutoDTO;
import br.imd.Market.model.ProdutoEntity;
import br.imd.Market.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Optional<ProdutoEntity> produtoOptional = produtoRepository.findByIdAndAtivoTrue(id);
        if (produtoOptional.isPresent()) {
            ProdutoEntity produto = produtoOptional.get();
            if(produtoAtt.getNomeProduto() != null){
                produto.setNomeProduto(produtoAtt.getNomeProduto());
            }
            if (produtoAtt.getMarca() != null){
                produto.setMarca(produtoAtt.getMarca());
            }
            if (produtoAtt.getDataFabricacao() != null){
                produto.setDataFabricacao(produtoAtt.getDataFabricacao());
            }
            if (produtoAtt.getDataValidade() != null){
                produto.setDataValidade(produtoAtt.getDataValidade());
            }
            if (produtoAtt.getGenero() != null){
                produto.setGenero(ProdutoEntity.GeneroEnum.valueOf(produtoAtt.getGenero()));
            }
            if (produtoAtt.getLote() != null){
                produto.setLote(produtoAtt.getLote());
            }
            return produtoRepository.save(produto);
        } else {
            throw new RuntimeException("Produto nao existe no banco de dados da loja");
        }
    }

    //Metodo para retornar todos os produtos ativos do banco de dados
    public List<ProdutoEntity> getAll() {
        List<ProdutoEntity> produtos = produtoRepository.findAllByAtivoTrue();
        return produtos;
    }

    //Metodo para retornar produto ativo pelo id
    public ProdutoEntity getById(Long id) {
        Optional<ProdutoEntity> produto = produtoRepository.findByIdAndAtivoTrue(id);
        return produto.orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    //Metodo para deletar um produto pelo id
    public ProdutoEntity deleteById(Long id) {
        Optional<ProdutoEntity> produto = produtoRepository.findById(id);
        ProdutoEntity produtoAtt = produto.get();
        produtoRepository.delete(produtoAtt);
        return produtoAtt;
    }

    //Metodo DeleteLogic
    @Transactional
    public ProdutoEntity DeleteLogic(Long id){
        Optional<ProdutoEntity> produto = produtoRepository.findByIdAndAtivoTrue(id);
        ProdutoEntity produtoDelete = produto.get();
        produtoDelete.setAtivo(false);
        produtoRepository.save(produtoDelete);
        return produtoDelete;
    }

}
