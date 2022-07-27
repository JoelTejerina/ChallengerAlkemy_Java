package com.joel.java.alkemychallenger.disney.exception;

public class GeneroException extends Exception {
	public GeneroException() {
		super();
	}

	public GeneroException(String mensaje) {
		super(mensaje);
	}

	public GeneroException(String mensaje, Throwable throwable) {
		super(mensaje, throwable);
	}
}
