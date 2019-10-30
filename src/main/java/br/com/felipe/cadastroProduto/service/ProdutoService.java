package br.com.felipe.cadastroProduto.service;

import br.com.felipe.cadastroProduto.client.LocalidadeClient;
import br.com.felipe.cadastroProduto.client.dto.Localidade;
import br.com.felipe.cadastroProduto.domain.Produto;
import br.com.felipe.cadastroProduto.domain.type.EnumDeletado;
import br.com.felipe.cadastroProduto.dto.OutProduto;
import br.com.felipe.cadastroProduto.exception.BloqueadaVendaProdutoException;
import br.com.felipe.cadastroProduto.repository.ProdutoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static br.com.felipe.cadastroProduto.converter.ProdutoConverter.toOutProduto;

@Slf4j
@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;
    private LocalidadeClient localidadeClient;

    public ProdutoService(ProdutoRepository produtoRepository, LocalidadeClient localidadeClient) {
        this.produtoRepository = produtoRepository;
        this.localidadeClient = localidadeClient;
    }

    public Optional<OutProduto> buscarProdutoPorCodigo(String codProduto) throws BloqueadaVendaProdutoException {

        log.info( "INICIO ProdutoService.buscarProdutoPorCodigo: " + codProduto + "|" + LocalDateTime.now());

        Optional<Produto> produto = produtoRepository.findTopByIsDeletedAndCodigo(EnumDeletado.N, codProduto);

        if (produto.isPresent()) {
            OutProduto out = toOutProduto(produto.get());
            alimentaLocalidadeESaldo(out);
            return Optional.of(out);
        } else {
            throw new BloqueadaVendaProdutoException( "Venda Bloqueada para esse Produto." );
        }

    }

    public void alimentaLocalidadeESaldo(OutProduto out){

        out.setLocalizacaoEstoque(localidadeClient.getLocalidade(out.getCodigo()));
        ajustaTotais(out);

    }


    public void ajustaTotais(OutProduto out) {
        out.setSaldo(
                out.getLocalizacaoEstoque()
                        .stream()
                        .map(Localidade::getSaldo)
                        .reduce(BigDecimal::add)
                        .orElse(BigDecimal.ZERO)
        );
    }
}
