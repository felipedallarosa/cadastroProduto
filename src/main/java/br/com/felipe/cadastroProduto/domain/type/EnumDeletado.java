package br.com.felipe.cadastroProduto.domain.type;

public enum EnumDeletado {
    S("Sim"),
    N("Nao");

    String deletado;
    EnumDeletado(String deletado) {
        this.deletado = deletado;
    }

    public void setDeletado(String deletado) {
        this.deletado = deletado;
    }
}
