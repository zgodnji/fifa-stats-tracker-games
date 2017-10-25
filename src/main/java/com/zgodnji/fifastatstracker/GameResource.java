package com.zgodnji.fifastatstracker;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("games")
public class GameResource {

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
