package com.algaworks.ecommerce.operacoesemcascata;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.ItemPedido;
import com.algaworks.ecommerce.model.ItemPedidoId;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class CascadeTypeRemoveTest extends EntityManagerTest {

    @Ignore
    @Test
    public void removerItensOrfaos() {
        Pedido pedido = entityManager.find(Pedido.class, 1);

        Assert.assertFalse(pedido.getItensPedido().isEmpty());

        entityManager.getTransaction().begin();
        pedido.getItensPedido().clear();
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertTrue(pedidoVerificacao.getItensPedido().isEmpty());
    }

    @Test
    public void removerRelacaoProdutoCategoria() {
        Produto produto = entityManager.find(Produto.class, 1);

        Assert.assertFalse(produto.getCategorias().isEmpty());

        entityManager.getTransaction().begin();
        produto.getCategorias().clear();
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertTrue(produtoVerificacao.getCategorias().isEmpty());
    }

    @Ignore
    @Test
    public void removerPedidoEItens() {
        Pedido pedido = entityManager.find(Pedido.class, 1);

        entityManager.getTransaction().begin();
        entityManager.remove(pedido); // Necessário CascadeType.REMOVE no atributo "itens".
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertNull(pedidoVerificacao);
    }

    @Ignore
    @Test
    public void removerItemPedidoEPedido() {
        ItemPedido itemPedido = entityManager.find(ItemPedido.class, new ItemPedidoId(1, 1));

        entityManager.getTransaction().begin();
        entityManager.remove(itemPedido); // Necessário CascadeType.REMOVE no atributo "pedido".
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, itemPedido.getPedido().getId());
        Assert.assertNull(pedidoVerificacao);
    }

}
