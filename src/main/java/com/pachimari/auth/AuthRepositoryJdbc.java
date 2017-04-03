package com.pachimari.auth;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by iPlowPlow on 10/03/2017.
 */
@Repository
public class AuthRepositoryJdbc extends JdbcDaoSupport {

    public AuthRepositoryJdbc(DataSource dataSource){
        setDataSource(dataSource);
    }

    public AuthEntity tryAuth(String login, String pwd){
        JdbcTemplate jdbcTemplate = getJdbcTemplate();
        String sql = "Select * From auth  Where login = ? AND pwd = ?";
        return jdbcTemplate.queryForObject(sql, this::map, new String[]{login, pwd});
    }


    private AuthEntity map(ResultSet rs, int rowNum) throws SQLException {
        return AuthEntity.builder()
                .id(rs.getLong("id"))
                .login(rs.getString("login"))
                .pwd(rs.getString("pwd"))
                .build();
    }
}
