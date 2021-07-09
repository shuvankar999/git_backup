package com.tip.fetchattention.model;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class AttentionResponseTest {
	private AttentionResponse attentionResponse;
	@Before
	public void setUp() throws Exception { attentionResponse = new AttentionResponse();
	List<Attention> attentionList = null;
	attentionResponse.setAttentionList(attentionList);
	Map<String, Object> attentionListMap = null;
	attentionResponse.setAttentionListMap(attentionListMap);
	attentionResponse.setErrorCd(null);
	
}


	@Test
	public void test() {
	
	}

}