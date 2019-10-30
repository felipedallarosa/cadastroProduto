package br.com.felipe.cadastroProduto.dto;


import br.com.felipe.cadastroProduto.client.dto.Localidade;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode
@Setter
@ToString
public class OutProduto {

    private String codigo;
    private String descricao;
    private BigDecimal valorUnitario;
    private BigDecimal saldo;
    private String status;
    private List<Localidade> localizacaoEstoque;


}
