package com.rolando;

import java.util.concurrent.BlockingQueue;

/**
 * Clase encargada de asignar las llamadas a los empleados.
 * 
 * @author Rolando_Huber
 *
 */
public class CallManager implements Runnable {

	private int operatorsCount = 0;
	private int supervisorsCount = 0;
	private int directorsCount = 0;

	private BlockingQueue<Call> incomingCallsQueue;

	// Cola para operadores
	private CallQueue operatorsQueue;
	// Cola para supervisores
	private CallQueue suppervisorsQueue;
	// Cola para directores
	private CallQueue directorsQueue;

	/**
	 * Constructor de la clase
	 * 
	 * @param incomingCallsQueue
	 *            Cola de llamadas
	 * @param operatorsCount
	 *            cantidad de operadores a crear
	 * @param supervisorsCount
	 *            cantidad de supervisores a crear
	 * @param directorsCount
	 *            cantidad de directores a crear
	 */
	public CallManager(BlockingQueue<Call> incomingCallsQueue, int operatorsCount, int supervisorsCount,
			int directorsCount) {

		this.incomingCallsQueue = incomingCallsQueue;

		this.operatorsCount = operatorsCount;
		this.supervisorsCount = supervisorsCount;
		this.directorsCount = directorsCount;

		if (this.operatorsCount > 0)
			this.operatorsQueue = new CallQueue(this.operatorsCount, "OPERADOR");
		if (this.supervisorsCount > 0)
			this.suppervisorsQueue = new CallQueue(this.supervisorsCount, "SUPERVISOR");
		if (this.directorsCount > 0)
			this.directorsQueue = new CallQueue(this.directorsCount, "DIRECTOR");
	}

	public void run() {
		while (true) {
			try {
				boolean added = false;
				Call call = incomingCallsQueue.take();
				/*
				 * Se obtiene la llamada siguiente en la cola y se la intenta
				 * asignar a un empleado, si no se asigna queda siguiente en la
				 * cola hasta que algun empleado se libere.
				 */
				while (!added) {
					added = dispatchCall(call);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Metodo encargado de asignar las llamadas a los empleados de acuerdo a su
	 * jerarquia.
	 * 
	 * @param call
	 *            llamada a asignar.
	 * @return <code>true</code> si se pudo asignar la llamada a algun empleado,
	 *         <code>false</code> si no se pudo asignar la llamada
	 */
	private boolean dispatchCall(Call call) {
		if (this.operatorsCount > 0 && operatorsQueue.addCall(call)) {
			return true;
		} else if (this.supervisorsCount > 0 && suppervisorsQueue.addCall(call)) {
			return true;
		} else if (this.directorsCount > 0 && directorsQueue.addCall(call)) {
			return true;
		}
		return false;
	}

}
