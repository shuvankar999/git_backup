package com.tip.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



public class UserObj  {


	private int Empl_Id;
	private String Empl_Name;
	
	
	public int getEmpl_Id() {
		return Empl_Id;
	}
	public void setEmpl_Id(int empl_Id) {
		Empl_Id = empl_Id;
	}
	public String getEmpl_Name() {
		return Empl_Name;
	}
	public void setEmpl_Name(String empl_Name) {
		Empl_Name = empl_Name;
	}
	
    public UserObj(int Empl_Id, String Empl_Name) {
        this.Empl_Id = Empl_Id;
        this.Empl_Name = Empl_Name;
    }
	
	
}
