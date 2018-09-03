package com.rolando;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingQueue;

/**
 * Clase que representa un empleado.
 * 
 * @author Rolando_Huber
 *
 */
public class Employee implements Runnable {

	// Cola de llamadas para empleados del mismo rol.
	private BlockingQueue<Call> incomingCallsQueue;
	private String rol;

	/**
	 * Constructor del empleado.
	 * 
	 * @param incomingCallsQueue
	 *            Cola concurrente de llamadas para empleados del mismo rol.
	 * @param rol
	 *            El rol del empleado.
	 */
	public Employee(BlockingQueue<Call> incomingCallsQueue, String rol) {
		this.incomingCallsQueue = incomingCallsQueue;
		this.rol = rol;
	}

	/**
	 * Metodo que simula la atencion de la llamada, toma la llamada de la cola y
	 * espera a que se cumpla el tiempo de la llamada para tomar la siguiente.
	 */
	public void run() {
		while (true) {
			try {
				Call call = incomingCallsQueue.take();
				System.out.println(rol + " take the call " + call.getCallNumber() + " at " + new SimpleDateFormat("mm:ss").format(new Date()));
				Thread.sleep(call.getCallDuration() * 1000);
				System.out.println("Call atended by " + this.rol + " Call number " + call.getCallNumber() + " Call duration " + call.getCallDuration() + " Finish at "+ new SimpleDateFormat("mm:ss").format(new Date()));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
