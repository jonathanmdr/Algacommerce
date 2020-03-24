package com.algaworks.ecommerce.conhecendoentitymanager;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.StatusPedido;
import org.junit.Assert;
import org.junit.Test;

public class FlushTest extends EntityManagerTest {

    @Test
    public void chamarFlush() {
        try {
            entityManager.getTransaction().begin();

            Pedido pedido = entityManager.find(Pedido.class, 1);
            pedido.setStatus(StatusPedido.PAGO);

            entityManager.flush();

            Pedido pedidoPago = entityManager.createQuery("from Pedido p where p.id = :pedido_id", Pedido.class)
                    .setParameter("pedido_id", pedido.getId())
                    .getSingleResult();

            Assert.assertEquals(String.format("Pedido de id: %d não foi atualizado para pago", pedido.getId()), pedido.getStatus(), pedidoPago.getStatus());
            entityManager.getTransaction().commit();
        } catch(Exception ex) {
            entityManager.getTransaction().rollback();
            throw ex;
        }
    }

}
