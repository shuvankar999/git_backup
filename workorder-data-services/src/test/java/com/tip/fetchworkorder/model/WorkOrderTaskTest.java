package com.tip.fetchworkorder.model;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class WorkOrderTaskTest {
	private WorkOrderTask workOrderTask;
	@Before
	public void setUp() throws Exception { workOrderTask = new WorkOrderTask();
	workOrderTask.setAction("aaa");
	workOrderTask.setActionCd("aaa");
	workOrderTask.setActivity("aaa");
	workOrderTask.setActualTime(45d);
	//workOrderTask.setActualTimeReason("aaa");
	workOrderTask.setAlert("aaa");
	workOrderTask.setDescription("aaa");
	workOrderTask.setFailureCause("aaa");
	workOrderTask.setFailureCauseDesc("aaa");
	workOrderTask.setGroupDesc("aaa");
	workOrderTask.setGuideTime(5d);
	workOrderTask.setLevel1("aaa");
	workOrderTask.setLevel2("aaa");
	workOrderTask.setLevel3("aaa");
	workOrderTask.setPositionId("aaa");
	workOrderTask.setReason("aaa");
	workOrderTask.setReasonCd("aaa");
	workOrderTask.setStopReasonCd(5);
	workOrderTask.setStopReasonDesc("aaa");
	workOrderTask.setSubGroup("aaa");
	workOrderTask.setValidationStatus("aaa");
	workOrderTask.setWorkOrderNr(5);
	workOrderTask.setWorkOrderTaskNr(5);
	workOrderTask.setWorkPackNr(BigDecimal.ONE);
	workOrderTask.setWotStatusDesc("aaa");
	workOrderTask.setWotStatusId(5);
	
	workOrderTask.getAction();
	workOrderTask.getActionCd();
	workOrderTask.getActivity();
	workOrderTask.getActualTime();
	//workOrderTask.getActualTimeReason();
	workOrderTask.getAlert();
	workOrderTask.getDescription();
	workOrderTask.getFailureCause();
	workOrderTask.getFailureCauseDesc();
	workOrderTask.getGroupDesc();
	workOrderTask.getGuideTime();
	workOrderTask.getLevel1();
	workOrderTask.getLevel2();
	workOrderTask.getLevel3();
	workOrderTask.getPositionId();
	workOrderTask.getReason();
	workOrderTask.getReasonCd();
	workOrderTask.getStopReasonCd();
	workOrderTask.getStopReasonDesc();
	workOrderTask.getSubGroup();
	workOrderTask.getValidationStatus();
	workOrderTask.getWorkOrderNr();
	workOrderTask.getWorkOrderTaskNr();
	workOrderTask.getWorkPackNr();
	workOrderTask.getWotStatusDesc();
	workOrderTask.getWotStatusId();	
	}
	@Test
	public void test() {	
	}

}