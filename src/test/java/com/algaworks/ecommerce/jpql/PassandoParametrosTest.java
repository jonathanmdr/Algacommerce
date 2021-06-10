package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.NotaFiscal;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import java.util.Date;

public class PassandoParametrosTest extends EntityManagerTest {

    @Test
    public void passandoParametros() {
        TypedQuery<NotaFiscal> typedQuery = entityManager.createQuery("select nf from NotaFiscal nf where nf.dataEmissao <= ?1", NotaFiscal.class);
        typedQuery.setParameter(1, new Date(), TemporalType.TIMESTAMP);
        NotaFiscal notaFiscal = typedQuery.getSingleResult();
        Assert.assertNotNull(notaFiscal);
    }

}
