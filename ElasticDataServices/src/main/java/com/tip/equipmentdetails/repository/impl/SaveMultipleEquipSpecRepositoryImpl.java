package com.tip.equipmentdetails.repository.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tip.asset.util.AssetConstants;
import com.tip.equipmentdetails.model.MultiCopyRequest;
import com.tip.equipmentdetails.model.MultiCopyResponse;
import com.tip.equipmentdetails.repository.SaveMultipleEquipSpecRepository;

@Repository
public class SaveMultipleEquipSpecRepositoryImpl implements SaveMultipleEquipSpecRepository{

	static final Logger logger = LoggerFactory.getLogger(SaveMultipleEquipSpecRepositoryImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public MultiCopyResponse updateMultipleEquip(MultiCopyRequest multiCopyRequest, Map<String, Object> dbColumnMap) {

		MultiCopyResponse multiCopyResponse = new MultiCopyResponse();
		multiCopyResponse.setSourceEquipmentNr(multiCopyRequest.getSourceEquipmentNr());
		try {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
			parameters.addValue("desUnitList", multiCopyRequest.getDestinationEquipmentNrs());
			parameters.addValue("srcUnit", multiCopyRequest.getSourceEquipmentNr());
			parameters.addValue("ssoId", multiCopyRequest.getSsoId());

			StringBuffer sqlQueryUnits = getDynamicQuery(multiCopyRequest, (List)dbColumnMap.get("unitsColumns"),AssetConstants.UNITS_TAB);
			int updateCountUnits = namedParameterJdbcTemplate.update(sqlQueryUnits.toString(), parameters);
			logger.info("Units update counts: "+updateCountUnits);
			
			StringBuffer sqlQueryUnitsDyn = getDynamicQuery(multiCopyRequest, (List)dbColumnMap.get("unitsDynamicColumns"),AssetConstants.UNITS_DYNAMIC_TAB);
			int updateCountUnitsDyn = namedParameterJdbcTemplate.update(sqlQueryUnitsDyn.toString(), parameters);
			logger.info("Units_dynamics update counts: "+updateCountUnitsDyn);
			
			multiCopyResponse.setUpdateCountsUnits(updateCountUnits);
			multiCopyResponse.setUpdateCountsUnitsDynamic(updateCountUnitsDyn);
			multiCopyResponse.setErrorCd("Success");
		}catch(DataAccessException e) {
			logger.error("Data access exception has been occurred"+e);
			multiCopyResponse.setErrorCd("Data access exception has been occurred");
		}
		return multiCopyResponse;
	}

	private StringBuffer getDynamicQuery(MultiCopyRequest multiCopyRequest, List<String> dbColumnList, String tabName) {
		StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append("UPDATE "+tabName+" SET ");
		for(String column:dbColumnList) {
			sqlQuery.append(column);
			sqlQuery.append("=");
			StringBuffer innerQuery = new StringBuffer();
			innerQuery.append("(SELECT ");
			innerQuery.append(column);
			innerQuery.append(" FROM "+tabName+" WHERE Unit_Nr = :srcUnit)");
			sqlQuery.append(innerQuery);
			sqlQuery.append(", ");
		}
		sqlQuery.append("Maint_User = :ssoId, ");
		sqlQuery.append("Maint_Date = getDate() ");
		sqlQuery.append("WHERE Unit_Nr ");
		sqlQuery.append("IN (:desUnitList)");
		return sqlQuery;
	}

}