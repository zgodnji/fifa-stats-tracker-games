package com.zgodnji.fifastatstracker;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RequestScoped
@Path("games")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GameResource {

    @Inject
    private GameProperties properties;

    @GET
    @Path("test")
    public Response testResponse() {
        String response =
                "{" +
                        "\"stringProperty\": \"%s\"," +
                        "\"booleanProperty\": %b," +
                        "\"integerProperty\": %d" +
                        "}";

        response = String.format(
                response,
                properties.getStringProperty(),
                properties.getBooleanProperty(),
                properties.getIntegerProperty());

        return Response.ok(response).build();
    }

    @GET
    public Response getAllGames() {
        List<Game> games = Database.getGames();
        return Response.ok(games).build();
    }

    @GET
    @Path("{gameId}")
    public Response getGame(@PathParam("gameId") String gameId) {
        Game game = Database.getGame(gameId);
        return game != null
                ? Response.ok(game).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response addNewGame(Game game) {
        Database.addGame(game);
        return Response.noContent().build();
    }

    @DELETE
    @Path("{gameId}")
    public Response deleteGame(@PathParam("gameId") String gameId) {
        Database.deleteGame(gameId);
        return Response.noContent().build();

    }
}
