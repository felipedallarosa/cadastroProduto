package br.com.felipe.cadastroProduto.domain;

import br.com.felipe.cadastroProduto.domain.type.EnumDeletado;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author FelipeAntonioDallaro
 */
@Entity
@Table(name = "PRODUTO")
@XmlRootElement

@Getter
@EqualsAndHashCode
@Setter
@ToString
public class Produto implements Serializable{

        private static final long serialVersionUID = 1L;

        @Id
        @Basic(optional = false)
        @NotNull
        @Column(name = "RECNO")
        private BigDecimal recno;

        @Basic(optional = false)
        @NotNull
        @Column(name = "IS_DELETED")
        @Enumerated
        private EnumDeletado isDeleted;

        @Size(max = 30)
        @Column(name = "FBLOQ")
        private Boolean isBloqueado;

        @Basic(optional = false)
        @NotNull
        @Size(min = 1, max = 2)
        @Column(name = "FCOD")
        private String codigo;

        @Size(max = 30)
        @Column(name = "FDESC")
        private String descricao;

        @Column(name = "FVALORUNI")
        private BigDecimal valorUnitario;

        @Column(name = "FMARGEM")
        private BigDecimal margemDeLucro;
}
