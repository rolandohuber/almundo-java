package com.rolando;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Clase que representa un grupo de empleados de un cierto rol.
 * 
 * @author Rolando_Huber
 *
 */
public class CallQueue {

	/**
	 * Cola interna de llamadas para un cierto rol.
	 */
	private BlockingQueue<Call> employeeCallsQueue;

	/**
	 * Contructor del pool de empleados.
	 * 
	 * @param employeePool
	 *            Cantidad de empleados que se crearan para un cierto rol.
	 * @param rol
	 *            El rol del empleado.
	 */
	public CallQueue(int employeePool, String rol) {
		this.employeeCallsQueue = new ArrayBlockingQueue<Call>(employeePool);
		for (int i = 0; i < employeePool; i++) {
			Employee emp = new Employee(employeeCallsQueue, rol + " " + i);
			System.out.println(rol + " creado");
			new Thread(emp).start();
		}
	}

	/**
	 * Agrega una llamada a la cola de empleados.
	 * 
	 * @param call
	 *            Llamada a agregar.
	 * @return <code>true</code> si se pudo agregar la llamada
	 *         <code>false</code> si no habia empleados disponibles para esta
	 *         llamada.
	 */
	public boolean addCall(Call call) {
		return this.employeeCallsQueue.offer(call);
	}

}
