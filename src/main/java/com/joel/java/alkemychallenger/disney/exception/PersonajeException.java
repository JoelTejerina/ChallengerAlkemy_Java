package com.joel.java.alkemychallenger.disney.exception;

public class PersonajeException extends Exception {

	public PersonajeException() {
		super();
	}

	public PersonajeException(String mensaje) {
		super(mensaje);
	}

	public PersonajeException(String mensaje, Throwable throwable) {
		super(mensaje, throwable);
	}
}
