import models.Player;
import models.Team;
import models.Game;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * This stateless session bean serves as a RESTful resource handler for the CPDB.
 * It uses a container-managed entity manager.
 *
 * @author kvlinden
 * @version Spring, 2017
 */
@Stateless
@Path("bbdb")
public class BBDBResource {

    /**
     * JPA creates and maintains a managed entity manager with an integrated JTA that we can inject here.
     *     E.g., http://wiki.eclipse.org/EclipseLink/Examples/REST/GettingStarted
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * GET a default message.
     *
     * @return a static hello-world message
     */


    @GET
    @Path("player/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Player getPlayer(@PathParam("id") String id) {
        return em.find(Player.class, id);
    }

    @GET
    @Path("players")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Player> getPlayers() {
        return em.createQuery(em.getCriteriaBuilder().createQuery(Player.class)).getResultList();
    }

    @GET
    @Path("team/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Team getTeam(@PathParam("id") String id) {
        return em.find(Team.class, id);
    }

    @GET
    @Path("teams")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Team> getTeams() {
        return em.createQuery(em.getCriteriaBuilder().createQuery(Team.class)).getResultList();
    }

    @GET
    @Path("game/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Game getGame(@PathParam("id") String id) {
        return em.find(Game.class, id);
    }

    @GET
    @Path("games")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Game> getGames() {
        return em.createQuery(em.getCriteriaBuilder().createQuery(Game.class)).getResultList();
    }


    @PUT
    @Path("/player/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putPerson(Player putPlayer, @PathParam("id") String id) {
        Player player = em.find(Player.class, id);
        if(player == null || id != putPlayer.getId())
        {
            return Response.serverError().entity("Invalid id").build();
        }
        else {
            putPlayer.setTeam(em.find(Team.class, putPlayer.getTeam().getId()));
            player = em.merge(putPlayer);
            return Response.ok(em.find(Player.class, id), MediaType.APPLICATION_JSON).build();
        }
    }


    @POST
    @Path("/people")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Player postPerson(Player newPerson) {
        Player person = new Player();
        newPerson.setId(person.getId());
        newPerson.setTeam(em.find(Team.class, newPerson.getTeam().getId()));
        em.persist(newPerson);
        return newPerson;
    }

    @DELETE
    @Path("/player/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deletePerson(@PathParam("id") String id)
    {
        Player player = em.find(Player.class, id);
        if(player != null)
        {
            em.remove(player);
        }
    }
}

