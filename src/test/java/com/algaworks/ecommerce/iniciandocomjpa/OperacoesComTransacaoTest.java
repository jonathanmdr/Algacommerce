package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class OperacoesComTransacaoTest extends EntityManagerTest {

    @Test
    public void inserirProduto() {
        Produto produto = criaProduto(2, "Câmera Canon", "A melhor definição para suas fotos", new BigDecimal(5000));

        entityManager.getTransaction().begin();
        entityManager.persist(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull(String.format("Produto de id: %d não foi inserido na base de dados", produtoVerificacao.getId()), produtoVerificacao);
    }

    @Test
    public void inserirProdutoComMetodoMerge() {
        Produto produto = criaProduto(4, "Microfone Rode Videmic", "A melhor qualidade de som", new BigDecimal(1000));

        entityManager.getTransaction().begin();
        entityManager.merge(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull(String.format("Produto de id: %d não foi inserido na base de dados", produtoVerificacao.getId()), produtoVerificacao);
    }

    @Test
    public void removerProduto() {
        Produto produto = entityManager.find(Produto.class, 3);

        entityManager.getTransaction().begin();
        entityManager.remove(produto);
        entityManager.getTransaction().commit();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertNull(String.format("Produto de id: %d não foi excluído", produto.getId()), produtoVerificacao);
    }

    @Test
    public void atualizarProdutoNaoGerenciado() {
        Produto produto = criaProduto(1, "Kindle Paperwhite", "Conheça o novo kindle.", new BigDecimal(599));

        entityManager.getTransaction().begin();
        entityManager.merge(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull(String.format("Produto de id: %d é nulo", produto.getId()), produtoVerificacao);
        Assert.assertEquals("Kindle Paperwhite", produtoVerificacao.getNome());
    }

    @Test
    public void atualizarProdutoGerenciado() {
        Produto produto = entityManager.find(Produto.class, 1);

        entityManager.getTransaction().begin();
        produto.setNome("Kindle Paperwhite 2º geração");
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertEquals("Kindle Paperwhite 2º geração", produtoVerificacao.getNome());
    }

    /**
     * Método somente para exemplificar uma transação
     */
    @Test
    public void abrirEFecharATransacao() {
//        Produto produto = new Produto();

        entityManager.getTransaction().begin();

//        entityManager.persist(produto);
//        entityManager.merge(produto);
//        entityManager.remove(produto);

        entityManager.getTransaction().commit();
    }

    private Produto criaProduto(Integer id, String nome, String descricao, BigDecimal preco) {
        Produto produto = new Produto();
        produto.setId(id);
        produto.setNome(nome);
        produto.setDescricao(descricao);
        produto.setPreco(preco);
        return produto;
    }

}