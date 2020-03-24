package com.algaworks.ecommerce.mapeamentobasico;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.SexoCliente;
import org.junit.Assert;
import org.junit.Test;

public class MapeandoEnumeracoesTest extends EntityManagerTest {

    @Test
    public void testeEnum() {
        Cliente cliente = criaCliente("José Mineiro", SexoCliente.MASCULINO);

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertNotNull(String.format("Cliente de id: %d é nulo", cliente.getId()), clienteVerificacao);
    }

    private Cliente criaCliente(String nome, SexoCliente sexo) {
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setSexo(sexo);
        return cliente;
    }

}
