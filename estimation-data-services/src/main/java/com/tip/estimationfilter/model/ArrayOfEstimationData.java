package com.tip.estimationfilter.model;

import java.util.ArrayList;
import java.util.List;

public class ArrayOfEstimationData {

	protected List<EstimationSearchResponse> listOfEstimationData;
	protected Long startIndex;
	protected Integer count;
	
	public List<EstimationSearchResponse> getListOfEstimationData() {
		if(listOfEstimationData == null) {
			listOfEstimationData = new ArrayList<EstimationSearchResponse>();
		}
		return listOfEstimationData;
	}
	public void setListOfEstimationData(List<EstimationSearchResponse> listOfEstimationData) {
		this.listOfEstimationData = listOfEstimationData;
	}
	public Long getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(Long startIndex) {
		this.startIndex = startIndex;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	
}
