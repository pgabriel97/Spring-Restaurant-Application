package com.restaurant.Restaurant.Dao;

import com.restaurant.Restaurant.Interface.ReservationDao;
import com.restaurant.Restaurant.Model.Reservation;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Repository
public class ReservationDaoImpl implements ReservationDao {

    public ReservationDaoImpl(NamedParameterJdbcTemplate template ){
        this.template = template;
    }
    NamedParameterJdbcTemplate template;
    @Override
    public List<Reservation> findAll(String userId) {
        final String sql ="select * from reservation where id_user = :id";
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("id", userId);
        return template.query(sql,parameters,new ReservationRowMapper() );
    }
    @Override
    public void insertReservation(Reservation reservation, String rest_id, String user_id) throws ParseException {
        final String sql = "insert into reservation(id_restaurant, id_user, start_date, start_time, guest_number) " +
                "values(:id_restaurant, :id_user, :start_date, :start_time, :guest_number)";
        KeyHolder holder = new GeneratedKeyHolder();
        //LocalDateTime.parse(reservation.getStart_time() ));//.atZone(ZoneId.systemDefault()).toInstant();
       // Time t = d.getTime();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id_restaurant", Integer.parseInt(rest_id))
                .addValue("id_user", user_id)
                .addValue("start_date", Date.valueOf(reservation.getStart_date()))
                .addValue("start_time", new java.sql.Time(new SimpleDateFormat("HH:mm").parse(reservation.getStart_time()).getTime()))
                //.addValue("start_time", t)
                .addValue("guest_number", Integer.parseInt(reservation.getGuest_number()));
        template.update(sql,param, holder);
    }

    @Override
    public void updateReservation(Reservation reservation) {

    }

    @Override
    public void executeReservation(Reservation reservation) {

    }

    @Override
    public void deleteReservation(Reservation reservation) {

    }
    /*
    @Override
    public void updateReservation(Reservation reservation) {
        final String sql = "update reservation set id_restaurant=:id_restaurant, id_user=:{id_user , " +
                "start_date=:start_date guest_number=:guest_number where ;
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("username", reservation.getUsername())
                .addValue("password", reservation.getPassword())
                .addValue("enabled", reservation.getEnabled());
        template.update(sql,param, holder);
    }
    @Override
    public void executeUpdateReservation(Reservation user) {
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
    public void deleteReservation(Reservation user) {
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
    */
}
