package com.spring.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.person.Person;


public class PersonRowMapper implements RowMapper<Person>{
	
		public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			Person prs = new Person();
			prs.setId(rs.getInt("id"));
			prs.setFirstName(rs.getString("first_name"));
			prs.setLastName((rs.getString("last_name")));
			prs.setAddress(rs.getString("address"));
			return prs;
		}

	}



