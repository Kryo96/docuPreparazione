package org.example;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@ApplicationScoped
public class ResourcesProducer {

    @Produces
    public DataSource produceDataSource() {
        try {
            InitialContext ctx = new InitialContext();
            return (DataSource) ctx.lookup("java:jboss/datasources/MyOrderDB");
        } catch (NamingException e) {
            throw new RuntimeException("Error lookup DataSource", e);
        }
    }
}
