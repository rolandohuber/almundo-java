package com.rolando;

import org.junit.Test;

/**
 * Simula un call center.
 * 
 * @author Rolando_Huber
 *
 */
public class CallCenterTest {

	/**
	 * Test que simula 10 llamados.
	 */
	@Test
	public void testTenCalls() {
		int callNumber = 10;
		CallCenter callCenter = new CallCenter(callNumber);
		callCenter.start();
	}
}
