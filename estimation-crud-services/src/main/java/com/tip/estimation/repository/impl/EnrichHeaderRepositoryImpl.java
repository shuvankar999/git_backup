package com.tip.estimation.repository.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import com.tip.estimation.model.EnrichHeaderObject;
import com.tip.estimation.model.VersionObject;
import com.tip.estimation.repository.EnrichHeaderRepository;
import com.tip.estimation.util.DatatypeCommonUtility;
import com.tip.estimation.util.EstimationConstant;


	@Repository
	public class EnrichHeaderRepositoryImpl implements EnrichHeaderRepository,CallableStatementCreator{
		
		static final Logger logger = LoggerFactory.getLogger(EnrichHeaderRepositoryImpl.class);

		@Autowired
		private JdbcTemplate jdbcTemplate;

		private String procedureCall;
		private EnrichHeaderObject enrichHeaderObject;


		public void setSaveEnrichNotes(EnrichHeaderObject enrichHeaderObject) {
			this.enrichHeaderObject = enrichHeaderObject;
		}


		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		public VersionObject saveEnrichHeader(EnrichHeaderObject enrichHeaderObject) {
			Map<String, Object> resultMap= null;		
			VersionObject versionObject = new VersionObject();
			enrichHeaderObject.setShowHide(DatatypeCommonUtility.booleanConversion(enrichHeaderObject.getShowHide()));
			try {
				SqlParameter estimationIdParam = new SqlParameter(Types.NUMERIC);
				SqlParameter concessionParam = new SqlParameter(Types.NUMERIC);
				SqlParameter custNotesParam = new SqlParameter(Types.VARCHAR);
				SqlParameter internalNotesParam = new SqlParameter(Types.VARCHAR);
				SqlParameter isApprovedParam = new SqlParameter(Types.VARCHAR);
				SqlParameter isRejectedParam = new SqlParameter(Types.VARCHAR);
				SqlParameter rejectedReasonParam = new SqlParameter(Types.INTEGER);
				SqlParameter showHideParam = new SqlParameter(Types.VARCHAR);
				SqlParameter versionParam = new SqlParameter(Types.INTEGER);
				SqlParameter estnStatusIdParam = new SqlParameter(Types.INTEGER); 
				SqlParameter saveAsParam = new SqlParameter(Types.VARCHAR);
				SqlParameter ssoIdParam = new SqlParameter(Types.VARCHAR);
				
				
				List paramList = new ArrayList();

				paramList.add(estimationIdParam);
				paramList.add(concessionParam);
				paramList.add(custNotesParam);
				paramList.add(internalNotesParam);
				paramList.add(isApprovedParam);
				paramList.add(isRejectedParam);
				paramList.add(rejectedReasonParam);
				paramList.add(showHideParam);
				paramList.add(versionParam);
				paramList.add(saveAsParam);
				paramList.add(estnStatusIdParam);
				paramList.add(ssoIdParam);
				
				
				EnrichHeaderRepositoryImpl saveEnrichNotesRepositoryImpl = new EnrichHeaderRepositoryImpl();
				saveEnrichNotesRepositoryImpl.enrichHeaderObject = enrichHeaderObject;

				saveEnrichNotesRepositoryImpl.procedureCall = "{call "+ EstimationConstant.PROC_ENRICH_SAVE_NOTES_DETAILS + " (?,?,?,?,?,?,?,?,?,?,?,?)}";
			
			
			resultMap = jdbcTemplate.call(saveEnrichNotesRepositoryImpl, paramList);
			
			if (null != resultMap) {
				for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
					if ("#result-set-1".equalsIgnoreCase(entry.getKey())) {
						List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
						for (int i = 0; i < lst.size(); i++) {
							Map<String, Object> versionMap = lst.get(i);
							versionObject.setVersion((Integer) versionMap.get("version"));
							versionObject.setSupplementary((Integer) versionMap.get("supplementary"));
							versionObject.setVersionDate((String) versionMap.get("versionDate"));
							
						}
						
					}
				}
			}
			
			}catch (Exception e) {
				logger.error("An error occurred while saving Enrich Notes " + e);
				
			
			}
			
			return versionObject;
		}
		
		@Override
		public CallableStatement createCallableStatement(Connection connection) throws SQLException {
			connection.setAutoCommit(false);
			CallableStatement callableStatement = connection.prepareCall(procedureCall);
			callableStatement.setBigDecimal(1, enrichHeaderObject.getEstimationId());
			callableStatement.setBigDecimal(2, enrichHeaderObject.getConcession());
			callableStatement.setString(3, enrichHeaderObject.getCustNotes());
			callableStatement.setString(4,  enrichHeaderObject.getInternalNotes());
			callableStatement.setString(5,  enrichHeaderObject.getIsApproved());
			callableStatement.setString(6, enrichHeaderObject.getIsRejected());
			DatatypeCommonUtility.checkNull(7, callableStatement, enrichHeaderObject.getRejectedReason());
			callableStatement.setString(8, enrichHeaderObject.getShowHide());
			DatatypeCommonUtility.checkNull(9, callableStatement, enrichHeaderObject.getVersion());
			callableStatement.setString(10, enrichHeaderObject.getSaveAs());
			DatatypeCommonUtility.checkNull(11, callableStatement, enrichHeaderObject.getEstnStatusId());
			callableStatement.setString(12, enrichHeaderObject.getSsoId());
			
			connection.setAutoCommit(true);
			return callableStatement;
			
		}
	}
