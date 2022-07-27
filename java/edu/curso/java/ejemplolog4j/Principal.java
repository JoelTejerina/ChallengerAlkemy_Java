package edu.curso.java.ejemplolog4j;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Principal {

	private final static Logger Log = LogManager.getLogger(Principal.class);
			
	public static void main(String[] args) {
		Log.info("Inicio del programa: " + new Date());
		// TODO Auto-generated method stub
		Log.debug("Ejemplo de debug.......");
		
		try {
			GestorDeArchivos gestorDeArchivos = new GestorDeArchivos();
			gestorDeArchivos.guardarArchivo("c:/pepe.txt", "Hola...");
		}catch(Exception e) {
			Log.error("Hay un error general: " + e);
		}
		
		Log.info("fin del programa: " + new Date());
	}

}
