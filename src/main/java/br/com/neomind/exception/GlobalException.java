package br.com.neomind.exception;


public class GlobalException extends Exception{
	
private static final long serialVersionUID = 1L;

	public GlobalException(String msg){		
			super(msg);
	}

	public GlobalException(String msg, Throwable cause){		
			super("Ocorreu um erro ao realizar a operação "+msg, cause);
	}


}
