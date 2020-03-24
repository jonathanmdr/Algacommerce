package com.algaworks.ecommerce.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "nota_fiscal")
public class NotaFiscal {

    @EqualsAndHashCode.Include
    @Id
    private Integer id;

    @Column(name = "pedido_id")
    private Integer pedidoId;

    private String xml;

    @Column(name = "data_emissao")
    private Date dataEmissao;

}
