package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class JoinTest extends EntityManagerTest {

    @Test
    public void usarFecthJoin() {
        StringBuilder builder = new StringBuilder();
        builder.delete(0, builder.length());
        builder.append(" select p from Pedido p ");
        builder.append(" left join fetch p.pagamento ");
        builder.append(" join fetch p.cliente ");
        builder.append(" left join fetch p.notaFiscal ");

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(builder.toString(), Pedido.class);
        List<Pedido> pedidos = typedQuery.getResultList();
        Assert.assertFalse(pedidos.isEmpty());
    }

    @Test
    public void fazerLeftJoin() {
        TypedQuery<Pedido> typedQuery = entityManager.createQuery("select p from Pedido p left join p.pagamento pag where pag.status = 'PROCESSANDO'", Pedido.class);
        List<Pedido> pedidos = typedQuery.getResultList();
        Assert.assertFalse(pedidos.isEmpty());
    }

    @Test
    public void fazerJoin() {
        TypedQuery<Pedido> typedQuery = entityManager.createQuery("select p from Pedido p join p.pagamento pag", Pedido.class);
        List<Pedido> pedidos = typedQuery.getResultList();
        Assert.assertFalse(pedidos.isEmpty());
    }

}
