package com.algaworks.ecommerce.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Estoque {

    @EqualsAndHashCode.Include
    @Id
    private Integer id;

    private Integer produtoId;

    private Integer quantidade;

}
