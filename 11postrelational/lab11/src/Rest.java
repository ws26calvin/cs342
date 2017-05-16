
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.awt.*;
import java.net.URI;


@Path("/hello/api")
public class Rest {

    @GET
    @Produces("text/plain")
    public String getGet() {
        return "Getting... ";
    }

    @PUT
    @Path("/{int}")
    @Produces("text/plain")
    public String getPut(@PathParam("int") Integer inte) {
        return "Putting: " + inte;
    }

    @POST
    @Path("{msg}")
    @Produces("text/plain")
    public String getPost(@PathParam("msg") String msg) {
        return "Posting: " +  msg;
    }

    @POST
    @Path("{del}")
    @Produces("text/plain")
    public String getDel(@PathParam("del") Integer del) {
        if (del > -1 && del < 10)
            return "Deleting: " + del;
        else
            return "Not a single digit integer";
    }
}
