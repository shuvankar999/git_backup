package com.tip.model;

public class UserObject {
	
	private String Appl_Group_Id;
	private String Application_Name;
	private String Error_Cd;
	
	
	
	public String getAppl_Group_Id() {
		return Appl_Group_Id;
	}



	public void setAppl_Group_Id(String appl_Group_Id) {
		Appl_Group_Id = appl_Group_Id;
	}



	public String getApplication_Name() {
		return Application_Name;
	}



	public void setApplication_Name(String application_Name) {
		Application_Name = application_Name;
	}



	public String getError_Cd() {
		return Error_Cd;
	}



	public void setError_Cd(String error_Cd) {
		Error_Cd = error_Cd;
	}



	public UserObject(String appl_Group_Id, String application_Name, String error_Cd) {
		super();
		Appl_Group_Id = appl_Group_Id;
		Application_Name = application_Name;
		Error_Cd = error_Cd;
	}

	
}
