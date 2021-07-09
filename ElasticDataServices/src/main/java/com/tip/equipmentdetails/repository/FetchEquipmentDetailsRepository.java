/**
 * 
 */
package com.tip.equipmentdetails.repository;

import java.util.Map;

import com.tip.equipmentdetails.model.FetchEquipDetailsRequest;

/**
 * @author Shuvankar Paul
 * Created on Dec 4, 2017
 * 
 */
@FunctionalInterface
public interface FetchEquipmentDetailsRepository {

	Map<String, Object> getEquipmentDetils(FetchEquipDetailsRequest fetchEquipDetailsRequest, boolean refreshFlag);

}
