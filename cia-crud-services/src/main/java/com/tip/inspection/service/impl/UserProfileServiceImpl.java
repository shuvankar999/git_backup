package com.tip.inspection.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.inspection.model.UserProfileObject;
import com.tip.inspection.model.UserProfileRequest;
import com.tip.inspection.model.UserProfileResponse;
import com.tip.inspection.repository.CiaOracleMethodesRepository;
import com.tip.inspection.repository.UserProfileRepository;
import com.tip.inspection.service.UserProfileService;
import com.tip.inspection.util.EncodeDecodeUtil;

@Service
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

	@Autowired
	UserProfileRepository userProfileRepository;

	@Autowired
	CiaOracleMethodesRepository oracleMethodesRepo;

	
	@Override
	public UserProfileResponse saveUserProfile(UserProfileRequest userProfileRequest) {
		UserProfileResponse userProfileResponse = new UserProfileResponse();
		for (UserProfileObject userProfileObject : userProfileRequest.getUserProfileDetailsList()) {
			System.out.println("Enter pwd for "+userProfileObject.getUserEmail()+" :"+userProfileObject.getLoginPwd());
			String privatekey=userProfileRepository.fetchCIAprivateKey(userProfileObject.getUserEmail());
			System.out.println("Private Key for "+userProfileObject.getUserEmail()+" :"+privatekey);
			String decodedPWD=EncodeDecodeUtil.decrypt(privatekey,userProfileObject.getLoginPwd());
			System.out.println("Decoded pwd for "+userProfileObject.getUserEmail()+" :"+decodedPWD);
			String encryptedHashPWD=oracleMethodesRepo.getHashPWD(userProfileObject.getUserEmail(),decodedPWD);
			System.out.println("Encoded has pwd for "+userProfileObject.getUserEmail()+" :"+encryptedHashPWD);
			userProfileObject.setLoginPwd(encryptedHashPWD);
			userProfileResponse.getUserProfileResponseList()
					.add(userProfileRepository.saveUserProfile(userProfileObject));
		}
		return userProfileResponse;
	}
}