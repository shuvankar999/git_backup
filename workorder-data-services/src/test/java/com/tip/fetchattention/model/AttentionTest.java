package com.tip.fetchattention.model;


import org.junit.Before;
import org.junit.Test;


public class AttentionTest {
	private Attention attention;
	@Before
	public void setUp() throws Exception { attention = new Attention();
	attention.setCategory("aa");
	attention.setComments("aa");
	attention.setDueDate(null);
	attention.setDueOverDue("aa");
	attention.setWorkOrderNr(5);
	attention.setWorkPackNr(5);
}


	@Test
	public void test() {
	
	}

}
