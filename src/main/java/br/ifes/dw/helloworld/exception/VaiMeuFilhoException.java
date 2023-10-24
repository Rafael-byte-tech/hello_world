package br.ifes.dw.helloworld.exception;

public class VaiMeuFilhoException extends Exception
{
    public String getMessage()
    {
        String message;

        message = "行くぞ！";

        return message;
    }
}
