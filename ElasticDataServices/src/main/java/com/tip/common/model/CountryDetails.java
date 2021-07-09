package com.tip.common.model;

import java.util.ArrayList;
import java.util.List;

public class CountryDetails {

	String elasticDbColumn;
	List<String> countryNameList;
	
	public String getElasticDbColumn() {
		return elasticDbColumn;
	}
	public void setElasticDbColumn(String elasticDbColumn) {
		this.elasticDbColumn = elasticDbColumn;
	}
	public List<String> getCountryNameList() {
		if(countryNameList == null) {
			countryNameList = new ArrayList<>();
		}
		return this.countryNameList;
	}
}
