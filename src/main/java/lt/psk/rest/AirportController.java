package lt.psk.rest;

import lombok.Getter;
import lombok.Setter;
import lt.psk.entities.Airport;
import lt.psk.persistence.AirportsDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/airport")
public class AirportController {

    @Inject
    @Getter @Setter
    private AirportsDAO airportsDAO;

    @Path("/{airportId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("airportId")final int airportId) {
        Airport airport = airportsDAO.findOne(airportId);
        if (airport == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        AirportDTO airportDTO = new AirportDTO();
        airportDTO.setName(airport.getName());
        airportDTO.setAirportAddress(airport.getAirportAddress());

        return Response.ok(airportDTO).build();
    }

    @Path("/{airportId}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(
            @PathParam("airportId") final int airportId,
            AirportDTO airportData) {
        try {
            Airport airport = airportsDAO.findOne(airportId);
            if (airport == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            if (airportData.getName() != null) airport.setName(airportData.getName());
            if (airportData.getAirportAddress() != null) airport.setAirportAddress(airportData.getAirportAddress());

            airportsDAO.update(airport);
            return Response.ok().build();

        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(AirportDTO airportData) {
        try {
            Airport airport = new Airport();
            airport.setName(airportData.getName());
            airport.setAirportAddress(airportData.getAirportAddress());

            airportsDAO.persist(airport);
            return Response.ok().build();

        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}
