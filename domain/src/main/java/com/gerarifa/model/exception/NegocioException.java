package com.gerarifa.model.exception;

public class NegocioException extends RuntimeException{
    public NegocioException(String mensagem){
        super(mensagem);
    }
}
