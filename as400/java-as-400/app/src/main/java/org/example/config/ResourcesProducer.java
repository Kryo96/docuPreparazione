package org.example.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@ApplicationScoped
public class ResourcesProducer {

    private static final Logger logger = LoggerFactory.getLogger(ResourcesProducer.class);
    
    @Produces
    public DataSource produceDataSource() {
        logger.info("Producing DataSource via JNDI lookup");
        
        try {
            InitialContext ctx = new InitialContext();
            logger.debug("InitialContext created, performing JNDI lookup for: java:jboss/datasources/MyOrderDB");
            
            DataSource ds = (DataSource) ctx.lookup("java:jboss/datasources/MyOrderDB");
            logger.info("DataSource lookup successful: {}", ds.getClass().getName());
            
            // Test connection
            try {
                ds.getConnection().close();
                logger.info("DataSource connection test successful");
            } catch (Exception e) {
                logger.warn("DataSource connection test failed, but DataSource was found", e);
            }
            
            return ds;
        } catch (NamingException e) {
            logger.error("JNDI lookup failed for DataSource", e);
            throw new RuntimeException("Error lookup DataSource", e);
        }
    }
}
