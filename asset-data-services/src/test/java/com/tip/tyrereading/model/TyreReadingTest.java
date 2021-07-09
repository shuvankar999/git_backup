package com.tip.tyrereading.model;

import org.junit.Before;
import org.junit.Test;


public class TyreReadingTest {

	private TyreReading tyreReading;

	@Before
	public void setUp() throws Exception { tyreReading = new TyreReading();
	
	tyreReading.setTyreDepth(1.00);
	tyreReading.setTyreCd("aa");
	tyreReading.setTyreMake("1");
	tyreReading.setTyrePSI(1.00);
	tyreReading.setTyreSerialNr("aa");
	tyreReading.setTyreSize("aa");
	}
@Test
	public void test() {
}
}
