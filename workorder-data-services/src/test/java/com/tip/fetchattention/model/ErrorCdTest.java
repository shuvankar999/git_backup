package com.tip.fetchattention.model;

import org.junit.Before;
import org.junit.Test;
public class ErrorCdTest {
	private ErrorCd errorCd;
	@Before
	public void setUp() throws Exception { errorCd = new ErrorCd();
	errorCd.setErrorCode("404");
	errorCd.setErrorMessage("error");
}


	@Test
	public void test() {
	
	}

}
