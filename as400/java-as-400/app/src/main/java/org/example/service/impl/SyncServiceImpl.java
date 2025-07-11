package org.example.service.impl;

import org.example.service.SyncService;
import org.example.repository.AS400Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.logging.Logger;

@ApplicationScoped
public class SyncServiceImpl implements SyncService {

    private static final Logger LOGGER = Logger.getLogger(SyncServiceImpl.class.getName());

    @Inject
    private AS400Repository as400Repository;

}