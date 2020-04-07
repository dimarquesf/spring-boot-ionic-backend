package br.com.diego.cursomc.services.exception;

public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException(String msg) {
		super(msg); // chamar super Classe e passando msg para RunTime
	}
	
	public ObjectNotFoundException(String msg, Throwable cause ) {
		super(msg, cause); // chamar super Classe e passando msg, cause para RunTime
	}
	
	

}
