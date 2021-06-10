package com.gerarifa.domain.model.exception;

public class NegocioException extends RuntimeException{
    public NegocioException(String mensagem){
        super(mensagem);
    }
}
