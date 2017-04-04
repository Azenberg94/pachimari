package com.pachimari.product;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by andrem on 23/03/2017.
 */
@Repository
public class ProductRepositoryJdbc extends JdbcDaoSupport{

    public ProductRepositoryJdbc(DataSource dataSource){
        setDataSource(dataSource);
    }

    public List<ProductEntity> findAll(){
        JdbcTemplate jdbcTemplate = getJdbcTemplate();
        return jdbcTemplate.query("select * from product", this::map);
    }

    private ProductEntity map(ResultSet rs, int rowNum) throws SQLException {
        return ProductEntity.builder()
                .id(rs.getString("id"))
                .name(rs.getString("name"))
                .brand(rs.getString("brand"))
                .typeId(rs.getInt("typeId"))
                .price(rs.getDouble("price"))
                .imageURL(rs.getString("imageURL"))
                .build();
    }
}
