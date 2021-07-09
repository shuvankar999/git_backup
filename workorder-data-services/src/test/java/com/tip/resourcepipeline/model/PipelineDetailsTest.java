package com.tip.resourcepipeline.model;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class PipelineDetailsTest {

	private PipelineDetails pipelineDetails;

	@Before
	public void setUp() throws Exception {
		pipelineDetails = new PipelineDetails();

		pipelineDetails.setAssetNumber(5);
		pipelineDetails.setChassisNumber("aa");
		pipelineDetails.setCustomerName("aa");
		pipelineDetails.setCustomerNr(5);
		pipelineDetails.setCustomerReferenceNr("aa");
		pipelineDetails.setDescription("aa");
		pipelineDetails.setRegistrationNr("aa");
		pipelineDetails.setDriverWaiting("aa");
		pipelineDetails.setRequiredDoneDate("aa");
		pipelineDetails.setSupplierId(5);
		pipelineDetails.setTotalGuideTime(5d);
		pipelineDetails.setWorkOrderCnt(5);
		pipelineDetails.setWorkPackStatus("ss");
		pipelineDetails.setWorkPackStatusId(5);
		pipelineDetails.setWorkPackNr(BigDecimal.ONE);

	}

	@Test
	public void test() {

	}

}