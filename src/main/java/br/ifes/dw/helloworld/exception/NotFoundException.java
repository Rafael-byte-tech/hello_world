package br.ifes.dw.helloworld.exception;

public class NotFoundException extends Exception
{
    public String getMessage()
    {
        String message;

        message = "なんとか事とは見つけなかった";

        return message;
    }
}
