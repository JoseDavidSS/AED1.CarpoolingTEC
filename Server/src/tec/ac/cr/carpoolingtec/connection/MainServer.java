package tec.ac.cr.carpoolingtec.connection;


import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;

@ApplicationPath("hola")
@Path("hola")
public class MainServer extends Application {
    @GET
    public String main() {
        return "Server Configurado";
    }

}
