package ua.nure.kopaniev.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.nure.kopaniev.bean.EmailText;
import ua.nure.kopaniev.bean.EmailType;

@Repository
public class SendMailDAO {

    @Autowired
    private NamedParameterJdbcTemplate template;

    public static final String GET_TEMPLATE_BY_TYPE = "SELECT * FROM mail_template WHERE email_type = :type";

    public EmailText getTemplateByType(String resourceName) {
        return template.queryForObject(GET_TEMPLATE_BY_TYPE, new MapSqlParameterSource("type", resourceName),
                (rs, i) -> new EmailText(
                rs.getString("email_type"),
                rs.getString("email_subject"),
                rs.getString("email_text")
        ));
    }
}
