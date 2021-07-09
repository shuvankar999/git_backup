package com.tip.inspection.repository.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import com.tip.inspection.repository.CiaOracleMethodesRepository;

@Repository
public class CiaOracleMethoesRepositoryImpl implements CiaOracleMethodesRepository{

	@Autowired
	@Qualifier("jdbcCpAPIOracle")
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public String getHashPWD(String ssoId, String pwd) {
		// TODO Auto-generated method stub
		CallableStatement statement = null;
		Connection connection = null;
		connection = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
		  String strSPCall = "{call  GETHASHPWD(?, ?,?)}";
		  String tokenRs="";
			try {
				statement = connection.prepareCall(strSPCall);
				statement.setString(1, ssoId);
				statement.setString(2, pwd);
			//
				statement.registerOutParameter(3,Types.VARCHAR);
				statement.executeQuery();
				
				tokenRs=statement.getString(3);
				

						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return tokenRs;
	}
}
