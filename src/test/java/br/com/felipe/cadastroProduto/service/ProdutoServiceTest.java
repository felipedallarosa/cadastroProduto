package br.com.felipe.cadastroProduto.service;

import br.com.felipe.cadastroProduto.client.LocalidadeClient;
import br.com.felipe.cadastroProduto.client.dto.Localidade;
import br.com.felipe.cadastroProduto.domain.Produto;
import br.com.felipe.cadastroProduto.domain.type.EnumDeletado;
import br.com.felipe.cadastroProduto.dto.OutProduto;
import br.com.felipe.cadastroProduto.exception.BloqueadaVendaProdutoException;
import br.com.felipe.cadastroProduto.repository.ProdutoRepository;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProdutoServiceTest  {

    private ProdutoService produtoService;

    @Mock
    private ProdutoRepository produtoRepositoryMock;
    @Mock
    private LocalidadeClient localidadeClientMock;

    @Before
    public void setUp() { produtoService = new ProdutoService( produtoRepositoryMock,
                                                               localidadeClientMock);
    }

    @Test
    public void buscarDadosProduto() throws BloqueadaVendaProdutoException {
        // given

        String codProduto = "12345";
        Produto produto = new Produto();
        produto.setCodigo(codProduto);

        when(produtoRepositoryMock.findTopByIsDeletedAndCodigo(EnumDeletado.N, codProduto))
                                     .thenReturn( Optional.of(produto));


        Localidade localidade = new Localidade();
        localidade.setSaldo(BigDecimal.TEN);

        when( localidadeClientMock.getLocalidade(produto.getCodigo()))
                                     .thenReturn(Arrays.asList( localidade ));

        // when
        Optional<OutProduto> retorno =  produtoService.buscarProdutoPorCodigo(codProduto);

        //then
        assertEquals( codProduto , retorno.get().getCodigo());
        assertEquals( BigDecimal.TEN, retorno.get().getLocalizacaoEstoque().get(0).getSaldo());

    }


}