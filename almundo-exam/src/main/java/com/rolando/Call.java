package com.rolando;

import java.util.Random;

/**
 * Clase que representa una llamada
 * 
 * @author Rolando_Huber
 */
public class Call {

	private int maxDuration = 10;
	private int minDuration = 5;
	private int callNumber = 0;

	// obtine cuanto va a durar la llamada
	private int callDuration = new Random().nextInt(maxDuration - minDuration) + minDuration;

	public Call(int callNumber) {
		this.callNumber = callNumber;
	}

	/**
	 * Devuelve la duracion de la llamada
	 * 
	 * @return la duracion de la llamada
	 */
	public int getCallDuration() {
		return callDuration;
	}

	/**
	 * Devuelve el identificador de la llamada
	 * 
	 * @return el identificador
	 */
	public int getCallNumber() {
		return callNumber;
	}

}
