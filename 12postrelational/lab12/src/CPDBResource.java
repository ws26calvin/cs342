import models.Household;
import models.Person;

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
@Path("cpdb")
public class CPDBResource {

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
    @Path("hello")
    @Produces("text/plain")
    public String getClichedMessage() {
        return "Hello, JPA!";
    }

    /**
     * GET an individual person record.
     *
     * @param id the ID of the person to retrieve
     * @return a person record
     */
    @GET
    @Path("person/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Person getPerson(@PathParam("id") long id) {
        return em.find(Person.class, id);
    }

    /**
     * GET all people using the criteria query API.
     * This could be refactored to use a JPQL query, but this entitymanager-based approach
     * is consistent with the other handlers.
     *
     * @return a list of all person records
     */
    @GET
    @Path("people")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getPeople() {
        return em.createQuery(em.getCriteriaBuilder().createQuery(Person.class)).getResultList();
    }


    @PUT
    @Path("/person/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putPerson(Person putPerson, @PathParam("id") Integer id) {
        Person person = em.find(Person.class, id);
        if(person == null || id != putPerson.getId())
        {
            return Response.serverError().entity("Invalid id").build();
        }
        else {
            putPerson.setHousehold(em.find(Household.class, putPerson.getHousehold().getId()));
            person = em.merge(putPerson);
            return Response.ok(em.find(Person.class, id), MediaType.APPLICATION_JSON).build();
        }
    }


    @POST
    @Path("/people")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Person postPerson(Person newPerson) {
        Person person = new Person();
        newPerson.setId(person.getId());
        newPerson.setHousehold(em.find(Household.class, newPerson.getHousehold().getId()));
        em.persist(newPerson);
        return newPerson;
    }

    @DELETE
    @Path("/person/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deletePerson(@PathParam("id") Integer id)
    {
        Person person = em.find(Person.class, id);
        if(person != null)
        {
            em.remove(person);
        }
    }

}

