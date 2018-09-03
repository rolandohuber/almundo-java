package com.rolando;

import java.util.concurrent.BlockingQueue;

/**
 * Clase encargada de simular los llamados.
 * 
 * @author Rolando_Huber
 *
 */
public class CallGenerator implements Runnable {
	/*
	 * Cola concurrente de llamadas a las que se agregaran las nuevas.
	 */
	private BlockingQueue<Call> incomingCallsQueue;

	// Cantidad de llamadas a generar
	private int callNumber = 0;

	/**
	 * Constructor del generador de llamadas.
	 * 
	 * @param incomingCallsQueue
	 *            Cola compartida de llamadas
	 * @param callNumber
	 *            Cantidad de llamadas a generar
	 */
	public CallGenerator(BlockingQueue<Call> incomingCallsQueue, int callNumber) {
		this.incomingCallsQueue = incomingCallsQueue;
		this.callNumber = callNumber;
	}

	public void run() {
		int count = 0;
		while (count < this.callNumber) {
			Call call = new Call(count + 1);
			incomingCallsQueue.offer(call);
			System.out.println("Call created: " + incomingCallsQueue.size());
			count++;
		}
	}

}
