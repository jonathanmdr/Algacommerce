package com.algaworks.ecommerce.model;

import com.mysql.cj.util.StringUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "cliente")
public class Cliente {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @Transient
    private String primeiroNome;

    @Enumerated(EnumType.STRING)
    private SexoCliente sexo;

    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;

    @ElementCollection
    @CollectionTable(name = "cliente_contato",
            joinColumns = @JoinColumn(name = "cliente_id"))
    @MapKeyColumn(name = "tipo")
    @Column(name = "descricao")
    private Map<String, String> contatos;

    @PostLoad
    private void pegaPrimeiroNome() {
        if (!StringUtils.isNullOrEmpty(nome)) {
            int index = nome.indexOf(" ");
            if (index > -1) {
                primeiroNome = nome.substring(0, index);
            }
        }
    }

}
