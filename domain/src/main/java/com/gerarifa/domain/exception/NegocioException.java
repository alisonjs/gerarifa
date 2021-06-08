package com.gerarifa.domain.exception;

public class NegocioException extends RuntimeException{
    public NegocioException(String mensagem){
        super(mensagem);
    }
}
