package ua.nure.kopaniev.repository;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.nure.kopaniev.bean.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

@Slf4j
@Repository
public class UserDAO implements UserRepository {

    @Autowired
    NamedParameterJdbcTemplate template;

    @Override
    public void addUser(User user) {
        log.info("::addUser({})", user.toString());

        val sql = "INSERT INTO 'user' (email, password, address, birth_date, full_name, phone_number)" +
                " VALUES (:email, :password, :address, :birth_date, :full_name, :phone_number)";

        val params = new HashMap<String, Object>();
        params.put("email", user.getEmail());
        params.put("password", user.getPassword());
        params.put("address", user.getAddress());
        params.put("birth_date", user.getBirthdate());
        params.put("full_name", user.getFullname());
        params.put("phone_number", user.getPhone());


        template.update(sql, params);
    }

    @Override
    public User getUser(String email) {
        log.info("::getUser({})", email);

        val sql = "SELECT * FROM user WHERE email = :email";

        val params = new HashMap<String, Object>();
        params.put("email", email);
        return template.queryForObject(sql, params, this::toUser);
    }

    private User toUser(@NonNull ResultSet rs, int rowNum) throws SQLException {
        val user = new User();
        user.setId(rs.getInt("id"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setAddress(rs.getString("address"));
        user.setBirthdate(rs.getDate("birth_date"));
        user.setFullname(rs.getString("full_name"));
        user.setPhone(rs.getString("phone_number"));
        return user;
    }
}
