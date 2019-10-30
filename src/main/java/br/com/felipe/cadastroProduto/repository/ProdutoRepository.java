package br.com.felipe.cadastroProduto.repository;

import br.com.felipe.cadastroProduto.domain.type.EnumDeletado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.felipe.cadastroProduto.domain.Produto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByIsDeletedAndDescricaoStartsWith(EnumDeletado isDeleted, String descricao);

    Optional<Produto> findTopByIsDeletedAndCodigo(EnumDeletado isDeleted, String codigo);

}
