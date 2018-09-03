package com.rolando;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Clase que representa al call center
 * 
 * @author Rolando_Huber
 *
 */
public class CallCenter {

	private int callNumber = 0;

	/**
	 * Constructor del call center
	 * 
	 * @param callNumber
	 *            la cantidad de llamadas a generar
	 */
	public CallCenter(int callNumber) {
		this.callNumber = callNumber;
	}

	/**
	 * Metodo que inicia el call center.
	 */
	public void start() {

		ExecutorService executor = Executors.newFixedThreadPool(2);

		// representa la cantidad de empleados de cada tipo
		int operatorsCount = 2;
		int supervisorsCount = 1;
		int directorsCount = 1;

		/*
		 * Cola concurrente donde se guardaran todas las llamadas, a medida que
		 * se generan llamadas se guardan aca, por mas que no existan empleados
		 * disponibles.
		 */
		BlockingQueue<Call> incomingCallsQueue = new LinkedBlockingQueue<Call>();

		/*
		 * Generador de llamadas
		 */
		CallGenerator generator = new CallGenerator(incomingCallsQueue, this.callNumber);
		executor.execute(generator);

		/*
		 * Manager de llamadas
		 */
		CallManager manager = new CallManager(incomingCallsQueue, operatorsCount, supervisorsCount, directorsCount);
		executor.execute(manager);

		executor.shutdown();

		try {
			executor.awaitTermination(60, TimeUnit.SECONDS);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
