package dev.asjordi.interceptors;

import dev.asjordi.configs.MysqlConnection;
import dev.asjordi.exceptions.ServiceJDBCException;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import java.sql.Connection;
import java.util.logging.Logger;

@TransactionalJdbc
@Interceptor
public class TransactionalInterceptor {

    @Inject
    @MysqlConnection
    private Connection conn;

    @AroundInvoke
    public Object manageTransaction(InvocationContext ctx) throws Exception {
        if (conn.getAutoCommit()) conn.setAutoCommit(false);
        try {
            Object result = ctx.proceed();
            conn.commit();
            return result;
        } catch (ServiceJDBCException e) {
            conn.rollback();
            throw e;
        }
    }

}
