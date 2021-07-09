package com.tip.fetchwpvalidate.model;


import org.junit.Before;
import org.junit.Test;


public class FetchWPValidateTest {
	private FetchWPValidate fetchWPValidate;
	@Before
	public void setUp() throws Exception { fetchWPValidate = new FetchWPValidate();
	
	fetchWPValidate.setActualTime(5d);
	fetchWPValidate.setAssetNumber(5);
	fetchWPValidate.setChassisNumber("aaa");
	fetchWPValidate.setComments("aaa");
	fetchWPValidate.setCustomerName("aaa");
	fetchWPValidate.setCustomerNr(5);
	fetchWPValidate.setCustomerReferenceNr("aaa");
	fetchWPValidate.setDriverWaitingFlag("aaa");
	fetchWPValidate.setFlag("aaa");
	fetchWPValidate.setRegistrationNr("aaa");
	fetchWPValidate.setRequireDoneDate(null);
	fetchWPValidate.setStatus(0);
	fetchWPValidate.setSupplierId(1);
	fetchWPValidate.setTotalGuideTime(5d);
	fetchWPValidate.setWorkOrderCnt(5);
	fetchWPValidate.setWorkPackNr(5d);
	}
	@Test
	public void test() {
	
	}

}