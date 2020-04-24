package com.algaworks.ecommerce.mapeamentoavancado;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.FileUploader;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.NotaFiscal;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class SalvandoArquivosTest extends EntityManagerTest {

    @Test
    public void salvaXmlNotaFiscal() {
        Pedido pedido = entityManager.find(Pedido.class, 1);

        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setPedido(pedido);
        notaFiscal.setDataEmissao(new Date());
        notaFiscal.setXml(FileUploader.carregarArquivo("/nota-fiscal.xml"));

        entityManager.getTransaction().begin();
        entityManager.persist(notaFiscal);
        entityManager.getTransaction().commit();

        entityManager.clear();

        NotaFiscal notaFiscalVerificacao = entityManager.find(NotaFiscal.class, notaFiscal.getId());
        Assert.assertNotNull("Nota fiscal é nula", notaFiscalVerificacao.getXml());
        Assert.assertTrue("XML da nota fiscal não tem bytes salvos", notaFiscalVerificacao.getXml().length > 0);
    }

    @Test
    public void salvaFotoProduto() {
        Produto produto = new Produto();
        produto.setNome("Kindle");
        produto.setDescricao("Ótimo para leitura");
        produto.setPreco(new BigDecimal(750));
        produto.setTags(List.of("Eletrônicos", "Leitura"));
        produto.setFoto(FileUploader.carregarArquivo("/kindle.jpg"));

        entityManager.getTransaction().begin();
        entityManager.persist(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull("Produto é nulo", produtoVerificacao.getFoto());
        Assert.assertTrue("Foto do produto não tem bytes salvos", produtoVerificacao.getFoto().length > 0);
    }

}
