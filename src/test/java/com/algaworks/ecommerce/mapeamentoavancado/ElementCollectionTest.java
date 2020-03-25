package com.algaworks.ecommerce.mapeamentoavancado;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Atributo;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

public class ElementCollectionTest extends EntityManagerTest {

    @Test
    public void aplicarTags() {
        entityManager.getTransaction().begin();
        Produto produto = entityManager.find(Produto.class, 1);
        produto.setTags(Arrays.asList("ebook", "livro-digital"));

        entityManager.getTransaction().commit();
        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertFalse("Lista de tags está vazia", produtoVerificacao.getTags().isEmpty());
    }

    @Test
    public void aplicarAtributos() {
        entityManager.getTransaction().begin();
        Produto produto = entityManager.find(Produto.class, 1);
        produto.setAtributos(Arrays.asList(new Atributo("tela", "320x600")));

        entityManager.getTransaction().commit();
        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertFalse("Lista de atributos está vazia", produtoVerificacao.getAtributos().isEmpty());
    }

    @Test
    public void aplicarContatos() {
        entityManager.getTransaction().begin();
        Cliente cliente = entityManager.find(Cliente.class, 1);
        cliente.setContatos(Collections.singletonMap("email", "fernando@email.com"));

        entityManager.getTransaction().commit();
        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertFalse("Lista de contatos está vazia", clienteVerificacao.getContatos().isEmpty());
        Assert.assertEquals("E-mail retornado não é igual ao esperado", "fernando@email.com", clienteVerificacao.getContatos().get("email"));
    }

}
