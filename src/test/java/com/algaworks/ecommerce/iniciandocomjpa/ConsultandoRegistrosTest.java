package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ConsultandoRegistrosTest extends EntityManagerTest {

    private static final int PRODUTO_ID = 1;

    @Test
    public void A_buscarPorId() {
        Produto produto = entityManager.find(Produto.class, PRODUTO_ID);

        Assert.assertNotNull(String.format("Produto de id: %d é nulo", PRODUTO_ID), produto);
        Assert.assertEquals("Kindle", produto.getNome());
    }

    @Test
    public void B_atualizarAReferencia() {
        Produto produto = entityManager.find(Produto.class, PRODUTO_ID);
        produto.setNome("Microfone Samson");

        entityManager.refresh(produto);

        Assert.assertEquals("Kindle", produto.getNome());
    }

}
