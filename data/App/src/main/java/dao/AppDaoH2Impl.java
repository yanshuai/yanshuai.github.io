package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.App;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@Component
public class AppDaoH2Impl implements AppDao {

    private JdbcTemplate jdbcTemplate;
    
    public void init() throws Exception {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(org.h2.Driver.class);
        dataSource.setUsername("sa");
        dataSource.setUrl(String.format("jdbc:h2:%s/AppStore",
                System.getProperty("user.dir")));
        dataSource.setPassword("");
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute("DROP TABLE Apps IF EXiSTS");
        jdbcTemplate.execute("CREATE TABLE Apps(" +
                "id serial, name varchar(255), description text)");
    }

    public App addApp(final App app) throws Exception {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(
                                "INSERT INTO Apps(name, description) values(?, ?)",
                                new String[] {"id"});
                        ps.setString(1, app.getName());
                        ps.setString(2, app.getDescription());
                        return ps;
                    }
                }, keyHolder);
        return new App(keyHolder.getKey().longValue(), app.getName(),
                app.getDescription());
    }

    public void delApp(final int id) throws Exception {
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(
                                "DELETE FROM Apps WHERE id = ?");
                        ps.setInt(1, id);
                        return ps;
                    }
                });
    }

    public List<App> getApps() throws Exception {
        return jdbcTemplate.query("SELECT * FROM Apps", new RowMapper<App>() {
            @Override
            public App mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new App(rs.getLong("id"), rs.getString("name"),
                        rs.getString("description"));
            }
        });
    }
}
