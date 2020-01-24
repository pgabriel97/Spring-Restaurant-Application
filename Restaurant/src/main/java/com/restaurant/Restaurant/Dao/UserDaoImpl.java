package com.restaurant.Restaurant.Dao;

import com.restaurant.Restaurant.Interface.UserDao;
import com.restaurant.Restaurant.Model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {

    public UserDaoImpl(NamedParameterJdbcTemplate template ){
        this.template = template;
    }
    NamedParameterJdbcTemplate template;
    @Override
    public List<User> findAll() {
        return template.query("select * from users", new UserRowMapper());
    }
    @Override
    public void insertUser(User user) {
        final String sql = "insert into users(username, password , enabled) " +
                           "values(:username,:password,:enabled)";
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("username", user.getUsername())
                .addValue("password", ("{noom}" + user.getPassword()))
                .addValue("enabled", true);
        template.update(sql,param, holder);
    }
    @Override
    public void updateUser(User user) {
        final String sql = "update users set username=:username, password=:{noom}password , " +
                            "enabled=:enabled where username=:username ";
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("username", user.getUsername())
                .addValue("password", user.getPassword())
                .addValue("enabled", user.getEnabled());
        template.update(sql,param, holder);
    }
    @Override
    public void executeUpdateUser(User user) {
        final String sql = "update users set username=:username, password=:{noom}password , " +
                "enabled=:enabled where username=:username ";
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("username", user.getUsername());
        map.put("password", user.getPassword());
        map.put("enabled", user.getEnabled());

        template.execute(sql,map,new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                return ps.executeUpdate();
            }
        });
    }
    @Override
    public void deleteUser(User user) {
        final String sql = "delete from users where username=:username";
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("username", user.getUsername());
        template.execute(sql,map,new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                return ps.executeUpdate();
            }
        });
    }
}
