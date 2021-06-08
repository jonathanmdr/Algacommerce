package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.dto.ProdutoDTO;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pedido;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class BasicoJPQLTest extends EntityManagerTest {

    @Test
    public void projetarOResultadoComDto() {
        final String jpql = "select new com.algaworks.ecommerce.dto.ProdutoDTO(id, nome) from Produto";
        TypedQuery<ProdutoDTO> typedQuery = entityManager.createQuery(jpql, ProdutoDTO.class);
        List<ProdutoDTO> objects = typedQuery.getResultList();
        Assert.assertFalse(objects.isEmpty());

        objects.forEach(dto -> System.out.println(dto.getId() + " - " + dto.getNome()));
    }

    @Test
    public void projetarOResultado() {
        TypedQuery<Object[]> typedQuery = entityManager.createQuery("select id, nome from Produto", Object[].class);
        List<Object[]> objects = typedQuery.getResultList();
        Assert.assertTrue(objects.get(0).length == 2);

        objects.forEach(array -> System.out.println(array[0] + " - " + array[1]));
    }

    @Test
    public void buscandoUmUnicoAtributoDaEntidade() {
        TypedQuery<String> stringTypedQuery = entityManager.createQuery("select p.nome from Produto p", String.class);
        List<String> produtos = stringTypedQuery.getResultList();
        Assert.assertTrue(String.class.equals(produtos.get(0).getClass()));

        TypedQuery<Cliente> clienteTypedQuery = entityManager.createQuery("select p.cliente from Pedido p", Cliente.class);
        List<Cliente> clientes = clienteTypedQuery.getResultList();
        Assert.assertTrue(Cliente.class.equals(clientes.get(0).getClass()));
    }

    @Test
    public void buscaPorId() {
        Pedido pedido = entityManager.find(Pedido.class, 1);
        Assert.assertNotNull(pedido);

        TypedQuery<Pedido> typedQuery = entityManager.createQuery("select p from Pedido p where p.id = :id", Pedido.class);
        typedQuery.setParameter("id", 1);
        Pedido pedido1 = typedQuery.getSingleResult();
        Assert.assertNotNull(pedido1);
    }

    @Test
    public void diferencaEntreQueryETypedQuery() {
        final String jpql = "select p from Pedido p where p.id = :id";

        Query query = entityManager.createQuery(jpql);
        query.setParameter("id", 1);
        Pedido pedidoUm = (Pedido) query.getSingleResult();
        Assert.assertNotNull(pedidoUm);

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
        typedQuery.setParameter("id", 1);
        Pedido pedidoDois = typedQuery.getSingleResult();
        Assert.assertNotNull(pedidoDois);
    }

}
