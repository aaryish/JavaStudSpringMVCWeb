package com.javastud.springmvcweb.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.javastud.springmvcweb.model.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private DataSource dataSource;

	@Override
	public boolean validateUser(User user) {
		try{
			
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			
			String query = "SELECT username FROM USER WHERE username = '" +   user.getUsername() +
						   "' AND PASSWORD = '" +   user.getPassword() + "'";      
			System.out.println(query);
			
			String dbUserName = jdbcTemplate.queryForObject(query, String.class);
			if(dbUserName != null && dbUserName.equals(user.getUsername())){
				return true;
			}else {
				return false;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}


}
