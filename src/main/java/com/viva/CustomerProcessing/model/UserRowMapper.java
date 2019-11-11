package com.viva.CustomerProcessing.model;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.viva.CustomerProcessing.model.FeeInfo;

public class UserRowMapper implements RowMapper<FeeInfo> {

	
	public FeeInfo mapRow(ResultSet rs, int rowNum) throws SQLException {

		FeeInfo user = new FeeInfo();
         
	//	user.setPhoneNumber(rs.getString("phoneNumber"));
		user.setFeeAmount(rs.getDouble("fee_amount"));
		user.setCreatedOn(rs.getDate("created_on"));

		return user;
	}

}