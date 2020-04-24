package com.algaworks.ecommerce.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "pagamento_cartao")
public class PagamentoCartao extends EntidadeBaseInteger {

    @MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @Enumerated(EnumType.STRING)
    private StatusPagamento status;

    private String numero;

}
