package br.com.felipe.cadastroProduto.controller

import br.com.felipe.cadastroProduto.dto.OutProduto
import br.com.felipe.cadastroProduto.exception.BloqueadaVendaProdutoException
import br.com.felipe.cadastroProduto.service.ProdutoService
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProdutoController.class)
public class ProdutoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProdutoService produtoService;

    @Before
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void deveBuscarPorCodigo() throws Exception {

        OutProduto produto = new OutProduto();
        produto.setCodigo("12345");

        when(produtoService.buscarProdutoPorCodigo(any())).thenReturn( Optional.of(produto) );

        mvc.perform(get("/produto/{codProduto}", "12345")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.codigo").value( "12345" ));
    }

    @Test
    public void deveBuscarPorCodigoENaoTerCadastro() throws Exception {


        when(produtoService.buscarProdutoPorCodigo(any())).thenReturn( Optional.empty() );

        mvc.perform(get("/produto/{codProduto}", "12345")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isNotFound());
    }


    @Test
    public void deveBuscarPorCodigoEEstarDeletado() throws Exception {

        when(produtoService.buscarProdutoPorCodigo(any())).thenThrow(BloqueadaVendaProdutoException.class );

        mvc.perform(get("/produto/{codProduto}", "12345")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isForbidden());
    }


}
