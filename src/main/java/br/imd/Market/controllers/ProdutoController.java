package br.imd.Market.controllers;


import br.imd.Market.DTO.ProdutoDTO;
import br.imd.Market.model.ProdutoEntity;
import br.imd.Market.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("produtos")
public class ProdutoController {


    @Autowired
    private ProdutoService produtoService;

    //Metodo para salvar produto
    @PostMapping("postProduto")
    public ResponseEntity<ProdutoEntity> postProduto(@Valid @RequestBody ProdutoDTO produtoDTO) {
        ProdutoEntity produto = produtoService.postProduto(produtoDTO);
        return ResponseEntity.ok(produto);

    }

    //Metodo para retornar todos os produtos
    @GetMapping("getAll")
    public ResponseEntity<List<ProdutoEntity>> getAll(){
        List<ProdutoEntity> produtos = produtoService.getAll();
        return ResponseEntity.ok(produtos);
    }

    //Metodo para retornar o produto pelo id
    @GetMapping("getById/{id}")
    public ProdutoEntity getById(@PathVariable Long id){

        return produtoService.getById(id);
    }

    //Metodo para alterar dados de um elemento pelo id
    @PutMapping("putProduto/{id}")
    public ProdutoEntity putProduto(@PathVariable Long id, @RequestBody ProdutoDTO produtoAtt){
        return produtoService.putProduto(id, produtoAtt);
    }

    //Metodo para deletar elemento pelo id
    @DeleteMapping("DeleteProduto/{id}")
    public ResponseEntity<ProdutoEntity> deleteProduto(@PathVariable Long id){
        ProdutoEntity produto = produtoService.deleteById(id);
        return ResponseEntity.ok(produto);
    }

    //Metodo para deletar elemento logicamento(altera campo ativo para false)
    @DeleteMapping("DeleteLogic/{id}")
    public ResponseEntity<String> deleteLogic(@PathVariable Long id){
        produtoService.DeleteLogic(id);
        return ResponseEntity.ok("Produto excluido com sucesso");
    }
}
