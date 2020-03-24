package com.algaworks.ecommerce.relacionamentos;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import org.junit.Assert;
import org.junit.Test;

public class RemovendoEntidadesReferenciadasTest extends EntityManagerTest {

    @Test
    public void removerEntidadeRelacionada() {
        Pedido pedido = entityManager.find(Pedido.class, 1);

        Assert.assertFalse(String.format("Pedido de id: %d não possuí itens associados", pedido.getId()), pedido.getItensPedido().isEmpty());

        entityManager.getTransaction().begin();
        pedido.getItensPedido().forEach(item -> entityManager.remove(item));
        entityManager.remove(pedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertNull(String.format("Pedido de id: %d não pôde ser excluído", pedido.getId()), pedidoVerificacao);
    }

}
