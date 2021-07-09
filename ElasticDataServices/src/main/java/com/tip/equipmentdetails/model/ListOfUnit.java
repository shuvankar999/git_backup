/**
 * 
 */
package com.tip.equipmentdetails.model;

import java.util.List;

public class ListOfUnit {

	private List<String> units;
	private long count;
	
	public List<String> getUnits() {
		return units;
	}

	public void setUnits(List<String> units) {
		this.units = units;
		this.count = units.size();
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}
}
