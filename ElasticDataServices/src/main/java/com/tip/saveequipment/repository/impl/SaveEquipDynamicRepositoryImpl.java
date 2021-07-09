package com.tip.saveequipment.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tip.asset.util.AssetConstants;
import com.tip.elastic.util.DatatypeCommonUtility;
import com.tip.elastic.util.ElasticSearchConstant;
import com.tip.saveequipment.model.DynamicModule;
import com.tip.saveequipment.model.Form;
import com.tip.saveequipment.model.Modules;
import com.tip.saveequipment.model.SaveEquipDetailsRequest;
import com.tip.saveequipment.model.Status;
import com.tip.saveequipment.repository.SaveEquipDynamicRepository;


/**
 * @author Shuvankar Paul
 * Created on Dec 15, 2017
 * 
 */

@Repository
public class SaveEquipDynamicRepositoryImpl implements SaveEquipDynamicRepository{

	
	static final Logger logger = LoggerFactory.getLogger(SaveEquipDynamicRepositoryImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> saveEquip(SaveEquipDetailsRequest saveEquipDetailsRequest, String appCd, String ssoId) {
		
		Map<String, Object> saveEquipResponse = new HashMap();		
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			statement = connection.prepareStatement("{call " + ElasticSearchConstant.PROC_SAVE_EQUIP_DYNAMIC + "(?,?,?,?,?,?,?)}");
			connection.setAutoCommit(false);	
			Modules modules = saveEquipDetailsRequest.getModulesDet();
			Iterator iteratorDynamic = modules.getDynamicModuleList().iterator();
			Iterator itStatus = saveEquipDetailsRequest.getStatusList().iterator();
			Status status = new Status();
			while (itStatus.hasNext()) {
				status = (Status) itStatus.next();
			}
			while (iteratorDynamic.hasNext()) {
				DynamicModule dynamicModule= (DynamicModule) iteratorDynamic.next();	
				Iterator itrForm = dynamicModule.getForms().iterator();
				
				while (itrForm.hasNext()) {
					Form form = (Form) itrForm.next();
					/*Set Dynamicmodule values*/
					statement.setString(1, appCd);
					DatatypeCommonUtility.checkNull(2, statement, status.getUnitNr());
					DatatypeCommonUtility.checkNull(3, statement, dynamicModule.getSpecgrId());
					
					
					/*Set Form values*/
					DatatypeCommonUtility.checkNull(4, statement, form.getSpecitmSeq());
					statement.setString(5, form.getValue());
					statement.setString(6, form.getUnitspComment());
					statement.setString(7, ssoId);
					
					statement.addBatch();
					logger.info("Batch Added : \n App_Cd : " + appCd + "\n Unit_Nr : " + status.getUnitNr() + "\n Specgr_Id : "
							+ dynamicModule.getSpecgrId() + "\n Specitm_Seq : " + form.getSpecitmSeq() + "\n Unitsp_Val : " + form.getValue()
							+ "\n Unitsp_Comment : " + form.getUnitspComment() + "\n Sso_Id : " + ssoId);
				}
			}
			int[] returnArr=statement.executeBatch();
			connection.commit();
			logger.debug("Return array  :"+Arrays.toString(returnArr));
			for(int i:returnArr){
				if(i==0){
					saveEquipResponse.put(ElasticSearchConstant.ERROR_CODE, "FAILURE");
					logger.error(ElasticSearchConstant.ERROR_CODE+": FAILURE");
					break;
				}else{
					logger.debug(ElasticSearchConstant.ERROR_CODE +": SUCCESS");
					saveEquipResponse.put(ElasticSearchConstant.ERROR_CODE, "SUCCESS");
				}				
			}
			statement.close();
			connection.setAutoCommit(true);
		} catch (Exception e) {
			if(statement != null){
				try {
					statement.close();
				} catch (SQLException e1) {
					saveEquipResponse.put(ElasticSearchConstant.ERROR_CODE, "EXCEPTION_OCCURRED");
					logger.error("An error occurred while closing the statement for saveEquipment dynamic tab: " , e1);
				}
			}
			saveEquipResponse.put(ElasticSearchConstant.ERROR_CODE, "EXCEPTION_OCCURRED");
			logger.error("An error occurred while saving equipment details in dynamic table: " + e);
			logger.debug("Error occurred: "+e);
		}	
		return saveEquipResponse;
	}


}
