package dev.asjordi.configs;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@ApplicationScoped
public class ProducerResources {

    @Resource(name = "jdbc/mysqlDB")
    private DataSource ds;

    @Produces
    @RequestScoped
    @MysqlConnection
    private Connection beanConnection() throws NamingException, SQLException {
        return ds.getConnection();
    }

    public void closeConnection(@Disposes @MysqlConnection Connection conn) throws SQLException {
        conn.close();
    }

}
