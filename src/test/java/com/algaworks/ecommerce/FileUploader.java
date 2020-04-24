package com.algaworks.ecommerce;

import lombok.experimental.UtilityClass;

import java.io.IOException;

@UtilityClass
public class FileUploader {

    public static byte[] carregarArquivo(String filePath) {
        try {
            return FileUploader.class.getResourceAsStream(filePath).readAllBytes();
        } catch(IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}
