package com.algaworks.ecommerce.relacionamentos;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Categoria;
import org.junit.Assert;
import org.junit.Test;

public class AutoRelacionamentoTest extends EntityManagerTest {

    @Test
    public void verificarAutoRelacionamento() {
        Categoria categoriaPai = new Categoria();
        categoriaPai.setNome("Eletrônicos novos");

        Categoria categoriaFilha = new Categoria();
        categoriaFilha.setNome("Celulares");
        categoriaFilha.setCategoriaPai(categoriaPai);

        entityManager.getTransaction().begin();
        entityManager.persist(categoriaPai);
        entityManager.persist(categoriaFilha);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Categoria categoriaFilhaVerificacao = entityManager.find(Categoria.class, categoriaFilha.getId());
        Assert.assertNotNull(String.format("Categoria de id: %d não tem categoria pai vinculada", categoriaFilha.getId()), categoriaFilhaVerificacao);

        Categoria categoriaPaiVerificacao = entityManager.find(Categoria.class, categoriaPai.getId());
        Assert.assertFalse(String.format("Categoria de id: %d não tem categorias filhas associadas", categoriaPai.getId()), categoriaPaiVerificacao.getCategorias().isEmpty());
    }

}
