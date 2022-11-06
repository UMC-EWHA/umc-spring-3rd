package com.umcewha.board;

import com.umcewha.board.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetUserRes> userRes(){
        return this.jdbcTemplate.query("Select * from User",
                (rs, rowNum) -> new GetUserRes(
                        rs.getInt("userIdx"),
                        rs.getString("username"),
                        rs.getString("password")
                )
        );
    }

    public int addUser(PostUserReq postuserReq){
        String createUserQuery = "insert into User (username, password) VALUES (?,?)";
        Object[] createUserParams = new Object[]{
                postuserReq.getUsername(), postuserReq.getPassword()
        };
        this.jdbcTemplate.update(createUserQuery, createUserParams);

        return this.jdbcTemplate.queryForObject("select last_insert_id()",int.class);
    }
}
