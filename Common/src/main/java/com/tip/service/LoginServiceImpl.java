package com.tip.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.model.RoleAccess;
import com.tip.model.UserObj;
import com.tip.repository.UserRepository;


@Service
@Transactional
public class LoginServiceImpl implements LoginService {

	@Autowired
	private  UserRepository userRepository;

/*	@Override
	public List<UserObj> getUserList(String name) {
		return (List<UserObj>) userRepository.findbyempl_name(name);
	}*/
	
	@Override
	public List<UserObj> getUserList(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoleAccess getUserAccessBasedOnRole(String sso) {
		return userRepository.retrieveUserAccessBasedOnRole(sso);
	}

	
	@Override
	public RoleAccess getUserAccessBasedOnMatrixRole(String sso) {
		return userRepository.retrieveUserAccessBasedOnMatrixRole(sso);
	}

	@Override
	public RoleAccess getCMUserAccessBasedOnRole(String sso) {
		return userRepository.retrieveCMUserAccessBasedOnRole(sso);
	}

	
	
	@Override
	public HashMap<String, Object> retrieveUserNameDetailsMOS(String sso) {
		// TODO Auto-generated method stub
		return userRepository.retrieveUserNameDetailsMOS(sso);
	}

	@Override
	public RoleAccess getUserAccessBasedOnSetCustomerRole(String sso) {
		return userRepository.retrieveUserAccessBasedOnSetCustomerRole(sso);
	}

	@Override
	public RoleAccess getUserAccessBasedOnEquipRole(String sso) {
		return userRepository.retrieveUserAccessBasedOnEquipRole(sso);
	}

}
