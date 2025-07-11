package org.example.service.impl;

import org.example.rest.SyncEndpoint;
import org.example.service.SyncService;
import org.example.repository.AS400Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class SyncServiceImpl implements SyncService {

    private static final Logger logger = LoggerFactory.getLogger(SyncEndpoint.class);

    @Inject
    private AS400Repository as400Repository;

}