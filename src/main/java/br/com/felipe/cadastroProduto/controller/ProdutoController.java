package br.com.felipe.cadastroProduto.controller;

import br.com.felipe.cadastroProduto.dto.OutProduto;
import br.com.felipe.cadastroProduto.exception.BloqueadaVendaProdutoException;
import br.com.felipe.cadastroProduto.service.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static org.springframework.http.HttpStatus.FORBIDDEN;

@RestController
@RequestMapping("/produto")
@Api(
        value = "Informacoes do Produto e Localizacao",
        tags = {"Detalhar Informações de Produto e Localizacao"})
@Slf4j
public class ProdutoController {

    private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping(value="/{codProduto}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Buscar Produto Por Codigo")
    public ResponseEntity<OutProduto> consultarProdutoCodigo(@PathVariable("codProduto") String codProduto)  {

        try {
            Optional<OutProduto> produto = produtoService.buscarProdutoPorCodigo(codProduto);

            if (produto.isPresent()) {
                return ResponseEntity.ok().body(produto.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        }catch (BloqueadaVendaProdutoException ex){
            log.info( "Produto: " + codProduto + " possui bloqueio de Venda.", ex );
            OutProduto out = new OutProduto();
            out.setStatus(ex.getMessage());
            return ResponseEntity.status( FORBIDDEN ).body(out);
        }

    }


}
