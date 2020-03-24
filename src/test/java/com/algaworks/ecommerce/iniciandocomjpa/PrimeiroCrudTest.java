package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PrimeiroCrudTest extends EntityManagerTest {

    @Test
    public void A_inserirCliente() {
        Cliente cliente = criaCliente(3, "José Lucas");

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertNotNull(String.format("Cliente de id: %d é nulo", cliente.getId()), clienteVerificacao);
    }

    @Test
    public void B_buscaClientePorId() {
        final int CLIENTE_ID = 1;
        Cliente cliente = entityManager.find(Cliente.class, CLIENTE_ID);

        Assert.assertNotNull(String.format("Cliente de id: %d é nulo", CLIENTE_ID), cliente);
        Assert.assertEquals("Fernando Medeiros", cliente.getNome());
    }

    @Test
    public void C_atualizarCliente() {
        Cliente cliente = new Cliente();
        cliente.setId(1);
        cliente.setNome("Fernando Medeiros Silva");

        entityManager.getTransaction().begin();
        entityManager.merge(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertEquals("Fernando Medeiros Silva", clienteVerificacao.getNome());
    }

    @Test
    public void D_removerCliente() {
        Cliente cliente = entityManager.find(Cliente.class, 2);

        entityManager.getTransaction().begin();
        entityManager.remove(cliente);
        entityManager.getTransaction().commit();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertNull(String.format("Cliente de id: %d não foi exluído", cliente.getId()), clienteVerificacao);
    }

    private Cliente criaCliente(Integer id, String nome) {
        Cliente cliente = new Cliente();
        cliente.setId(id);
        cliente.setNome(nome);
        return cliente;
    }

}
