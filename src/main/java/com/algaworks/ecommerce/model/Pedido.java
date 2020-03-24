package com.algaworks.ecommerce.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "pedido")
public class Pedido {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;

    @OneToOne(mappedBy = "pedido")
    private NotaFiscal notaFiscal;

    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @Embedded
    private EnderecoEntregaPedido enderecoEntrega;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itensPedido;

    @OneToOne(mappedBy = "pedido")
    private PagamentoCartao pagamentoCartao;

    public void calcularTotalPedido() {
        if (itensPedido != null) {
            total = itensPedido.stream()
                    .map(ItemPedido::getPrecoProduto)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
    }

    @PrePersist
    public void aoPersistir() {
        dataCriacao = LocalDateTime.now();
        calcularTotalPedido();
    }

    @PreUpdate
    public void aoAtualizar() {
        dataAtualizacao = LocalDateTime.now();
        calcularTotalPedido();
    }

    @PreRemove
    public void aoRemover() {
        System.out.println("Antes de excluir");
    }

    @PostPersist
    public void aposPersistir() {
        System.out.println("Depois da inclusão");
    }

    @PostUpdate
    public void aposAtualizar() {
        System.out.println("Depois da alteração");
    }

    @PostRemove
    public void aposRemover() {
        System.out.println("Depois de excluir");
    }

}
