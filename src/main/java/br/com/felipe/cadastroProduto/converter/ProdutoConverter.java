package br.com.felipe.cadastroProduto.converter;

import br.com.felipe.cadastroProduto.domain.Produto;
import br.com.felipe.cadastroProduto.dto.OutProduto;

public class ProdutoConverter {

    protected ProdutoConverter() {
        throw new IllegalStateException("Utility class");
    }

    public static OutProduto toOutProduto(Produto cadastroProduto) {
        OutProduto outProduto = new OutProduto();
        outProduto.setCodigo(cadastroProduto.getCodigo());
        outProduto.setDescricao(cadastroProduto.getDescricao());
        outProduto.setValorUnitario(cadastroProduto.getValorUnitario());
        return outProduto;
    }

}
