package com.tip.resourcepipeline.repository.impl;

import com.tip.resourcepipeline.model.ResourcePipelineRequest;
import com.tip.resourcepipeline.repository.ResourcePipelineRepository;
import com.tip.workorder.util.WorkOrderConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;
import java.util.Map.Entry;

@Repository
public class ResourcePipelineRepositoryImpl implements ResourcePipelineRepository, CallableStatementCreator {

    static final Logger logger = LoggerFactory.getLogger(ResourcePipelineRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private String procedureCall;
    private ResourcePipelineRequest resourcePipelineRequest;

    @Autowired
    public void setDataSource(DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
    }

    public void setResourcePipelineRequest(ResourcePipelineRequest resourcePipelineRequest) {
        this.resourcePipelineRequest = resourcePipelineRequest;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public Map<String, Object> fetchResourcePipelineData(ResourcePipelineRequest resourcePipelineRequest) {

        Map<String, Object> resultMap = null;

        Map<String, Object> resourcePipelineMap = new HashMap();

        try {

            SqlParameter branchIdSQLparam = new SqlParameter(Types.VARCHAR);
            SqlParameter errorCdSQLparam = new SqlParameter(Types.VARCHAR);

            List paramList = new ArrayList();
            paramList.add(branchIdSQLparam);
            paramList.add(errorCdSQLparam);
            ResourcePipelineRepositoryImpl lResourcePipelineRepositoryImpl = new ResourcePipelineRepositoryImpl();
            lResourcePipelineRepositoryImpl.resourcePipelineRequest = resourcePipelineRequest;
            lResourcePipelineRepositoryImpl.procedureCall = "{call " + WorkOrderConstants.PROC_FETCH_PIPELINE_LIST + "(?, ?)}";
            resultMap = jdbcTemplate.call((CallableStatementCreator) lResourcePipelineRepositoryImpl, paramList);

            Iterator<Entry<String, Object>> it = resultMap.entrySet().iterator();

            while (it.hasNext()) {
                Map.Entry<String, Object> entry = it.next();
                String key =  entry.getKey();

                logger.info("key>>"+key+"\n value>>>"+entry.getValue());
                if (("#result-set-1").equalsIgnoreCase(key)) {
                    resourcePipelineMap.put("PipelineDetails", entry.getValue());
                } else if (("Error_Cd").equalsIgnoreCase(key)) {
                    resourcePipelineMap.put("Error_Cd", entry.getValue());
                }
            }

        } catch (Exception e) {
            logger.error("An error occurred while fetching resource pipeline details: " + e);
        }

        return resourcePipelineMap;
    }

    @Override
    public CallableStatement createCallableStatement(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall(procedureCall);
        callableStatement.setString(1, resourcePipelineRequest.getBranchId());
        callableStatement.setString(2, "NULL");
        connection.setAutoCommit(true);
        return callableStatement;
    }
}
