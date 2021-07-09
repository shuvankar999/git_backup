package com.tip.service;

import java.util.HashMap;
import java.util.List;

import com.tip.model.RoleAccess;
import com.tip.model.UserObj;

public interface LoginService {
	List<UserObj> getUserList(String name);
	RoleAccess getUserAccessBasedOnRole(String sso);
	RoleAccess getUserAccessBasedOnMatrixRole(String sso);	
	RoleAccess getCMUserAccessBasedOnRole(String sso);
	RoleAccess getUserAccessBasedOnSetCustomerRole(String sso);
	RoleAccess getUserAccessBasedOnEquipRole(String sso);
	HashMap<String,Object> retrieveUserNameDetailsMOS(String sso);
}
