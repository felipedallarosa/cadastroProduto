package br.com.felipe.cadastroProduto.client.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@EqualsAndHashCode
@Setter
@ToString
public class Localidade {

    private String cnpj;
    private String cidade;
    private String estado;
    private BigDecimal saldo;

}
