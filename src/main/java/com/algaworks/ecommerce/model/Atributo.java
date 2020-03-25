package com.algaworks.ecommerce.model;

import lombok.*;

import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Atributo {

    private String nome;
    private String valor;

}
