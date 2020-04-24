package com.algaworks.ecommerce.model;

import com.algaworks.ecommerce.listner.GerarNotaFiscalListner;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@EntityListeners({GerarNotaFiscalListner.class})
@Entity
@Table(name = "pedido")
public class Pedido extends EntidadeBaseInteger {

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao", insertable = false)
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

    public boolean isPago() {
        return status.equals(StatusPedido.PAGO);
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
