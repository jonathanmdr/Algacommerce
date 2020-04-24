package com.algaworks.ecommerce;

import com.algaworks.ecommerce.mapeamentoavancado.SalvandoArquivosTest;
import lombok.experimental.UtilityClass;

import java.io.IOException;

@UtilityClass
public class FileUploader {

    public static byte[] carregarArquivo(String filePath) {
        try {
            return SalvandoArquivosTest.class.getResourceAsStream(filePath).readAllBytes();
        } catch(IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}
