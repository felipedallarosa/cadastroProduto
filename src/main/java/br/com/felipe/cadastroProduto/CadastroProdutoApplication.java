package br.com.felipe.cadastroProduto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = { "br.com.felipe.cadastroProduto" })
public class CadastroProdutoApplication {

    public static void main(String[] args) {

        SpringApplication.run(CadastroProdutoApplication.class, args);
    }

}

