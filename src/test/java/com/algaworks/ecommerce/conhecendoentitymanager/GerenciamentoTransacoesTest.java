package com.algaworks.ecommerce.conhecendoentitymanager;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.StatusPedido;
import org.junit.Test;

public class GerenciamentoTransacoesTest extends EntityManagerTest {

    @Test(expected = Exception.class)
    public void abrirFecharTransacao() {
        try {
            entityManager.getTransaction().begin();
            metodoDeRegraDeNegocio();
            entityManager.getTransaction().commit();
        } catch(Exception ex) {
            entityManager.getTransaction().rollback();
            throw ex;
        }
    }

    private void metodoDeRegraDeNegocio() {
        Pedido pedido = entityManager.find(Pedido.class, 1);

        pedido.setStatus(StatusPedido.PAGO);

        if (pedido.getPagamento() == null) {
            throw new RuntimeException("Pagamento do pedido ainda não foi efetuado");
        }
    }

}
