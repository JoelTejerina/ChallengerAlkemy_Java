package com.joel.java.alkemychallenger.disney.exception;

import java.sql.SQLException;

public class ExceptionSql extends SQLException {
	
	public ExceptionSql() {
		super();
	}

	public ExceptionSql(String mensaje) {
		super(mensaje);
	}

	public ExceptionSql(String mensaje, Throwable throwable) {
		super(mensaje, throwable);
	}
}
