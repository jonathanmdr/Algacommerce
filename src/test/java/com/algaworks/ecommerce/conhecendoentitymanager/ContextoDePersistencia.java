package com.algaworks.ecommerce.conhecendoentitymanager;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Test;

import java.math.BigDecimal;

public class ContextoDePersistencia extends EntityManagerTest {

    @Test
    public void usarContextoPersistencia() {
        entityManager.getTransaction().begin();

        Produto produto = entityManager.find(Produto.class, 1);
        produto.setPreco(new BigDecimal(100));

        Produto produtoNovo = new Produto();
        produtoNovo.setNome("Caneca para café");
        produtoNovo.setDescricao("Boa caneca para café");
        produtoNovo.setPreco(new BigDecimal(10));
        entityManager.persist(produtoNovo);

        Produto produtoNovo2 = new Produto();
        produtoNovo2.setNome("Caneca para chá");
        produtoNovo2.setDescricao("Boa caneca para chá");
        produtoNovo2.setPreco(new BigDecimal(10));
        produtoNovo2 = entityManager.merge(produtoNovo2);

        entityManager.flush();

        produtoNovo2.setDescricao("Alterar descrição");

        entityManager.getTransaction().commit();
    }

}
