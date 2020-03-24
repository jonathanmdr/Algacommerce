package com.algaworks.ecommerce.service;

import com.algaworks.ecommerce.model.Pedido;

public class NotaFiscalService {

    public void gerar(Pedido pedido) {
        System.out.println(String.format("Gerando a NF para o pedido de nº: %d", pedido.getId()));
    }

}
