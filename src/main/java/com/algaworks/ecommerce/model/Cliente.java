package com.algaworks.ecommerce.model;

import com.mysql.cj.util.StringUtils;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
@SecondaryTable(name = "cliente_detalhe", pkJoinColumns = @PrimaryKeyJoinColumn(name = "cliente_id"))
@Entity
@Table(name = "cliente")
public class Cliente extends EntidadeBaseInteger {

    private String nome;

    @Transient
    private String primeiroNome;

    @Column(table = "cliente_detalhe")
    @Enumerated(EnumType.STRING)
    private SexoCliente sexo;

    @Column(name = "data_nascimento", table = "cliente_detalhe")
    private LocalDate dataNascimento;

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
