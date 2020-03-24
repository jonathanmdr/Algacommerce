package com.algaworks.ecommerce.mapeamentobasico;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.EnderecoEntregaPedido;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.StatusPedido;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MapeandoObjetoEmbutido extends EntityManagerTest {

    @Test
    public void analisarMapeamentoObjetoEmbutido() {
        Cliente cliente = entityManager.find(Cliente.class, 1);

        EnderecoEntregaPedido endereco = new EnderecoEntregaPedido();
        endereco.setCep("00000-000");
        endereco.setLogradouro("Ruas das Laranjeiras");
        endereco.setNumero("123");
        endereco.setBairro("Centro");
        endereco.setCidade("Uberlândia");
        endereco.setEstado("MG");

        Pedido pedido = new Pedido();
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setTotal(new BigDecimal(1000));
        pedido.setEnderecoEntrega(endereco);
        pedido.setCliente(cliente);

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertNotNull(String.format("Pedido de id: %d é nulo", pedido.getId()), pedidoVerificacao);
        Assert.assertNotNull(String.format("Cliente do pedido de id: %d é nulo", pedido.getId()), pedidoVerificacao.getCliente());
        Assert.assertNotNull(String.format("Endereço de entrega do pedido de id: %d é nulo", pedido.getId()), pedidoVerificacao.getEnderecoEntrega());
        Assert.assertNotNull(String.format("CEP do endereço de entrega do pedido de id: %d é nulo", pedido.getId()), pedidoVerificacao.getEnderecoEntrega().getCep());
    }

}
