package com.tip.fetchworkorder.model;


import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class WorkPackResponseTest {
	private WorkPackResponse workPackResponse;
	@Before
	public void setUp() throws Exception { workPackResponse = new WorkPackResponse();
	workPackResponse.setActualTime(5d);
	workPackResponse.setAlert("aa");
	workPackResponse.setAssetNumber(5);
	workPackResponse.setComments("aa");
	workPackResponse.setChassisNumber("aa");
	workPackResponse.setCustomerName("aa");
	workPackResponse.setCustomerNr(5);
	workPackResponse.setCustomerReferenceNr("aa");
	workPackResponse.setDriverWaitingFlag("aa");
	workPackResponse.setRegistrationNr("aa");
	workPackResponse.setRequireDoneDate("aa");
	workPackResponse.setRoadWorthy("aa");
	workPackResponse.setStatus("aa");
	workPackResponse.setStatusId(5);
	workPackResponse.setStopReasonCd(5);
	workPackResponse.setStopReasonDesc("aa");
	workPackResponse.setSupplierId(5);
	workPackResponse.setSupplierName("aa");
	workPackResponse.setTotalGuideTime(5d);
	workPackResponse.setWoCnt(5);
	workPackResponse.setWorkPackNr(BigDecimal.ONE);
	
	
	
	
	
	}
	@Test
	public void test() {
	
	}

}