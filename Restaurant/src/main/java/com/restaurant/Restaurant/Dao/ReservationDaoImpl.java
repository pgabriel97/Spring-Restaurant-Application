package com.restaurant.Restaurant.Dao;

import com.restaurant.Restaurant.Interface.ReservationDao;
import com.restaurant.Restaurant.Model.Reservation;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.List;

@Repository
public class ReservationDaoImpl implements ReservationDao {

    public ReservationDaoImpl(NamedParameterJdbcTemplate template ){
        this.template = template;
    }
    NamedParameterJdbcTemplate template;
    @Override
    public List<Reservation> findAll() {
        return template.query("select * from reservation", new ReservationRowMapper());
    }
    @Override
    public void insertReservation(Reservation reservation, String rest_id) {
        final String sql = "insert into reservation(id_restaurant, id_user , start_date, start_time, guest_number) " +
                "values(:id_restaurant, :id_user, :start_date, :start_time, :guest_number)";
        KeyHolder holder = new GeneratedKeyHolder();

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id_restaurant", rest_id)
                .addValue("id_user", ( reservation.getId_user()))
                .addValue("start_date", reservation.getStart_date())
                .addValue("start_time", reservation.getStart_time())
                .addValue("guest_number", reservation.getGuest_number());
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
