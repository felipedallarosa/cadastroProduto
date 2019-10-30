package br.com.felipe.cadastroProduto.client;

import br.com.felipe.cadastroProduto.client.dto.Localidade;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "Localidade", url = "${localidade.url}")
public interface LocalidadeClient {

    @GetMapping("/buscaLocalidade")
    List<Localidade> getLocalidade(@RequestParam("codProduto") String codigoProduto);
}
