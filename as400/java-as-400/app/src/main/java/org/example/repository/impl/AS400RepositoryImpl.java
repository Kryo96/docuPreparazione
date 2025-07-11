package org.example.repository.impl;

import org.example.entity.Customer;
import org.example.repository.AS400Repository;
import org.example.exception.MiddlewareException;
import jakarta.enterprise.context.ApplicationScoped;
import javax.sql.DataSource;
import jakarta.transaction.Transactional;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class AS400RepositoryImpl implements AS400Repository {

    private static final Logger logger = LoggerFactory.getLogger(AS400RepositoryImpl.class);

    // Resource is present in datasource
    @Resource(lookup = "java:jboss/datasources/productionJT")
    private DataSource dataSource;

    @Override
    public Customer findCustomerById(String customerId) throws MiddlewareException {
        return null;
    }

    @Override
    public void updateCustomer(Customer customer) throws MiddlewareException {
        logger.info("NOT DEFINED");
    }

}
