package com.tip.common.model;

public class DropDownRequest {

	protected String elasticDbColumn;
	protected String entity;
	protected String text;
	protected CountryDetails countryObject;
	//protected Integer fromIndex;
	protected Integer indexSize;
	
	public String getElasticDbColumn() {
		return elasticDbColumn;
	}
	public void setElasticDbColumn(String elasticDbColumn) {
		this.elasticDbColumn = elasticDbColumn;
	}
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public CountryDetails getCountryObject() {
		return countryObject;
	}
	public void setCountryObject(CountryDetails countryObject) {
		this.countryObject = countryObject;
	}
/*	public Integer getFromIndex() {
		return fromIndex;
	}
	public void setFromIndex(Integer fromIndex) {
		this.fromIndex = fromIndex;
	}*/
	public Integer getIndexSize() {
		return indexSize;
	}
	public void setIndexSize(Integer indexSize) {
		this.indexSize = indexSize;
	}
	
}
