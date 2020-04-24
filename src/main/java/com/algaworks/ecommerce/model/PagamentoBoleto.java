package com.algaworks.ecommerce.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "pagamento_boleto")
public class PagamentoBoleto extends EntidadeBaseInteger {

    @Column(name = "pedido_id")
    private Integer pedidoId;

    @Enumerated(EnumType.STRING)
    private StatusPagamento status;

    @Column(name = "codigo_barras")
    private String codigoBarras;

}
