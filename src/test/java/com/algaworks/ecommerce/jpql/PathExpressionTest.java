package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class PathExpressionTest extends EntityManagerTest {

    @Test
    public void buscarPedidoComProdutoEspecifico() {
        TypedQuery<Pedido> typedQuery = entityManager.createQuery("select p from Pedido p join fetch p.itensPedido i where i.produto.id = 1", Pedido.class);
        List<Pedido> pedidos = typedQuery.getResultList();
        Assert.assertFalse(pedidos.isEmpty());
    }

    @Test
    public void usarPathExpression() {
        TypedQuery<String> typedQuery = entityManager.createQuery("select p.cliente.nome from Pedido p", String.class);
        List<String> stringList = typedQuery.getResultList();
        Assert.assertFalse(stringList.isEmpty());
    }

}
