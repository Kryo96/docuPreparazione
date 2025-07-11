package org.example.rest;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.service.SyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Path("/sync")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SyncEndpoint {
    private static final Logger logger = LoggerFactory.getLogger(SyncEndpoint.class);

    @Inject
    private SyncService syncService;

    @POST
    @Path("/customer/{id}")
    public Response syncCustomer(@PathParam("id") String salesforceId) {
        logger.info("SONO QUI");
        return Response.ok()
                .entity("{\"status\":\"success\",\"message\":\"Operativo\"}")
                .build();
    }

    @GET
    @Path("/customer/{id}")
    public Response getCustomer(@PathParam("id") String salesforceId) {
        logger.info("SONO QUI");
        return Response.ok()
                .entity("{\"status\":\"success\",\"message\":\"Operativo\"}")
                .build();
    }
}
