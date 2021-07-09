package com.tip.workorder.util;

public class ShortNameUtil {

	private ShortNameUtil(){}
	public static String getShortName(String originalName){
		String fullName = originalName.trim().toUpperCase();
		String firstNameInitials = fullName.substring(0,1);
		int indexOfLastName = fullName.lastIndexOf(' ')+1;
		String lastNameInitials = fullName.substring(indexOfLastName, indexOfLastName+1);
		return firstNameInitials+" "+lastNameInitials;
	}
}
