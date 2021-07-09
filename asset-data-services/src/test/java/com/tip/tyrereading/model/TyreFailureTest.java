package com.tip.tyrereading.model;

import org.junit.Before;
import org.junit.Test;

public class TyreFailureTest {

	private TyreFailure tyreFailure;

	@Before
	public void setUp() throws Exception { tyreFailure = new TyreFailure();
	tyreFailure.setWorkPackNr(1.00);
	tyreFailure.setAssetNr(1);
	tyreFailure.setCountOfFailures(1);
	tyreFailure.setStatus("OK");
	tyreFailure.setUserId("123");
	}
	@Test
	public void test() {
}

}
