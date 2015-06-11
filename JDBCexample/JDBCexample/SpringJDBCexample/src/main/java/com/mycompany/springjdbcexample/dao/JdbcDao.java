/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.springjdbcexample.dao;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.*;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import java.sql.SQLException;
import java.sql.*;
import java.util.*;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

/**
 *
 * @author jupiter
 */
public class JdbcDao {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcCall getPersonNames;//return type is only Map<String, Object>
    @Inject
    public JdbcDao(@Named("data") DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        getPersonNames = new SimpleJdbcCall(jdbcTemplate).withProcedureName("GetPersonNames");
    }

    public void run() {
        //insert
        String sqlInsert = "INSERT INTO contact VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sqlInsert, new Object[]{"Tom", "tomea@mail.com", "USA", "12345"});
        jdbcTemplate.update(sqlInsert, new Object[]{"Jery", "tadfasa@mail.com", "WA", "2341253"});
        //update
        String sqlUpdate = "UPDATE contact set email=? where name=?";
        jdbcTemplate.update(sqlUpdate, "tomee@mail.com", "Tom");

        //返回一个类的写法
        String sqlSelect = "SELECT * FROM contact";
        List<Contact> listContact = jdbcTemplate.query(sqlSelect, new RowMapper<Contact>() {
            @Override
            public Contact mapRow(ResultSet result, int rowNum) throws SQLException {
                Contact contact = new Contact();
                contact.setName(result.getString("name"));
                contact.setEmail(result.getString("email"));
                contact.setAddress(result.getString("address"));
                contact.setPhone(result.getString("telephone"));
                return contact;
            }
        });

        for (Contact aContact : listContact) {
            System.out.println(aContact);
        }
        
        //处理其中几个东西
        Map<Integer, String> names = jdbcTemplate.query("SELECT * FROM contact", new ResultSetExtractor<Map<Integer, String>>(){
            @Override
            public Map<Integer, String> extractData(ResultSet rs) throws SQLException {
                  Map<Integer, String> ret = new HashMap();
                  while(rs.next()) {
                      ret.put(rs.getInt("contact_id"), rs.getString("name"));
                  } 
                  return ret;
            }
        });
        
        for (Map.Entry<Integer, String> entry: names.entrySet()) {
            System.out.println(entry.toString());
        }
        
        //delete
        String sqlDelete = "DELETE FROM contact where name=?";
        jdbcTemplate.update(sqlDelete, "Tom");
        
    }
    
    public void run2() {
        Map<String, Object> args = new HashMap<>();
        args.put("personID", 100);
        args.put("personname", null);
        args.put("Address", null);
        Map<String, Object> results = getPersonNames.execute(args);//every funny return the last one type is only Map<String, Object> 

        
        for(Map.Entry<String, Object> entry: results.entrySet()) {
            System.out.println(entry.toString());
        }
        
        
        
    }
    
}
