package org.example.rest;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.exception.TransactionException;
import org.example.service.SyncService;

import java.util.logging.Logger;


@Path("/sync")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SyncEndpoint {
    private static final Logger LOGGER = Logger.getLogger(SyncEndpoint.class.getName());

    @Inject
    private SyncService syncService;

    @POST
    @Path("/customer/{id}")
    public Response syncCustomer(@PathParam("id") String salesforceId) {
        return Response.ok()
                .entity("{\"status\":\"success\",\"message\":\"Operativo\"}")
                .build();
    }
}
